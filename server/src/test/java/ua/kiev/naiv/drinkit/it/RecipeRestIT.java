package ua.kiev.naiv.drinkit.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;
import ua.kiev.naiv.drinkit.springconfig.AppConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static ua.kiev.naiv.drinkit.MockObjectsGenerator.creteMockRecipe;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional()
public class RecipeRestIT {


    @Autowired
    RecipeService recipeService;

    @Test
    public void recipeTestCase() {
        Recipe recipe = creteMockRecipe();
        int id = recipeService.save(recipe);
        assertEquals(recipe, recipeService.getRecipeById(id));
        recipe.setName("modified");
        recipe.setId(id);
        recipeService.save(recipe);
        assertEquals(recipe, recipeService.getRecipeById(id));
        recipeService.delete(id);
        assertNull(recipeService.getRecipeById(id));
    }

}
