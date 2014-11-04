package ua.kiev.naiv.drinkit.cocktail.persistence.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
@Table(name = "ingredients")
public class Ingredient {

    private Integer id;
    private String name;
    private Integer vol;
    private String description;
    private Set<IngredientWithQuantity> cocktailIngredients = new HashSet<>();

    public Ingredient() {
    }

    public Ingredient(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
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

    @OneToMany(mappedBy = "recipeIngredientId.ingredient")
    public Set<IngredientWithQuantity> getCocktailIngredients() {
        return cocktailIngredients;
    }

    public void setCocktailIngredients(Set<IngredientWithQuantity> ingredientWithQuantities) {
        this.cocktailIngredients = ingredientWithQuantities;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vol=" + vol +
                ", description='" + description + '\'' +
                '}';
    }
}
