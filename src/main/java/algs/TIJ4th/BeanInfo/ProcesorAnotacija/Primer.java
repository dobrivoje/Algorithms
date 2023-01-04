package algs.TIJ4th.BeanInfo.ProcesorAnotacija;

import algs.TIJ4th.collections.Person;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

public class Primer {

	public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
		System.err.println( "getCanonicalName = " + Primer.class.getCanonicalName() );
		System.err.println( "getName = " + Primer.class.getName() );
		System.err.println( "------------------------" );
		System.err.println( "Operations getName = " + MyOperations.class.getName() );
		System.err.println( "Operations getCanonicalName = " + MyOperations.class.getCanonicalName() );

		// Class<?> aClass = Class.forName( MyOperations.class.getName() );
		Class<?> aClass = Class.forName( "algs.TIJ4th.BeanInfo.ProcesorAnotacija.MyOperations" );
		MyOperations operations1 = (MyOperations) aClass.getConstructor().newInstance();
		double v = operations1.publicSum( 2, 3 );
		System.err.println( "v = " + v );

		Method publicSumMethod = MyOperations.class.getMethod( "publicSum", int.class, double.class );
		Double result = (Double) publicSumMethod.invoke( new MyOperations(), 1, 3 );
		System.err.println( "publicSum = " + result );

		Function<String, String> stringProcessor = in -> ">>> new value : " + in;
		String stringProcessorCanonicalName = stringProcessor.getClass().getSimpleName();
		System.err.println( "stringProcessor class path = " + stringProcessorCanonicalName );
		System.err.println( stringProcessor.apply( "test 1" ) );

		Method apply1 = stringProcessor.getClass().getDeclaredMethod( "apply", Object.class );
		String param1 = (String) apply1.invoke( stringProcessor, "test 2 preko refleksije" );
		System.err.println( param1 );

		Function<Integer, Integer> intProcessor = in -> in + 100;

		Person person = new Person( "dobrisha", 7975, new Date() );

		//<editor-fold desc="refl. utils">
		ReflectionUtils.doWithFields( person.getClass(), field -> {
			field.setAccessible( true );

			Optional.of( field )
					.map( f -> f.getAnnotation( Transformator.class ) )
					.map( Transformator::process )
					.ifPresent( annotProcessValue -> {
						try {
							String personFieldValue = (String) field.get( person );
							Function<String, String> customAnnPrcessor =
									in -> annotProcessValue + personFieldValue;
							String processFieldValue = customAnnPrcessor.apply( personFieldValue );

							field.set( person, processFieldValue );
						} catch (IllegalAccessException e) {
							throw new RuntimeException( e );
						}
					} );

			Optional.of( field )
					.map( f -> f.getAnnotation( Transformator.class ) )
					.map( Transformator::skip )
					.ifPresent( skipVal -> System.out.println(
							field.getName() + " -> anotacija \"Transformator::skip\" vrednost : " + skipVal ) );

			Optional.of( field )
					.map( f -> f.getAnnotation( Transformator.Include.class ) )
					.map( f -> "ok".equalsIgnoreCase( f.name() ) )
					.ifPresent( inclVal -> System.out.println(
							field.getName() + " -> anotacija \"Transformator.Include\" vrednost: " + inclVal ) );
		} );
		//</editor-fold>

		System.err.println( "------------" );
		System.err.println( person.getName() );
		System.err.println( "------------" );
	}
}
