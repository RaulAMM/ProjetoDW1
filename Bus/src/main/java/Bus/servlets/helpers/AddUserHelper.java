package Bus.servlets.helpers;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bus.model.daos.UserDao;
import Bus.model.entities.User;
import Bus.utils.PasswordEncode;
import Bus.utils.SearcherDataSource;

public class AddUserHelper {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Long CPF = req.getDateHeader("CPF");
		Long RG = req.getDateHeader("RG");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String dateOfBirth = req.getParameter("dateOfBirth");
		
		User user = new User();
		user.setCPF(CPF);
		user.setRG(RG);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(PasswordEncode.encode(password));
		user.setDateOfBirth(LocalDate.parse(dateOfBirth));
		
		UserDao userDao = new UserDao(SearcherDataSource.getInstance().getDataSource());
		
		if(userDao.save(user)) {
			req.setAttribute("result", "registered");
			return "/login.jsp";
		}else {
			req.setAttribute("result", "notRegistered");
			return "/user-register.jsp";
		}
	}
}
