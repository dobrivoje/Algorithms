package algs.TIJ4th.ForkJoinTasks.danas19.geeksforgeeks.demo;

import algs.TIJ4th.BeanInfo.User;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class Vezdzbanje {

	static final List<String> YES_NO_VALID = Arrays.asList( "yes", "no", "1", "0" );
	static final List<String> YES          = Arrays.asList( "yes", "1" );

	@SafeVarargs
	static <T, R> void processBeanField(Supplier<T> fieldSupplier, Consumer<R> fieldConsumer, Function<T, R>... processors) {
		if (processors == null || processors.length == 0) {
			return;
		}
		Arrays.stream( processors ).map( p -> p.apply( fieldSupplier.get() ) ).forEach( fieldConsumer );
	}

	public static String beautify(String text) {
		if (text == null || text.trim().isEmpty()) {
			return "";
		}
		return text.replaceAll( "(\\r\\n)", "<br>" ).replaceAll( "(\\n)", "<br>" ).replaceAll( "\\s+", " " );
	}

	/**
	 * Prva dva datuma upoređujemo s trećim,<br>
	 * sva tri moraju da su isti datum bez vremena, a prva dva moraju biti takva<br>
	 * da prvi mora bit pre drugog, i onda <b>NEMA preklapanja</b><br>
	 */
	static boolean checkStartEndTimeOverlapping(Date jobDate, Date dateToCheck, Date currentDate) {
		ZonedDateTime zJobDate = jobDate.toInstant().atZone( ZoneId.systemDefault() );
		ZonedDateTime zDateToCheck = dateToCheck.toInstant().atZone( ZoneId.systemDefault() );
		ZonedDateTime zCurrentDate = currentDate.toInstant().atZone( ZoneId.systemDefault() );
		if (zCurrentDate.toLocalDate().equals( zDateToCheck.toLocalDate() )
				&& zCurrentDate.toLocalDate().equals( zJobDate.toLocalDate() )) {
			return zJobDate.toLocalTime().isBefore( zDateToCheck.toLocalTime() );
		}
		return false;
	}

	public static void main(String[] args) {

		//<editor-fold desc="primeri...">
		String validatedAsStr = "0";
		boolean mediaConverterModel = Optional.of( validatedAsStr )
											  .map( String::trim )
											  .map( String::toLowerCase )
											  .filter( YES_NO_VALID::contains )
											  .map( YES::contains )
											  .orElseThrow( () -> new RuntimeException( "Only yes/no are valid." ) );

		System.err.println( mediaConverterModel );

		Consumer<Integer> display = System.out::println;
		display.accept( 12 );

		Consumer<List<Integer>> listDoublerConsumer = list -> {
			for (int i = 0; i < list.size(); i++) {
				if (i % 2 == 0) {
					list.set( i, list.get( i ) * 2 );
				}
			}
		};
		Consumer<List<Integer>> listPrinterConsumer = list -> list.stream().map( e -> "doubled -> " + e )
																  .forEach( System.out::println );

		List<Integer> L = new ArrayList<>();
		L.add( 2 );
		L.add( 1 );
		L.add( 3 );
		listDoublerConsumer.andThen( listPrinterConsumer ).accept( L );

		int procenatGranica = 5;

		double ukupnoElemenata = 218907;
		double procenatZaIspis = 100.00 / procenatGranica;
		double procenatTrigger1 = Math.floor( ukupnoElemenata / procenatZaIspis );

		System.err.println( procenatZaIspis + "" + " Posto = " + procenatTrigger1 );

		int proc = 0;
		for (int i = 0; i < ukupnoElemenata; i++) {
			if (i != 0 && i % procenatTrigger1 == 0) {
				++proc;
				System.err.println( "#".concat( "" + proc * (Math.round( 100.00 / procenatZaIspis )) )
									   .concat( "%" ) + ", elem. id : " + i );
			}
		}
		System.err.println( "#100%" );

		System.err.println( "------" );

		String broj = "'   00022342786 '";
		String replaced = broj == null ? "" : broj.replace( " ", "" ).replace( "'", "" );
		System.err.println( "broj = " + broj + ", replaced: " + replaced );

		String[] grupe = { "grupa1", "grupa2", "grupa3", "grupa4" };
		System.err.println( "grupe : " + String.join( ", ", grupe ) + ", su ok." );

		System.err.println( "****************" );

		int nowYear = LocalDateTime.now().getYear();
		LocalDateTime year2 = LocalDateTime.of( 2018, 9, 21, 13, 38, 45 );
		long between = ChronoUnit.YEARS.between( LocalDateTime.now(), year2 );
		System.err.println( between );
		//</editor-fold>

		//<editor-fold desc="primeri 2...">
		String opasanSadrzaj1 = ">>>> > 12>".replaceAll( "[>]+", "'>'" );
		System.err.println( "opasanSadrzaj : " + opasanSadrzaj1 );

		String opasanSadrzaj2 = "&&& && krme&brat  krme && nina".replaceAll( "[&]+", " and " )
																.replaceAll( "\\s+", " " );
		System.err.println( "opasanSadrzaj : " + opasanSadrzaj2 );

		String htmlneformatiranSadrzaj = "US1: As a Resi customer I would like to choose Invoice format with VAT or without VAT.\n"
				+ "Acceptance criteria:\n"
				+ "\n"
				+ "    Invoice template for Resi invoices with VAT is the same as template for resi Invoices > 250pounds.\n"
				+ "\n"
				+ "    Only Resi customer will have option to choose invoice format, with VAT or without VAT.";
		System.err.println( "------------------------------" );
		System.err.println( "htmlneformatiranSadrzaj :" );
		System.err.println( htmlneformatiranSadrzaj );
		htmlneformatiranSadrzaj = htmlneformatiranSadrzaj.replaceAll( "\\n", "<br>" )
														 .replaceAll( "\t", "<t>" );
		System.err.println( "------------------------------" );
		System.err.println( "******************************" );
		System.err.println( "formatirano : " );
		System.err.println( htmlneformatiranSadrzaj );

		String unformated2 =
				"We stand by our claim that Hyperoptics DSAR process is compliant with the "
						+ "\nGDPR and in line with the ICO guidance. If you are dissatisfied with this response, "
						+ "\nyou can also choose to complain to the UK Information Commissioners Office. "
						+ "\nInformation on how to do this is available at http:ico.org.ukcomplaints\n"
						+ "\n"
						+ "I understand your onboarding experience was not seamless and I am here to answer any additional questions you might have. However, I am looking forward to finding an amicable solution with you, so please let me know how you would like to proceed.\n";

		unformated2 = unformated2.replaceAll( "\n", "<br>" )
								 .replaceAll( "\\s+", " " );
		System.err.println( "------------------------------" );
		System.err.println( "******************************" );
		System.err.println( "formatirano 2: " );
		System.err.println( unformated2 );
		//</editor-fold>

		//<editor-fold desc="primer 3 - funk. interfejsi">
		Function<Integer, Integer> doubleIt = (value) -> value * 2;
		Function<Integer, Integer> add3 = (value) -> value + 3;
		Function<Integer, Integer> minus4 = (value) -> value - 4;
		Function<Integer, Integer> addThenMultiply = doubleIt.compose( minus4 ).compose( add3 );
		Integer res1 = addThenMultiply.apply( 3 );
		System.out.println( "res1 = " + res1 );

		Function<Integer, Integer> andThen1 = minus4.andThen( add3 )
													.andThen( doubleIt )
													.compose( doubleIt );
		int res2 = andThen1.apply( 13 );
		System.err.println( "res2 = " + res2 );
		//</editor-fold>

		List<User> users = new ArrayList<>( Arrays.asList(
				new User( 1L, "email-1" ),
				new User( 2L, "email-2" ),
				new User( 3L, "email-3" )
		) );

		Function<String, String> processor1 = in -> ">>> " + in + " <<<";
		Function<String, String> processor2 = in -> "processed2 : " + in;
		Function<String, String> processor3 = in -> "processed3 : " + in;

		for (User user : users) {
			processBeanField( user::getEmailAddress, user::setEmailAddress,
							  processor1, processor2, processor3 );
		}

		System.err.println( "---------result1---------" );
		System.err.println();
		users.forEach( System.err::println );

		System.err.println( "---------result2---------" );
		System.err.println();
		Function<String, String> redaljka2 = Stream.of( processor1, processor2, processor3 )
												   .reduce( input -> input, Function::andThen );

		List<User> users2 = new ArrayList<>( Arrays.asList(
				new User( 11L, "email-11" ),
				new User( 12L, "email-12" ),
				new User( 13L, "email-13" )
		) );
		users2.forEach( u -> processBeanField( u::getEmailAddress, u::setEmailAddress, redaljka2 ) );
		users2.forEach( System.err::println );

		Function<Long, String> ip1 = in -> "user: " + (in.intValue() + 1);
		Function<Long, String> ip2 = in -> "user: " + (2 * in.intValue());
		for (User user : users) {
			processBeanField( user::getUserId, user::setFirstName,
							  ip1, ip2 );
		}

		User next = users.iterator().next();
		String html1 = "\nUS1: As a Resi customer I would like to choose Invoice format with VAT or without VAT.\n"
				+ "Acceptance criteria:\n"
				+ "\n"
				+ "    Invoice template for Resi invoices with VAT is the same as template for resi Invoices > 250pounds.\n"
				+ "\n"
				+ "    Only Resi customer will have option to choose invoice format, with VAT or without VAT.";
		next.setHtml( html1 );

		Function<String, String> cleanerProcessor = ((Function<String, String>) Vezdzbanje::beautify)
				.andThen( processor1 )
				.andThen( processor2 )
				.andThen( processor3 );

		processBeanField( next::getHtml, next::setHtml, cleanerProcessor );
		processBeanField( next::getEmailAddress, next::setEmailAddress, cleanerProcessor );

		System.err.println( "---------result4 beautify ---------" );
		System.err.println();
		System.err.println( "next : " );
		System.err.println( next.getHtml() );
		System.err.println( next.getEmailAddress() );

		List<Integer> integers = Arrays.asList( 1, 3, 44, 311 );
		long count = integers.stream().filter( Arrays.asList( 2, 4, 6 )::contains )
							 .count();
		System.err.println( "count = " + count );

		//				String relatedSiteStrIds = "";
		String relatedSiteStrIds = "null,      123,88888990, 456,     98456j456,   ";
		//		String relatedSiteStrIds = null;

		if (relatedSiteStrIds == null || relatedSiteStrIds.trim().isEmpty()) {
			return;
		}

		try {
			int i = relatedSiteStrIds.trim().lastIndexOf( "," );
			Set<Long> relatedSiteIds = Stream.of( relatedSiteStrIds.split( "," ) )
											 .map( String::trim ).filter( s -> s.matches( "\\d*" ) )
											 .map( Long::parseLong )
											 .collect( Collectors.toSet() );

			System.err.println( "rezultat : " + relatedSiteIds );
		} catch (Exception e) {
			System.err.println( "greška: " + e );
		}

		Set<Integer> s1 = new HashSet<>( Arrays.asList( 1, 2, 3, 4 ) );
		Set<Integer> s2 = new HashSet<>( Arrays.asList( 5, 6, 3, 4 ) );
		Set<Integer> s3 = new HashSet<>( Arrays.asList( 7, 8, 9, 3, 4 ) );
		Set<Integer> s4 = new HashSet<>();

		s1.retainAll( s4 );
		System.err.println( "presek praznog i punog : " + s1 );
		System.err.println( "presek praznog i punog : " + s4 );

		List<Set<Integer>> skupovi = Arrays.asList( s1, s2, s3 );

		Set<Integer> res = new HashSet<>( skupovi.get( 0 ) );
		for (int i = 1; i < skupovi.size(); i++) {
			Set<Integer> s = skupovi.get( i );
			res.retainAll( s );
		}
		System.err.println( "presek " + skupovi.size() + " skupa : " + res );

		System.err.println( "kraj." );

		Map<Integer, List<Integer>> xx1 = new ConcurrentHashMap<>();
		xx1.put( 1, new ArrayList<>( Arrays.asList( 10, 11, 12 ) ) );
		xx1.put( 2, new ArrayList<>( Arrays.asList( 20, 21, 22 ) ) );
		xx1.put( 3, new ArrayList<>( Arrays.asList( 30, 31, 32 ) ) );

		xx1.computeIfPresent( 1, (id, lista) -> {
			lista.add( id );
			return lista;
		} );

		System.err.println( "*********" );

		Map<Integer, Integer> xx2 = new ConcurrentHashMap<>();
		xx2.put( 1, 0 );
		xx2.put( 2, 10 );
		xx2.computeIfPresent( 1, (eid, cnt) -> ++cnt );
		xx2.computeIfPresent( 1, (eid, cnt) -> ++cnt );
		System.err.println( xx2.getOrDefault( 1, 0 ) );

		List<Integer> ints = Arrays.asList( 1, 2, 3, 4, 5, 6 );
		Map<Integer, List<String>> resErrors = new LinkedHashMap<>();

		for (Integer num : ints) {
			resErrors.merge( num, new LinkedList<>(), (L1, L2) -> {
				System.err.println();

				L2.addAll( L1 );
				L2.add( "num*200" + (num * 200) );
				return L2;
			} );
		}

		for (Integer num : ints) {
			resErrors.merge( num, new LinkedList<>(), (L1, L2) -> {
				System.err.println();

				L2.addAll( L1 );
				L2.add( "num*200" + (num * 200) );
				return L2;
			} );
		}

		System.err.println( resErrors );

		resErrors.merge( 2, new ArrayList<>(), (l1, l2) -> {
			l1.add( "new za 2" );
			l1.addAll( l2 );
			return l1;
		} );

		System.err.println( resErrors.get( 2 ).toString() );

		Map<Integer, String> m = new LinkedHashMap<>();
		m.compute( 1, (currInt, errMsg) -> errMsg + " -> init" );
		String msg = m.get( 1 );
		System.err.println( "msg : " + msg );
		m.compute( 1, (currInt, errMsg) -> errMsg + " -> 2. put" );
		String msg2 = m.get( 1 );
		System.err.println( "msg : " + msg2 );

		m.merge( 1, "n/a", (currErrMsg, newErrMsg) -> newErrMsg + " -> 3. put" );
		String msg3 = m.get( 1 );
		System.err.println( "msg : " + msg3 );

		System.err.println( "******************" );
		String join = ints.stream().map( Objects::toString ).collect( Collectors.joining( ", " ) );
		System.err.println( join );

		String NET_BUDGET_COLUMNS = "CATEGORY:A;ITEM:B;HYPEROPTIC STOCK CODE SKU:C;"
				+ "ITEM PRICE (£):E;QTY:F;Total Line Cost (£):G;   :H;PLANNER ADVICE NOTES:I";

		Map<String, Pair<String, Integer>> resExcelCC = getExcelColumnCoordinates( NET_BUDGET_COLUMNS );
		resExcelCC.entrySet().forEach( System.err::println );

		Pair<String, Integer> a1 = Pair.of( "a", 1 );
		Pair<String, Integer> b3 = Pair.of( "b", 31 );
		Pair<String, Integer> c4 = Pair.of( "c", 41 );
		Pair<String, Integer> d5 = Pair.of( "d", 51 );
		Pair<String, Integer> e7 = Pair.of( "e", 7 );
		Pair<String, Integer> f8 = Pair.of( "f", 8 );
		Set<Pair<String, Integer>> def = new LinkedHashSet<>( Arrays.asList( a1, b3, c4, d5, e7, f8 ) );

		Pair<String, Integer> aa1 = Pair.of( "a", 1 );
		Pair<String, Integer> bb2 = Pair.of( "b", 2 );
		Pair<String, Integer> bb21 = Pair.of( "bx", 31 );
		Pair<String, Integer> cc3 = Pair.of( "c", 3 );
		Pair<String, Integer> dd4 = Pair.of( "d", 5 );
		Set<Pair<String, Integer>> exc = new LinkedHashSet<>( Arrays.asList( aa1, bb2, bb21, cc3, dd4 ) );

		System.err.println( "--------------------------------------------------------------" );
		Set<Pair<String, Integer>> symDiff = SymmetricDifference( def, exc );
		System.err.println( "symDiff = " + symDiff );
		Map<String, List<Integer>> collect = symDiff.stream().collect(
				Collectors.groupingBy( Pair::getLeft, Collectors.mapping( Pair::getRight,
																		  Collectors.toCollection( LinkedList::new ) ) ) );
		System.err.println( "--------------------------------------------------------------" );
		System.err.println( "--------------------------------------------------------------" );
		collect.entrySet().forEach( System.err::println );

		System.err.println( "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
		System.err.println( ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" );

		Set<Pair<String, Integer>> symDiff3 = SymmetricDifference2( def, exc, LinkedHashSet::new );
		System.err.println( "symDiff3 = " + symDiff3 );
		Map<String, List<Integer>> collect3 = symDiff3.stream().collect(
				Collectors.groupingBy( Pair::getLeft, Collectors.mapping( Pair::getRight, Collectors.toList() ) ) );
		collect3.entrySet().forEach( System.err::println );

		System.err.println( "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );
		System.err.println();

		Map<String, List<Integer>> nonExistingColumnNames =
				collect3.entrySet().stream().filter( v -> v.getValue().size() == 1 ).collect(
						Collectors.toMap( Map.Entry::getKey, Map.Entry::getValue ) );
		Map<String, List<Integer>> differentColumnsPlaces =
				collect3.entrySet().stream().filter( v -> v.getValue().size() == 2 ).collect(
						Collectors.toMap( Map.Entry::getKey, Map.Entry::getValue ) );

		System.err.println( "nonExistingColumnNames : " );
		nonExistingColumnNames.entrySet().forEach( System.err::println );

		System.err.println( "----" );

		System.err.println( "differentColumnsPlaces : " );
		differentColumnsPlaces.entrySet().forEach( System.err::println );

		System.err.println( "--------------xxxxxxxxxxx-------------xxxxxxxxxxx--------------xxxxxxxxxxx---------------xxxxxxxxxxx" );
		Pair<String, Integer> x1 = Pair.of( "a", 1 );
		Pair<String, Integer> x2 = Pair.of( "b", 2 );
		Pair<String, Integer> x3 = Pair.of( "c", 3 );
		Pair<String, Integer> x4 = Pair.of( "d", 4 );
		Set<Pair<String, Integer>> def2 = new LinkedHashSet<>( Arrays.asList( x1, x2, x3, x4 ) );

		Pair<String, Integer> y1 = Pair.of( "y", 1 );
		Pair<String, Integer> y2 = Pair.of( "z", 2 );
		Pair<String, Integer> y3 = Pair.of( "w", 3 );
		// Pair<String, Integer> y4 = Pair.of( "d", 4 );
		Set<Pair<String, Integer>> exc2 = new LinkedHashSet<>( Arrays.asList( y1, y2, y3/*, y4*/ ) );

		Set<Pair<String, Integer>> symDiff4 = SymmetricDifference2( def2, exc2, LinkedHashSet::new );
		System.err.println( "symDiff4 = " + symDiff4 );
		Map<String, List<Integer>> collect4 = symDiff4.stream().collect(
				Collectors.groupingBy( Pair::getLeft, Collectors.mapping( Pair::getRight, Collectors.toList() ) ) );
		collect4.entrySet().forEach( System.err::println );

		Map<Pair<String, Integer>, Set<Integer>> M = new LinkedHashMap<>();
		M.putIfAbsent( y1, new HashSet<>( Collections.singletonList( 222 ) ) );
		M.putIfAbsent( y2, new HashSet<>( Collections.singletonList( 333 ) ) );
		M.putIfAbsent( y3, new HashSet<>( Collections.singletonList( 444 ) ) );
		Set<Integer> sss = M.get( Pair.of( "y", 1 ) );
		if (sss == null) {
			throw new RuntimeException( "777" );
		} else {
			System.err.println( "sss : " + sss );
		}
		Map<Triple<Long, Long, Integer>, String> CSV = new LinkedHashMap<>();
		CSV.putIfAbsent( Triple.of( 100L, 188L, 0 ), "string 1" );
		CSV.putIfAbsent( Triple.of( 101L, 188L, 0 ), "string 2" );
		CSV.putIfAbsent( Triple.of( 102L, 188L, 0 ), "string 3" );
		CSV.putIfAbsent( Triple.of( 103L, 188L, 0 ), "string 4" );

		Triple<Long, Long, Integer> k1 = Triple.of( 100L, 188L, 0 );
		CSV.merge( k1, "", (currVal, newVal) -> currVal + " -> " + newVal );
		CSV.entrySet().forEach( System.err::println );
		CSV.merge( k1, "", (currVal, newVal) -> currVal + " -> " + newVal );
		CSV.entrySet().forEach( System.err::println );

		String a = "12";
		boolean present = Optional.of( a ).filter( "12"::equals ).isPresent();
		System.err.println( "present (12==21) ? " + present );

		String as = "aaa";
		String bs = "bbb";
		String res22 = as + String.join( "<BR>", bs );
		String collect22 = String.join( "<XXX>", as, bs );
		System.err.println( res22 );
		System.err.println( collect22 );

		String SSAIssueTypesQuoted = Stream.of( "Premise / Site management (SSA)", "Free connection request (SSA)" )
										   .map( Vezdzbanje::doubleQuote )
										   .collect( Collectors.joining( ", " ) );

		System.err.println( SSAIssueTypesQuoted );

		String with1 = "\"%s\"";
		String with2 = "(%s)";
		String res11 = String.format( with1, "AAAA" );
		String res12 = String.format( with2, "BBBB" );
		System.err.println( res11 );
		System.err.println( res12 );

		Map<Integer, String> cache = new HashMap<>();
		System.err.println( "cache : " + cache );
		String compute1 = cache.compute( 1, (ind, val) -> {
			if (val != null) {
				return val;
			} else {
				try {
					TimeUnit.MILLISECONDS.sleep( 500 );
				} catch (InterruptedException e) {
				}

				return "jedan";
			}
		} );
		System.err.println( "cache : " + cache );
		String compute2 = cache.compute( 1, (ind, val) -> {
			if (val != null) {
				return val;
			} else {
				try {
					TimeUnit.MILLISECONDS.sleep( 500 );
				} catch (InterruptedException e) {
				}

				return "jedan";
			}
		} );
		System.err.println( "cache : " + cache );

		String s = cache.putIfAbsent( 1, "jedan 2. put" );
		System.err.println( "cache : " + s );

		// jurimo ovakav datum : 2022-04-01T16:30
		String JIRA_MISTERY_DATETIME_NOC_LOSS_SERVICE_PATTERN = "yyyy-MM-dd'T'hh:mm"; // "yyyy-MM-dd'T'hh:mm:ss.SSSZ";
		String date1 = new SimpleDateFormat( JIRA_MISTERY_DATETIME_NOC_LOSS_SERVICE_PATTERN ).format( new Date() );
		System.err.println( "serviceLossDateAsString : " + date1 );

		String JIRA_MISTERY_DATETIME_PATTERN2 = "yyyy-MM-dd hh:mm a";
		String date2 = new SimpleDateFormat( JIRA_MISTERY_DATETIME_PATTERN2 ).format( new Date() );
		System.err.println( "serviceLossDateAsString : " + date2 );

		String JIRA_MISTERY_DATETIME_PATTERN1 = "dd-MM-yyyy hh:mm"; // "dd-MM-yyyy hh:mm a";// "dd/MMM/yy+hh:mm+a";
		String date3 = new SimpleDateFormat( JIRA_MISTERY_DATETIME_PATTERN1 ).format( new Date() );
		System.err.println( "serviceLossDateAsString : " + date3 );

		// jurimo ovakav datum : 2022-04-06T02:30+01:00
//		String pat1 =  "yyyy-MM-dd'T'hh:mm:ss.SSSZ";
//		String pat1 =  "yyyy-MM-dd'T'hh:mmZ";
		String pat1 =  "yyyy-MM-dd'T'hh:mmXX";
		String date4 = new SimpleDateFormat( pat1 ).format( new Date() );
		System.err.println( "serviceLossDateAsString : " + date4 );
	}

	static String doubleQuote(String element) {
		if (element == null || element.trim().isEmpty()) {
			throw new RuntimeException( "No string supplied" );
		}

		return "\"" + element + "\"";
	}

	public static <T> Set<T> SymmetricDifference(Set<T> s1, Set<T> s2) {
		if (s1.size() == s2.size()) {
			return new LinkedHashSet<>();
		}
		Set<T> union = new LinkedHashSet<>( s1 );
		union.addAll( s2 );

		Set<T> intersected = new LinkedHashSet<>( s1 );
		intersected.retainAll( s2 );

		union.removeAll( intersected );
		return union;
	}

	public static <T> Set<T> SymmetricDifference2(Set<T> s1, Set<T> s2, Supplier<Set<T>> setSupplier) {
		if (s1.size() == s2.size()) {
			//			return setSupplier.get();
		}
		Set<T> union = setSupplier.get();
		union.addAll( s1 );
		union.addAll( s2 );

		Set<T> intersected = setSupplier.get();
		intersected.addAll( s1 );
		intersected.retainAll( s2 );

		union.removeAll( intersected );
		return union;
	}

	private static Map<String, Pair<String, Integer>> getExcelColumnCoordinates(String excelColumnsDefinition) {
		return getExcelColumnCoordinates( excelColumnsDefinition, ";", ":" );
	}

	private static Map<String, Pair<String, Integer>> getExcelColumnCoordinates(
			String excelColumnsDefinition, String columnSeparator, String cellSeparator) {

		Map<String, Pair<String, Integer>> res = new LinkedHashMap<>();

		String[] columnNamesValues = excelColumnsDefinition.split( columnSeparator );
		Map<String, List<String>> possibleDuplicates =
				Stream.of( columnNamesValues )
					  .map( e -> e.split( cellSeparator ) ).map( e -> e[0] )
					  .collect( Collectors.groupingBy( k -> k, Collectors.toList() ) );

		String duplicates = possibleDuplicates.entrySet().stream().filter( e -> e.getValue().size() > 1 )
											  .map( Map.Entry::getKey )
											  .collect( Collectors.joining( ", " ) );

		if (!duplicates.isEmpty()) {
			throw new RuntimeException( "Duplicate columns defined: " + duplicates );
		}

		for (String cell : columnNamesValues) {
			String[] cellKeyValue = cell.split( ":" );
			if (cellKeyValue.length != 2) {
				throw new RuntimeException( "Not valid column(s) definition(s)." );
			}
			String cellName = cellKeyValue[0];
			String cellValue = cellKeyValue[1].toUpperCase();

			char[] cellColumnValue = cellValue.toCharArray();
			if (cellColumnValue.length != 1) {
				throw new RuntimeException( "Cell value must be a single alphabet character." );
			}
			char cellColumnCharacter = cellColumnValue[0];
			if (!Character.isAlphabetic( cellColumnCharacter )) {
				throw new RuntimeException( "Cell value must be a single alphabet character." );
			}

			int cellColumnIndex = cellColumnCharacter - 'A';
			System.err.println( cellName + " : " + cellColumnIndex );

			res.putIfAbsent( cellName, Pair.of( cellValue, cellColumnIndex ) );
		}

		return res;
	}

}
