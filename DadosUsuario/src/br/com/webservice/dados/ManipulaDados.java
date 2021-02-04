package br.com.webservice.dados;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.webservice.modelo.DadosUsuario;
import br.com.webservice.requisicao.Requisicao;

public class ManipulaDados {

	public static List<DadosUsuario> parsenDados(String requisicao,JTable table, JComboBox<String> comboBoxSeg, JLabel nSeguidores) {

		try {
			JSONObject jsonObj = new JSONObject(requisicao);
			String requisicaoSeguidores = jsonObj.getString("followers_url");
			String listaSeguidores = Requisicao.requisicao(requisicaoSeguidores);
			nSeguidores.setText(jsonObj.getString("followers"));
			JSONArray jArray = new JSONArray(listaSeguidores);
			List<DadosUsuario> dadosUsuarios = new ArrayList<DadosUsuario>();

			for (int i = 0; i < jArray.length(); i++) {

				JSONObject jsonObjeto = new JSONObject(jArray.getJSONObject(i).toString());
				dadosUsuarios.add(parseDadosSeguidores(jsonObjeto));
			}

			for (int i = 0; i < dadosUsuarios.size(); i++)
				comboBoxSeg.addItem(dadosUsuarios.get(i).getNomeUsuario());

			return dadosUsuarios;

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static DadosUsuario parseDadosSeguidores(JSONObject jsonObjeto) {
		try {
			DadosUsuario usuario = new DadosUsuario();

			String requisicaoNome = Requisicao.requisicao(jsonObjeto.getString("url"));

			usuario.setNomeUsuario((parserUsuario(requisicaoNome)));

			String repositorios = Requisicao.requisicao(jsonObjeto.getString("repos_url"));
			usuario.setRepositorio(parserRepositorios(repositorios));
			return usuario;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static List<String> parserRepositorios(String repositorios) {

		try {
			List<String> listaRepositorios = new ArrayList<String>();
			JSONArray jArray = new JSONArray(repositorios);

			for (int i = 0; i < jArray.length(); i++) {
				JSONObject jsonObjeto = new JSONObject(jArray.getJSONObject(i).toString());
				listaRepositorios.add(jsonObjeto.getString("name").toString());
			}
			return listaRepositorios;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String parserUsuario(String requisicaoNome) {
		try {
			JSONObject jsonObj = new JSONObject(requisicaoNome);
			return jsonObj.getString("name").toString();
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static boolean organizaRepositorios(DefaultTableModel defaultTableModel, List<DadosUsuario> dadosUsuarios,
			JComboBox<String> comboBoxSeg, JTable table) {
		
		if(dadosUsuarios==null) 
			return false;
		else {
			
			int item = comboBoxSeg.getSelectedIndex();
			defaultTableModel.setNumRows(0);
			
			for (int j = 0; j < dadosUsuarios.get(item).getRepositorio().size(); j++)
				defaultTableModel.insertRow(defaultTableModel.getRowCount(),
						new Object[] {dadosUsuarios.get(item).getRepositorio().get(j)});
			
			return true;
		}	
	}
}

