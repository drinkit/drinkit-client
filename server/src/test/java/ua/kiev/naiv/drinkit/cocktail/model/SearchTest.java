package ua.kiev.naiv.drinkit.cocktail.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kiev.naiv.drinkit.cocktail.search.CriteriaPOJO;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;
import ua.kiev.naiv.drinkit.cocktail.service.IngredientService;
import ua.kiev.naiv.drinkit.springconfig.TestConfig;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 22.07.13
 * Time: 21:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class SearchTest {

    @Autowired
    IngredientService ingredientService;

    @Autowired
    CocktailService cocktailService;

    private CriteriaPOJO getMockCriteria() {
        CriteriaPOJO criteria = new CriteriaPOJO();
        criteria.setBurning(true);
        criteria.setChecked(false);
        criteria.setIBA(false);
        criteria.setFlacky(true);
        criteria.setIngredients(new HashSet<Ingredient>() {{
            add(ingredientService.getById(1));
            add(ingredientService.getById(2));
        }});
        criteria.setCocktailTypes(new HashSet<CocktailType>() {{
            add(cocktailService.getCocktailType(1));
            add(cocktailService.getCocktailType(2));
        }});
        return criteria;
    }

    @Test
    public void jsonTest() throws IOException {
        Cocktail cubaLibre = cocktailService.findById(1);
        File tmpFile = new File("d:\\tmp\\json.json");
        ObjectMapper objectMapper = new ObjectMapper();
        CriteriaPOJO mockCriteria = getMockCriteria();
        objectMapper.writeValue(tmpFile, mockCriteria);
        CriteriaPOJO criteriaPOJO = objectMapper.readValue(tmpFile, CriteriaPOJO.class);
        Assert.assertEquals(criteriaPOJO, mockCriteria);
    }
}
