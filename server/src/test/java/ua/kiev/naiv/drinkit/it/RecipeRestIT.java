package ua.kiev.naiv.drinkit.it;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.service.IngredientService;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;
import ua.kiev.naiv.drinkit.springconfig.AppConfig;

import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional()
public class RecipeRestIT {

    private Ingredient firstIngredient = new Ingredient();
    private Ingredient secondIngredient = new Ingredient();

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    RecipeService recipeService;

    @Autowired
    IngredientService ingredientService;

    @Before
    public void initTestData() {
        System.out.println("before");
        firstIngredient.setDescription("firstIngredient");
        firstIngredient.setName("First");
        firstIngredient.setVol(30);
        ingredientService.create(firstIngredient);

        secondIngredient.setDescription("secondIngredient");
        secondIngredient.setName("Second");
        secondIngredient.setVol(40);
        ingredientService.create(secondIngredient);

    }

    @Test
//    @Rollback(false)
    public void recipeTestCase() {
        Recipe created = recipeService.save(createNewRecipe());
        int id = created.getId();
        Recipe modifiedRecipe = recipeService.getRecipeById(id);
        assertEquals(created, modifiedRecipe);
        modifiedRecipe.setName("modified");
        modifiedRecipe.setId(id);
        recipeService.save(modifiedRecipe);
        assertEquals(modifiedRecipe, recipeService.getRecipeById(id));
        recipeService.delete(id);
        assertNull(recipeService.getRecipeById(id));
    }

    @Test
    public void recipeStatisticsTest() {
        int userId = -1;
        Recipe createdRecipe = recipeService.save(createNewRecipe());
        assertEquals(0, createdRecipe.getViews());
        assertNull(createdRecipe.getRating());
//        recipeStatisticsService.setRecipeRatingForUser(recipe.getId(), userId, 6);//todo
        Recipe receivedRecipe = recipeService.getRecipeByIdAndIncrementViewsCount(createdRecipe.getId(), userId);
        assertEquals(1, receivedRecipe.getViews());
        assertNull(receivedRecipe.getRating());
    }

    private Recipe createNewRecipe() {
        Recipe recipe = new Recipe();
        recipe.setCocktailTypeId(1);
        recipe.setDescription("desc");
        recipe.setName("Test2");
        recipe.setOptions(new int[]{1, 2});
        recipe.setCocktailIngredients(new Integer[][]{{firstIngredient.getId(), 50}, {secondIngredient.getId(), 60}});
        return recipe;
    }

}
