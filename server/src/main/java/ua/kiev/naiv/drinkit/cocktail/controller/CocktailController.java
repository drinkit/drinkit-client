package ua.kiev.naiv.drinkit.cocktail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.kiev.naiv.drinkit.cocktail.model.Cocktail;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;

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

    @RequestMapping("/{id}")
    @ResponseBody
    public Cocktail getById(@PathVariable int id){
        return cocktailService.findById(id);
    }
}
