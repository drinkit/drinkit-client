//package guru.drinkit.cocktail.service.impl;
//
//import guru.drinkit.cocktail.persistence.entity.RecipeStatistics;
//import guru.drinkit.cocktail.persistence.repository.RecipeRepository;
//import guru.drinkit.cocktail.persistence.repository.RecipesStatisticsRepository;
//import guru.drinkit.cocktail.service.RecipeStatisticsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import guru.drinkit.cocktail.persistence.entity.Recipe;
//
///**
// * @author pkolmykov
// */
//@Service
//public class RecipeStatisticsServiceImpl implements RecipeStatisticsService {
//    @Autowired
//    private RecipesStatisticsRepository recipesStatisticsRepository;
//    @Autowired
//    private RecipeRepository recipeRepository;
//
//    @Override
//    @Transactional
//    public void incrementViewsCount(int recipeId, int userId) {
//        int updatedRecords = recipesStatisticsRepository.incrementViewsField(recipeId, userId);
//        if (updatedRecords == 0) {
//            Recipe recipe = recipeRepository.findOne(recipeId);
//            RecipeStatistics recipeStatistics = new RecipeStatistics();
//            recipeStatistics.setViews(1);
//            recipeStatistics.setUserId(userId);
//            recipeStatistics.setRecipe(recipe);
//            recipe.getRecipeStatistics().add(recipeStatistics);
//            recipesStatisticsRepository.saveAndFlush(recipeStatistics);
//        }
//    }
//}
