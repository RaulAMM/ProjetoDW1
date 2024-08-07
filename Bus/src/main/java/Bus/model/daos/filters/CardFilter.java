package Bus.model.daos.filters;

import Bus.model.entities.CardType;
import Bus.model.entities.User;

public class CardFilter {

	private User user;
	
	private CardType type;

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
