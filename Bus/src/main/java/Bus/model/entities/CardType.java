package Bus.model.entities;

public enum CardType {
	Escolar("Escolar"),
	Padrão("Padrão"),
	Empresarial("Empresarial");
	
	private String type;
	
	CardType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
