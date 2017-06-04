package algs.junit.Udemy.BO;

import java.sql.SQLException;
import algs.junit.Udemy.DAO.IOrderDAO;
import algs.junit.Udemy.Order;
import algs.junit.Udemy.exceptions.BOException;

public class OrderBOImpl implements IOrderBO {

    private IOrderDAO dao;

    @Override
    public boolean placeOrder(Order order) throws BOException {
        try {
            int result = dao.create(order);
            if (result == 0) {
                return false;
            }
        } catch (SQLException ex) {
            throw new BOException(ex);
        }

        return true;
    }

    @Override
    public boolean cancelOrder(int id) throws BOException {
        try {
            Order o = dao.read(id);
            o.setStatus("cancel");
            int res = dao.update(o);

            if (res == 0) {
                return false;
            }
        } catch (SQLException ex) {
            throw new BOException(ex);
        }
        return true;
    }

    @Override
    public boolean deleteOrder(int id) throws BOException {
        try {
            int res = dao.delete(id);
            if (res == 0) {
                return false;
            }
        } catch (SQLException ex) {
            throw new BOException(ex);
        }
        return false;
    }

    @Override
    public IOrderDAO getDao() {
        return dao;
    }

    @Override
    public void setDao(IOrderDAO dao) {
        this.dao = dao;
    }

}
