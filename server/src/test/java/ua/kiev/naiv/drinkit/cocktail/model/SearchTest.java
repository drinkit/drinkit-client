package ua.kiev.naiv.drinkit.cocktail.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.junit.Test;
import ua.kiev.naiv.drinkit.cocktail.search.CriteriaPOJO;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 22.07.13
 * Time: 21:41
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
public class SearchTest {

    private final static String CRITERIA_JSON_PATH = "json/request/search.json";

    @Test
    public void readAndSerializeJsonIntoCriteria() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CriteriaPOJO criteria = objectMapper.readValue(new File(CRITERIA_JSON_PATH), CriteriaPOJO.class);
        Assert.assertNotNull(criteria);
    }
}
