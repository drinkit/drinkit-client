package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeComparatorByCriteria;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.TransformUtils;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.CocktailTypeRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.RecipeRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.SearchSpecification;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static ua.kiev.naiv.drinkit.cocktail.persistence.model.TransformUtils.transform;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 21:56
 */
@Service
public class RecipeServiceImpl implements RecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);

    @Resource
    RecipeRepository recipeRepository;

    @Resource
    CocktailTypeRepository cocktailTypeRepository;

    @Override
    public int save(Recipe recipe) {
        return recipeRepository.saveAndFlush(transform(recipe)).getId();
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
//        List<Recipe> recipeEntities = recipeRepository.findAll(SearchSpecification.byCriteria(criteria));
//        if (criteria.getIngredients().size() > 0) {
//            Collections.sort(recipeEntities, new RecipeComparatorByCriteria(criteria));
//        }
//        LOGGER.info("findByCriteria({}): found {} records", criteria, recipeEntities.size());
//        return recipeEntities;
    }

    @Override
    public Recipe getRecipeById(int id) {
        return transform(recipeRepository.findOne(id));
    }

//    @Override
//    public CocktailType findCocktailTypeById(int id) {
//        return cocktailTypeRepository.findOne(id);
//    }

//    @Override
//    public List<CocktailType> findAllCocktailType() {
//        return cocktailTypeRepository.findAll();
//    }

//    @Override
//    public List<CocktailType> getCocktailTypes() {
//        List<CocktailType> cocktailTypes = cocktailTypeRepository.findAll();
//        LOGGER.info("getCocktailTypes: found {} records", cocktailTypes.size());
//        return cocktailTypes;
//    }



}
