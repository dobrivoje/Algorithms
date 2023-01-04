package hibernate.primer2;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class DiverConnPrimer {

    public static void main(String[] args) {
        String sqlQuery2 = "SELECT DISTINCT WORKORDER_RES_ID AS workOrderResId "
                + "			FROM oss_edn.`edn_survey_result`  "
                + "			WHERE WORKORDER_NO = :taskIdd";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://35.197.238.116/oss_edn", "dobrivoje.prtenjak", "X$D#nq@&amp;9RSr%Gia" );
                PreparedStatement preparedStatement = conn.prepareStatement( sqlQuery2 )) {
            preparedStatement.setLong( 1, 191030124004185851L );

            List<Long> res = new LinkedList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong( "workOrderResId" );
                res.add( id );
            }

            res.forEach( System.out::println );
        } catch (SQLException e) {
            System.err.format( "SQL State: %s\n%s", e.getSQLState(), e.getMessage() );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.err.println();
            System.err.println( "finally..." );
        }
    }

}
