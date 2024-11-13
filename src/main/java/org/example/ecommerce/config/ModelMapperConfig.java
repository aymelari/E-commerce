package org.example.ecommerce.config;

import org.example.ecommerce.dto.OrderResponseDto;
import org.example.ecommerce.entity.Order;
import org.example.ecommerce.entity.OrderItem;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper ModelMapper() {

        ModelMapper modelMapper = new ModelMapper();



return modelMapper;
    }}
