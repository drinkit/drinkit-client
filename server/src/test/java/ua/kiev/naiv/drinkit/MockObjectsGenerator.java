package ua.kiev.naiv.drinkit;

import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author pkolmykov
 */
public class MockObjectsGenerator {

    public static Recipe creteMockRecipe() {
        Recipe recipe = new Recipe();
        recipe.setCocktailTypeId(1);
        recipe.setDescription("desc");
        recipe.setName("Test2");
        recipe.setOptions(new int[]{1, 2});
        recipe.setCocktailIngredients(new Integer[][]{{1, 50}, {2, 60}});
        return recipe;
    }

    public static Criteria createMockCriteria() {
        return new Criteria(new HashSet<>(Arrays.asList(1)),
                new HashSet<>(Arrays.asList(1)),
                new HashSet<>(Arrays.asList(1)));
    }

    public static Ingredient createMockIngredient(){
        Ingredient ingredient = new Ingredient();
        ingredient.setName("name");
        ingredient.setDescription("desc");
        ingredient.setVol(30);
        return ingredient;
    }
}
