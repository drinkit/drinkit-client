package ua.kiev.naiv.drinkit.cocktail.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 16:42
 */

@Entity
@Table(name = "recipes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ncocktailkey"),
        @UniqueConstraint(columnNames = "cname")
})
public class Cocktail {

    private Integer id;
    private String name;
    private String description;
    private CocktailType cocktailType;
    private Boolean isBurning;
    private Boolean isWithIce;
    private Boolean isChecked;
    private Boolean isIBA;
    private Boolean isFlacky;
    private Set<CocktailIngredient> ingredients;

    @Id
    @GeneratedValue()
    @Column(name = "ncocktailkey", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "cname", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "cdescription")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne()
    @JoinColumn(name = "ntype")
    public CocktailType getCocktailType() {
        return cocktailType;
    }

    public void setCocktailType(CocktailType cocktailType) {
        this.cocktailType = cocktailType;
    }

    @Column(name = "bisburning")
    public Boolean getBurning() {
        return isBurning;
    }

    public void setBurning(Boolean burning) {
        isBurning = burning;
    }


    @Column(name = "bwithice")
    public Boolean getWithIce() {
        return isWithIce;
    }

    public void setWithIce(Boolean withIce) {
        isWithIce = withIce;
    }

    @Column(name = "bischecked")
    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    @Column(name = "biba")
    public Boolean getIBA() {
        return isIBA;
    }

    public void setIBA(Boolean IBA) {
        isIBA = IBA;
    }

    @Column(name = "bisflacky")
    public Boolean getFlacky() {
        return isFlacky;
    }

    public void setFlacky(Boolean flacky) {
        isFlacky = flacky;
    }

    @OneToMany(mappedBy = "id.cocktail")
    public Set<CocktailIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<CocktailIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
