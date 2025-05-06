package br.com.josemarinho.http;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.ArrayList;
import java.util.function.Supplier;

@Component
public class HttpConnection {

    private final HttpClient httpClient;

    public HttpConnection(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public <O extends HttpResponse> O doRequest(HttpRequest request, Supplier<O> responseSupplier) throws Exception {

        java.net.http.HttpRequest httpRequest;
        java.net.http.HttpResponse<?> httpResponse;
        java.net.http.HttpRequest.Builder builder;
        var listHeaders = new ArrayList<String>();

        var uri = URI.create(request.getUriRelative());

        if (request.getHeaders() != null) {
            for (var header : request.getHeaders().entrySet()) {
                listHeaders.add(header.getKey());
                listHeaders.add(header.getValue());
            }
        }

        if (request.getStringMediaTypeMap() != null) {
            for (var header : request.getStringMediaTypeMap().entrySet()) {
                listHeaders.add(header.getKey());
                listHeaders.add(header.getValue().toString());
            }
        }

        var arrayHeaders = listHeaders.toArray(new String[0]);

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

        return responseSupplier.get().handle(request, httpResponse);
    }
}
