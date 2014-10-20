package ua.kiev.naiv.drinkit.cocktail.web.controller;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

import java.io.InputStream;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RecipeControllerIT extends AbstractRestMockMvc {

    private static final String RESOURCE_ENDPOINT = "/recipes";

    @Autowired
    RecipeService recipeService;
    private Recipe insertedRecipe;

    @Before
    public void insertTestRecipe() {
        insertedRecipe = recipeService.save(createNewRecipe());
    }


    @Test
    public void testGetRecipeById() throws Exception {

    }

    @Test
    public void testCreateRecipe() throws Exception {
        mockMvc.perform(
                post(RESOURCE_ENDPOINT)
                        .content(objectMapper.writeValueAsBytes(createNewRecipe()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(createNewRecipe().getName()));
    }

    @Test
    public void testSearchRecipes() throws Exception {

    }

    @Test
    public void testDeleteRecipe() throws Exception {
        assertNotNull(recipeService.getRecipeById(insertedRecipe.getId()));
        mockMvc.perform(delete(RESOURCE_ENDPOINT + "/" + insertedRecipe.getId()))
                .andExpect(status().isNoContent());
        assertNull(recipeService.getRecipeById(insertedRecipe.getId()));
        mockMvc.perform(delete(RESOURCE_ENDPOINT + "/" + insertedRecipe.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateRecipe() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test.jpg");

        insertedRecipe.setImage(IOUtils.toByteArray(inputStream));
        insertedRecipe.setCocktailIngredients(new Integer[][]{{firstIngredient.getId(), 13}});
        insertedRecipe.setName("modified");
        mockMvc.perform(
                put(RESOURCE_ENDPOINT + "/" + insertedRecipe.getId())
                        .content(objectMapper.writeValueAsBytes(insertedRecipe))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertEquals(insertedRecipe, recipeService.getRecipeById(insertedRecipe.getId()));
    }
}