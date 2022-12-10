package br.unitins.topicos1.coffeebreak.model;

public enum TipoPagamento {
	
	CREDITO(1, "Credito"), 
	DEBITO(2, "Debito"), 
	PIX(3, "Pix"), 
	BOLETO(4, "Boleto");

	private int id;
	private String label;
	
	TipoPagamento(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public static TipoPagamento valueOf(Integer id) {
		if (id == null)
			return null;
		for (TipoPagamento tipo : TipoPagamento.values()) 
			if (tipo.getId() == id) 
				return tipo;
		return null;
	}
	
	//GETTERS E SETTERS
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
		
	}
	

}
