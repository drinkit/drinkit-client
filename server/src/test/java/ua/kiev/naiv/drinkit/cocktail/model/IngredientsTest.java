package ua.kiev.naiv.drinkit.cocktail.model;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kiev.naiv.drinkit.cocktail.repository.IngredientRepository;
import ua.kiev.naiv.drinkit.springconfig.TestConfig;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 21.07.13
 * Time: 20:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class IngredientsTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    public void getIngredientsList() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        Assert.assertNotNull(ingredients);
    }
}
