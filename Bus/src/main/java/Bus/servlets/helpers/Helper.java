package Bus.servlets.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Helper {

	public abstract Object execute(HttpServletRequest req, 
			HttpServletResponse resp) throws Exception;

}
