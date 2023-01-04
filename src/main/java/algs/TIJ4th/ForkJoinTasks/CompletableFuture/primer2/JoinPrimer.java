package algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer2;

import algs.Util.DataConversionUtil;
import lombok.Getter;
import lombok.NonNull;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class JoinPrimer {

	@Getter
	enum ERD {
		LO( "looo" ),
		HI( "hihh" ),
		OK( "okk" );

		public final String naziv;

		ERD(String naziv) {
			this.naziv = naziv;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread( () -> {
			System.err.println( "t1 - startovan..." );
			try {
				TimeUnit.MILLISECONDS.sleep( 800 );
			} catch (InterruptedException e) {
			}
			System.err.println( "t1 - kraj." );
		} );

		Thread t2 = new Thread( () -> {
			System.err.println( "t2 - startovan..." );
			try {
				TimeUnit.MILLISECONDS.sleep( 300 );
			} catch (InterruptedException e) {
			}
			System.err.println( "t2 - kraj." );
		} );

		Thread t3 = new Thread( () -> {
			System.err.println( "t3 - startovan..." );
			try {
				TimeUnit.MILLISECONDS.sleep( 100 );
			} catch (InterruptedException e) {
			}
			System.err.println( "t3 - kraj." );
		} );

		/*System.out.println( Thread.currentThread().getName() + " is Started" );
		t1.start();
		t1.join();
		t2.start();
		t2.join();
		t3.start();
		t3.join();
		System.out.println( Thread.currentThread().getName() + " is Started" );*/

		List<String> siteAddress = getSiteAddress( 123 );
		System.err.println( "siteAddress : " + siteAddress );
		System.err.println( "-----------" );

		String value = null;
		boolean isNullStrEmpty = Optional.ofNullable( value ).map( String::trim ).map( String::isEmpty ).orElse( true );
		System.err.println( "isNullStrEmpty ? " + isNullStrEmpty );

		boolean isEmpty = Optional.ofNullable( value ).map( String::trim ).map( String::isEmpty ).orElse( true );
		System.err.println( "isEmptyStrPresent ? " + isEmpty );

		boolean isNonEmptyStrEmpty = Optional.ofNullable( value ).map( String::trim ).map( String::isEmpty ).orElse( true );
		System.err.println( "isNonEmptyStrEmpty ? " + isNonEmptyStrEmpty );

		boolean isValidSiteName = !Optional.ofNullable( value ).map( String::trim ).map( String::isEmpty )
										   .orElse( true );
		System.err.println( "isValidSiteName ? " + isValidSiteName );

		String xx = "";
		boolean isPresent1 = Optional.ofNullable( xx ).map( String::trim ).isPresent();
		System.err.println( "isPresent 1 ? " + isPresent1 );
		boolean isPresent2 = Optional.ofNullable( xx ).map( String::trim ).filter( s -> !s.isEmpty() ).isPresent();
		System.err.println( "isPresent 2 ? " + isPresent2 );
		boolean isStrNull2_greska = Optional.ofNullable( xx ).map( String::trim ).filter( String::isEmpty ).isPresent();
		System.err.println( "isStrNull2_greska ? " + isStrNull2_greska );
		boolean isStrNull1 = Optional.ofNullable( xx ).map( String::trim ).map( String::isEmpty ).orElse( true );
		System.err.println( "isStrNull or empty 1 ? " + isStrNull1 );

		System.err.println( "--------------------" );
		Double lA = 12.0000009999;
		Double lB = 12.0000009999;

		System.err.println( "lA.compareTo( lB ) : " + lA.compareTo( lB ) );

		String engineerName = "eng 1,   dobrivoje   prtenjak";
		String[] engineerNames = Optional.ofNullable( engineerName )
										 .map( String::trim )
										 .map( s -> s.split( "," ) )
										 .orElse( null );
		for (String name : engineerNames) {
			System.err.println( "engineerNames: " + name.trim().replaceAll( "\\s+", " " ) );
		}

		List<String> verzije = Arrays.asList( "1", "4", "8", "dfg", " ", "2" );
		Long posl = verzije.stream().map( String::trim )
						   .filter( s -> !s.isEmpty() )
						   .map( DataConversionUtil::ToLong )
						   .filter( Objects::nonNull )
						   .max( Comparator.comparingLong( v -> v ) )
						   .orElse( 0L );

		System.err.println( "max broj : " + posl );

		User u1 = new User( "Aaman", 4 );
		User u2 = new User( "Joyita", 1 );
		User u3 = new User( "Suvam", 2 );
		User u4 = new User( "Mahafuj", 3 );

		System.out.println( "---------------------" );
		List<User> users = Arrays.asList( u1, u2, u3, null, u4 );
		users.sort( Comparator.nullsLast( Comparator.comparing( User::getId ) )
							  .thenComparing( User::getName ) );
		users.forEach( System.err::println );

		// poslednji element niza :
		List<String> list = Collections.emptyList(); // Arrays.asList("node", "java", "c++", "react", "javascript");
		String result = list.stream().reduce( (first, second) -> second ).orElse( "no last element" );
		System.out.println( result );

		ERD erd = ERD.HI;
		Enum<?> e = erd;
		ERD s = (ERD) e;
		System.err.println( "Naziv ali preko kastovanog Enum<?> : " + s.naziv );

		int param2 = 79;
		BiFunction<List<Integer>, List<Integer>, List<Integer>> res1 = uniteCollectionsProcessor( param2 );
		List<Integer> l1 = new ArrayList<>( Arrays.asList( 1, 2, 3 ) );
		List<Integer> l2 = new LinkedList<>( Arrays.asList( 10, 11 ) );
		List<Integer> applied = res1.apply( l1, l2 );
		System.err.println( "applied : " + applied );
		List<Integer> l3 = new LinkedList<>( Arrays.asList( 1, 2, 3, 20 ) );
		List<Integer> integers2 = UniteCollections( () -> l1, l2, l3 );
		System.err.println( "integers2 : " + integers2 );

		BiFunction<Set<Integer>, Set<Integer>, Set<Integer>> res2 = uniteCollectionsProcessor( 1 );
		Set<Integer> s1 = new HashSet<>( Arrays.asList( 1, 2, 3 ) );
		Set<Integer> s2 = new LinkedHashSet<>( Arrays.asList( 10, 11 ) );
		Set<Integer> applied2 = res2.apply( s1, s2 );
		System.err.println( "applied sets : " + applied2 );

		System.err.println();

		Set<Integer> s3 = new LinkedHashSet<>( Arrays.asList( 1, 2, 3, 10, 11, 20, 21 ) );
		Set<Integer> res3 = UniteCollections( () -> s1, s2, s3 );
		System.err.println( "res3 : " + Arrays.toString( res3.toArray() ) );

		System.err.println("------------------------");
		Function<String, Integer> intExtractor = Integer::valueOf;
		Integer no1 = intExtractor.apply( "112" );

		Function<Integer, Boolean> cond1Processor = paran -> paran % 2 == 0;
		Function<Boolean, Integer> cond2Processor = in -> !in ? 19 : no1;
		Function<String, Integer> finalFormula = intExtractor.andThen( cond1Processor ).andThen( cond2Processor );

		System.err.println("333 : " + finalFormula.apply( "333" ));
		System.err.println("112 : " + finalFormula.apply( "112" ));
	}

	public static <T, R extends Collection<T>> BiFunction<R, R, R> uniteCollectionsProcessor(T withElement) {
		return (c1, c2) -> {
			c2.add( withElement );
			c1.addAll( c2 );
			return c1;
		};
	}

	@SafeVarargs
	public static <T, R extends Collection<T>> R UniteCollections(@NonNull Supplier<R> colSupplier, R... collections) {
		R result = colSupplier.get();
		if (collections == null || collections.length == 0) {
			return result;
		}
		Arrays.stream( collections ).forEach( result::addAll );
		return result;
	}

	private static List<String> getSiteAddress(long siteId) {
		String siteName = "Broomhouse, Glasgow G69 7UR - POD 6 (Taylor Wimpey) #TW";

		String latitudeAsStr = Optional.of( "51.5024310410527" )
									   .map( Objects::toString )
									   .map( String::trim )
									   .filter( s -> !s.isEmpty() )
									   .map( s -> "lat: " + s )
									   .orElse( "" );
		String longitudeAsStr = Optional.of( "-0.0832491143455563" )
										.map( Objects::toString )
										.map( String::trim )
										.filter( s -> !s.isEmpty() )
										.map( s -> "lon: " + s )
										.orElse( "" );

		boolean isValidCoordinates = !latitudeAsStr.isEmpty() && !longitudeAsStr.isEmpty();

		String coordinates = !isValidCoordinates ? "" : new StringJoiner( ", " ).add( "coordinates: " + latitudeAsStr )
																				.add( longitudeAsStr ).toString();
		StringJoiner siteAddressJoiner = new StringJoiner( ", " ).add( siteName );
		if (isValidCoordinates) {
			siteAddressJoiner.add( coordinates );
		}

		String siteAddress = siteAddressJoiner.toString();
		return Collections.singletonList( siteAddress );
	}

}
