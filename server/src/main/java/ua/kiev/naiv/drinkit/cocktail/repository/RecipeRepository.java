package ua.kiev.naiv.drinkit.cocktail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.naiv.drinkit.cocktail.model.Recipe;
import ua.kiev.naiv.drinkit.cocktail.search.CriteriaPOJO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 21:47
 */
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByCriteria(CriteriaPOJO criteria);
}
