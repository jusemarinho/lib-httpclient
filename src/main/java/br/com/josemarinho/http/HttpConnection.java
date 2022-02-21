package br.com.josemarinho.http;

import org.springframework.http.HttpMethod;

import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.ArrayList;

public class HttpConnection {

    private HttpClient httpClient;

    public HttpConnection() {
        this.httpClient = HttpClient.newBuilder().build();
    }

    public <T extends HttpRequest, O extends HttpResponse> O doRequest(T request, O interfaceHttpResponse) throws Exception {

        java.net.http.HttpRequest httpRequest = null;
        java.net.http.HttpResponse<?> httpResponse = null;
        var listaHeaders = new ArrayList<String>();

        var uri = URI.create(request.getUriRelative());

        if (request.getHeaders() != null) {
            for (var header : request.getHeaders().entrySet()) {
                listaHeaders.add(header.getKey());
                listaHeaders.add(header.getValue());
            }
        }

        if (request.getStringMediaTypeMap() != null) {
            for (var header : request.getStringMediaTypeMap().entrySet()) {
                listaHeaders.add(header.getKey());
                listaHeaders.add(header.getValue().toString());
            }
        }

        var arrayHeaders = listaHeaders.toArray(new String[0]);

        if (request.getHttpMethod() == HttpMethod.GET) {
            httpRequest = java.net.http.HttpRequest.newBuilder()
                    .uri(uri)
                    .headers(arrayHeaders)
                    .GET()
                    .timeout(Duration.ofMillis(request.getTimeout()))
                    .build();
            httpResponse = httpClient.send(httpRequest, java.net.http.HttpResponse.BodyHandlers.ofString());

        } else if (request.getHttpMethod() == HttpMethod.POST) {
            httpRequest = java.net.http.HttpRequest.newBuilder()
                    .uri(uri)
                    .headers(arrayHeaders)
                    .POST(java.net.http.HttpRequest.BodyPublishers.ofString(request.getPayload()))
                    .timeout(Duration.ofMillis(request.getTimeout()))
                    .build();




            httpResponse = httpClient.send(httpRequest, java.net.http.HttpResponse.BodyHandlers.ofString());
        }
        else {
            throw new Exception();
        }


        Class<?> clazz = Class.forName(interfaceHttpResponse.getClass().getName());
        Constructor<?> ctor = clazz.getConstructor();
        Object object = ctor.newInstance();
        return (O) ((O) object).handle(request, httpResponse);
    }
}
