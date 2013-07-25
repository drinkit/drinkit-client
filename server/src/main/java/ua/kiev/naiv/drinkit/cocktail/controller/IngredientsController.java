package ua.kiev.naiv.drinkit.cocktail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.kiev.naiv.drinkit.cocktail.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 21.07.13
 * Time: 20:47
 */
@Controller
@RequestMapping("ingredients")
public class IngredientsController {

    @Autowired
    CocktailService cocktailService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Ingredient> findAll(){
        return cocktailService.findAllIngredients();
    }
}
