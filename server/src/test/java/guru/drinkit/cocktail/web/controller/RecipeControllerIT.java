package guru.drinkit.cocktail.web.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import guru.drinkit.cocktail.service.RecipeService;
import guru.drinkit.cocktail.web.dto.RecipeDto;
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


    private RecipeDto insertedRecipeDto;

    @Before
    public void insertTestRecipe() {
        insertedRecipeDto = recipeService.save(createNewRecipeDto());
    }


//    @Test//todo fix it
//    public void testGetRecipeByIdWithStats() throws Exception {
//        int views = insertedRecipeDto.getViews();
//        mockMvc.perform(get(RESOURCE_ENDPOINT + "/" + insertedRecipeDto.getId()))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(insertedRecipeDto)));
//        assertEquals(recipeService.getRecipeById(insertedRecipeDto.getId()).getViews(), views);
//        SecurityContextHolder.createEmptyContext();
//        SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken(null, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))));
//        mockMvc.perform(get(RESOURCE_ENDPOINT + "/" + insertedRecipeDto.getId()))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(insertedRecipeDto)));
//        assertEquals(recipeService.getRecipeById(insertedRecipeDto.getId()).getViews(), ++views);
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
        assertNotNull(recipeService.getRecipeById(insertedRecipeDto.getId()));
        mockMvc.perform(delete(RESOURCE_ENDPOINT + "/" + insertedRecipeDto.getId()))
                .andExpect(status().isNoContent());
        assertNull(recipeService.getRecipeById(insertedRecipeDto.getId()));
        mockMvc.perform(delete(RESOURCE_ENDPOINT + "/" + insertedRecipeDto.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateRecipe() throws Exception {
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
    public void testUploadMedia() throws Exception {
        byte[] image = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("test.jpg"));
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("image", image);
        objectNode.put("thumbnail", image);

        mockMvc.perform(post(RESOURCE_ENDPOINT + "/" + insertedRecipeDto.getId() + "/media")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectNode.toString()))
                .andExpect(status().isNoContent());

        RecipeDto recipeDto = recipeService.getRecipeById(insertedRecipeDto.getId());
        assertNotNull(recipeDto.getImageUrl());
        assertNotNull(recipeDto.getThumbnailUrl());
    }

    @Test
    public void testFindRecipesByNamePart() throws Exception {
        mockMvc.perform(get(RESOURCE_ENDPOINT).param("namePart", "Integration Tests"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(insertedRecipeDto))));

        mockMvc.perform(get(RESOURCE_ENDPOINT).param("namePart", "%%%%%%%not exist$$$$$$$"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }
}