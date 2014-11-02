package ua.kiev.naiv.drinkit.cocktail.persistence.model;


import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.Serializable;

@SuppressWarnings("unused")
@Embeddable
public class RecipeIngredientId implements Serializable {

    private Recipe recipe;
    private Ingredient ingredient;

    @ManyToOne
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @ManyToOne()
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
        ingredient.setId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeIngredientId that = (RecipeIngredientId) o;

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
