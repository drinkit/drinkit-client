package ua.kiev.naiv.drinkit.cocktail.mixin;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.kiev.naiv.drinkit.cocktail.model.CocktailIngredient;
import ua.kiev.naiv.drinkit.cocktail.model.CocktailType;
import ua.kiev.naiv.drinkit.cocktail.model.Option;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 02.08.13
 * Time: 0:39
 */
public abstract class RecipeInfoResult {
    @JsonIdentityReference(alwaysAsId = true)
    private CocktailType cocktailType;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<CocktailIngredient> ingredients;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Option> options;
    @JsonIgnore
    private byte[] image;
}
