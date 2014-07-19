package ua.kiev.naiv.drinkit.cocktail.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 17:43
 */
@Entity
@Table(name = "recipes_has_ingredients")
@AssociationOverrides({
        @AssociationOverride(name = "cocktailIngredientId.recipeEntity",
                joinColumns = @JoinColumn(name = "recipe_id")),
        @AssociationOverride(name = "cocktailIngredientId.ingredient",
                joinColumns = @JoinColumn(name = "ingredient_id"))})
public class IngredientWithQuantity implements Serializable {

    private Integer quantity;
    private CocktailIngredientId cocktailIngredientId = new CocktailIngredientId();

    @EmbeddedId
    public CocktailIngredientId getCocktailIngredientId() {
        return cocktailIngredientId;
    }

    public void setCocktailIngredientId(CocktailIngredientId cocktailIngredientId) {
        this.cocktailIngredientId = cocktailIngredientId;
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
        return getCocktailIngredientId().getIngredient();
    }

    public void setIngredient(Ingredient ingredient) {
        getCocktailIngredientId().setIngredient(ingredient);
    }

    @Transient
    public RecipeEntity getRecipeEntity() {
        return getCocktailIngredientId().getRecipeEntity();
    }

    public void setRecipeEntity(RecipeEntity recipeEntity) {
        getCocktailIngredientId().setRecipeEntity(recipeEntity);
    }

    @Override
    public String toString() {
        return getIngredient().getName() + ":" + quantity;
    }
}
