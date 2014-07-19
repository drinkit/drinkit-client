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
import ua.kiev.naiv.drinkit.cocktail.service.IngredientService;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.kiev.naiv.drinkit.MockObjectsGenerator.createMockIngredient;

/**
 * @author pkolmykov
 */
@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTestCase {

    @InjectMocks
    IngredientsController controller = new IngredientsController();

    @Mock
    IngredientService service;

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
//                .setHandlerExceptionResolvers(new DefaultHandlerExceptionResolver())
                .build();
    }


    @Test
    public void getIngredientsShouldReturnValidResult() throws Exception {
        when(service.getIngredients()).thenReturn(Arrays.asList(createMockIngredient()));
        ResultActions resultActions = mockMvc.perform(get("/ingredients"));
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(objectMapper.writeValueAsString(
                        Arrays.asList(createMockIngredient()))));
    }


    @Test
    public void deleteShouldReturn200() throws Exception {
        mockMvc.perform(delete("/ingredients/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateShouldReturn200() throws Exception {
        mockMvc.perform(put("/ingredients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(createMockIngredient())))
                .andExpect(status().isOk());
    }

    @Test
    public void createShouldReturnId() throws Exception {
        when(service.create(any())).thenReturn(1);
        String json = objectMapper.writeValueAsString(createMockIngredient());
        mockMvc.perform(post("/ingredients").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}
