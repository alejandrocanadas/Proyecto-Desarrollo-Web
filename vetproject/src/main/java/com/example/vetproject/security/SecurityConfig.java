package com.example.vetproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Decir que en esta clase se van a crear beans
@Configuration
@EnableWebSecurity//aca compilo todo lo que se relaciona con seguridad
public class SecurityConfig {
// clase parafiltros de quien se puede conectar a nuestras APIs
    @Autowired
    private JwtAuthEntityPoint jwtAuthEntryPoint; 
    
    @Bean//crear un objeto que spring va a manejar dentro de su manejador de objetos, cuando se necesite este lo saca y lo usa
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http.
                csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests(requests -> requests
                    // Rutas públicas - accesibles para todos sin autenticación
                    .requestMatchers("/h2-vetConsole/**").permitAll()
                    .requestMatchers("/clientes/login").permitAll()
                    .requestMatchers("/veterinario/login").permitAll()
                    .requestMatchers("/admin/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/clientes/add").permitAll() // Asumimos que el registro de cliente es público

                    // Rutas específicas por rol

                    // ADMIN: Acceso completo a la mayoría de las operaciones de gestión
                    .requestMatchers("/admin/**").hasAuthority("ADMIN") // Estadísticas y cualquier otra ruta de admin
                    .requestMatchers("/clientes/all").hasAnyAuthority("VETERINARIO","ADMIN") // Ver todos los clientes
                    .requestMatchers("/mascotas/all").hasAnyAuthority("VETERINARIO","ADMIN") // Ver todos los clientes

                    .requestMatchers("/clientes/update/**").hasAuthority("ADMIN") // Actualizar clientes (cualquier cliente)
                    .requestMatchers("/clientes/delete/**").hasAuthority("ADMIN") // Eliminar clientes (cualquier cliente)
                    .requestMatchers("/veterinario").hasAuthority("ADMIN") // Ver todos los veterinarios
                    .requestMatchers(HttpMethod.POST, "/veterinario").hasAuthority("ADMIN") // Añadir veterinario
                    .requestMatchers(HttpMethod.PUT, "/veterinario/{id}").hasAuthority("ADMIN") // Actualizar veterinario
                    .requestMatchers(HttpMethod.DELETE, "/veterinario/{id}").hasAuthority("ADMIN") // Eliminar veterinario
                    .requestMatchers(HttpMethod.GET, "/mascotas/**").hasAnyAuthority("ADMIN","VETERINARIO") // Ver mascotas (todas, por cliente, por id)
                    .requestMatchers(HttpMethod.DELETE, "/mascotas/delete/**").hasAuthority("ADMIN") // Eliminar mascotas
                    .requestMatchers("/api/medicamentos/**").hasAnyAuthority("ADMIN", "VETERINARIO") // Todas las operaciones de medicamentos
                    .requestMatchers(HttpMethod.GET, "/api/tratamientos/**").hasAnyAuthority("ADMIN", "VETERINARIO") // Ver tratamientos (todos, por mascota, por veterinario)

                    // VETERINARIO: Puede gestionar tratamientos y mascotas, y ver su información
                    .requestMatchers("/veterinario/find/**").hasAnyAuthority("VETERINARIO", "ADMIN") // Veterinario puede verse a sí mismo (y ADMIN puede ver a cualquiera)
                    .requestMatchers("/veterinario/tratamientos/**").hasAnyAuthority("VETERINARIO", "ADMIN") // Veterinario puede ver sus tratamientos (y ADMIN los de cualquiera)
                    .requestMatchers(HttpMethod.POST, "/mascotas/add").hasAnyAuthority("VETERINARIO", "ADMIN") // Añadir mascota
                    .requestMatchers(HttpMethod.POST, "/mascotas/update").hasAnyAuthority("VETERINARIO", "ADMIN") // Actualizar mascota
                    .requestMatchers(HttpMethod.POST, "/api/tratamientos").hasAuthority("VETERINARIO") // Solo Veterinarios pueden añadir tratamiento
                    .requestMatchers("/veterinario/me").hasAnyAuthority("VETERINARIO", "ADMIN")
                    // Si hay un endpoint PUT para tratamientos, añadirlo aquí con .hasAnyAuthority("VETERINARIO", "ADMIN")

                    // CLIENTE: Solo puede ver su información y la de sus mascotas/tratamientos después de autenticarse
                    // NOTA: La lógica para restringir a "su propio" {id} debe estar en el controlador. Aquí solo aseguramos que estén autenticados y tengan el rol.
                    .requestMatchers("/clientes/find/**").hasAnyAuthority("CLIENTE", "ADMIN") // Cliente puede ver su detalle (y ADMIN cualquiera)
                    .requestMatchers("/mascotas/cliente/**").hasAnyAuthority("CLIENTE", "ADMIN") // Cliente puede ver sus mascotas (y ADMIN las de cualquier cliente)
                    .requestMatchers("/api/tratamientos/mascota/**").hasAnyAuthority("CLIENTE", "ADMIN", "VETERINARIO") // Cliente puede ver tratamientos de sus mascotas (y ADMIN de cualquier mascota)
                    .requestMatchers("/mascotas/find/**").hasAnyAuthority("CLIENTE", "ADMIN", "VETERINARIO")
                    .requestMatchers("/clientes/me").hasAnyAuthority("CLIENTE", "ADMIN")
                    
                    .anyRequest().permitAll() // Considera cambiar a .denyAll() o .authenticated() si quieres mayor seguridad por defecto
                )
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint));

                http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    //Contraseñas encriptadas
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration
    ) throws Exception{
        return authenticationConfiguration.getAuthenticationManager(); 
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }
}
