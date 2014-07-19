package ua.kiev.naiv.drinkit.cocktail.persistence.model;


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
public class CocktailType implements Serializable {

    private Integer id;
    private String name;
    private Set<RecipeEntity> recipeEntities;

    public CocktailType() {
    }

    public CocktailType(int id) {
        this.id = id;
    }

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
    public Set<RecipeEntity> getRecipeEntities() {
        return recipeEntities;
    }

    public void setRecipeEntities(Set<RecipeEntity> recipeEntities) {
        this.recipeEntities = recipeEntities;
    }
}
