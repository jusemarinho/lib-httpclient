package br.com.josemarinho.http;

public interface HttpResponse {
    <T extends HttpResponse> T handle(HttpRequest httpRequest, java.net.http.HttpResponse<?> httpResponseMessage);
}
