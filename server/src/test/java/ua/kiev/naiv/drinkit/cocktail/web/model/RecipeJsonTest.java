package ua.kiev.naiv.drinkit.cocktail.web.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RecipeJsonTest {
    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void readWriteJson() throws IOException {
        Recipe mockRecipe = creteMockRecipe();
        mockRecipe.setId(1);
        String json = objectMapper.writeValueAsString(mockRecipe);
        System.out.println(json);
        Recipe parsedRecipe = objectMapper.readValue(json, Recipe.class);
        assertEquals(mockRecipe, parsedRecipe);
    }

    private Recipe creteMockRecipe() {
        Recipe recipe = new Recipe();
        recipe.setCocktailTypeId(1);
        recipe.setDescription("desc");
        recipe.setName("Test2");
        recipe.setOptions(new int[]{1, 2});
        recipe.setCocktailIngredients(new Integer[][]{{1, 50}, {2, 60}});
        return recipe;
    }

    @Test
    public void readJson() throws IOException {
        objectMapper.readValue(getClass().getResource("recipe.json"), Recipe.class);
    }

}