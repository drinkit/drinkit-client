package ua.kiev.naiv.drinkit.cocktail.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import ua.kiev.naiv.drinkit.cocktail.common.DrinkitUtils;
import ua.kiev.naiv.drinkit.cocktail.common.aspect.annotation.JsonMixIn;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;
import ua.kiev.naiv.drinkit.cocktail.web.model.RecipeSearchResultMixin;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "recipes")
public class RecipeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    RecipeService recipeService;

    @RequestMapping(value = "/{recipeId}", method = RequestMethod.GET)
    @ResponseBody
    public Recipe getRecipeById(@PathVariable int recipeId) {
        return recipeService.getRecipeById(recipeId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Assert.isNull(recipe.getId());
        DrinkitUtils.logOperation("Creating recipe", recipe);
        return new HttpEntity<>(recipeService.save(recipe));
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @JsonMixIn(value = RecipeSearchResultMixin.class, targetClass = Recipe.class)
    public List<Recipe> searchRecipes(@RequestParam(value = "criteria", required = false) String json) {
        List<Recipe> recipes;
        if (json != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            Criteria criteria;
            try {
                criteria = objectMapper.readValue(json, Criteria.class);
            } catch (IOException e) {
                LOGGER.error("Bad criteria", e);
                return null;
            }
            recipes = recipeService.findByCriteria(criteria);
        } else {
            recipes = recipeService.findAll();
        }
        return recipes;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        try {
            DrinkitUtils.logOperation("Deleting recipe", id);
            recipeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "{recipeId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable int recipeId, @RequestBody Recipe recipe) {
        Assert.isTrue(recipeId == recipe.getId(), "id from uri and id from json should be identical");
        DrinkitUtils.logOperation("Updating recipe", recipe);
        recipeService.save(recipe);
    }

}
