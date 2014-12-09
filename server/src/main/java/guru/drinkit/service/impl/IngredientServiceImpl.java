package guru.drinkit.service.impl;

import guru.drinkit.domain.Ingredient;
import guru.drinkit.exception.RecipesFoundException;
import guru.drinkit.repository.IngredientRepository;
import guru.drinkit.service.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientService.class);

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> getIngredients() {
//        LOGGER.info("getIngredients: found {} records", ingredients.size());
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getIngredientById(int id) {
        return ingredientRepository.findOne(id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        if (ingredient.getId() == null) {
            Integer lastId = ingredientRepository.findFirstByOrderByIdDesc().getId();
            ingredient.setId(lastId == null ? 1 : ++lastId);
        }
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void delete(int id) throws RecipesFoundException {
        ingredientRepository.delete(id);//todo RecipesFoundException
    }
}
