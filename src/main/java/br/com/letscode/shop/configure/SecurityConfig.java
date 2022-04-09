package br.com.letscode.shop.configure;

import br.com.letscode.shop.filter.CustomAuthenticationFilter;
import br.com.letscode.shop.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/usuarios/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/produtos/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/fabricantes/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/carrinho/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/usuarios/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/produtos/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/fabricantes/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/carrinho/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/usuarios/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/produtos/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/fabricantes/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/carrinho/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/usuarios/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/produtos/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/fabricantes/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/carrinho/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
