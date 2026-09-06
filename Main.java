package desafio3ViaCEPAPI;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        String cep = "";
        GeradorDeArquivos gerador = new GeradorDeArquivos();
        System.out.println("====== Bem vindo ao Buscador de CEP ======");
        while(!cep.equalsIgnoreCase("sair")){
            System.out.println("Digite o seu CEP para consulta ou sair");
            cep = leitura.nextLine();
            if(!cep.equalsIgnoreCase("sair")){
                Consulta consulta = new Consulta();
                if(!consulta.formatarCep(cep).equalsIgnoreCase("CEP inválido")){
                    Endereco novoEndereco = consulta.buscaEndereco(cep);
                    gerador.salvarJson(novoEndereco);
                    System.out.println("Endereço encontrado! Armazenado em um arquivo .json");
                    System.out.println("Deseja exibir o endereço? Digite sim ou não.");
                    String resposta = leitura.nextLine();
                    if (resposta.equalsIgnoreCase("sim")){
                        System.out.println(novoEndereco);
                    }
                }
            }
        }
        System.out.println("O Programa finalizou!");


//



    }
}
//Criar uma aplicação para consultar a API ViaCEP
//Menu para o usuário informar o CEP para busca
//Geração de um arquivo .JSON com os dados do endereço