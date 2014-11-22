package guru.drinkit.cocktail.persistence.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "recipe_statistics", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "recipe_id"})})
@IdClass(RecipeStatistics.PrimaryKey.class)
public class RecipeStatistics implements Serializable {

    private int userId;
    private Recipe recipe;
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
    @ManyToOne(optional = false)
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
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
        private int recipe;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getrecipe() {
            return recipe;
        }

        public void setrecipe(int recipe) {
            this.recipe = recipe;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PrimaryKey that = (PrimaryKey) o;

            if (recipe != that.recipe) return false;
            if (userId != that.userId) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = userId;
            result = 31 * result + recipe;
            return result;
        }
    }
}
