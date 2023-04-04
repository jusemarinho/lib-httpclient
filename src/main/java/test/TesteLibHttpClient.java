package test;

import br.com.josemarinho.http.HttpConnection;
import br.com.josemarinho.http.HttpConnectionImpl;
import br.com.josemarinho.http.HttpRepository;
import test.requests.ViaCepRequest;
import test.responses.ObterCepResponse;

import java.net.http.HttpClient;

public class TesteLibHttpClient extends HttpRepository {

    public TesteLibHttpClient(HttpConnection httpConnection) {
        super(httpConnection);
    }

    public void teste() throws Exception {
        var viaCepRequest = new ViaCepRequest();
        var viaCepResponse = this.getHttpConnection().doRequest(viaCepRequest, new ObterCepResponse());

        var viaCepResponeData = viaCepResponse.getViaCepResponeData();
    }

    public static void main(String[] args) throws Exception {
        new TesteLibHttpClient(new HttpConnectionImpl(HttpClient.newHttpClient())).teste();
    }
}
