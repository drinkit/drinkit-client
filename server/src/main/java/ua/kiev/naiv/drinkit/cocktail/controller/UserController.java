package ua.kiev.naiv.drinkit.cocktail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.kiev.naiv.drinkit.cocktail.model.User;
import ua.kiev.naiv.drinkit.cocktail.service.impl.BasicUserDetailsService;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 16.03.14
 * Time: 23:03
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    BasicUserDetailsService basicUserDetailsService;

    @RequestMapping("/getInfo")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public Object getCurrentUserInfo() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(method = RequestMethod.PUT, value = "/register")
    public ResponseEntity<User> registerUser(@RequestParam String email, @RequestParam String password, @RequestParam String displayName) {
        User user = new User();
        user.setUsername(email);
        user.setPassword(password);
        user.setDisplayName(displayName);
        user.setAccessLevel(9);//todo
        boolean created = basicUserDetailsService.createUser(user);
        return new ResponseEntity<>(created ? HttpStatus.CREATED : HttpStatus.FORBIDDEN);
    }

}
