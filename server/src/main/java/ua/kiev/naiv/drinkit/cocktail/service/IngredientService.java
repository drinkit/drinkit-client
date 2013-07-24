package ua.kiev.naiv.drinkit.cocktail.service;

import ua.kiev.naiv.drinkit.cocktail.model.Ingredient;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 21.07.13
 * Time: 20:09
 */
public interface IngredientService {

    public List<Ingredient> getAllIngredients();

    Ingredient getById(int id);
}
