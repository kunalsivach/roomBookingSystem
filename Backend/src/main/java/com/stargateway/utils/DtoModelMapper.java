package com.stargateway.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoModelMapper {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
