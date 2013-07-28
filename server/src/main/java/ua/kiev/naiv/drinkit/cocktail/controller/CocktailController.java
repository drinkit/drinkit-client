package ua.kiev.naiv.drinkit.cocktail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.kiev.naiv.drinkit.cocktail.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.model.Recipe;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 21.07.13
 * Time: 13:54
 */
@Controller
@RequestMapping(value = "cocktail")
public class CocktailController {

    @Autowired
    CocktailService cocktailService;

    @RequestMapping("/findById/{id}")
    @ResponseBody
    public Recipe getById(@PathVariable int id) {
        return cocktailService.findById(id);
    }

    @RequestMapping("/ingredients")
    @ResponseBody
    public List<Ingredient> findIngredients() {
        return cocktailService.findAllIngredients();
    }

    @RequestMapping("/search/")
    @ResponseBody
    public List<Recipe> searchCocktails(@RequestParam(value = "criteria") String json) {

        System.out.println(json);
        return Arrays.asList(cocktailService.findById(1));
    }

}
