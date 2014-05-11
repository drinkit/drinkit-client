package ua.kiev.naiv.drinkit.cocktail.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 16.03.14
 * Time: 23:03
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("/getInfo")
    @ResponseBody
    public Object getCurrentUserInfo() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
