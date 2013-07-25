package ua.kiev.naiv.drinkit.cocktail.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;
import ua.kiev.naiv.drinkit.springconfig.TestConfig;
import ua.kiev.naiv.drinkit.springconfig.TestHelper;

import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 21:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class CocktailTest {

    @Autowired
    CocktailService cocktailService;

    @Autowired
    TestHelper testHelper;

    @Test
    public void wannaCubaLibre() throws IOException {
        Recipe cubaLibre = cocktailService.findById(1);
        Assert.assertNotNull(cubaLibre);
        Assert.assertEquals("Куба Либре", cubaLibre.getName());
        new ObjectMapper().writeValue(testHelper.createIfNotExistJsonFileResponse("cubaLibre"), cubaLibre);
    }

    @Test
    public void cocktailTypeTest() {
        Iterable<CocktailType> cocktailTypes = cocktailService.findAllCocktailType();
        CocktailType cocktailType = cocktailService.findCocktailTypeById(1);
        Assert.assertTrue(cocktailTypes.iterator().hasNext());
        Assert.assertNotNull(cocktailType);
    }
}
