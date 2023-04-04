package br.com.josemarinho.http;

public class HttpRepository {

    private final HttpConnection httpConnection;

    public HttpRepository(HttpConnection httpConnection) {
        this.httpConnection = httpConnection;
    }

    public <T extends HttpRequest, O extends HttpResponse> O doRequest(T httpRequest, O objectClass) throws Exception {
        return this.httpConnection.doRequest(httpRequest, objectClass);
    }
    public HttpConnection getHttpConnection() {
        return httpConnection;
    }

}
