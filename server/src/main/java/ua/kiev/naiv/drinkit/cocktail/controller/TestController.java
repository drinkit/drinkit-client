package ua.kiev.naiv.drinkit.cocktail.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 16.03.14
 * Time: 21:41
 */
@Controller
public class TestController {

    @RequestMapping("/user/getInfo")
    @ResponseBody
    public Object getIngredients() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
