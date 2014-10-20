package ua.kiev.naiv.drinkit.cocktail.web.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.service.IngredientService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientsControllerIT extends AbstractRestMockMvc {

    @Autowired
    IngredientService ingredientService;

    private static final String RESOURCE_ENDPOINT = "/ingredients";

    @Test
    public void testGetIngredients() throws Exception {
        mockMvc.perform(get(RESOURCE_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new Ingredient[]{firstIngredient, secondIngredient})))
                .andExpect(jsonPath("$[0].name").value("First"))
                .andExpect(jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void testAddNewIngredient() throws Exception {

    }

    @Test
    public void testEditIngredient() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {
        assertNotNull(ingredientService.getIngredientById(firstIngredient.getId()));
        mockMvc.perform(delete(RESOURCE_ENDPOINT + "/" + firstIngredient.getId()))
                .andExpect(status().isNoContent());
        assertNull(ingredientService.getIngredientById(firstIngredient.getId()));
        mockMvc.perform(delete(RESOURCE_ENDPOINT + "/" + firstIngredient.getId()))
                .andExpect(status().isNotFound());
    }
}