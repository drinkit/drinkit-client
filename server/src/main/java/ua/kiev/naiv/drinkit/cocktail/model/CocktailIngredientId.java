package ua.kiev.naiv.drinkit.cocktail.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 20:42
 */

@Embeddable
public class CocktailIngredientId implements Serializable {

    @JsonIgnore
    private Recipe recipe;
    private Ingredient ingredient;

    @ManyToOne
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Transient
    private int getId() {
        return ingredient.getId();
    }

    @Transient
    private void setId(int id) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CocktailIngredientId that = (CocktailIngredientId) o;

        if (!ingredient.equals(that.ingredient)) return false;
        if (!recipe.equals(that.recipe)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recipe.hashCode();
        result = 31 * result + ingredient.hashCode();
        return result;
    }
}
