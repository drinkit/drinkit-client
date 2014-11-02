package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.naiv.drinkit.cocktail.common.aspect.EnableStats;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Recipe;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeComparatorByCriteria;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.TransformUtils;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.IngredientRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.RecipeRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.SearchSpecification;
import ua.kiev.naiv.drinkit.cocktail.service.FileStoreService;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.dto.RecipeDto;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static ua.kiev.naiv.drinkit.cocktail.persistence.model.TransformUtils.transform;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    @Resource
    FileStoreService fileStoreService;

    @Resource
    private RecipeRepository recipeRepository;

    @Resource
    private IngredientRepository ingredientRepository;

    @Resource
    private Environment environment;

    @Override
    public RecipeDto save(RecipeDto recipeDto) {
        return transform(recipeRepository.saveAndFlush(transform(recipeDto, ingredientRepository)));
    }

    @Override
    public void delete(int id) {
        recipeRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findAll() {
        return recipeRepository.findAll().stream()
                .map(TransformUtils::transform)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findByCriteria(Criteria criteria) {
        return recipeRepository.findAll(SearchSpecification.byCriteria(criteria)).stream()
                .sorted(new RecipeComparatorByCriteria(criteria))
                .map(TransformUtils::transform)
                .collect(Collectors.toList());
    }

    @Override
    @EnableStats
    @Transactional(readOnly = true)
    public RecipeDto getRecipeById(int id) {
        return transform(recipeRepository.findOne(id));
    }


    @Override
    public List<RecipeDto> findByRecipeNameContaining(String namePart) {
        return recipeRepository.findByNameContaining(namePart).stream()
                .map(TransformUtils::transform)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void saveMedia(int recipeId, byte[] image, byte[] thumbnail) throws IOException {
        Recipe recipe = recipeRepository.findOne(recipeId);
        recipe.setImageFileName(fileStoreService.save(recipeId, image, "image"));
        recipe.setThumbnailFileName(fileStoreService.save(recipeId, thumbnail, "thumbnail"));
        recipeRepository.saveAndFlush(recipe);
    }
}
