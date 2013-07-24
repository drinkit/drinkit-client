package ua.kiev.naiv.drinkit.cocktail.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 17:35
 */

@Entity
@Table(name = "ingredients")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ingredient {

    private Integer id;
    private String name;
    private Integer vol;
    private String description;
//    private Set<CocktailIngredient> cocktailIngredients;

    @Id
    @Column(name = "ningredientkey")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "cname")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "nvol")
    public Integer getVol() {
        return vol;
    }

    public void setVol(Integer vol) {
        this.vol = vol;
    }

    @Column(name = "cdescription")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @OneToMany(mappedBy = "id.ingredient")
//    public Set<CocktailIngredient> getCocktailIngredients() {
//        return cocktailIngredients;
//    }

//    public void setCocktailIngredients(Set<CocktailIngredient> cocktailIngredients) {
//        this.cocktailIngredients = cocktailIngredients;
//    }
}
