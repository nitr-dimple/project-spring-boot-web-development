package com.neu.dimple.houserentalapplication.config;

import com.neu.dimple.houserentalapplication.controller.HouseController;
import com.neu.dimple.houserentalapplication.controller.UserLoginController;
import com.neu.dimple.houserentalapplication.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    Logger logger = LoggerFactory.getLogger(HouseController.class);

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.info("Inside filter method");

        return http
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/user/addPost.htm*","/user/viewHouse.htm",
                        "/user/addHouse.htm", "/user/deleteHouse.htm", "/user/updateHouse.htm",
                        "/user/addHousePhoto.htm", "/user/deleteHousePhoto.htm", "/user/addResidence.htm",
                        "/user/viewResidence.htm", "/user/deleteResidence.htm", "/user/updateResidence.htm",
                        "/user/addResidencePhoto.htm", "/user/deleteResidencePhoto.htm", "/user/viewPost.htm",
                        "/user/viewVisitBooking.htm", "/user/viewVisits.htm","/user/markVisitComplete.htm",
                        "/user/scheduleVisitTour.htm"
                )
                .hasAnyAuthority("owner", "admin")
                .antMatchers("/user/viewUser.htm", "/user/updateUser.htm", "/user/deleteUser.htm",
                        "/user/visitHouse.htm")
                .hasAnyAuthority("owner", "admin", "seeker")
                .antMatchers("/", "/images/**", "/user/explore.htm", "/user/add.htm", "/user/verifyUserEmail/*")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/user/login.htm")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout.htm"))
                .logoutSuccessUrl("/user/login.htm?logout")
                .permitAll()
                .and()
                .build()
                ;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(customUserDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/")
//                .antMatchers("/images**");
//    }

}
