package ua.kiev.naiv.drinkit.springconfig;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ua.kiev.naiv.drinkit.cocktail.common.WebContextFilter;

import javax.servlet.Filter;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 28.07.13
 * Time: 19:17
 */
@Order(1)
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/rest/*"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new WebContextFilter(), new ShallowEtagHeaderFilter()};
    }

}
