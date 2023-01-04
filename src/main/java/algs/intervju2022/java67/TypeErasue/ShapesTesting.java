package algs.intervju2022.java67.TypeErasue;

import com.google.gson.reflect.TypeToken;
import org.springframework.core.ParameterizedTypeReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ShapesTesting {

	public static void main(String[] args) {
		List<Shape> shapes = new ArrayList<>();
		List<RoundShape> roundShapes1 = new ArrayList<>();
		roundShapes1.add( new Circle( 1 ) );
		double circleCircumSum = roundShapes1.stream().map( Shape::getCircumference )
											 .reduce( 0d, Double::sum );
		System.err.println( circleCircumSum );

		List<? super Shape> rs2 = new ArrayList<>();
		rs2.add( new Circle( 1 ) );
		rs2.add( new Square( 1 ) );

		NewShape<? extends Number> ns = new NewCircle<>( 1 );
		NewShape<? extends Number> ns2 = new NewCircle<>( 1L );
		NewShape<? super Double> ns3 = new NewCircle<>( 1 );

		System.err.println( "ns.getName() = " + ns.getName() );

		Set<? extends Number> setOfUnknownType = new LinkedHashSet<Integer>();
		setOfUnknownType = new TreeSet<Long>();
		setOfUnknownType = new TreeSet<Double>();

		Set<? extends Object> setOfObject = new HashSet<String>();
		setOfObject = new LinkedHashSet<>();

		Set<? extends Number> setOfAllSubTypeOfNumber = new HashSet<Integer>();
		setOfAllSubTypeOfNumber = new LinkedHashSet<Double>();
		setOfAllSubTypeOfNumber = new LinkedHashSet<Integer>();

		Map<String, Number> e = new HashMap<>();
		e.put( "1", 1 );
		e.put( "2", 1.02 );
		e.put( "3", 1000L );
		Set</*? extends*/ Map<String, Integer /*? extends Number*/>> ss = new LinkedHashSet<>();
		Map<String, Integer> v = new HashMap<>();
		v.put( "222", 1 );
		v.put( "333", 2 );
		v.put( "444", 3 );
		ss.add( v );

		System.err.println( "ss : " + ss );
		List<Collection<Integer>> collect = ss.stream().map( Map::values ).collect( Collectors.toList() );

		List<? super Shape> ii = new LinkedList<>();
		Shape vv = new Square( 1 );
		ii.add( vv );

		Shape[] shapes2 = new Shape[2];
		shapes2[0] = new Square( 1 );
		shapes2[1] = new Triangle( 1, 1 );
		Double ukObim = Arrays.stream( shapes2 ).map( Shape::getCircumference )
							  .reduce( 0.00, Double::sum );
		System.err.println( "ukObim = " + ukObim );

		List<Shape> shapes3 = new LinkedList<>();
		shapes3.add( new Square( 1 ) );
		shapes3.add( new Triangle( 1, 1 ) );
		Double ukObim2 = shapes3.stream().map( Shape::getCircumference )
								.reduce( 0.00, Double::sum );
		System.err.println( "ukObim2 = " + ukObim2 );


		List<? super LinearShape> shapes4 = new LinkedList<>();
		LinearShape linearShape = new Square( 1 );
		shapes4.add( linearShape );
		Square shape = (Square) shapes4.get( 0 );
		System.err.println( shape );

		ParameterizedTypeReference<List<String>> ref = new ParameterizedTypeReference<List<String>>() {};
		TypeToken<List<String>> typeToken = new TypeToken<List<String>>() {};
		System.err.println(typeToken.getType().getTypeName());

		List intList = new ArrayList<Integer>(); // raw type warning
		List<String> strList = intList; // no warning

		List<? super Shape> cShapes=Arrays.asList( new Circle( 1 ) , new Triangle( 1,1 ));
		consumerShapes( cShapes );
		createShapes( Arrays.asList( new Triangle( 1, 1 ), new Circle( 1 ) ) );
		consumerShapes( Arrays.asList( new Triangle( 1,1 ), new Circle( 1 ) ) );
		System.err.println();

		Set<Integer> unmod=new HashSet<>(Arrays.asList( 1,2 ));
		Set<Integer> integers = Collections.unmodifiableSet( unmod );
		System.err.println(integers);

		Set<Integer> vvv=new LinkedHashSet<>(Arrays.asList( 1,2,3 ));
		Set<Integer> objects = UnmodSet1( vvv );
		Integer next = objects.iterator().next();
		System.err.println( next );

		Set<Object> integers1 = UnmodSet2( vvv );
		System.err.println("integers1 : "+integers1);

		String s1 = "abc";
		String s2 = "abc";

		System.out.println("s1 == s2 is:" + (s1 == s2));
		System.out.println("s1 == s2 is:" + s1 == s2);

		HashSet<Short> shortSet = new HashSet<>();
		for (Short i = 0; i < (short)100; i++) {
			shortSet.add(i);
			shortSet.remove((short)(i - 1));
		}
		System.out.println(shortSet.size());

		HashSet<Object> shortSet2 = new HashSet<>(Arrays.asList( 1,2,3 ));
		shortSet2.remove( 1 );
		System.err.println("shortSet2.size()==2" + (shortSet2.size()==2));
	}

	public static <T> Set<T> UnmodSet1(Set<? extends T> s) {
		return new HashSet<>(s);
	}

	public static <T> Set<Object> UnmodSet2(Set<? super T> s) {
		Set<Object> res = new LinkedHashSet<>( s );
		return res;
	}

	public static void createShapes(List<? extends Shape> list) {
		System.err.println( "list : " + list.toString() );
	}

	public static void consumerShapes(List<? super Shape> l) {
		System.out.println("Processing list of string");
	}

	public void process1(List<? extends LinearShape> l,int s) {
		System.out.println("Processing list of integer");
	}
	public void process1(List< ? extends Shape> l) {
		System.out.println("Processing list of integer");
	}
}
