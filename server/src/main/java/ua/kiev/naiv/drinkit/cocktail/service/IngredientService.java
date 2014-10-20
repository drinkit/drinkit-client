package ua.kiev.naiv.drinkit.cocktail.service;

import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> getIngredients();

    Ingredient getIngredientById(int id);

    int create(Ingredient ingredient);

    void update(Ingredient ingredient);

    void delete(int id);
}
