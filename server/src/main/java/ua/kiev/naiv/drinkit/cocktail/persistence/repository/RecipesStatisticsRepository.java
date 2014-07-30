package ua.kiev.naiv.drinkit.cocktail.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeEntity;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeStatistics;

public interface RecipesStatisticsRepository extends JpaRepository<RecipeStatistics, RecipeStatistics.PrimaryKey> {

    @Modifying
    @Query("UPDATE RecipeStatistics s SET s.views=s.views + 1 WHERE s.recipeEntity=:recipeEntity AND s.userId=:userId")
    public int incrementViewsField(@Param("recipeEntity") RecipeEntity recipeEntity, @Param("userId") int userId);

}
