package com.tmh.practice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.jackson.ObjectMapperCustomizer;
import jakarta.inject.Singleton;

import java.text.SimpleDateFormat;


@Singleton
public class RegisterCustomModuleCustomizer implements ObjectMapperCustomizer {

  public void customize(ObjectMapper mapper) {
    JavaTimeModule module = new JavaTimeModule();
    DateSerializer dateSerializer = new DateSerializer(false,
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    module.addSerializer(java.util.Date.class, dateSerializer);
    mapper.registerModule(module);
  }
}