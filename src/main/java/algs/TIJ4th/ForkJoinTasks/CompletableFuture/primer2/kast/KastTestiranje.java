package algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer2.kast;

import algs.TIJ4th.BeanInfo.DLL;
import algs.TIJ4th.BeanInfo.User;
import algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer2.kast.infra.Enumable;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KastTestiranje {

	public static void main(String[] args) throws ParseException {
		GenericSupport gs = new GenericSupport();
		Map<Enumable, Set<ImportResultDto>> setErrors = new LinkedHashMap<>();
		Map<Enumable, List<ImportResultDto>> listErrors = new LinkedHashMap<>();

		ImportResultDto<String> ie1 = ImportResultDto.<String>builder().column( ET.OK ).result( "ok !" ).successful( true )
													 .build();
		ImportResultDto<User> ie2 = ImportResultDto.<User>builder().column( ET.NO ).result( new User() )
												   .successful( false )
												   .errorMessage( "ma kakvi - greška samo tako" )
												   .build();
		ImportResultDto<User> ie3 = ImportResultDto.<User>builder().column( ET.NO ).result( new User() )
												   .successful( false )
												   .errorMessage( "ma kakvi - greška samo tako" )
												   .build();
		ie3.setImportException( new RuntimeException( ie3.errorMessage ) );
		ImportResultDto<User> ie4 = ImportResultDto.<User>builder().column( ET.NO ).result( new User() )
												   .successful( false )
												   .errorMessage( "ma kakvi - greška samo tako" )
												   .build();
		ie4.setImportException( new RuntimeException( ie4.errorMessage ) );

		Map<Enumable, Set<ImportResultDto<DLL>>> setErrors2 = new LinkedHashMap<>();
		ImportResultDto<DLL> iDLL1 = ImportResultDto.<DLL>builder().column( ET.MESANO ).result( new DLL() )
													.successful( true ).build();
		gs.addError( iDLL1, setErrors2 );

		gs.addRepeatableError( ie1, listErrors );
		gs.addRepeatableError( ie2, listErrors );
		gs.addRepeatableError( ie3, listErrors );
		gs.addRepeatableError( ie4, listErrors );
		gs.addRepeatableError( iDLL1, listErrors );

		gs.addError( ie1, setErrors );
		gs.addError( ie2, setErrors );
		gs.addError( ie3, setErrors );
		gs.addError( ie4, setErrors );
		gs.addError( iDLL1, setErrors );

		System.err.println( "listErrors. ukupno: " + listErrors.size() + "/" +
									(listErrors.get( ET.OK ).size() + listErrors.get( ET.NO ).size()
											+ listErrors.get( ET.MESANO ).size()) );
		listErrors.entrySet().forEach( System.err::print );
		System.err.println();
		System.err.println( "------------------------------" );
		System.err.println();
		System.err.println( "setErrors. ukupno: " + setErrors.keySet().size() + "/" +
									(setErrors.get( ET.OK ).size() + setErrors.get( ET.NO ).size()
											+ listErrors.get( ET.MESANO ).size()) );
		setErrors.entrySet().forEach( System.err::print );

		boolean isMandatory = false;
		String input = "aaaaaaaaaaaaaaaaaaabb";

		Function<String, Boolean> commentValidator = s -> s == null || s.trim().length() <= 20;
		Boolean result1 = commentValidator.apply( input );
		System.err.println( "result1 : " + result1 );

		boolean result3 = !isMandatory && /*commentValidator != null &&*/ commentValidator.apply( input );
		System.err.println( "result3 : " + result3 );

		String LatinAndNonSpecChars = "^[^\\s][A-Za-z0-9?,( )*&\\-\"']+$";
		String content = "ovo 'je' neki-\"komentar\"   sa brojevima 123";
		// String content = "henry's";
		final Pattern pattern = Pattern.compile( LatinAndNonSpecChars, Pattern.MULTILINE );
		final Matcher matcher = pattern.matcher( content );

		while (matcher.find()) {
			String g0 = matcher.group( 0 );
			if (!g0.isEmpty()) {
				System.err.println( "string je ok" );
			}
		}
		//		if (!matcher.matches()) {
		System.err.println( "string ima grešku !" );
		//		}

		String area = "dobrivoje prtenjak,          omladinska 9";
		Optional<String> optIme = Optional.of( area ).map( s -> s.split( "," ) )
										  .filter( e -> e.length > 0 )
										  .map( e -> e[0] ).map( String::trim );
		optIme.map( s -> "'" + s + "'" ).ifPresent( System.err::println );

		Function<String, Integer> strToIntConverter = Integer::valueOf;
		Function<Integer, Boolean> zeroOneValidator = iNt -> iNt != null && (iNt == 0 || iNt == 1) && iNt == 1;
		Function<String, Boolean> CSVColumnConverter = strToIntConverter.andThen( zeroOneValidator );

		Boolean apply = CSVColumnConverter.apply( "2" );
		System.err.println( apply );

		LocalDateTime ldt = LocalDateTime.now();
		System.err.println( "ldt : " + ldt );
		ldt = ldt.withSecond( 0 ).withNano( 0 );
		System.err.println( "ldt -> " + ldt );

		Double x = 8.000001d;
		System.err.println( "8.00d > 8 ? " + (x > 8.00d) );

		System.err.println( "------------" );
		Gson gson = new Gson();
		String dateAsStr = gson.toJson( new Date() );
		System.err.println( "date as string : " + dateAsStr );
		Date date = gson.fromJson( dateAsStr, Date.class );
		System.err.println( "date : " + date );

		System.err.println( "2. ----------------" );
		String dateAsStr2 = "Jul 18, 2022 5:13:00 PM";
		String dateAsStr3 = "Jul 18, 2022 7:00:30 PM";

		// System.err.println( "date2 : " + gson2.fromJson( dateAsStr2,Date.class ) );
		/*String df1 = gson2.toJson( dateAsStr2, Date.class );
		System.err.println( "date2 : " + df1 );*/

		String JIRA_WEBHOOK_ETA_MISTERY_DATEFORMAT = "dd/MMM/yy h:mm a";
		Date date2 = new SimpleDateFormat( JIRA_WEBHOOK_ETA_MISTERY_DATEFORMAT ).parse( "18/Jul/22 5:13 PM" );
		System.err.println( "date2 : " + date2 );

		System.err.println( "3. ------------------" );
		String hourAndMinuteAsStr = "11:59";
		String[] hm = hourAndMinuteAsStr.split( ":" );
		int h = Integer.parseInt( hm[0] );
		int m = Integer.parseInt( hm[1] );
		System.err.println( "hm : " + Arrays.asList( h, m ) );

		List<Integer> emptyStream = Stream.builder().build().map( e -> (Integer) e ).collect( Collectors.toList() );
		System.err.println( "emptyStream : " + emptyStream );

		String L = emptyStream.stream() // pogrešan zaključak : Stream.of( null/*, 1, 2, 3*/ )
							  .map( KastTestiranje::getNetOutageSMSParameters )
							  .filter( s -> !s.isEmpty() )
							  .findFirst()
							  .orElseThrow( () -> new RuntimeException( "Incident SMS template should have SMS parameters setup." ) );

		System.err.println( "L = " + L );
	}

	private static String getNetOutageSMSParameters(Integer integer) {
		return integer == null ? "" : integer.toString();
	}
}
