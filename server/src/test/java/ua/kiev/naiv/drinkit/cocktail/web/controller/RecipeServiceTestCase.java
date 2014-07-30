package ua.kiev.naiv.drinkit.cocktail.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.kiev.naiv.drinkit.MockObjectsGenerator.createMockCriteria;
import static ua.kiev.naiv.drinkit.MockObjectsGenerator.creteMockRecipe;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTestCase {

    @InjectMocks
    RecipeController recipeController = new RecipeController();

    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                .setHandlerExceptionResolvers(new DefaultHandlerExceptionResolver())
                .build();
    }

    @Test
    public void getRecipeByIdShouldReturnValidRecipe() throws Exception {
        when(recipeService.getRecipeById(1)).thenReturn(creteMockRecipe());
        ResultActions resultActions = mockMvc.perform(get("/recipes/1"));
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(objectMapper.writeValueAsString(creteMockRecipe())));
    }

    @Test
    public void searchRecipeShouldReturnValidRecipes() throws Exception {
        Criteria criteria = createMockCriteria();
        when(recipeService.findByCriteria(any()))//todo fix any to criteria
                .thenReturn(Arrays.asList(creteMockRecipe()));
        ResultActions resultActions = mockMvc.perform(get("/recipes")
        .param("criteria", objectMapper.writeValueAsString(criteria)));
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(objectMapper.writeValueAsString(Arrays.asList(creteMockRecipe()))));
    }

//    @Test
//    @Ignore
//    public void deleteRecipeShouldReturnException() throws Exception {
//        doThrow(new RuntimeException("RecordNotFound")).when(recipeService).delete(0);
//        mockMvc.perform(delete("/recipes/0"));
//    }

    @Test
    public void deleteRecipeShouldReturn200() throws Exception {
        mockMvc.perform(delete("/recipes/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateRecipeShouldReturn200() throws Exception {
        Recipe recipe = creteMockRecipe();
        recipe.setId(1);
        mockMvc.perform(put("/recipes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(recipe)))
                .andExpect(status().isOk());
    }

    @Test
    public void createRecipeShouldReturnId() throws Exception {
        Recipe recipe = creteMockRecipe();
        when(recipeService.save(recipe)).thenReturn(recipe);
        String json = objectMapper.writeValueAsString(recipe);
        mockMvc.perform(post("/recipes").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().bytes(objectMapper.writeValueAsBytes(recipe)));
    }


}
