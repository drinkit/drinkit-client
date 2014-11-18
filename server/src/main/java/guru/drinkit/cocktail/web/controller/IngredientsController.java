package guru.drinkit.cocktail.web.controller;

import guru.drinkit.cocktail.common.DrinkitUtils;
import guru.drinkit.cocktail.service.IngredientService;
import guru.drinkit.cocktail.web.dto.IngredientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("ingredients")
public class IngredientsController {

    @Autowired
    IngredientService ingredientService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<IngredientDto> getIngredients() {
        return ingredientService.getIngredients();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewIngredient(@RequestBody IngredientDto ingredientDto) {
        Assert.isNull(ingredientDto.getId());
        DrinkitUtils.logOperation("Creating ingredient", ingredientDto);
        ingredientService.create(ingredientDto);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Updated")
    public void editIngredient(@RequestBody IngredientDto ingredientDto, @PathVariable int id) {
        Assert.isTrue(id == ingredientDto.getId(), "id from uri and id from json should be identical");
        DrinkitUtils.logOperation("Updating ingredient", ingredientDto);
        ingredientService.update(ingredientDto);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Deleted")
    public void delete(@PathVariable int id) {
        DrinkitUtils.logOperation("Deleting ingredient", id);
        ingredientService.delete(id);
    }

}
