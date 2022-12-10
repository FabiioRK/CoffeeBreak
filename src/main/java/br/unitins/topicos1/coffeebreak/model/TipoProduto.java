package br.unitins.topicos1.coffeebreak.model;

public enum TipoProduto {

	CAFE(1, "Cafe"),
	CAFETEIRA(2, "Cafeteira");
	
	private int id;
	private String label;
	
	private TipoProduto(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public static TipoProduto valueOf(Integer id) {
		if (id == null)
			return null;
		for (TipoProduto tipo : TipoProduto.values()) 
			if (tipo.getId() == id) 
				return tipo;
		return null;
	}	
	
	//GETTERS E SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
