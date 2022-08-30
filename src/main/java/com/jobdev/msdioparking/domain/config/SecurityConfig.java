package com.jobdev.msdioparking.domain.config;

import com.jobdev.msdioparking.domain.setting.AppSecurityInMemoryUserSetting;
import com.jobdev.msdioparking.domain.setting.SpringDocSwaggerUiSetting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppSecurityInMemoryUserSetting appSecurityInMemoryUserSetting;
    private final SpringDocSwaggerUiSetting springDocSwaggerUiSetting;

    public SecurityConfig(AppSecurityInMemoryUserSetting appSecurityInMemoryUserSetting, SpringDocSwaggerUiSetting springDocSwaggerUiSetting) {
        this.appSecurityInMemoryUserSetting = appSecurityInMemoryUserSetting;
        this.springDocSwaggerUiSetting = springDocSwaggerUiSetting;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(appSecurityInMemoryUserSetting.getName())
                .password(passwordEncoder().encode(appSecurityInMemoryUserSetting.getPassword()))
                .roles(appSecurityInMemoryUserSetting.getRoles())
                .and().passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(springDocSwaggerUiSetting.getPath()).permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .anyRequest().authenticated().and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
