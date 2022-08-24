package org.microframework.java.generic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.microframework.java.generate.IdGenerate;

public class E {
    public void foo() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readValue("", IdGenerate.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
