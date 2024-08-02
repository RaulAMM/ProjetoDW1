package Bus.model.dto;

public class CardByType {
	private String type;
	private Integer count;

	public CardByType(String type, Integer count) {
		this.type = type;
		this.count = count;
	}
	
	public CardByType() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
