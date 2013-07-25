package ua.kiev.naiv.drinkit.cocktail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
        @AssociationOverride(name = "id.recipe",
                joinColumns = @JoinColumn(name = "recipe_id")),
        @AssociationOverride(name = "id.ingredient",
                joinColumns = @JoinColumn(name = "ingredient_id")) })
public class CocktailIngredient implements Serializable{

    private Integer quantity;
    private CocktailIngredientId id;

    @EmbeddedId
    public CocktailIngredientId getId() {
        return id;
    }

    public void setId(CocktailIngredientId id) {
        this.id = id;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Transient
    public Ingredient getIngredient(){
        return getId().getIngredient();
    }

    public void setIngredient(Ingredient ingredient){
        getId().setIngredient(ingredient);
    }

    @Transient
    @JsonIgnore
    public Recipe getRecipe(){
        return getId().getRecipe();
    }

    public void setRecipe(Recipe recipe){
        getId().setRecipe(recipe);
    }



}
