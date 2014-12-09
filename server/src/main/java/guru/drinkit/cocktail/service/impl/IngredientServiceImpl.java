package guru.drinkit.cocktail.service.impl;

import guru.drinkit.cocktail.exception.RecipesFoundException;
import guru.drinkit.cocktail.persistence.repository.IngredientRepository;
import guru.drinkit.cocktail.service.IngredientService;
import guru.drinkit.cocktail.web.dto.IngredientDto;
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
    public List<IngredientDto> getIngredients() {
//        LOGGER.info("getIngredients: found {} records", ingredients.size());
        return ingredientRepository.findAll();
    }

    @Override
    public IngredientDto getIngredientById(int id) {
        return ingredientRepository.findOne(id);
    }

    @Override
    public IngredientDto save(IngredientDto ingredientDto) {
        if (ingredientDto.getId() == null) {
            Integer lastId = ingredientRepository.findFirstByOrderByIdDesc().getId();
            ingredientDto.setId(lastId == null ? 1 : ++lastId);
        }
        return ingredientRepository.save(ingredientDto);
    }

    @Override
    public void delete(int id) throws RecipesFoundException {
        ingredientRepository.delete(id);//todo RecipesFoundException
    }
}
