package ua.kiev.naiv.drinkit.cocktail.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import ua.kiev.naiv.drinkit.cocktail.common.DrinkitUtils;
import ua.kiev.naiv.drinkit.cocktail.common.JsonMixIn;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.service.IngredientService;
import ua.kiev.naiv.drinkit.cocktail.web.model.IngredientMixIn;

import java.util.List;

@Controller
@RequestMapping("ingredients")
public class IngredientsController {

    @Autowired
    IngredientService ingredientService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @JsonMixIn(value = IngredientMixIn.class, targetClass = Ingredient.class)
    public List<Ingredient> getIngredients() {
        return ingredientService.getIngredients();
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<Integer> addNewIngredient(@RequestBody Ingredient ingredient) {
        Assert.isNull(ingredient.getId());
        DrinkitUtils.logOperation("Creating ingredient", ingredient);
        return new HttpEntity<>(ingredientService.create(ingredient));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editIngredient(@RequestBody Ingredient ingredient, @PathVariable int id) {
        Assert.isTrue(id == ingredient.getId(), "id from uri and id from json should be identical");
        DrinkitUtils.logOperation("Updating ingredient", ingredient);
        ingredientService.update(ingredient);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        DrinkitUtils.logOperation("Deleting ingredient", id);
        ingredientService.delete(id);
    }

}
