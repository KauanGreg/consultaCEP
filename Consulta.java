package desafio3ViaCEPAPI;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consulta{

    public Endereco buscaEndereco(String cep){
        URI endereco = URI.create("https://viacep.com.br/ws/" + cep + "/json/");
        HttpResponse<String> response = null;
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(endereco)
                    .build();
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Endereco.class);
        }catch (IOException | JsonSyntaxException | InterruptedException e){
            throw new RuntimeException("Não foi possivel fazer a consulta");
        }
    }

    public String formatarCep(String cep){
        String cepFormatado = cep.replace("-","");
        if(cepFormatado.length() != 8){
            System.out.println("CEP inválido: Você digitou " + cep.length() + " caracteres, quando eram necessários 8 para consulta.");
            return "CEP inválido";
        }
        return cepFormatado;
    }

}
