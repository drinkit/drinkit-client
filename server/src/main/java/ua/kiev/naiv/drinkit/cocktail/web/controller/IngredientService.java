package ua.kiev.naiv.drinkit.cocktail.web.controller;

import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;

import java.util.List;

/**
 * @author pkolmykov
 */
public interface IngredientService {

    List<Ingredient> getIngredients();

    Ingredient findIngredientById(int id);

    int create(Ingredient ingredient);

    void update(Ingredient ingredient);

    void delete(int id);
}
