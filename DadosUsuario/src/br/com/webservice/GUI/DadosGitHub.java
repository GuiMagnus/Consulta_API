package br.com.webservice.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.webservice.dados.ManipulaDados;
import br.com.webservice.modelo.DadosUsuario;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import br.com.webservice.requisicao.Requisicao;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.CardLayout;
public class DadosGitHub extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JComboBox<String> comboBoxSeg;
	private JTable table;
	private JLabel nSeguidores;
	private List<DadosUsuario> dadosUsuarios;
	private DefaultTableModel defaultTableModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DadosGitHub frame = new DadosGitHub();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DadosGitHub() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(245, 45, 242, 20);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio:");
		lblNewLabel.setBounds(0, 25, 739, 20);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);
		
		JLabel seguidores = new JLabel("Seguidores:");
		seguidores.setBounds(538, 30, 77, 14);
		contentPane.add(seguidores);
		
		nSeguidores = new JLabel("");
		nSeguidores.setBounds(610, 30, 37, 14);
		contentPane.add(nSeguidores);
		
		String[] colunas = new String[] {"Repositórios"};
		defaultTableModel = new DefaultTableModel(colunas,0);
		defaultTableModel.setColumnIdentifiers(colunas);
		comboBoxSeg = new JComboBox<String>();
		comboBoxSeg.setBounds(32, 44, 136, 22);
		comboBoxSeg.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ManipulaDados.organizaRepositorios(defaultTableModel,dadosUsuarios,comboBoxSeg,table);
				table.setVisible(true);
				
			}
		});
		contentPane.add(comboBoxSeg);
		
		JLabel seguidores_1 = new JLabel("Seguidores:");
		seguidores_1.setBounds(61, 30, 77, 14);
		contentPane.add(seguidores_1);
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.setBounds(320, 66, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String requisicao = Requisicao.requisicao("https://api.github.com/users/"+textField.getText());
				dadosUsuarios =  ManipulaDados.parsenDados(requisicao,table,comboBoxSeg,nSeguidores);
			}
		});
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(162, 131, 394, 247);
		contentPane.add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "name_68843880167400");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setEnabled(false);
		table.setFillsViewportHeight(true);
		table.setVisible(true);
		table.setModel(defaultTableModel);
	}
}
