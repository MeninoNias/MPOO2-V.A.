package model;

public class Pergunta {

	private String pergunta;
	private String resposta01;
	private String resposta02;
	private boolean acao;
	
	public Pergunta(String pergunta, String resposta01, String resposta02, boolean acao) {
		this.pergunta = pergunta;
		this.resposta01 = resposta01;
		this.resposta02 = resposta02;
		this.acao = acao;
	}

	public String getResposta02() {
		return resposta02;
	}

	public String getPergunta() {
		return pergunta;
	}

	public String getResposta01() {
		return resposta01;
	}

	public boolean isAcao() {
		return acao;
	}

	@Override
	public String toString() {
		return pergunta + " " + resposta01 + " " + resposta02 + " " + acao;
	}
	
	
	
}
