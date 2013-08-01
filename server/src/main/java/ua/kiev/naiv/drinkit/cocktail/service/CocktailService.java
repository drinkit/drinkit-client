package ua.kiev.naiv.drinkit.cocktail.service;

import ua.kiev.naiv.drinkit.cocktail.model.CocktailType;
import ua.kiev.naiv.drinkit.cocktail.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.model.Recipe;
import ua.kiev.naiv.drinkit.cocktail.search.Criteria;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 21:52
 */
public interface CocktailService {

    Recipe create(Recipe recipe);

    Recipe delete(int id);

    List<Recipe> findAll();

    List<Recipe> findByCriteria(Criteria criteria);

    Recipe update(Recipe recipe);

    Recipe findById(int id);

    CocktailType findCocktailTypeById(int id);

    List<CocktailType> findAllCocktailType();

    List<Ingredient> findAllIngredients();

    Ingredient findIngredientById(int id);
}
