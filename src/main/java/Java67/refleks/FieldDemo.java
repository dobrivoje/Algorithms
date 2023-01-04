package Java67.refleks;

import java.lang.reflect.Field;

public class FieldDemo {

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {

		SampleClass sampleObject = new SampleClass();
		sampleObject.setSampleField( "data" );

		Field field = SampleClass.class.getField( "sampleField" );
		System.out.println( field.get( sampleObject ) );
	}
}