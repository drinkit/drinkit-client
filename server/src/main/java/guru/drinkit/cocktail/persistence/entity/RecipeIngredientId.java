package guru.drinkit.cocktail.persistence.entity;


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

        if (ingredient.getId() != null ? !ingredient.getId().equals(that.ingredient.getId()) : that.ingredient.getId() != null)
            return false;
        if (recipe.getId() != null ? !recipe.getId().equals(that.recipe.getId()) : that.recipe.getId() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recipe.getId() != null ? recipe.getId().hashCode() : 0;
        result = 31 * result + ingredient.getId().hashCode();
        return result;
    }


//    @Override
//    public int hashCode() {
//        int result = recipe != null ? recipe.hashCode() : 0;
//        result = 31 * result + (ingredient != null ? ingredient.hashCode() : 0);
//        return result;
//    }
}
