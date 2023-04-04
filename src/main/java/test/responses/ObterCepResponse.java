package test.responses;

import br.com.josemarinho.http.HttpRequest;
import br.com.josemarinho.http.HttpResponse;
import br.com.josemarinho.http.JsonHelper;

public class ObterCepResponse implements HttpResponse {

    private ObterCepResponseData viaCepResponeData;

    public ObterCepResponseData getViaCepResponeData() {
        return viaCepResponeData;
    }

    @Override
    public HttpResponse handle(HttpRequest httpRequest, java.net.http.HttpResponse<?> httpResponseMessage) {

        if (httpResponseMessage.statusCode() != 200)
            return this;

        this.viaCepResponeData = new JsonHelper<ObterCepResponseData>().deserializeFromString(httpResponseMessage.body().toString(), ObterCepResponseData.class);

        return this;
    }


}
