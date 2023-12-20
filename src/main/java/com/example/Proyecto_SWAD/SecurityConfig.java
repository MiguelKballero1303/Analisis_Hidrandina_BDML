
package com.example.Proyecto_SWAD;
import com.example.Proyecto_SWAD.Componentes.AccesoDenegado;
import com.example.Proyecto_SWAD.Servicios.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .requestMatchers("/Login").permitAll() // Permitir el acceso a la página de login sin autenticación
                    .requestMatchers("/Menu").authenticated() // Requiere autenticación para la página de menú
                    .anyRequest().permitAll() // Permitir acceso a otras rutas sin autenticación
                .and()
                .formLogin()
                    .loginPage("/Login") // Especificar la página de login
                    .loginProcessingUrl("/Login") // Especificar la URL de procesamiento del formulario de login
                    .defaultSuccessUrl("/Menu") // Página de éxito después de iniciar sesión
                .and()
                .logout()
                    .logoutSuccessUrl("/Login") // Página a la que redirigir después de cerrar sesión
                .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler())
                .and()
                .csrf().disable(); // Deshabilitar CSRF por simplicidad, puedes habilitarlo según tus necesidades

        return http.build();
    }
    
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccesoDenegado(); // Implementa tu clase CustomAccessDeniedHandler
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        //PasswordEncoder encoder = null;
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}

