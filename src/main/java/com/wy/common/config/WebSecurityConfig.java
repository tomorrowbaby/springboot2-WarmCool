package com.wy.common.config;

import com.wy.common.handler.CustomAuthenticationFailureHandler;
import com.wy.common.handler.CustomAuthenticationSuccessHandler;
import com.wy.common.handler.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.cors.CorsUtils;

/**
 * 描述：Security 配置类
 * @author wangyu
 * @date 2019/5/19
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;


    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;


    @Autowired
        UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {



            //路由策略和访问权限的简单配置
            http
                    .formLogin()
                    .loginPage("/authentication/require")
                    .loginProcessingUrl("/login/auth")
                    .successHandler(customAuthenticationSuccessHandler)
                    .failureHandler(customAuthenticationFailureHandler)
                    .and().logout().deleteCookies("remove").invalidateHttpSession(false)
                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/login/**","/authentication/require")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .csrf().disable().cors()
            ;



        }

        /**
         * 描述：配置内存用户
         * @param auth
         * @throws Exception
         */

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .userDetailsService(userDetailsService);
        }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
