package ua.kiev.naiv.drinkit.cocktail.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ua.kiev.naiv.drinkit.cocktail.common.WebContextFilter;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.service.IngredientService;
import ua.kiev.naiv.drinkit.cocktail.web.model.IngredientMixIn;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;
import ua.kiev.naiv.drinkit.springconfig.AppConfig;
import ua.kiev.naiv.drinkit.springconfig.WebConfig;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 19.10.2014
 * Time: 21:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
@WebAppConfiguration
@Transactional()
public class AbstractRestMockMvc {

    protected MockMvc mockMvc;
    protected Ingredient firstIngredient = new Ingredient();
    protected Ingredient secondIngredient = new Ingredient();
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private IngredientService ingredientService;
    protected ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        objectMapper.addMixInAnnotations(Ingredient.class, IngredientMixIn.class);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new WebContextFilter())
                .build();

        firstIngredient.setDescription("firstIngredient");
        firstIngredient.setName("First");
        firstIngredient.setVol(30);
        ingredientService.create(firstIngredient);

        secondIngredient.setDescription("secondIngredient");
        secondIngredient.setName("Second");
        secondIngredient.setVol(40);
        ingredientService.create(secondIngredient);
    }

    protected Recipe createNewRecipe() {
        Recipe recipe = new Recipe();
        recipe.setCocktailTypeId(1);
        recipe.setDescription("desc");
        recipe.setName("Recipe for integration tests");
        recipe.setOptions(new int[]{1, 2});
        recipe.setCocktailIngredients(new Integer[][]{{firstIngredient.getId(), 50}, {secondIngredient.getId(), 60}});
        return recipe;
    }

    protected Ingredient createNewIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription("new ingredient");
        ingredient.setName("new");
        ingredient.setVol(44);
        return ingredient;
    }
}
