package lib;

import java.time.Duration;

public interface HttpResponse {
    HttpResponse handle(HttpRequest httpRequest, String httpResponseMessage);
}
