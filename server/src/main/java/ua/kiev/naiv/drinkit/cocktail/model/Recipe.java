package ua.kiev.naiv.drinkit.cocktail.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 16:42
 */

@Entity
@Table(name = "recipes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "name")
})
public class Recipe implements Serializable {

    private Integer id;
    private String name;
    private String description;
    @JsonIdentityReference(alwaysAsId = true)
    private CocktailType cocktailType;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<CocktailIngredient> ingredients;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Option> options;

    @Id
    @GeneratedValue()
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne()
    @JoinColumn(name = "type_id")
    public CocktailType getCocktailType() {
        return cocktailType;
    }

    public void setCocktailType(CocktailType cocktailType) {
        this.cocktailType = cocktailType;
    }

    @OneToMany(mappedBy = "id.recipe", fetch = FetchType.EAGER)
    public Set<CocktailIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<CocktailIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipes_has_options",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}
