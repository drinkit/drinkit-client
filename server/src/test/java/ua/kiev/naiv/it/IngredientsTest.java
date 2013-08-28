package ua.kiev.naiv.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kiev.naiv.drinkit.cocktail.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;
import ua.kiev.naiv.drinkit.springconfig.AppConfig;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 21.07.13
 * Time: 20:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class IngredientsTest {

    @Autowired
    CocktailService cocktailService;

    @Test
    public void getIngredientsList() throws IOException {
        List<Ingredient> ingredients = cocktailService.getIngredients();
        Assert.assertNotNull(ingredients);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(TestHelper.createIfNotExistJsonFileResponse("allIngredients"), ingredients);
    }
}
