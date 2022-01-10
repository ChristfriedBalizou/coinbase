package com.coinbase.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * The goal of this class is to convert from or to json
 * base on the class extending it.
 **/
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Serializer {

    public String toJson() {
        // This function will convert the instance to a json string
        try {
            return (new ObjectMapper()).writeValueAsString(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Object fromJson(String json, Class<?> klass) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.registerModule(new JavaTimeModule());

            return mapper.readValue(json, klass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
