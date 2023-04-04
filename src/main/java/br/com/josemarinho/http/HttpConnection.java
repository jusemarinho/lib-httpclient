package br.com.josemarinho.http;

public interface HttpConnection {
    <T extends HttpRequest, O extends HttpResponse> O doRequest(T httpRequest, O httpResponse) throws Exception;
}

