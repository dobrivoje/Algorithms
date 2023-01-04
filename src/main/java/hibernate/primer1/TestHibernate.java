package hibernate.primer1;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class TestHibernate {

	public static void main(String[] args) {
		Transaction tx = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();

			SQLQuery query = session.createSQLQuery(
					"SELECT CUT_ID, FIBER_SECT_ID, SEQ, REUSE_FLAG, CREATE_DATE FROM `edn_cable_cut` "
							+ "ORDER BY CREATE_USER DESC "
							+ "LIMIT 10" );
			List<? extends Object[]> rows = query.list();
			for (Object[] row : rows) {
				Long cutId = Long.parseLong( row[0].toString() );
				Long fibId = Long.parseLong( row[1].toString() );
				Long sec = Long.parseLong( row[2].toString() );
				String reuseFlag = row[3].toString();
				try {
					Date datum = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" ).parse( row[4].toString() );

					Stream.of( cutId, fibId, sec, reuseFlag, datum ).map( Objects::toString ).forEach( System.err::println );
				} catch (ParseException e) {
					System.err.println( "datum greška parsiranje" );
				}
			}

			SQLQuery queryTableStruct = session.createSQLQuery( "SHOW COLUMNS FROM `edn_work_order_event`" );
			List<? extends Object[]> rows2 = queryTableStruct.list();
			for (Object[] row : rows2) {
				String field = row[0].toString();
				String type = row[1].toString();
				String nullable = row[2].toString();

				Stream.of( field, type, nullable ).map( Objects::toString ).forEach( System.err::println );
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			throw new RuntimeException( e );
		}
	}
}