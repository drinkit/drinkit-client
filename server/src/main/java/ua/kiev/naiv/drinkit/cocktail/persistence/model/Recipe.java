package ua.kiev.naiv.drinkit.cocktail.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "recipes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "name")
})
public class Recipe implements Serializable {

    @Id
    @GeneratedValue()
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne()
    @JoinColumn(name = "type_id")
    private CocktailType cocktailType;
    @OneToMany(mappedBy = "recipeIngredientId.recipe", cascade = CascadeType.ALL)
    private List<IngredientWithQuantity> ingredientsWithQuantities;
    @ManyToMany()
    @JoinTable(name = "recipes_has_options",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    private List<Option> options;
    @Column(name = "image_filename")
    private String imageFileName;
    @Column(name = "thumbnail_filename")
    private String thumbnailFileName;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private List<RecipeStatistics> recipeStatistics = new ArrayList<>();

    @Transient
    public List<Integer> getIngredientIds() {
        return getIngredientsWithQuantities().stream()
                .map(ingredientWithQuantity -> ingredientWithQuantity.getIngredient().getId())
                .collect(Collectors.toList());
    }

    //-----------------------------------------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CocktailType getCocktailType() {
        return cocktailType;
    }

    public void setCocktailType(CocktailType cocktailType) {
        this.cocktailType = cocktailType;
    }

    public List<IngredientWithQuantity> getIngredientsWithQuantities() {
        return ingredientsWithQuantities;
    }

    public void setIngredientsWithQuantities(List<IngredientWithQuantity> ingredientsWithQuantities) {
        this.ingredientsWithQuantities = ingredientsWithQuantities;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getThumbnailFileName() {
        return thumbnailFileName;
    }

    public void setThumbnailFileName(String thumbnailFileName) {
        this.thumbnailFileName = thumbnailFileName;
    }

    public List<RecipeStatistics> getRecipeStatistics() {
        return recipeStatistics;
    }

    public void setRecipeStatistics(List<RecipeStatistics> recipeStatistics) {
        this.recipeStatistics = recipeStatistics;
    }

}
