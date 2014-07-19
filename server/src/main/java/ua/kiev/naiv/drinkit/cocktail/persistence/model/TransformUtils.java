package ua.kiev.naiv.drinkit.cocktail.persistence.model;

import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author pkolmykov
 */
public class TransformUtils {


    public static Recipe transform(RecipeEntity recipeEntity) {
        if(recipeEntity == null){
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(recipeEntity.getId());
        recipe.setImage(recipeEntity.getImage());
        recipe.setThumbnail(recipeEntity.getThumbnail());
        recipe.setCocktailTypeId(recipeEntity.getCocktailType().getId());
        recipe.setDescription(recipeEntity.getDescription());
        recipe.setName(recipeEntity.getName());
        recipe.setCocktailIngredients(recipeEntity.getIngredientsWithQuantities().stream()
                .<Integer[]>map(val -> new Integer[]{val.getIngredient().getId(), val.getQuantity()})
                .toArray(Integer[][]::new));
        recipe.setOptions(recipeEntity.getOptions().stream()
                .mapToInt(Option::getId)
                .toArray());
        return recipe;
    }

    public static RecipeEntity transform(Recipe recipe) {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setId(recipe.getId());
        recipeEntity.setName(recipe.getName());
        recipeEntity.setDescription(recipe.getDescription());
        recipeEntity.setOptions(Arrays.stream(recipe.getOptions()).<Option>mapToObj(Option::new).collect(Collectors.toList()));
        recipeEntity.setCocktailType(new CocktailType(recipe.getCocktailTypeId()));
        recipeEntity.setIngredientsWithQuantities(Arrays.stream(recipe.getCocktailIngredients()).<IngredientWithQuantity>map(val -> {
            IngredientWithQuantity ingredientWithQuantity = new IngredientWithQuantity();
            ingredientWithQuantity.setQuantity(val[1]);
            ingredientWithQuantity.setRecipeEntity(recipeEntity);
            ingredientWithQuantity.setIngredient(new Ingredient(val[0]));
            return ingredientWithQuantity;
        }).collect(Collectors.toList()));
        recipeEntity.setImage(recipe.getImage());
        recipeEntity.setThumbnail(recipe.getThumbnail());
        return recipeEntity;
    }

}
