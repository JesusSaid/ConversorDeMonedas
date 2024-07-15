import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarMoneda {
    public JsonObject buscarMoneda(String tipoDeMoneda){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/e616371a4b264b2500d999e3/latest/"+tipoDeMoneda);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response = null;

        JsonObject jsonobj = null;

        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            JsonParser jp = new JsonParser();
            jsonobj = jp.parse(response.body()).getAsJsonObject();
        } catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
        return jsonobj;
    }
}
