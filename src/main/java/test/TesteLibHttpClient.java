package test;

import br.com.josemarinho.http.HttpRepository;
import test.requests.ObterMoedasRequest;
import test.responses.ObterMoedasResponse;

public class TesteLibHttpClient extends HttpRepository {

    public void teste() throws Exception {
        var viaCepRequest = new ObterMoedasRequest();
        var viaCepResponse = this.getHttpConnection().doRequest(viaCepRequest, new ObterMoedasResponse());

        var viaCepResponeData = viaCepResponse.getViaCepResponeData();
    }

    public static void main(String[] args) throws Exception {
        new TesteLibHttpClient().teste();
    }
}
