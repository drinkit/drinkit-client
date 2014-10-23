package ua.kiev.naiv.drinkit.cocktail.service;

/**
 * @author pkolmykov
 */
public interface RecipeStatisticsService {
    void incrementViewsCount(int recipeId, int userId);
}
