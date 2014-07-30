package ua.kiev.naiv.drinkit.cocktail.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.User;

import java.util.OptionalInt;

public class DrinkitUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger("CommonLogging");

    public static void logOperation(String operation, Object obj) {
        String info = String.format("%s: %s", operation, obj);
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            info += " by user: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        LOGGER.info(info);
    }

    public static OptionalInt getCurrentUserId() {
        Integer userId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getAuthorities().size() != 0 &&
                    authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {
                userId = 0;
            } else if (!((UserDetails) authentication.getPrincipal()).getAuthorities()
                    .contains(new SimpleGrantedAuthority(Role.ROLE_ADMIN.name()))) {
                userId = ((User) authentication.getPrincipal()).getId();
            }
        }
        return userId == null ? OptionalInt.empty() : OptionalInt.of(userId);
    }

}
