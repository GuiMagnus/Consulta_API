package br.com.webservice.requisicao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Requisicao {
	public static String requisicao(String urlRequisicao) {
		URL url;
		try {
			url = new URL(urlRequisicao);
			URLConnection con = url.openConnection();
			BufferedReader input = new BufferedReader(
					new InputStreamReader(con.getInputStream(), "utf-8")
					);

			String line;
			StringBuilder source = new StringBuilder();

			while((line = input.readLine()) != null) {
				source.append(line);
			}
			input.close();
			return source.toString();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return urlRequisicao;
	}
}
