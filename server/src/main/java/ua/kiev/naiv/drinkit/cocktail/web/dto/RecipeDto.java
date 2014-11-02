package ua.kiev.naiv.drinkit.cocktail.web.dto;

import java.util.Arrays;

@SuppressWarnings("RedundantIfStatement")
public class RecipeDto {

    private int cocktailTypeId;
    private String description;
    private String name;
    private int[] options;
    private Integer[][] cocktailIngredients;
    private byte[] image;
    private byte[] thumbnail;
    private Integer id;
    private int views;
    private Double rating;
    private int votes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getCocktailTypeId() {
        return cocktailTypeId;
    }

    public void setCocktailTypeId(int cocktailTypeId) {
        this.cocktailTypeId = cocktailTypeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOptions(int[] options) {
        this.options = options;
    }

    public int[] getOptions() {
        return options;
    }

    public void setCocktailIngredients(Integer[][] cocktailIngredients) {
        this.cocktailIngredients = cocktailIngredients;
    }

    public Integer[][] getCocktailIngredients() {
        return cocktailIngredients;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeDto recipeDto = (RecipeDto) o;

        if (cocktailTypeId != recipeDto.cocktailTypeId) return false;
        if (description != null ? !description.equals(recipeDto.description) : recipeDto.description != null)
            return false;
        if (!Arrays.equals(image, recipeDto.image)) return false;
        if (!name.equals(recipeDto.name)) return false;
        if (!Arrays.equals(options, recipeDto.options)) return false;
        if (!Arrays.equals(thumbnail, recipeDto.thumbnail)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cocktailTypeId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (options != null ? Arrays.hashCode(options) : 0);
        result = 31 * result + (image != null ? Arrays.hashCode(image) : 0);
        result = 31 * result + (thumbnail != null ? Arrays.hashCode(thumbnail) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cocktailTypeId=" + cocktailTypeId +
                ", options=" + Arrays.toString(options) +
                ", image=" + (image == null ? "null" : Arrays.toString(Arrays.copyOfRange(image, 0, 5)) + "...") +
                ", thumbnail=" + (thumbnail == null ? "null" : Arrays.toString(Arrays.copyOfRange(thumbnail, 0, 5)) + "...") +
                '}';
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
