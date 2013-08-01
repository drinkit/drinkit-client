package ua.kiev.naiv.drinkit.cocktail.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kiev.naiv.drinkit.cocktail.TestHelper;
import ua.kiev.naiv.drinkit.cocktail.mixin.RecipeInfoResult;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;
import ua.kiev.naiv.drinkit.springconfig.AppConfig;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 21:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CocktailTest {

    @Autowired
    CocktailService cocktailService;

    @Test
    public void wannaCubaLibre() throws IOException {
        Recipe cubaLibre = cocktailService.getById(1);
        Assert.assertNotNull(cubaLibre);
        Assert.assertEquals("Куба Либре", cubaLibre.getName());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixInAnnotations(Recipe.class, RecipeInfoResult.class);
        objectMapper.writeValue(TestHelper.createIfNotExistJsonFileResponse("cubaLibre"), cubaLibre);
    }

    @Test
    public void cocktailTypeTest() {
        Iterable<CocktailType> cocktailTypes = cocktailService.findAllCocktailType();
        CocktailType cocktailType = cocktailService.findCocktailTypeById(1);
        Assert.assertTrue(cocktailTypes.iterator().hasNext());
        Assert.assertNotNull(cocktailType);
    }
}
