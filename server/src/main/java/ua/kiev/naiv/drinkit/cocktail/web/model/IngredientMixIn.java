package ua.kiev.naiv.drinkit.cocktail.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.IngredientWithQuantity;

import java.util.Set;

/**
 * @author pkolmykov
 */
public abstract class IngredientMixIn {
    @JsonIgnore
    private Set<IngredientWithQuantity> cocktailIngredients;
}
