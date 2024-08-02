package Bus.model.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import br.edu.ifsp.arq.ads.ifitness.model.daos.filters.ActivityFilter;
import br.edu.ifsp.arq.ads.ifitness.model.dto.ActivityByType;
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

		public Boolean update(Card crad) {
			String sql = "update activity set " + "type=?," + "activity_date=?," + "distance=?," + "duration=?,"
					+ "user_id=?" + " where id=?";
			try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, crad.getType().toString());
				ps.setDate(2, Date.valueOf(crad.getDate()));
				ps.setDouble(3, crad.getDistance());
				ps.setInt(4, crad.getDuration());
				ps.setLong(5, crad.getUser().getId());
				ps.setLong(6, crad.getId());
				ps.executeUpdate();
				return true;
			} catch (SQLException sqlException) {
				throw new RuntimeException("Erro ao atualizar dados", sqlException);
			}
		}

		public Boolean delete(Card activity) {
			String sql = "delete from activity where id=?";
			try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setLong(1, activity.getId());
				ps.executeUpdate();
				return true;
			} catch (SQLException sqlException) {
				throw new RuntimeException("Erro ao remover dados", sqlException);
			}
		}

		public List<Card> getActivitiesByFilter(ActivityFilter filter) throws SQLException {
			StringBuilder sql = 
					new StringBuilder("select * from activity where user_id=?");
			List<Object> params = new ArrayList<>();
			params.add(filter.getUser().getId());

			if (filter.getType() != null) {
				sql.append(" and type=?");
				params.add(filter.getType().getType().toString());
			}

			if (filter.getInitialDate() != null) {
				sql.append(" and activity_date >= ?");
				params.add(filter.getInitialDate());
			}

			if (filter.getFinalDate() != null) {
				sql.append(" and activity_date <= ?");
				params.add(filter.getFinalDate());
			}

			return getActivityList(sql.toString(), params, filter.getUser());
		}

		private List<Activity> getActivityList(String sql, List<Object> params,
				User user) throws SQLException {
			List<Activity> activities = new ArrayList<>();
			try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
				for (int i = 0; i < params.size(); i++) {
					ps.setObject(i + 1, params.get(i));
				}
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Card activity = new Card();
						activity.setId(rs.getLong(1));
						activity.setType(Cardype.valueOf(rs.getString(2)));
						activity.setDate(LocalDate.parse(rs.getDate(3).toString()));
						activity.setDistance(rs.getDouble(4));
						activity.setDuration(rs.getInt(5));
						activity.setUser(user);
						activities.add(activity);
					}
				}
			}
			return activities;
		}
		
		public List<CardByType> getActivitiesStatisticsByType(User user) {
			String sql = "select type, count(*) as activity_count from activity where user_id=? group by type";
			List<CardByType> activities = new ArrayList<>();
			try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setLong(1, user.getId());
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						CardByType activityByType = new CardByType();
						activityByType.setType(CardType.valueOf(rs.getString(1)).getType());
						activityByType.setCount(rs.getInt(2));
						activities.add(activityByType);
					}
				}
				return activities;
			} catch (SQLException sqlException) {
				throw new RuntimeException("Erro durante a consulta", sqlException);
			}
		}
		
		public List<CardByDay> getActivitiesStatisticsByDay(User user) {
			String sql = "select activity_date, SUM(distance) AS total_distance from activity where user_id=? group by activity_date";
			List<CardByDay> activities = new ArrayList<>();
			try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setLong(1, user.getId());
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						CardByDay activityByDay = new CardByDay();
						activityByDay.setDate(LocalDate.parse(rs.getDate(1).toString()));
						activityByDay.setTotalDistance(rs.getLong(2));
						activities.add(activityByDay);
					}
				}
				return activities;
			} catch (SQLException sqlException) {
				throw new RuntimeException("Erro durante a consulta", sqlException);
			}
		}
	}
}
