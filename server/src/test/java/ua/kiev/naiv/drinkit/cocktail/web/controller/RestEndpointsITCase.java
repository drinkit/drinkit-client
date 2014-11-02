package ua.kiev.naiv.drinkit.cocktail.web.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kiev.naiv.drinkit.cocktail.service.IngredientService;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.dto.RecipeDto;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author pkolmykov
 */
public class RestEndpointsITCase extends AbstractRestMockMvc {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private IngredientService ingredientService;


    @Test
    public void testDeleteRecipeShouldReturn409() throws Exception {
        int ingredientId = ingredientService.create(createNewIngredient());
        RecipeDto newRecipeDto = createNewRecipe();
        newRecipeDto.setCocktailIngredients(new Integer[][]{{ingredientId, 100}});
        recipeService.save(newRecipeDto);
        mockMvc.perform(delete("/ingredients/" + ingredientId))
                .andExpect(status().isConflict());
        assertNotNull(ingredientService.getIngredientById(ingredientId));
    }

}
