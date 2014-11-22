package guru.drinkit.cocktail.service.impl;

import guru.drinkit.cocktail.common.Role;
import guru.drinkit.cocktail.persistence.entity.User;
import guru.drinkit.cocktail.persistence.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import guru.drinkit.cocktail.common.DetailedUser;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.stream.Collectors;

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
        return Role.getRolesByAccessLevel(accessLevel).stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    public boolean createUser(User user) {
        boolean isNew = userRepository.findByUsername(user.getUsername()) == null;
        if(isNew){
            userRepository.saveAndFlush(user);
        }
        return isNew;
    }


}
