package ua.kiev.naiv.drinkit.cocktail.web.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;
import ua.kiev.naiv.drinkit.it.AbstractRestMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientsControllerIT extends AbstractRestMockMvc {

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

    }
}