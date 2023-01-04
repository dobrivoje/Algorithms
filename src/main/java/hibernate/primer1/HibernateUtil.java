package hibernate.primer1;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessionFactory = createSessionFactory();

	public static synchronized SessionFactory createSessionFactory() {
		try {
			//			if (SessionFactory == null) {
			if (sessionFactory != null) {
				sessionFactory.close();
			}

			StandardServiceRegistry standardRegistry =
					new StandardServiceRegistryBuilder().configure( "hibernate.cfg.xml" ).build();
			Metadata metaData = new MetadataSources( standardRegistry ).getMetadataBuilder().build();
			sessionFactory = metaData.getSessionFactoryBuilder().build();
			//			}
			return sessionFactory;
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError( ex );
		}
	}

	public static synchronized SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static synchronized void shutdown() {
		//		if (!sessionFactory.isClosed()) {
		sessionFactory.close();
		//		}
	}
}