package ua.kiev.naiv.drinkit.cocktail.search;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 22.07.13
 * Time: 21:39
 */
public class CriteriaPOJO {

    private Set<Integer> cocktailTypes;
    private Set<Integer> ingredients;
    private Set<Integer> options;

    public CriteriaPOJO() {
    }

    //    @JsonIdentityReference(alwaysAsId = true)
    public Set<Integer> getCocktailTypes() {
        return cocktailTypes;
    }

    public void setCocktailTypes(Set<Integer> cocktailTypes) {
        this.cocktailTypes = cocktailTypes;
    }

    //    @JsonIdentityReference(alwaysAsId = true)
    public Set<Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Integer> getOptions() {
        return options;
    }

    public void setOptions(Set<Integer> options) {
        this.options = options;
    }
}
