package ua.kiev.naiv.drinkit.cocktail.web.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RecipeDtoJsonTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void readWriteJson() throws IOException {
        RecipeDto mockRecipeDto = creteMockRecipe();
        mockRecipeDto.setId(1);
        String json = objectMapper.writeValueAsString(mockRecipeDto);
        System.out.println(json);
        RecipeDto parsedRecipeDto = objectMapper.readValue(json, RecipeDto.class);
        assertEquals(mockRecipeDto, parsedRecipeDto);
    }

    private RecipeDto creteMockRecipe() {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setCocktailTypeId(1);
        recipeDto.setDescription("desc");
        recipeDto.setName("Test2");
        recipeDto.setOptions(new int[]{1, 2});
        recipeDto.setCocktailIngredients(new Integer[][]{{1, 50}, {2, 60}});
        return recipeDto;
    }

    @Test
    public void readJson() throws IOException {
        objectMapper.readValue(getClass().getResource("recipe.json"), RecipeDto.class);
    }

}