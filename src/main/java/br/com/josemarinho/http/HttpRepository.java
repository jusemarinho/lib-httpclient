package br.com.josemarinho.http;

import java.util.function.Supplier;

public class HttpRepository {

    private final HttpConnection httpConnection;

    public HttpRepository(HttpConnection httpConnection) {
        this.httpConnection = httpConnection;
    }

    public <T extends HttpRequest, O extends HttpResponse> O doRequest(T httpRequest, Supplier<O> httpResponse) throws Exception {
        return this.httpConnection.doRequest(httpRequest, httpResponse);
    }
}
