package ua.kiev.naiv.drinkit.cocktail.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 20:42
 */

@Embeddable
public class CocktailIngredientId implements Serializable {

    private Cocktail cocktail;
    private Ingredient ingredient;

    @JsonIgnore
    @ManyToOne
    public Cocktail getCocktail() {
        return cocktail;
    }

    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
