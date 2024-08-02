package Bus.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bus.model.daos.UserDao;
import Bus.model.entities.User;
import Bus.utils.PasswordEncode;
import Bus.utils.SearcherDataSource;

public class UserRegisterServlet {

	private static final long serialVersionUID = 1L;

	public UserRegisterServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// recuperar os dados
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String gender = req.getParameter("gender");
		// instanciar o objeto User
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(PasswordEncode.encode(password));
		user.setDateOfBirth(LocalDate.parse(dateOfBirth));
		// instanciar o objeto UserDao
		UserDao userDao = new UserDao(
				SearcherDataSource.getInstance().getDataSource());
		
		RequestDispatcher dispatcher = null;
		
		if(userDao.save(user)) {
			req.setAttribute("result", "registered");
			dispatcher = req.getRequestDispatcher("/login.jsp");
		}else {
			req.setAttribute("result", "notRegistered");
			dispatcher = req.getRequestDispatcher("/user-register.jsp");
		}
		// encaminha a requisição
		dispatcher.forward(req, resp);
		
		//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
	}
}
