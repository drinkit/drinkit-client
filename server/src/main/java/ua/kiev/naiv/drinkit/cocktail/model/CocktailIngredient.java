package ua.kiev.naiv.drinkit.cocktail.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 17:43
 */
@Entity
@Table(name = "recipes_has_ingredients")
@AssociationOverrides({
        @AssociationOverride(name = "id.cocktail",
                joinColumns = @JoinColumn(name = "cocktails_ncocktailkey")),
        @AssociationOverride(name = "id.ingredient",
                joinColumns = @JoinColumn(name = "ingredients_ningredientkey")) })
public class CocktailIngredient {

    private Integer quantity;
    private CocktailIngredientId id;

    @EmbeddedId
    public CocktailIngredientId getId() {
        return id;
    }

    public void setId(CocktailIngredientId id) {
        this.id = id;
    }

    @Column(name = "nquantity")
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
    public Cocktail getCocktail(){
        return getId().getCocktail();
    }

    public void setCocktail(Cocktail cocktail){
        getId().setCocktail(cocktail);
    }



}
