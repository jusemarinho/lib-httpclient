package test.requests;

import br.com.josemarinho.http.HttpRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.HashMap;

public class ObterMoedasRequest extends HttpRequest {
    public ObterMoedasRequest() {
        setTimeout(5000);
        setHeaders(new HashMap<>(){{
            put("Content-Type", "application/x-www-form-urlencoded");
        }});
        setStringMediaTypeMap(new HashMap<>(){{
            put("Accept-Encoding", MediaType.APPLICATION_JSON);
        }});
        setUriRelative("https://lit-harbor-97954.herokuapp.com/v1/coins");
        setHttpMethod(HttpMethod.GET);
    }
}
