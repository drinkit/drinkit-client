package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.springframework.stereotype.Service;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeStatistics;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.RecipesStatisticsRepository;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeStatisticsService;

import javax.annotation.Resource;

@Service
public class RecipeStatisticsServiceImpl implements RecipeStatisticsService {

    @Resource
    RecipesStatisticsRepository recipesStatisticsRepository;

    @Override
    public void incrementViewsCount(int recipeId, int userId) {
        int updatedRecords = recipesStatisticsRepository.incrementViewsField(recipeId, userId);
        if (updatedRecords == 0) {
            RecipeStatistics recipeStatistics = new RecipeStatistics();
            recipeStatistics.setViews(1);
            recipeStatistics.setUserId(userId);
            recipeStatistics.setRecipeId(recipeId);
            recipesStatisticsRepository.saveAndFlush(recipeStatistics);
        }
    }

    @Override
    public void setRecipeRatingForUser(int id, int userId, int i) {
        throw new IllegalStateException("Not implemented yet"); //TODO Not implemented
    }
}
