package br.com.josemarinho.http;

import com.google.gson.Gson;
import org.apache.commons.codec.Charsets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JsonHelper<T> {

    private Gson gson;

    public JsonHelper() {
        this.gson = new Gson();
    }

    public String serializeToJson(T objeto) {
        return gson.toJson(objeto);
    }

    public T deserializeFromString(String json, Class<T> jsonClassType) {
        return this.gson.fromJson(json, jsonClassType);
    }
}
