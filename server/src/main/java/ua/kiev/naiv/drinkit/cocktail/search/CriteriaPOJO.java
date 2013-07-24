package ua.kiev.naiv.drinkit.cocktail.search;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 22.07.13
 * Time: 21:39
 */
public class CriteriaPOJO {

    private Set<Integer> cocktailTypes;
    private Boolean isBurning;
    private Boolean isWithIce;
    private Boolean isChecked;
    private Boolean isIBA;
    private Boolean isFlacky;
    private Set<Integer> ingredients;

    public CriteriaPOJO() {
    }

    public CriteriaPOJO(Set<Integer> cocktailTypes, Boolean burning, Boolean withIce, Boolean checked, Boolean IBA, Boolean flacky, Set<Integer> ingredients) {
        this.cocktailTypes = cocktailTypes;
        isBurning = burning;
        isWithIce = withIce;
        isChecked = checked;
        isIBA = IBA;
        isFlacky = flacky;
        this.ingredients = ingredients;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public Set<Integer> getCocktailTypes() {
        return cocktailTypes;
    }

    public void setCocktailTypes(Set<Integer> cocktailTypes) {
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
    public Set<Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Integer> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriteriaPOJO that = (CriteriaPOJO) o;

        if (cocktailTypes != null ? !cocktailTypes.equals(that.cocktailTypes) : that.cocktailTypes != null)
            return false;
        if (!ingredients.equals(that.ingredients)) return false;
        if (isBurning != null ? !isBurning.equals(that.isBurning) : that.isBurning != null) return false;
        if (isChecked != null ? !isChecked.equals(that.isChecked) : that.isChecked != null) return false;
        if (isFlacky != null ? !isFlacky.equals(that.isFlacky) : that.isFlacky != null) return false;
        if (isIBA != null ? !isIBA.equals(that.isIBA) : that.isIBA != null) return false;
        if (isWithIce != null ? !isWithIce.equals(that.isWithIce) : that.isWithIce != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cocktailTypes != null ? cocktailTypes.hashCode() : 0;
        result = 31 * result + (isBurning != null ? isBurning.hashCode() : 0);
        result = 31 * result + (isWithIce != null ? isWithIce.hashCode() : 0);
        result = 31 * result + (isChecked != null ? isChecked.hashCode() : 0);
        result = 31 * result + (isIBA != null ? isIBA.hashCode() : 0);
        result = 31 * result + (isFlacky != null ? isFlacky.hashCode() : 0);
        result = 31 * result + ingredients.hashCode();
        return result;
    }
}
