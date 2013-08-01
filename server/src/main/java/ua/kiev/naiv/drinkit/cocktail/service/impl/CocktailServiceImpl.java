package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.naiv.drinkit.cocktail.model.CocktailType;
import ua.kiev.naiv.drinkit.cocktail.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.model.Recipe;
import ua.kiev.naiv.drinkit.cocktail.repository.CocktailTypeRepository;
import ua.kiev.naiv.drinkit.cocktail.repository.IngredientRepository;
import ua.kiev.naiv.drinkit.cocktail.repository.RecipeRepository;
import ua.kiev.naiv.drinkit.cocktail.search.CriteriaPOJO;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 21:56
 */
@Service
public class CocktailServiceImpl implements CocktailService {

    @Resource
    RecipeRepository recipeRepository;

    @Resource
    CocktailTypeRepository cocktailTypeRepository;

    @Override
    public Recipe create(Recipe recipe) {
        throw new IllegalStateException("Not implemented yet"); //TODO Not implemented
    }

    @Override
    public Recipe delete(int id) {
        throw new IllegalStateException("Not implemented yet"); //TODO Not implemented
    }

    @Override
    public List<Recipe> findAll() {
        throw new IllegalStateException("Not implemented yet"); //TODO Not implemented
    }

    @Override
    public List<Recipe> findByCriteria(CriteriaPOJO criteria) {
//        return recipeRepository.findByCriteria(criteria);
        return null;
    }

    @Override
    public Recipe update(Recipe recipe) {
        throw new IllegalStateException("Not implemented yet"); //TODO Not implemented
    }

    @Override
    public Recipe findById(int id) {
        return recipeRepository.findOne(id);
    }

    @Override
    public CocktailType findCocktailTypeById(int id) {
        return cocktailTypeRepository.findOne(id);
    }

    @Override
    public List<CocktailType> findAllCocktailType() {
        return cocktailTypeRepository.findAll();
    }

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient findIngredientById(int id) {
        return ingredientRepository.findOne(id);
    }
}
