package com.omnia.salusbackend.utils;

import com.omnia.salusbackend.ecxeptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public class JpaUtils {
    public static <V> void abstractUpdate(Map<String, Object> json, JpaRepository<V, Long> repository)  {

        Object idValue = json.get("id");
        if (idValue != null) {
            Long id = Long.valueOf(idValue.toString());
            var loadedModel = repository.findById(id).orElseThrow(() -> new NotFoundException(""));
            if (loadedModel != null) {
                for (Map.Entry<String, Object> entry : json.entrySet()) {
                    try {
                        String fieldName = entry.getKey();
                        Object fieldValue = entry.getValue();

                        var fieldClass = loadedModel.getClass().getDeclaredField(fieldName);
                        Class<?> fieldType = fieldClass.getType();


                        if (fieldValue != null && !fieldName.equals("id")) {
                            fieldClass.setAccessible(true);
                            if (fieldType.getSimpleName().equals("Long")) {fieldValue = Long.valueOf(fieldValue.toString());}
                            fieldClass.set(loadedModel, fieldValue);
                        }
                    }
                    catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }}
                repository.save(loadedModel);
            }
        }


    }
}
