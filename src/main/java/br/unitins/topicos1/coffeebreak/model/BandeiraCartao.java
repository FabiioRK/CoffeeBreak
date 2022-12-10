package br.unitins.topicos1.coffeebreak.model;

public enum BandeiraCartao {
	
	VISA(1, "Visa"), 
	MASTERCARD(2, "Master Card");

	private int id;
	private String label;
	
	BandeiraCartao(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public static BandeiraCartao valueOf(Integer id) {
		if (id == null)
			return null;
		for (BandeiraCartao tipo : BandeiraCartao.values()) 
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
