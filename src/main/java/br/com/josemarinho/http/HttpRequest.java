package br.com.josemarinho.http;

import lombok.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.awt.*;
import java.util.Map;

@Builder
@Getter
@Setter
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

}
