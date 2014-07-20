package ua.kiev.naiv.drinkit.cocktail.web.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import ua.kiev.naiv.drinkit.MockObjectsGenerator;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RecipeJsonTest {
    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void readWriteJson() throws IOException {
        Recipe mockRecipe = MockObjectsGenerator.creteMockRecipe();
        mockRecipe.setId(1);
        String json = objectMapper.writeValueAsString(mockRecipe);
        System.out.println(json);
        Recipe parsedRecipe = objectMapper.readValue(json, Recipe.class);
        assertEquals(mockRecipe, parsedRecipe);
    }

    @Test
    public void readJson() throws IOException {
        objectMapper.readValue(getClass().getResource("recipe.json"), Recipe.class);
    }

}