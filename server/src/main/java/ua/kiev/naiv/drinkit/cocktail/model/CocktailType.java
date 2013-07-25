package ua.kiev.naiv.drinkit.cocktail.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 17:32
 */
@Entity
@Table(name = "recipe_types")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CocktailType implements Serializable {

    private Integer id;
    private String name;
    private Set<Recipe> recipes;

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

    @OneToMany(mappedBy = "cocktailType")
    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
