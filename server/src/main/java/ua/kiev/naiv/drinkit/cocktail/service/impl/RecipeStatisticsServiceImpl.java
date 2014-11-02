package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Recipe;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeStatistics;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.RecipeRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.RecipesStatisticsRepository;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeStatisticsService;

/**
 * @author pkolmykov
 */
@Service
public class RecipeStatisticsServiceImpl implements RecipeStatisticsService {
    @Autowired
    private RecipesStatisticsRepository recipesStatisticsRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    @Transactional
    public void incrementViewsCount(int recipeId, int userId) {
        int updatedRecords = recipesStatisticsRepository.incrementViewsField(recipeId, userId);
        if (updatedRecords == 0) {
            Recipe recipe = recipeRepository.findOne(recipeId);
            RecipeStatistics recipeStatistics = new RecipeStatistics();
            recipeStatistics.setViews(1);
            recipeStatistics.setUserId(userId);
            recipeStatistics.setRecipe(recipe);
            recipe.getRecipeStatistics().add(recipeStatistics);
            recipesStatisticsRepository.saveAndFlush(recipeStatistics);
        }
    }
}
