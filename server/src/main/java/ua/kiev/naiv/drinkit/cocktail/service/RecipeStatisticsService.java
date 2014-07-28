package ua.kiev.naiv.drinkit.cocktail.service;

public interface RecipeStatisticsService {
    void incrementViewsCount(int recipeId, int userId);

    void setRecipeRatingForUser(int id, int userId, int i);
}
