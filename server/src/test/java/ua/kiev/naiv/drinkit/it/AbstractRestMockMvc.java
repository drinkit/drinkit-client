package ua.kiev.naiv.drinkit.it;

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

    @Before
    public void initTestData() {
        System.out.println("main before");
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
}
