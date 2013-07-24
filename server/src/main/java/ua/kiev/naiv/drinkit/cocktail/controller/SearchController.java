package ua.kiev.naiv.drinkit.cocktail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.kiev.naiv.drinkit.cocktail.search.CriteriaPOJO;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 22.07.13
 * Time: 23:26
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping()
    @ResponseBody
    public CriteriaPOJO searchCocktails(@RequestParam(value = "criteria") String json) {


        return new CriteriaPOJO();
    }
}
