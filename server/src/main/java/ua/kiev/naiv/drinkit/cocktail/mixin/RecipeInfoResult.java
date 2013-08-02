package ua.kiev.naiv.drinkit.cocktail.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 02.08.13
 * Time: 0:39
 */
public abstract class RecipeInfoResult {

    @JsonIgnore
    private byte[] thumbnail;

    @JsonIgnore
    public abstract Set<Integer> getIngredientIds();
}
