package br.com.josemarinho.http;

public class HttpRepository {

    private HttpConnection httpConnection;

    public HttpRepository() {
        this.httpConnection = new HttpConnection();
    }

    public <T extends HttpRequest, O extends HttpResponse> O doRequest(T httpRequest, O objectClass) throws Exception {
        return this.httpConnection.doRequest(httpRequest, objectClass);
    }

    public HttpConnection getHttpConnection() {
        return httpConnection;
    }

    public void setHttpConnection(HttpConnection httpConnection) {
        this.httpConnection = httpConnection;
    }
}
