package algs.junit.Udemy.DAO;

import java.sql.SQLException;
import algs.junit.Udemy.Order;

public interface IOrderDAO {

    int create(Order order) throws SQLException;

    Order read(int id) throws SQLException;

    int update(Order order) throws SQLException;

    int delete(int id) throws SQLException;
}
