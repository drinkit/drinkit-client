package ua.kiev.naiv.drinkit.cocktail.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "recipes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "name")
})
public class RecipeEntity implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private CocktailType cocktailType;
    private List<IngredientWithQuantity> ingredientsWithQuantities;
    private List<Option> options;
    private byte[] image;
    private byte[] thumbnail;

    @Transient
    public List<Integer> getIngredientIds() {
        return getIngredientsWithQuantities().stream()
                .map(ingredientWithQuantity -> ingredientWithQuantity.getIngredient().getId())
                .collect(Collectors.toList());
    }

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

    @OneToMany(mappedBy = "cocktailIngredientId.recipeEntity", cascade = CascadeType.ALL)
    public List<IngredientWithQuantity> getIngredientsWithQuantities() {
        return ingredientsWithQuantities;
    }

    public void setIngredientsWithQuantities(List<IngredientWithQuantity> ingredientsWithQuantities) {
        this.ingredientsWithQuantities = ingredientsWithQuantities;
    }

    @ManyToMany()
    @JoinTable(name = "recipes_has_options",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column()
    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }
}
