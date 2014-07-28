package ua.kiev.naiv.drinkit.cocktail.persistence.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "recipe_statistics", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "recipe_id"})})
@IdClass(RecipeStatistics.PrimaryKey.class)
public class RecipeStatistics implements Serializable {

    private int userId;
    private int recipeId;
    private RecipeEntity recipeEntity;
    private int views;
    private Integer rating;
    private Date lastTimestamp;

    @Column(name = "user_id")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "recipe_id")
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @ManyToOne()
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    public RecipeEntity getRecipeEntity() {
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity) {
        this.recipeEntity = recipeEntity;
    }

    @Column(name = "views")
    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Column(name = "rating")
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Column(name = "last_timestamp")
    public Date getLastTimestamp() {
        return lastTimestamp;
    }

    public void setLastTimestamp(Date lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }

    @SuppressWarnings("unused")
    public static class PrimaryKey implements Serializable {
        private int userId;
        private int recipeId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getRecipeId() {
            return recipeId;
        }

        public void setRecipeId(int recipeId) {
            this.recipeId = recipeId;
        }
    }
}
