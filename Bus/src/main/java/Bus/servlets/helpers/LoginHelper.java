package Bus.servlets.helpers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bus.model.daos.UserDao;
import Bus.model.entities.User;
import Bus.utils.SearcherDataSource;

public class LoginHelper {
	
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UserDao userDao = new UserDao(SearcherDataSource.getInstance().getDataSource());
		Optional<User> optional = userDao.getUserByEmailAndPassword(email, password);
		if(optional.isPresent()) {
			User user = optional.get();
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(600);
			session.setAttribute("user", user);
			return "/ControllerServlet?action=listActivities";
		}else {
			req.setAttribute("result", "loginError");
			return "/login.jsp";
		}
	}
}
