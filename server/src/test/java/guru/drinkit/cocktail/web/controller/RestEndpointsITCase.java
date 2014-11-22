package guru.drinkit.cocktail.web.controller;

import guru.drinkit.cocktail.service.RecipeService;
import guru.drinkit.cocktail.web.dto.IngredientDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import guru.drinkit.cocktail.service.IngredientService;
import guru.drinkit.cocktail.web.dto.RecipeDto;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        int ingredientId = ingredientService.create(createNewIngredient()).getId();
        RecipeDto newRecipeDto = createNewRecipeDto();
        newRecipeDto.setCocktailIngredients(new Integer[][]{{ingredientId, 100}});
        recipeService.save(newRecipeDto);
        mockMvc.perform(delete("/ingredients/" + ingredientId))
                .andExpect(status().isConflict());
        assertNotNull(ingredientService.getIngredientById(ingredientId));
    }

    private IngredientDto createNewIngredient() {
        IngredientDto ingredient = new IngredientDto();
        ingredient.setDescription("new ingredient");
        ingredient.setName("new");
        ingredient.setVol(44);
        return ingredient;
    }

}
