package guru.drinkit.cocktail;

import guru.drinkit.cocktail.service.IngredientService;
import guru.drinkit.cocktail.web.dto.IngredientDto;
import guru.drinkit.cocktail.web.dto.RecipeDto;
import guru.drinkit.springconfig.AppConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 09.11.2014
 * Time: 16:45
 */
@ContextConfiguration(classes = {AppConfig.class})
@Transactional()
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractBaseTest {
    protected IngredientDto firstIngredient;
    protected IngredientDto secondIngredient;
    @Autowired
    private IngredientService ingredientService;

    @Before
    public void initTestData() {
        firstIngredient = new IngredientDto();
        firstIngredient.setDescription("firstIngredient");
        firstIngredient.setName("First");
        firstIngredient.setVol(30);
        firstIngredient = ingredientService.save(firstIngredient);

        secondIngredient = new IngredientDto();
        secondIngredient.setDescription("secondIngredient");
        secondIngredient.setName("Second");
        secondIngredient.setVol(40);
        secondIngredient = ingredientService.save(secondIngredient);
    }

    protected RecipeDto createNewRecipeDto() {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setCocktailTypeId(1);
        recipeDto.setDescription("desc");
        recipeDto.setName("Recipe for integration tests");
        recipeDto.setOptions(new int[]{1, 2});
        recipeDto.setCocktailIngredients(new Integer[][]{{firstIngredient.getId(), 50}, {secondIngredient.getId(), 60}});
        return recipeDto;
    }

}
