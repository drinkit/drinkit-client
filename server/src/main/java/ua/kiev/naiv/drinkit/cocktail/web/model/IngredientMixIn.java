package ua.kiev.naiv.drinkit.cocktail.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.IngredientWithQuantity;

import java.util.Set;

@SuppressWarnings("unused")
public abstract class IngredientMixIn {
    @JsonIgnore
    private Set<IngredientWithQuantity> cocktailIngredients;
}
