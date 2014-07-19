package ua.kiev.naiv.drinkit.cocktail.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pkolmykov
 */
public enum Role {

    ROLE_ADMIN(0), ROLE_USER(9);
    private final int accessLevel;

    Role(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public static List<Role> getRolesByAccessLevel(Integer accessLevel) {
        return Arrays.stream(Role.values())
                .filter(role -> role.accessLevel >= accessLevel)
                .collect(Collectors.toList());
    }

}
