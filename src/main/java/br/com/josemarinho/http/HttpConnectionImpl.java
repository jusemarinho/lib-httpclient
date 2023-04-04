package br.com.josemarinho.http;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.ArrayList;

@Component
public class HttpConnectionImpl implements HttpConnection {

    private final HttpClient httpClient;

    public HttpConnectionImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public <T extends HttpRequest, O extends HttpResponse> O doRequest(T request, O interfaceHttpResponse) throws Exception {

        java.net.http.HttpRequest httpRequest = null;
        java.net.http.HttpResponse<?> httpResponse = null;
        java.net.http.HttpRequest.Builder builder = null;
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

        if (arrayHeaders.length == 0) {
            builder = java.net.http.HttpRequest.newBuilder().uri(uri).timeout(Duration.ofMillis(request.getTimeout()));
        } else {
            builder = java.net.http.HttpRequest.newBuilder().uri(uri).headers(arrayHeaders).timeout(Duration.ofMillis(request.getTimeout()));
        }

        if (request.getHttpMethod() == HttpMethod.GET) {
            httpRequest = builder.GET().build();
            httpResponse = httpClient.send(httpRequest, java.net.http.HttpResponse.BodyHandlers.ofString());

        } else if (request.getHttpMethod() == HttpMethod.POST) {
            httpRequest = builder.POST(java.net.http.HttpRequest.BodyPublishers.ofString(request.getPayload())).build();
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
