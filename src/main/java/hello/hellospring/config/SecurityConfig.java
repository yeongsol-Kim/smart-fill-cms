package hello.hellospring.config;

import hello.hellospring.service.MemberService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MemberService memberService;

    public SecurityConfig(MemberService memberService) {
        this.memberService = memberService;
    }
//    private final AuthSuccessHandler authSuccessHandler;
//    private final AuthFailureHandler authFailureHandler;

//    public SecurityConfig(MemberService memberService, AuthSuccessHandler authSuccessHandler, AuthFailureHandler authFailureHandler) {
//        this.memberService = memberService;
//        this.authSuccessHandler = authSuccessHandler;
//        this.authFailureHandler = authFailureHandler;
//    }

//    @Bean
//    public BCryptPasswordEncoder encryptPassword() {
//        return new BCryptPasswordEncoder();
//    }


//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(memberService);
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/login/**", "/js/**", "/css/**", "/vender/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//            .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("email")
//                .usernameParameter("password")
//                .loginProcessingUrl("/login/action")
//                .successHandler(authSuccessHandler)
//                .failureHandler(authFailureHandler)
//            .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true)
//            .and()
//                .sessionManagement()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(false)
//                .expiredUrl("/login")
//            .and()
//            .and().rememberMe()
//                .alwaysRemember(false)
//                .tokenValiditySeconds(3600)
//                .rememberMeParameter("remember-me");

        http.csrf().disable().headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                        .antMatchers("/login", "/member", "/signup").permitAll()
                        .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/")
                .and()
                    .logout()
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)

        ;
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws  Exception {
//        auth.userDetailsService(memberService)
//                .passwordEncoder(null);
//    }
}

