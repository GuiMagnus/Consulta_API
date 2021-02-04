package br.com.webservice.app;
import static br.com.webservice.view.EntradaESaida.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.webservice.modelo.DadosCEP;
public class AppCEP {
	private static String URL_BUSCA_CEP = "https://viacep.com.br/ws/";
	private static String TIPO_RETORNO = "/json";
	
	public static void main(String[] args) {
		iniciar();
	}

	public static void iniciar() {
		int opcao = 0;
		do {
			opcao = converteInteiro("Consulta de CEP", "1-Busca CEP\n2-Sair\n\nOpção:");
			switch (opcao) {
			case 1:
				dadosCEP();
				break;
			default:
				if(opcao != 2)
					exibirErro("Consulta de CEP", "Opção Inválida!");
				break;
			}
		} while (opcao != 2);

	}


	public static void dadosCEP() {
		String cep = inputString("Busca", "Número do CEP (Apenas Números):");
		if(cep.matches(("^[0-9]*$")) && cep.length() == 8) {
			exibeDados(consulta(cep));
		}
		else
			exibirErro("Busca", "CEP Inválido!");
	}

	public static void exibeDados(String respostaWebService) {
		if(!respostaWebService.contains("erro")) {
			try {
				JSONObject jsonObj = new JSONObject(respostaWebService);
				DadosCEP dadosCEP = new DadosCEP();
				dadosCEP.setCep(jsonObj.getString("cep"));
				dadosCEP.setLogradouro(jsonObj.getString("logradouro"));
				dadosCEP.setBairro(jsonObj.getString("bairro"));
				dadosCEP.setLocalidade(jsonObj.getString("localidade"));
				dadosCEP.setUf(jsonObj.getString("uf"));
				dadosCEP.setIbge(jsonObj.getString("ibge"));
				exibirTexto("Relatório CEP",dadosCEP.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		else
			exibirTexto("Relatório CEP", "CEP não encontrado!");
	}

	public static String consulta(String cep) {
		URL url;
		try {
			url = new URL(URL_BUSCA_CEP+cep+TIPO_RETORNO);
			URLConnection con = url.openConnection();
			BufferedReader input = new BufferedReader(
					new InputStreamReader(con.getInputStream(), "utf-8")
					);

			String line;
			StringBuilder source = new StringBuilder();

			while((line = input.readLine()) != null)
				source.append(line);
			input.close();

			return source.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}