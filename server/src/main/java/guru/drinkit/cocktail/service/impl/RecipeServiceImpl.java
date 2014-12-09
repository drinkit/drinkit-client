package guru.drinkit.cocktail.service.impl;

import guru.drinkit.cocktail.common.aspect.EnableStats;
import guru.drinkit.cocktail.persistence.repository.RecipeRepository;
import guru.drinkit.cocktail.persistence.search.Criteria;
import guru.drinkit.cocktail.service.FileStoreService;
import guru.drinkit.cocktail.service.RecipeService;
import guru.drinkit.cocktail.web.dto.RecipeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public RecipeDto save(RecipeDto recipeDto) {
        if (recipeDto.getId() == null) {
            RecipeDto lastRecipe = recipeRepository.findFirstByOrderByIdDesc();
            recipeDto.setId(lastRecipe == null ? 1 : lastRecipe.getId() + 1);
        }
        return recipeRepository.save(recipeDto);
    }

    @Override
    public void delete(int id) {
        recipeRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findByCriteria(Criteria criteria) {
        return recipeRepository.findByCriteria(criteria);
    }

    @Override
    @EnableStats
    @Transactional(readOnly = true)
    public RecipeDto getRecipeById(int id) {
        return recipeRepository.findOne(id);
    }


    @Override
    public List<RecipeDto> findByRecipeNameContaining(String namePart) {
        return recipeRepository.findByNameContainingIgnoreCase(namePart);
    }


    @Override
    @Transactional
    public void saveMedia(int recipeId, byte[] image, byte[] thumbnail) throws IOException {
        RecipeDto recipe = recipeRepository.findOne(recipeId);
        recipe.setImageUrl(fileStoreService.getUrl(fileStoreService.save(recipeId, image, "image")));
        recipe.setThumbnailUrl(fileStoreService.getUrl(fileStoreService.save(recipeId, thumbnail, "thumbnail")));
        recipeRepository.save(recipe);
    }
}
