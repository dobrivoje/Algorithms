package junit.Udemy;

import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import org.mockito.Mock;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import algs.junit.Udemy.BO.IOrderBO;
import algs.junit.Udemy.BO.OrderBOImpl;
import algs.junit.Udemy.DAO.IOrderDAO;
import algs.junit.Udemy.Order;
import algs.junit.Udemy.exceptions.BOException;

public class PlaceOrderBOImplTest {

    private IOrderBO bussinesObject;
    private Order order;

    @Mock
    private IOrderDAO dao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        bussinesObject = new OrderBOImpl();
        order = new Order(1, "novi");

        bussinesObject.setDao(dao);
    }

    @Test
    public void placeOrderShouldCreateAnOrder() throws SQLException, BOException {
        when(dao.create(order)).thenReturn(1);
        boolean res = bussinesObject.placeOrder(order);

        System.out.println("order :" + dao.read(1));

        Assert.assertTrue(res);
        verify(dao, atLeast(1)).create(order);
    }

    @Test
    public void placeOrderShouldNotCreateAnOrder() throws SQLException, BOException {
        when(dao.create(order)).thenReturn(0);
        boolean res = bussinesObject.placeOrder(order);

        Assert.assertFalse(res);

        verify(dao).create(order);
    }

    @Test(expected = BOException.class)
    public void placeOrderShouldThrowsBOException() throws SQLException, BOException {
        when(dao.create(order)).thenThrow(SQLException.class);
        boolean res = bussinesObject.placeOrder(order);
    }

    @Test()
    public void cancelOrder() throws SQLException, BOException {
        int orderID = 123;

        when(dao.read(orderID)).thenReturn(order);
        when(dao.update(order)).thenReturn(1);
        boolean res = bussinesObject.cancelOrder(orderID);

        Assert.assertTrue(res);

        verify(dao).read(orderID);
        verify(dao).update(order);
    }

    @Test()
    public void cancelOrderShoulNotReturnOrder() throws SQLException, BOException {
        when(dao.read(123)).thenReturn(order);
        when(dao.update(order)).thenReturn(0);
        boolean res = bussinesObject.cancelOrder(123);

        Assert.assertFalse(res);

        verify(dao).read(123);
        verify(dao).update(order);
    }

    @Test(expected = BOException.class)
    public void cancelOrderShoulThrowsBOException() throws SQLException, BOException {
        when(dao.read(anyInt())).thenThrow(SQLException.class);
        boolean res = bussinesObject.cancelOrder(anyInt());
    }

    @Test(expected = BOException.class)
    public void cancelOrderShoulThrowsBOExceptionOnUpdate() throws SQLException, BOException {
        when(dao.read(123)).thenReturn(order);
        when(dao.update(order)).thenThrow(SQLException.class);
        boolean res = bussinesObject.cancelOrder(123);
    }
}
