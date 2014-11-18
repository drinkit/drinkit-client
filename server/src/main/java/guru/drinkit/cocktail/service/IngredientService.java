package guru.drinkit.cocktail.service;

import guru.drinkit.cocktail.exception.RecipesFoundException;
import guru.drinkit.cocktail.web.dto.IngredientDto;

import java.util.List;

public interface IngredientService {

    List<IngredientDto> getIngredients();

    IngredientDto getIngredientById(int id);

    IngredientDto create(IngredientDto ingredient);

    void update(IngredientDto ingredient);

    void delete(int id) throws RecipesFoundException;
}
