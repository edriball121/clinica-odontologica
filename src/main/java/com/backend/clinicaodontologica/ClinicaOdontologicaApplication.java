package com.backend.clinicaodontologica;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableWebSecurity
public class ClinicaOdontologicaApplication {

    private static Logger logger = LoggerFactory.getLogger(ClinicaOdontologicaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClinicaOdontologicaApplication.class, args);
        logger.info("ClinicaOdontologica is now running...");
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Clinica odontologica API")
                        .version("2.7.17")
                        .description("API enfocada en una clínica odontológica que permite gestionar información sobre odontólogos y pacientes, así como registrar turnos.")
                        .termsOfService("https://swagger.io/docs/"));
    }


}
