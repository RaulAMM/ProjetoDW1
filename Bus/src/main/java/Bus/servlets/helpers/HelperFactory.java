package Bus.servlets.helpers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class HelperFactory {

	public Helper getHelper(HttpServletRequest request) {
		String action = request.getParameter("action");
		ServletContext context = request.getServletContext();
		String className = context.getInitParameter(action);
		try {
			Class<?> clazz = Class.forName(className);
			Helper helper = (Helper)clazz.getDeclaredConstructor()
					.newInstance();
			return helper;
		}
		catch(Exception erro) {
			throw new RuntimeException(erro);
		}
	}
	
}
