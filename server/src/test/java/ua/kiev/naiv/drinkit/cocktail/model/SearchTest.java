package ua.kiev.naiv.drinkit.cocktail.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kiev.naiv.drinkit.cocktail.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;
import ua.kiev.naiv.drinkit.springconfig.AppConfig;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 22.07.13
 * Time: 21:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SearchTest {

    private final static String CRITERIA_JSON_PATH = "json/request/search.json";

    @Autowired
    CocktailService cocktailService;

    @Test
    public void readAndSerializeJsonIntoCriteria() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Criteria criteria = objectMapper.readValue(new File(CRITERIA_JSON_PATH), Criteria.class);
        Assert.assertNotNull(criteria);
        List<Recipe> recipes = cocktailService.findByCriteria(criteria);
        Assert.assertNotNull(recipes);
    }
}
