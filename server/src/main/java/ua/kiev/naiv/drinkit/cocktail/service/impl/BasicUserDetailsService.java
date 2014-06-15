package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.kiev.naiv.drinkit.cocktail.common.DetailedUser;
import ua.kiev.naiv.drinkit.cocktail.model.User;
import ua.kiev.naiv.drinkit.cocktail.repository.UserRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 16.03.14
 * Time: 19:22
 */
@Component
public class BasicUserDetailsService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Test msg");
        }
        return new DetailedUser(user.getUsername(), user.getPassword(),
                getAuthorities(user.getAccessLevel()), user.getId(), user.getDisplayName());
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Integer accessLevel) {
        List<GrantedAuthority> roles = new ArrayList<>();
        switch (accessLevel) {
            case User.ACCESS_LVL_ADMIN:
                roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            case User.ACCESS_LVL_USER:
                roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return roles;
    }

    public boolean createUser(User user) {
        try {
            userRepository.saveAndFlush(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
