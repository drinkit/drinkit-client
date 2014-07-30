package ua.kiev.naiv.drinkit.cocktail.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

import java.util.List;

public interface RecipeService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Recipe save(Recipe recipe);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(int id);

    List<Recipe> findAll();

    List<Recipe> findByCriteria(Criteria criteria);

    Recipe getRecipeById(int id);

    /**
     * Find recipe by id and increment count of views in Recipe Statistic table
     *
     * @param id     recipeId
     * @param userId id if user which requested this recipe.
     * @return recipe
     */
    Recipe getRecipeByIdAndIncrementViewsCount(int id, int userId);

}
