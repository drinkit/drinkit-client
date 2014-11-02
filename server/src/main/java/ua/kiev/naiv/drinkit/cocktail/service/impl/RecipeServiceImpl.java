package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.naiv.drinkit.cocktail.common.aspect.EnableStats;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeComparatorByCriteria;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.TransformUtils;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.IngredientRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.RecipeRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.SearchSpecification;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.dto.RecipeDto;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static ua.kiev.naiv.drinkit.cocktail.persistence.model.TransformUtils.transform;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    @Resource
    private RecipeRepository recipeRepository;

    @Resource
    private IngredientRepository ingredientRepository;

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
}
