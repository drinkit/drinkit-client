package ua.kiev.naiv.drinkit.cocktail.web.controller;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.dto.RecipeDto;
import ua.kiev.naiv.drinkit.cocktail.web.dto.RecipeSearchResultMixin;

import java.io.InputStream;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerIT extends AbstractRestMockMvc {

    private static final String RESOURCE_ENDPOINT = "/recipes";

    @Autowired
    RecipeService recipeService;
    private RecipeDto insertedRecipeDto;

    @Before
    public void insertTestRecipe() {
        insertedRecipeDto = recipeService.save(createNewRecipe());
    }


    @Test
    public void testGetRecipeByIdWithStats() throws Exception {
        int views = insertedRecipeDto.getViews();
        mockMvc.perform(get(RESOURCE_ENDPOINT + "/" + insertedRecipeDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(insertedRecipeDto)));
        assertEquals(recipeService.getRecipeById(insertedRecipeDto.getId()).getViews(), views);
        SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken(null, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))));
        mockMvc.perform(get(RESOURCE_ENDPOINT + "/" + insertedRecipeDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(insertedRecipeDto)));
        assertEquals(recipeService.getRecipeById(insertedRecipeDto.getId()).getViews(), ++views);

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
        assertNotNull(recipeService.getRecipeById(insertedRecipeDto.getId()));
        mockMvc.perform(delete(RESOURCE_ENDPOINT + "/" + insertedRecipeDto.getId()))
                .andExpect(status().isNoContent());
        assertNull(recipeService.getRecipeById(insertedRecipeDto.getId()));
        mockMvc.perform(delete(RESOURCE_ENDPOINT + "/" + insertedRecipeDto.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateRecipe() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test.jpg");

        insertedRecipeDto.setImage(IOUtils.toByteArray(inputStream));
        insertedRecipeDto.setCocktailIngredients(new Integer[][]{{firstIngredient.getId(), 13}});
        insertedRecipeDto.setName("modified");
        mockMvc.perform(
                put(RESOURCE_ENDPOINT + "/" + insertedRecipeDto.getId())
                        .content(objectMapper.writeValueAsBytes(insertedRecipeDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertEquals(insertedRecipeDto, recipeService.getRecipeById(insertedRecipeDto.getId()));
    }

    @Test
    public void testFindRecipesByNamePart() throws Exception {
        objectMapper.addMixInAnnotations(RecipeDto.class, RecipeSearchResultMixin.class);
        mockMvc.perform(get(RESOURCE_ENDPOINT).param("namePart", "Integration Tests"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(insertedRecipeDto))));

        mockMvc.perform(get(RESOURCE_ENDPOINT).param("namePart", "%%%%%%%not exist$$$$$$$"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }
}