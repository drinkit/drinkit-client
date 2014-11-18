package guru.drinkit.cocktail.service.impl;

import guru.drinkit.cocktail.exception.RecipesFoundException;
import guru.drinkit.cocktail.exception.RecordNotFoundException;
import guru.drinkit.cocktail.mapping.DtoMapper;
import guru.drinkit.cocktail.persistence.entity.Ingredient;
import guru.drinkit.cocktail.persistence.entity.IngredientWithQuantity;
import guru.drinkit.cocktail.persistence.repository.IngredientRepository;
import guru.drinkit.cocktail.service.IngredientService;
import guru.drinkit.cocktail.web.dto.IngredientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientService.class);

    @Autowired
    IngredientRepository ingredientRepository;
    @Resource
    private DtoMapper dtoMapper;

    @Override
    public List<IngredientDto> getIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        LOGGER.info("getIngredients: found {} records", ingredients.size());
        return dtoMapper.mapAsList(ingredients, IngredientDto.class);
    }

    @Override
    public IngredientDto getIngredientById(int id) {
        return dtoMapper.map(ingredientRepository.findOne(id), IngredientDto.class);
    }

    @Override
    public IngredientDto create(IngredientDto ingredientDto) {
        if (ingredientDto.getId() != null) {
            throw new IllegalArgumentException("create ingredient cannot have id");
        }
        return dtoMapper.map(ingredientRepository.saveAndFlush(
                dtoMapper.map(ingredientDto, Ingredient.class)), IngredientDto.class);
    }

    @Override
    public void update(IngredientDto ingredientDto) {
        if (ingredientDto.getId() == null) {
            throw new IllegalArgumentException("update ingredient should have id");
        }
        ingredientRepository.saveAndFlush(dtoMapper.map(ingredientDto, Ingredient.class));
    }

    @Override
    public void delete(int id) throws RecipesFoundException {
        Ingredient ingredient = ingredientRepository.findOne(id);
        if (ingredient == null) {
            throw new RecordNotFoundException("Ingredient not found : " + id);
        }
        Set<IngredientWithQuantity> cocktailIngredients = ingredient.getCocktailIngredients();
        if (cocktailIngredients.isEmpty()) {
            ingredientRepository.delete(id);
        } else {
            throw new RecipesFoundException(cocktailIngredients.size());
        }
    }
}
