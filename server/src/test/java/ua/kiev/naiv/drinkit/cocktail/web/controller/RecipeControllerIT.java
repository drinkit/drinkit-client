package ua.kiev.naiv.drinkit.cocktail.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;
import ua.kiev.naiv.drinkit.it.AbstractRestMockMvc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private Recipe createNewRecipe() {
        Recipe recipe = new Recipe();
        recipe.setCocktailTypeId(1);
        recipe.setDescription("desc");
        recipe.setName("Test2");
        recipe.setOptions(new int[]{1, 2});
        recipe.setCocktailIngredients(new Integer[][]{{firstIngredient.getId(), 50}, {secondIngredient.getId(), 60}});
        return recipe;
    }

    @Test
    public void testGetRecipeById() throws Exception {

    }

    @Test
    public void testCreateRecipe() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
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

    }
}