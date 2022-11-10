package com.example.grupo.belmg.paginavideojuegos.seguridad;


import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private ImplementacionServicioUsuario servicioUsuario;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // nuestro servicio usuarios va a ser un servicio de detalles que nos proovee de un metodo importante que nos sirve para la busqueda de usuarios
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService((UserDetailsService) servicioUsuario);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    //este valida si los datos obtenidos por authenticationProvider son validos o no
    public AuthenticationManagerBuilder configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
        return  auth;
    }


    //en esta clase lo que estamos haciendo es validar el login de los usuarios y validando un cerrar sesion
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers(
                "/usuarios/registro**", "/css/**", "/img/**").permitAll()
                .antMatchers("/usuarios/rgtAdmins").hasRole("SUPERADMIN")
                .antMatchers("/usuarios/eliminarAdmin").hasRole("SUPERADMIN")
                .antMatchers("/crudVideojuego").hasAnyRole("ADMIN","SUPERADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/usuarios/login")
                .permitAll()
                .defaultSuccessUrl("/categoria/inicio")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/usuarios/logout"))
                .logoutSuccessUrl("/usuarios/login?logout")
                .permitAll();
        return http.build();
    }

    

}
