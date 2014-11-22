package guru.drinkit.cocktail.persistence.repository;

import guru.drinkit.cocktail.persistence.entity.RecipeStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipesStatisticsRepository extends JpaRepository<RecipeStatistics, RecipeStatistics.PrimaryKey> {

    @Modifying
    @Query("UPDATE RecipeStatistics s SET s.views=s.views + 1 WHERE s.recipe.id=:recipeId AND s.userId=:userId")
    public int incrementViewsField(@Param("recipeId") int recipeId, @Param("userId") int userId);

}
