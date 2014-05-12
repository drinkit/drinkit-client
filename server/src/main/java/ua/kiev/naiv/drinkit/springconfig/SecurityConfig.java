package ua.kiev.naiv.drinkit.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import ua.kiev.naiv.drinkit.cocktail.service.impl.BasicUserDetailsService;
import ua.kiev.naiv.drinkit.security.CustomDigestAuthenticationEntryPoint;

/**
 * @author pkolmykov
 */
@Configuration
@EnableWebSecurity()
public class SecurityConfig extends WebSecurityConfigurerAdapter {


//    @Override
//    protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService());
//    }

//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return userDetailsService();
//    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new BasicUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().authenticationEntryPoint(digestAuthenticationEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .antMatcher("/rest/**")
                .addFilterAfter(digestAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/rest/user/**").authenticated()
                .antMatchers("/rest/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
        ;
    }

    @Bean
    public DigestAuthenticationFilter digestAuthenticationFilter() {
        DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
        digestAuthenticationFilter.setAuthenticationEntryPoint(digestAuthenticationEntryPoint());
        digestAuthenticationFilter.setUserDetailsService(userDetailsService());
        return digestAuthenticationFilter;
    }

    @Bean
    public DigestAuthenticationEntryPoint digestAuthenticationEntryPoint() {
        DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new CustomDigestAuthenticationEntryPoint();
        digestAuthenticationEntryPoint.setRealmName("DrinkIt");
        digestAuthenticationEntryPoint.setKey("boozinga");//todo move it to property file
        return digestAuthenticationEntryPoint;
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.
//    }
}
