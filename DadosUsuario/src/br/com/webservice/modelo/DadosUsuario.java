package br.com.webservice.modelo;

import java.util.ArrayList;
import java.util.List;

public class DadosUsuario {
	private String nomeUsuario;
	private List<String> repositorio;
	
	public DadosUsuario() {
		repositorio = new ArrayList<String>();
	}

	public DadosUsuario(String nomeUsuario, List<String> repositorio) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.repositorio = repositorio;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public List<String> getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(List<String> repositorio) {
		this.repositorio = repositorio;
	}
	
	
	
}
