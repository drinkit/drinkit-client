package guru.drinkit.cocktail.mapping;

import guru.drinkit.cocktail.AbstractBaseTest;
import guru.drinkit.cocktail.persistence.entity.CocktailType;
import guru.drinkit.cocktail.persistence.entity.IngredientWithQuantity;
import guru.drinkit.cocktail.persistence.entity.Option;
import guru.drinkit.cocktail.persistence.entity.Recipe;
import guru.drinkit.cocktail.persistence.repository.IngredientRepository;
import guru.drinkit.cocktail.web.dto.RecipeDto;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DtoMapperIT extends AbstractBaseTest {
    @Resource
    DtoMapper dtoMapper;
    @Resource
    IngredientRepository ingredientRepository;

    @Test
    public void testRecipeConverting() {
        Recipe newRecipe = createNewRecipe();
        RecipeDto recipeDto = dtoMapper.map(newRecipe, RecipeDto.class);
        Recipe recipe = dtoMapper.map(recipeDto, Recipe.class);
        assertEquals(newRecipe, recipe);
    }

    protected Recipe createNewRecipe() {
        Recipe recipe = new Recipe();
        recipe.setCocktailType(new CocktailType(1));
        recipe.setDescription("desc");
        recipe.setName("Recipe for integration tests");
        recipe.setOptions(new ArrayList<Option>() {{
            add(new Option(1));
        }});
        recipe.setIngredientsWithQuantities(new ArrayList<IngredientWithQuantity>() {{
            IngredientWithQuantity ingredientWithQuantity = new IngredientWithQuantity();
            ingredientWithQuantity.setQuantity(50);
            ingredientWithQuantity.setIngredient(ingredientRepository.findOne(firstIngredient.getId()));
            ingredientWithQuantity.setRecipe(recipe);
            add(ingredientWithQuantity);
        }});
//        recipe.setIngredientsWithQuantities(new Integer[][]{{firstIngredient.getId(), 50}, {secondIngredient.getId(), 60}});
        return recipe;
    }

}