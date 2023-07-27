package com.example.makersprojectbackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MakersProjectBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakersProjectBackendApplication.class, args);
    }

//<<<<<<< HEAD
//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
//=======
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

//>>>>>>> adelina
}
