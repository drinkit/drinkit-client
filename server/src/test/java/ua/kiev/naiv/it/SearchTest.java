package ua.kiev.naiv.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kiev.naiv.drinkit.cocktail.mixin.RecipeSearchResult;
import ua.kiev.naiv.drinkit.cocktail.model.Option;
import ua.kiev.naiv.drinkit.cocktail.model.Recipe;
import ua.kiev.naiv.drinkit.cocktail.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;
import ua.kiev.naiv.drinkit.springconfig.AppConfig;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 22.07.13
 * Time: 21:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SearchTest extends AbstractTransactionalJUnit4SpringContextTests{

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

    @Test
    public void findCubaLibreByCriteria() throws IOException {
        Criteria criteria = new Criteria(Collections.singleton(1), Collections.singleton(2), Collections.singleton(2));
        List<Recipe> recipes = cocktailService.findByCriteria(criteria);
        Assert.assertNotNull(recipes);
        Assert.assertTrue(recipes.size() > 0);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixInAnnotations(Recipe.class, RecipeSearchResult.class);
        objectMapper.writeValue(TestHelper.createIfNotExistJsonFileResponse("searchResult"), recipes);
    }

    @Test public void testSearchService(){
        Criteria criteria = new Criteria(Collections.singleton(1), Collections.singleton(2), Collections.singleton(2));
        List<Recipe> recipes = cocktailService.findByCriteria(criteria);
        recipes = cocktailService.findByCriteria(criteria);
        Iterator<Option> iter = recipes.get(0).getOptions().iterator();
        System.out.println(iter.next().getId());
        System.out.println(iter.next().getId());
        return;
    }
}
