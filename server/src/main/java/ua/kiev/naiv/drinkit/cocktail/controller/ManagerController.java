package ua.kiev.naiv.drinkit.cocktail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.kiev.naiv.drinkit.cocktail.service.CocktailService;

/**
 * @author pkolmykov
 */
@Controller
@RequestMapping("manager")
public class ManagerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    CocktailService cocktailService;

    @RequestMapping("/test.html")
    public ModelAndView test() {
        return new ModelAndView("test");
    }

    @RequestMapping("/list.html")
    public ModelAndView list() {
        return new ModelAndView("list", "list", cocktailService.findAll());
    }

    @RequestMapping("/addNew.html")
    public ModelAndView create() {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("command", cocktailService.getById(1));
        modelMap.addAttribute("cocktailTypes", cocktailService.getCocktailTypes());
        LOGGER.info("recipeEditForm opened");
        return new ModelAndView("recipeEditForm", modelMap);
    }

    @RequestMapping(value = "/saveRecipe.html", method = RequestMethod.POST)
    public String save(ModelMap modelMap) {
        return "redirect:recipeEditForm";
    }
}
