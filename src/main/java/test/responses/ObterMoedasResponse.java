package test.responses;

import br.com.josemarinho.http.HttpRequest;
import br.com.josemarinho.http.HttpResponse;
import br.com.josemarinho.http.JsonHelper;

public class ObterMoedasResponse implements HttpResponse {

    private ObterMoedasResponseData viaCepResponeData;

    public ObterMoedasResponseData getViaCepResponeData() {
        return viaCepResponeData;
    }

    @Override
    public HttpResponse handle(HttpRequest httpRequest, java.net.http.HttpResponse<?> httpResponseMessage) {

        if (httpResponseMessage.statusCode() != 200)
            return this;

        this.viaCepResponeData = new JsonHelper<ObterMoedasResponseData>().deserializeFromString(httpResponseMessage.body().toString(), ObterMoedasResponseData.class);

        return this;
    }


}
