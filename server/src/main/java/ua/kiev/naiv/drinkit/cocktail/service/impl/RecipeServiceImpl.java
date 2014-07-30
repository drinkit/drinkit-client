package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.springframework.stereotype.Service;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeComparatorByCriteria;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeEntity;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeStatistics;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.TransformUtils;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.RecipeRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.RecipesStatisticsRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.SearchSpecification;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static ua.kiev.naiv.drinkit.cocktail.persistence.model.TransformUtils.transform;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Resource
    RecipeRepository recipeRepository;

    @Resource
    public RecipesStatisticsRepository recipesStatisticsRepository;

    @Override
    public Recipe save(Recipe recipe) {
        return transform(recipeRepository.saveAndFlush(transform(recipe)));
    }

    @Override
    public void delete(int id) {
        recipeRepository.delete(id);
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll().stream()
                .map(TransformUtils::transform)
                .collect(Collectors.toList());
    }

    @Override
    public List<Recipe> findByCriteria(Criteria criteria) {
        return recipeRepository.findAll(SearchSpecification.byCriteria(criteria)).stream()
                .sorted(new RecipeComparatorByCriteria(criteria))
                .map(TransformUtils::transform)
                .collect(Collectors.toList());
    }

    @Override
    public Recipe getRecipeById(int id) {
        return transform(recipeRepository.findOne(id));
    }

    @Override
    public Recipe getRecipeByIdAndIncrementViewsCount(int id, int userId) {
        RecipeEntity recipeEntity = recipeRepository.findOne(id);
        incrementViewsCount(recipeEntity, userId);
        return transform(recipeEntity);
    }


    private void incrementViewsCount(RecipeEntity recipeEntity, int userId) {
        int updatedRecords = recipesStatisticsRepository.incrementViewsField(recipeEntity, userId);
        if (updatedRecords == 0) {
            RecipeStatistics recipeStatistics = new RecipeStatistics();
            recipeStatistics.setViews(1);
            recipeStatistics.setUserId(userId);
            recipeStatistics.setRecipeEntity(recipeEntity);
            recipeEntity.getRecipeStatistics().add(recipeStatistics);
            recipesStatisticsRepository.saveAndFlush(recipeStatistics);
        }
    }
}
