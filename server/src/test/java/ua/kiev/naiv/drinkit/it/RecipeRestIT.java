package ua.kiev.naiv.drinkit.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;
import ua.kiev.naiv.drinkit.springconfig.AppConfig;

import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static ua.kiev.naiv.drinkit.MockObjectsGenerator.creteMockRecipe;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional()
public class RecipeRestIT {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    RecipeService recipeService;

    @Test
    @Rollback(false)
    public void recipeTestCase() {
        Recipe recipe = creteMockRecipe();
        int id = recipeService.save(recipe).getId();
        assertEquals(recipe, recipeService.getRecipeById(id));
        recipe.setName("modified");
        recipe.setId(id);
        recipeService.save(recipe);
        assertEquals(recipe, recipeService.getRecipeById(id));
        recipeService.delete(id);
        assertNull(recipeService.getRecipeById(id));
    }

    @Test
    public void recipeStatisticsTest() {
        int userId = -1;
        Recipe createdRecipe = recipeService.save(creteMockRecipe());
        assertEquals(0, createdRecipe.getViews());
        assertNull(createdRecipe.getRating());
//        recipeStatisticsService.setRecipeRatingForUser(recipe.getId(), userId, 6);//todo
        Recipe receivedRecipe = recipeService.getRecipeByIdAndIncrementViewsCount(createdRecipe.getId(), userId);
        assertEquals(1, receivedRecipe.getViews());
        assertNull(receivedRecipe.getRating());
    }

}
