package guru.drinkit.cocktail.service.impl;

import guru.drinkit.cocktail.common.aspect.EnableStats;
import guru.drinkit.cocktail.persistence.repository.RecipeRepository;
import guru.drinkit.cocktail.persistence.search.Criteria;
import guru.drinkit.cocktail.persistence.search.SearchSpecification;
import guru.drinkit.cocktail.service.FileStoreService;
import guru.drinkit.cocktail.service.RecipeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import guru.drinkit.cocktail.mapping.DtoMapper;
import guru.drinkit.cocktail.persistence.entity.Recipe;
import guru.drinkit.cocktail.web.dto.RecipeDto;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    @Resource
    FileStoreService fileStoreService;

    @Resource
    private RecipeRepository recipeRepository;

    @Resource
    private DtoMapper dtoMapper;

    @Override
    public RecipeDto save(RecipeDto recipeDto) {
        return dtoMapper.map(recipeRepository.saveAndFlush(dtoMapper.map(recipeDto, Recipe.class)), RecipeDto.class);
    }

    @Override
    public void delete(int id) {
        recipeRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findAll() {
        return dtoMapper.mapAsList(recipeRepository.findAll(), RecipeDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findByCriteria(Criteria criteria) {
        return dtoMapper.mapAsList(recipeRepository.findAll(SearchSpecification.byCriteria(criteria)), RecipeDto.class);
    }

    @Override
    @EnableStats
    @Transactional(readOnly = true)
    public RecipeDto getRecipeById(int id) {
        return dtoMapper.map(recipeRepository.findOne(id), RecipeDto.class);
    }


    @Override
    public List<RecipeDto> findByRecipeNameContaining(String namePart) {
        return dtoMapper.mapAsList(recipeRepository.findByNameContaining(namePart), RecipeDto.class);
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
