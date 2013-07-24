package ua.kiev.naiv.drinkit.cocktail.search;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import ua.kiev.naiv.drinkit.cocktail.model.CocktailType;
import ua.kiev.naiv.drinkit.cocktail.model.Ingredient;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 22.07.13
 * Time: 21:39
 */
public class CriteriaPOJO {

    private Set<CocktailType> cocktailTypes;
    private Boolean isBurning;
    private Boolean isWithIce;
    private Boolean isChecked;
    private Boolean isIBA;
    private Boolean isFlacky;
    private Set<Ingredient> ingredients;

    public CriteriaPOJO() {
    }

    public CriteriaPOJO(Set<CocktailType> cocktailTypes, Boolean burning, Boolean withIce, Boolean checked, Boolean IBA, Boolean flacky, Set<Ingredient> ingredients) {
        this.cocktailTypes = cocktailTypes;
        isBurning = burning;
        isWithIce = withIce;
        isChecked = checked;
        isIBA = IBA;
        isFlacky = flacky;
        this.ingredients = ingredients;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public Set<CocktailType> getCocktailTypes() {
        return cocktailTypes;
    }

    public void setCocktailTypes(Set<CocktailType> cocktailTypes) {
        this.cocktailTypes = cocktailTypes;
    }

    public Boolean getBurning() {
        return isBurning;
    }

    public void setBurning(Boolean burning) {
        isBurning = burning;
    }

    public Boolean getWithIce() {
        return isWithIce;
    }

    public void setWithIce(Boolean withIce) {
        isWithIce = withIce;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public Boolean getIBA() {
        return isIBA;
    }

    public void setIBA(Boolean IBA) {
        isIBA = IBA;
    }

    public Boolean getFlacky() {
        return isFlacky;
    }

    public void setFlacky(Boolean flacky) {
        isFlacky = flacky;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        CriteriaPOJO that = (CriteriaPOJO) o;
//
//        if (!cocktailTypes.equals(that.cocktailTypes)) return false;
//        if (!ingredients.equals(that.ingredients)) return false;
//        if (!isBurning.equals(that.isBurning)) return false;
//        if (!isChecked.equals(that.isChecked)) return false;
//        if (!isFlacky.equals(that.isFlacky)) return false;
//        if (!isIBA.equals(that.isIBA)) return false;
//        if (!isWithIce.equals(that.isWithIce)) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = cocktailTypes.hashCode();
//        result = 31 * result + isBurning.hashCode();
//        result = 31 * result + isWithIce.hashCode();
//        result = 31 * result + isChecked.hashCode();
//        result = 31 * result + isIBA.hashCode();
//        result = 31 * result + isFlacky.hashCode();
//        result = 31 * result + ingredients.hashCode();
//        return result;
//    }
}
