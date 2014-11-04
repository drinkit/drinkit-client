package ua.kiev.naiv.drinkit.cocktail.persistence.model;

import org.springframework.dao.EmptyResultDataAccessException;
import ua.kiev.naiv.drinkit.cocktail.persistence.repository.IngredientRepository;
import ua.kiev.naiv.drinkit.cocktail.web.dto.RecipeDto;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TransformUtils {


    public static RecipeDto transform(Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(recipe.getId());
        recipeDto.setImageUrl("/rest/media/image/" + recipe.getImageFileName());
        recipeDto.setThumbnailUrl("/rest/media/thumbnail/" + recipe.getThumbnailFileName());
        recipeDto.setCocktailTypeId(recipe.getCocktailType().getId());
        recipeDto.setDescription(recipe.getDescription());
        recipeDto.setName(recipe.getName());
        recipeDto.setCocktailIngredients(recipe.getIngredientsWithQuantities().stream()
                .<Integer[]>map(val -> new Integer[]{val.getIngredient().getId(), val.getQuantity()})
                .toArray(Integer[][]::new));
        recipeDto.setOptions(recipe.getOptions().stream()
                .mapToInt(Option::getId)
                .toArray());
//        if (recipe.getRecipeStatistics() != null) {
//            processStatistics(recipe.getRecipeStatistics(), recipeDto);
//        }
        return recipeDto;
    }

//    private static void processStatistics(List<RecipeStatistics> recipeStatistics, RecipeDto recipeDto) {
//        recipeDto.setViews(recipeStatistics
//                .stream().mapToInt(RecipeStatistics::getViews).sum());
//        recipeStatistics
//                .stream().filter(val -> val.getRating() != null)
//                .mapToInt(RecipeStatistics::getRating).average().ifPresent(
//                recipeDto::setRating);
//        recipeDto.setVotes((int) recipeStatistics
//                .stream().filter(val -> val.getRating() != null).count());
//    }

    public static Recipe transform(RecipeDto recipeDto, IngredientRepository ingredientRepository) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeDto.getId());
        recipe.setName(recipeDto.getName());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setOptions(Arrays.stream(recipeDto.getOptions()).<Option>mapToObj(Option::new).collect(Collectors.toList()));
        recipe.setCocktailType(new CocktailType(recipeDto.getCocktailTypeId()));
        recipe.setIngredientsWithQuantities(Arrays.stream(recipeDto.getCocktailIngredients()).<IngredientWithQuantity>map(val -> {
            IngredientWithQuantity ingredientWithQuantity = new IngredientWithQuantity();
            ingredientWithQuantity.setQuantity(val[1]);
            ingredientWithQuantity.setrecipe(recipe);
            Ingredient ingredientById = ingredientRepository.findOne(val[0]);
            if (ingredientById == null) throw new EmptyResultDataAccessException(1);
            ingredientById.getCocktailIngredients().add(ingredientWithQuantity);
            ingredientWithQuantity.setIngredient(ingredientById);
            return ingredientWithQuantity;
        }).collect(Collectors.toList()));
//        recipe.setImageUrl(recipeDto.getImageUrl());
//        recipe.setThumbnailUrl(recipeDto.getThumbnailUrl());
        return recipe;
    }

}
