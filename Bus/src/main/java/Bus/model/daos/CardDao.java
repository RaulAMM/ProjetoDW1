package Bus.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import Bus.model.daos.filters.CardFilter;
import Bus.model.dto.CardByType;
import Bus.model.entities.User;
import Bus.model.entities.CardType;
import Bus.model.entities.Card;


public class CardDao {
		private DataSource dataSource;

		public CardDao(DataSource dataSource) {
			this.dataSource = dataSource;
		}

		public Boolean save(Card card) {
			String sql = "insert into Card (Tipo, IdCartao, Status, Saldo, NomeTitular, UsuarioCPF) values(?,?,?,?,?,?)";
			try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, card.getType().toString());
				ps.setLong(2, card.getId());
				ps.setBoolean(3, card.isStatus());
				ps.setDouble(4, card.getSaldo());
				ps.setString(5, card.getNomeTitular());
				ps.setLong(6, card.getUserCPF().getCPF());
				ps.executeUpdate();
				return true;
			} catch (SQLException sqlException) {
				throw new RuntimeException("Erro ao inserir dados", sqlException);
			}
		}

		public List<Card> getActivitiesByUser(User user) {
			String sql = "select * from activity where CPF=?";
			List<Card> cards = new ArrayList<>();
			try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setLong(1, user.getCPF());
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Card card = new Card();
						card.setId(rs.getLong(1));
						card.setType(CardType.valueOf(rs.getString(2)));
						card.setStatus(rs.getBoolean(3));
						card.setSaldo(rs.getDouble(4));
						card.setNomeTitular(rs.getString(5));
						card.setUserCPF(user);
						cards.add(card);
					}
				}
				return cards;
			} catch (SQLException sqlException) {
				throw new RuntimeException("Erro durante a consulta", sqlException);
			}
		}

		public Card getActivitiesById(Long id) {
			String sql = "select * from Card where id=?";
			Card card = null;
			try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setLong(1, id);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						card = new Card();
						card.setId(rs.getLong(1));
						card.setType(CardType.valueOf(rs.getString(2)));
						card.setStatus(rs.getBoolean(3));
						card.setSaldo(rs.getDouble(4));
						card.setNomeTitular(rs.getString(5));
						User user = new User();
						user.setCPF(rs.getLong(6));
						card.setUserCPF(user);
					}
				}
				return card;
			} catch (SQLException sqlException) {
				throw new RuntimeException("Erro durante a consulta", sqlException);
			}
		}

		public Boolean update(Card card) {
			String sql = "update Card set " + "IdCartao=?," + "Status=?," + "Saldo=?," + "NomeTitular=?," + "UsuarioCPF=?,"
					+ "user_id=?" + " where id=?";
			try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setLong(1, card.getId());
				ps.setString(2, card.getType().toString());
				ps.setBoolean(3, card.isStatus());
				ps.setDouble(4, card.getSaldo());
				ps.setString(5, card.getNomeTitular());
				ps.setLong(6, card.getUserCPF().getCPF());
				ps.executeUpdate();
				return true;
			} catch (SQLException sqlException) {
				throw new RuntimeException("Erro ao atualizar dados", sqlException);
			}
		}

		public Boolean delete(Card card) {
			String sql = "delete from Card where id=?";
			try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setLong(1, card.getId());
				ps.executeUpdate();
				return true;
			} catch (SQLException sqlException) {
				throw new RuntimeException("Erro ao remover dados", sqlException);
			}
		}

		
}
