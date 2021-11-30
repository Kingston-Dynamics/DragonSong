package com.kingstondynamics.dragonsong.api.util.response;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class ResponseUtils {
    
    // * CODE SAMPLE *
    // Type type = new TypeToken<GenericResponse<CustomObject>>(){}.getType();
    // GenericResponse<CustomObject> body = parseGenericResponse(json, type);
    public static <T> GenericResponse<T> parse(String json, Type type) {
        return new Gson().fromJson(json, type);
    }
}
