package algs.junit.Udemy.exceptions;

import java.sql.SQLException;

public class BOException extends Exception {
    
    public BOException(String message) {
        super(message);
    }
    
    public BOException(SQLException ex) {
        super(ex);
    }
    
}
