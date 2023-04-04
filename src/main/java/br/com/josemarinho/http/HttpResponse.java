package br.com.josemarinho.http;

import com.google.gson.Gson;

public interface HttpResponse {
    HttpResponse handle(HttpRequest httpRequest, java.net.http.HttpResponse<?> httpResponseMessage);
    default String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
