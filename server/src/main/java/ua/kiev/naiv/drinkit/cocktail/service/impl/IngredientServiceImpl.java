package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.IngredientRepository;
import ua.kiev.naiv.drinkit.cocktail.service.IngredientService;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientService.class);

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        LOGGER.info("getIngredients: found {} records", ingredients.size());
        return ingredients;
    }

    @Override
    public Ingredient getIngredientById(int id) {
        return ingredientRepository.findOne(id);
    }

    @Override
    public int create(Ingredient ingredient) {
        if(ingredient.getId() != null){
            throw new IllegalArgumentException("create ingredient cannot have id");
        }
        return ingredientRepository.saveAndFlush(ingredient).getId();
    }

    @Override
    public void update(Ingredient ingredient) {
        if(ingredient.getId() == null){
            throw new IllegalArgumentException("update ingredient should have id");
        }
        ingredientRepository.saveAndFlush(ingredient);
    }

    @Override
    public void delete(int id) {
        ingredientRepository.delete(id);
    }
}
