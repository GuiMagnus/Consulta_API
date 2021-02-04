package br.com.webservice.view;

import static javax.swing.JOptionPane.*;

public class EntradaESaida {
	public static void exibirTexto(String titulo, String texto) {
		showMessageDialog(null, texto, titulo, INFORMATION_MESSAGE);
	}
	
	public static void exibirErro(String titulo, String texto) {
		showMessageDialog(null, texto, titulo, ERROR_MESSAGE);
	}
	
	public static Integer converteInteiro(String titulo, String texto) {
		return Integer.parseInt(showInputDialog(null, texto, titulo, INFORMATION_MESSAGE));
	}
	
	public static String inputString(String titulo, String texto) {
		return showInputDialog(null, texto, texto, INFORMATION_MESSAGE);
	}
	
}
