package ua.kiev.naiv.drinkit.cocktail.model;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;
import ua.kiev.naiv.drinkit.springconfig.WebConfig;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 21:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
public class CocktailTest {

    @Autowired
    CocktailService cocktailService;

    @Test
    public void wannaCubaLibre(){
        Cocktail cubaLibre = cocktailService.findById(1);
        Assert.assertNotNull(cubaLibre);
        Assert.assertEquals("Куба Либре", cubaLibre.getName());
    }
}
