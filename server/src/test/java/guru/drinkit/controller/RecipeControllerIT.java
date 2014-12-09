package guru.drinkit.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import guru.drinkit.domain.Recipe;
import guru.drinkit.service.RecipeService;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
public class RecipeControllerIT extends AbstractRestMockMvc {

    private static final String RESOURCE_ENDPOINT = "/recipes";


    @Autowired
//            @Resource(name = "mockFileStoreService")
//    @InjectMocks
//            @Spy
            RecipeService recipeService;
//    @Resource(name = "mockFileStoreService")
//    FileStoreService fileStoreService;


    private Recipe insertedRecipe;

    @Before
    public void insertTestRecipe() {
        insertedRecipe = recipeService.save(createNewRecipeDto());
    }


//    @Test//todo fix it
//    public void testGetRecipeByIdWithStats() throws Exception {
//        int views = insertedRecipe.getViews();
//        mockMvc.perform(get(RESOURCE_ENDPOINT + "/" + insertedRecipe.getId()))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(insertedRecipe)));
//        assertEquals(recipeService.findById(insertedRecipe.getId()).getViews(), views);
//        SecurityContextHolder.createEmptyContext();
//        SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken(null, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))));
//        mockMvc.perform(get(RESOURCE_ENDPOINT + "/" + insertedRecipe.getId()))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(insertedRecipe)));
//        assertEquals(recipeService.findById(insertedRecipe.getId()).getViews(), ++views);
//
//    }

    @Test
    public void testCreateRecipe() throws Exception {
        mockMvc.perform(
                post(RESOURCE_ENDPOINT)
                        .content(objectMapper.writeValueAsBytes(createNewRecipeDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.id").exists())
//                .andExpect(jsonPath("$.name").value(createNewRecipeDto().getName()));
    }

    @Test
    public void testSearchRecipes() throws Exception {

    }

    @Test
    public void testDeleteRecipe() throws Exception {
        assertNotNull(recipeService.findById(insertedRecipe.getId()));
        mockMvc.perform(delete(RESOURCE_ENDPOINT + "/" + insertedRecipe.getId()))
                .andExpect(status().isNoContent());
        assertNull(recipeService.findById(insertedRecipe.getId()));
        mockMvc.perform(delete(RESOURCE_ENDPOINT + "/" + insertedRecipe.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateRecipe() throws Exception {
        insertedRecipe.setCocktailIngredients(new Integer[][]{{firstIngredient.getId(), 13}});
        insertedRecipe.setName("modified");
        mockMvc.perform(
                put(RESOURCE_ENDPOINT + "/" + insertedRecipe.getId())
                        .content(objectMapper.writeValueAsBytes(insertedRecipe))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertEquals(insertedRecipe, recipeService.findById(insertedRecipe.getId()));
    }

    @Test
    public void testUploadMedia() throws Exception {
        byte[] image = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("test.jpg"));
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("image", image);
        objectNode.put("thumbnail", image);

        mockMvc.perform(post(RESOURCE_ENDPOINT + "/" + insertedRecipe.getId() + "/media")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectNode.toString()))
                .andExpect(status().isNoContent());

        Recipe recipe = recipeService.findById(insertedRecipe.getId());
        assertNotNull(recipe.getImageUrl());
        assertNotNull(recipe.getThumbnailUrl());
    }

    @Test
    public void testFindRecipesByNamePart() throws Exception {
        mockMvc.perform(get(RESOURCE_ENDPOINT).param("namePart", "Integration Tests"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(insertedRecipe))));

        mockMvc.perform(get(RESOURCE_ENDPOINT).param("namePart", "%%%%%%%not exist$$$$$$$"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }
}