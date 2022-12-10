package br.unitins.topicos1.coffeebreak.model;

import java.util.List;

public enum Perfil {
	ADMINISTRADOR(1, "Administrador", List.of("/CoffeeBreak/faces/admin/usuario/usuarios.xhtml","/CoffeeBreak/faces/admin/usuario/formulario.xhtml")), 
	CLIENTE(2, "Cliente", List.of());

	private int id;
	private String label;
	private List<String> paginas;
	
	Perfil(int id, String label, List<String> paginas) {
		this.id = id;
		this.label = label;
		this.paginas = paginas;
	}
	
	public List<String> getPaginas() {
		return paginas;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
		
	}
	
	public static Perfil valueOf(Integer id) {
		if (id == null)
			return null;
		for (Perfil perfil : Perfil.values()) 
			if (perfil.getId() == id) 
				return perfil;
		return null;
	}
	

}
