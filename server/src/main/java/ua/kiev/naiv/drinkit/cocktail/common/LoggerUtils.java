package ua.kiev.naiv.drinkit.cocktail.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggerUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger("CommonLogging");

    public static void logOperation(String operation, Object obj) {
        String info = String.format("%s: %s", operation, obj);
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            info += " by user: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        LOGGER.info(info);
    }
}
