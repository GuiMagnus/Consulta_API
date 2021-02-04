package br.com.webservice.modelo;

public class DadosCEP {
	private String cep, logradouro, bairro, localidade, uf, ibge;

	public DadosCEP() {
	}

	public DadosCEP(String cep, String logradouro, String bairro, String localidade, String uf, String ibge) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ibge = ibge;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	@Override
	public String toString() {
		return String.format("CEP:%s\nLogradouro:%s\nBairro:%s\nLocalidade:%s\nUF:%s\nIBGE:%s", cep,
				logradouro, bairro, localidade, uf, ibge);
	}
	
	
}
