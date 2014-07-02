package ua.kiev.naiv.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.naiv.drinkit.cocktail.model.Recipe;
import ua.kiev.naiv.drinkit.cocktail.pojos.TransformTests;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;
import ua.kiev.naiv.drinkit.springconfig.AppConfig;

/**
 * @author pkolmykov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional()
public class CreateRecipeRestIT {


    @Autowired
    CocktailService cocktailService;

    @Test
    @Rollback(value = false)
    public void createRecipe() {
        Recipe recipe = cocktailService.create(new TransformTests().creteMockRecipeInput());
        System.out.println(recipe);
    }

//    protected Recipe createMockRecipe() {
//        Recipe recipe = new Recipe();
//        CocktailType cocktailType = new CocktailType();
//        cocktailType.setId(1);
//        recipe.setCocktailType(cocktailType);
//        recipe.setDescription("desc");
//        recipe.setName("Test1");
//        Option option = new Option();
//        option.setId(1);
//        recipe.setOptions(new HashSet<>(Arrays.asList(option)));
//        IngredientWithQuantity ingredientWithQuantity = new IngredientWithQuantity();
//        CocktailIngredientId cocktailIngredientId = new CocktailIngredientId();
//        Ingredient ingredient = cocktailService.getIngredients().get(0);
//        ingredient.setCocktailIngredients(new HashSet<>(Arrays.asList(ingredientWithQuantity)));
//        cocktailIngredientId.setIngredient(ingredient);
//        cocktailIngredientId.setRecipe(recipe);
//        ingredientWithQuantity.setCocktailIngredientId(cocktailIngredientId);
//        ingredientWithQuantity.setQuantity(50);
//        recipe.setIngredientsWithQuantities(new HashSet<>(Arrays.asList(ingredientWithQuantity)));
//        return recipe;
//    }


}
