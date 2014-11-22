package guru.drinkit.cocktail.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("unused")
@Entity
@Table(name = "recipes_has_ingredients")
@AssociationOverrides({
        @AssociationOverride(name = "recipeIngredientId.recipe",
                joinColumns = @JoinColumn(name = "recipe_id")),
        @AssociationOverride(name = "recipeIngredientId.ingredient",
                joinColumns = @JoinColumn(name = "ingredient_id"))})
public class IngredientWithQuantity implements Serializable {

    private Integer quantity;
    private RecipeIngredientId recipeIngredientId = new RecipeIngredientId();

    @EmbeddedId
    public RecipeIngredientId getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public void setRecipeIngredientId(RecipeIngredientId recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Transient
    public Ingredient getIngredient() {
        return getRecipeIngredientId().getIngredient();
    }

    public void setIngredient(Ingredient ingredient) {
        getRecipeIngredientId().setIngredient(ingredient);
    }

    @Transient
    public Recipe getRecipe() {
        return getRecipeIngredientId().getRecipe();
    }

    public void setRecipe(Recipe recipe) {
        getRecipeIngredientId().setRecipe(recipe);
    }

    @Override
    public String toString() {
        return getIngredient().getName() + ":" + quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngredientWithQuantity that = (IngredientWithQuantity) o;

        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (!recipeIngredientId.equals(that.recipeIngredientId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = quantity != null ? quantity.hashCode() : 0;
        result = 31 * result + recipeIngredientId.hashCode();
        return result;
    }
}
