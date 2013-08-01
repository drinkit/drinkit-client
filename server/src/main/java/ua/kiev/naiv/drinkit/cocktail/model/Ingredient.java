package ua.kiev.naiv.drinkit.cocktail.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

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
    @JsonIgnore
    private Set<CocktailIngredient> cocktailIngredients;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "vol")
    public Integer getVol() {
        return vol;
    }

    public void setVol(Integer vol) {
        this.vol = vol;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "cocktailIngredientId.ingredient")
    public Set<CocktailIngredient> getCocktailIngredients() {
        return cocktailIngredients;
    }

    public void setCocktailIngredients(Set<CocktailIngredient> cocktailIngredients) {
        this.cocktailIngredients = cocktailIngredients;
    }
}
