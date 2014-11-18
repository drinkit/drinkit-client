package guru.drinkit.cocktail.persistence.entity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (!cocktailType.equals(recipe.cocktailType)) return false;
        if (description != null ? !description.equals(recipe.description) : recipe.description != null) return false;
        if (id != null ? !id.equals(recipe.id) : recipe.id != null) return false;
        if (imageFileName != null ? !imageFileName.equals(recipe.imageFileName) : recipe.imageFileName != null)
            return false;
        if (ingredientsWithQuantities != null ? !ingredientsWithQuantities.equals(recipe.ingredientsWithQuantities) : recipe.ingredientsWithQuantities != null)
            return false;
        if (!name.equals(recipe.name)) return false;
        if (options != null ? !options.equals(recipe.options) : recipe.options != null) return false;
        if (thumbnailFileName != null ? !thumbnailFileName.equals(recipe.thumbnailFileName) : recipe.thumbnailFileName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + cocktailType.hashCode();
        result = 31 * result + (ingredientsWithQuantities != null ? ingredientsWithQuantities.hashCode() : 0);
        result = 31 * result + (options != null ? options.hashCode() : 0);
        result = 31 * result + (imageFileName != null ? imageFileName.hashCode() : 0);
        result = 31 * result + (thumbnailFileName != null ? thumbnailFileName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cocktailType=" + cocktailType +
                ", ingredientsWithQuantities=" + ingredientsWithQuantities +
                ", options=" + options +
                ", imageFileName='" + imageFileName + '\'' +
                ", thumbnailFileName='" + thumbnailFileName + '\'' +
                '}';
    }
}
