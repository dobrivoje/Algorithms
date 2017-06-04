package algs.junit.Udemy.BO;

import algs.junit.Udemy.DAO.IOrderDAO;
import algs.junit.Udemy.Order;
import algs.junit.Udemy.exceptions.BOException;

public interface IOrderBO {

    boolean placeOrder(Order order) throws BOException;

    boolean cancelOrder(int id) throws BOException;

    boolean deleteOrder(int id) throws BOException;

    IOrderDAO getDao();

    void setDao(IOrderDAO dao);
}
