package ua.kiev.naiv.drinkit.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.service.IngredientService;
import ua.kiev.naiv.drinkit.springconfig.AppConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static ua.kiev.naiv.drinkit.MockObjectsGenerator.createMockIngredient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional()
public class IngredientRestIT {


    @Autowired
    IngredientService ingredientService;

    @Test
    public void recipeTestCase() {
        Ingredient ingredient = createMockIngredient();
        int id = ingredientService.create(ingredient);
        assertEquals(ingredient, ingredientService.findIngredientById(id));
        ingredient.setName("modified");
        ingredient.setId(id);
        ingredientService.update(ingredient);
        assertEquals(ingredient, ingredientService.findIngredientById(id));
        ingredientService.delete(id);
        assertNull(ingredientService.findIngredientById(id));
    }

}
