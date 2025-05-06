package br.com.josemarinho.http;

import lombok.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.awt.*;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpRequest {

    private String uriRelative;
    private Map<String, MediaType> stringMediaTypeMap;
    private Map<String, String> headers;
    private String payload;
    private HttpMethod httpMethod;
    private long timeout;

    public long getTimeout() {
        return timeout == 0 ? 5_000 : timeout;
    }

    public String getUriRelative() {
        return uriRelative;
    }

    public void setUriRelative(String uriRelative) {
        this.uriRelative = uriRelative;
    }

    public Map<String, MediaType> getStringMediaTypeMap() {
        return stringMediaTypeMap;
    }

    public void setStringMediaTypeMap(Map<String, MediaType> stringMediaTypeMap) {
        this.stringMediaTypeMap = stringMediaTypeMap;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
