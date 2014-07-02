package ua.kiev.naiv.drinkit.cocktail.pojos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import ua.kiev.naiv.drinkit.cocktail.model.Recipe;

import java.io.IOException;

/**
 * @author pkolmykov
 */
public class TransformTests {

    @Test
    public void testRecipeInput() throws IOException {
        String json = "{\"cocktailTypeId\":1,\"description\":\"desc\",\"name\":\"Test2\",\"options\":[1,2],\"cocktailIngredients\":[[1,50],[2,60]]}";
        ObjectMapper objectMapper = new ObjectMapper();
        Assert.assertEquals(json, objectMapper.writeValueAsString(creteMockRecipeInput()));
        Assert.assertEquals(creteMockRecipeInput(), objectMapper.readValue(json, RecipeInput.class));
        Recipe recipe = creteMockRecipeInput().transform();
        System.out.println(recipe);
    }

    public RecipeInput creteMockRecipeInput() {
        RecipeInput recipeInput = new RecipeInput();
        recipeInput.setCocktailTypeId(1);
        recipeInput.setDescription("desc");
        recipeInput.setName("Test2");
        recipeInput.setOptions(new int[]{1, 2});
        recipeInput.setCocktailIngredients(new int[][]{{1, 50}, {2, 60}});
        return recipeInput;
    }
}
