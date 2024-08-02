package Bus.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import Bus.model.entities.Onibus;
import Bus.utils.PasswordEncode;

public class OnibusDao {
	private DataSource dataSource;
	
	public OnibusDao(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public Optional<Onibus> getUserByEmailAndPassword(String email, String password) {
		String passwordEncripted = PasswordEncode.encode(password);

		String sql = "select Placa,QTDPassageiros,LinhaOnibusIdLinhaOnibus from Onibus where Placa=? and LinhaOnibusIdLinhaOnibus=?";
		Optional<Onibus> optional = Optional.empty();
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, email);
			ps.setString(2, passwordEncripted);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setCPF(rs.getLong(1));
					user.setRG(rs.getLong(2));
					user.setName(rs.getString(3));
					user.setEmail(rs.getString(4));
					optional = Optional.of(user);
				}
			}
			return optional;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro durante a consulta", sqlException);
		}
	}
}
