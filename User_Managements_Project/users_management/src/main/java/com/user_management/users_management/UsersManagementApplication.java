package com.user_management.users_management;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class UsersManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(UsersManagementApplication.class, args);
    System.out.println("");
    System.out.println("");
    System.out.println("");
    System.out.println("Server Status On.......");

    System.out.println("");
    System.out.println("	   			**         **  **********    ***");
    System.out.println("				**         **  **            ***");
    System.out.println("				**         **  **            ***");
    System.out.println("				**         **  ******        ***");
    System.out.println("				**   **    **  **            ***");
    System.out.println("				** **   ** **  **            ************");
    System.out.println("				***       ***  **********    ************");
  }

   @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
    corsConfiguration.setAllowedHeaders(
      Arrays.asList(
        "Origin",
        "Content-Type",
        "Accept",
        "Authorization",
        "X-Requested-With"
      )
    );
    corsConfiguration.setExposedHeaders(
      Arrays.asList(
        "Origin",
        "Content-Type",
        "Accept",
        "Authorization",
        "Access-Control-Allow-Origin",
        "Access-Control-Allow-Credentials"
      )
    );
    corsConfiguration.setAllowedMethods(
      Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")
    );

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);

    return new CorsFilter(source);
  }
}
