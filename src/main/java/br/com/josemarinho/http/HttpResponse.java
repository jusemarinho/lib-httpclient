package br.com.josemarinho.http;

public interface HttpResponse {
    HttpResponse handle(HttpRequest httpRequest, java.net.http.HttpResponse<?> httpResponseMessage);
}
