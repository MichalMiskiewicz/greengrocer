package pl.miskiewiczmichal.greengrocerapi.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService jwtUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(List.of("*"));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/authenticate", "/users/add").permitAll()
                .antMatchers(HttpMethod.POST, "/products/add").hasAuthority("ROLE_Admin")
                .antMatchers(HttpMethod.POST, "/products/upload").hasAuthority("ROLE_Admin")
                .antMatchers(HttpMethod.PATCH, "/products/amount/{product_id}/{product_amount}").hasAuthority("ROLE_Admin")
                .antMatchers(HttpMethod.POST, "/users/add").hasAuthority("ROLE_Admin")
                .antMatchers(HttpMethod.GET, "/orders/all").hasAuthority("ROLE_Admin")
                .antMatchers(HttpMethod.GET, "/users/drivers/all/").hasAuthority("ROLE_Admin")
                .antMatchers(HttpMethod.PATCH, "/orders/{order_id}/status-change").hasAuthority("ROLE_Admin")
                .antMatchers(HttpMethod.PATCH, "/orders/{order_id}/status-change").hasAuthority("ROLE_Kierowca")
                .antMatchers(HttpMethod.PATCH, "/orders/{order_id}/driver-set/{driver_id}").hasAuthority("ROLE_Admin")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}