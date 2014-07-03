package ua.kiev.naiv.drinkit.cocktail.pojos;

import ua.kiev.naiv.drinkit.cocktail.model.*;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author pkolmykov
 */
public class RecipeInput {

    private int cocktailTypeId;
    private String description;
    private String name;
    private int[] options;
    private int[][] cocktailIngredients;

    public Recipe transform() {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setOptions(Arrays.stream(options).<Option>mapToObj(Option::new).collect(Collectors.toSet()));
        recipe.setCocktailType(new CocktailType(cocktailTypeId));
        recipe.setIngredientsWithQuantities(Arrays.stream(cocktailIngredients).<IngredientWithQuantity>map(val -> {
            IngredientWithQuantity ingredientWithQuantity = new IngredientWithQuantity();
            ingredientWithQuantity.setQuantity(val[1]);
            ingredientWithQuantity.setRecipe(recipe);
            ingredientWithQuantity.setIngredient(new Ingredient(val[0]));
            return ingredientWithQuantity;
        }).collect(Collectors.toSet()));
        return recipe;
    }

    public int getCocktailTypeId() {
        return cocktailTypeId;
    }

    public void setCocktailTypeId(int cocktailTypeId) {
        this.cocktailTypeId = cocktailTypeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOptions(int[] options) {
        this.options = options;
    }

    public int[] getOptions() {
        return options;
    }

    public void setCocktailIngredients(int[][] cocktailIngredients) {
        this.cocktailIngredients = cocktailIngredients;
    }

    public int[][] getCocktailIngredients() {
        return cocktailIngredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeInput that = (RecipeInput) o;

        if (cocktailTypeId != that.cocktailTypeId) return false;
        if (!description.equals(that.description)) return false;
        if (!name.equals(that.name)) return false;
        if (!Arrays.equals(options, that.options)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cocktailTypeId;
        result = 31 * result + description.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + Arrays.hashCode(options);
        return result;
    }
}
