package algs.TIJ4th.ForkJoinTasks.danas19.geeksforgeeks.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author д06ри, dobri7@gmail.com
 */
public enum AnonymizationReason {
	BECOME_CUSTOMER( "Become customer" ),
	UNBSUBSCRIBED_FROM_RECEIVING_UPDATES( "Unsubscribed from receiving updates" ),
	THREE_CALENDAR_YEARS( "3 calendar years" ),
	RTBF( "RtbF" );

	private String name;

	AnonymizationReason(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void deleteTicketsPermanently(Collection<Integer> userRequestedTicketIds, int maxElementsForDeleteion) {
		List<Integer> allElements = new ArrayList<>( userRequestedTicketIds );

		if (userRequestedTicketIds.size() > maxElementsForDeleteion) {
			List<Integer> nextBulkDeletionIds = new ArrayList<>( userRequestedTicketIds.size() - maxElementsForDeleteion );
			for (int i = maxElementsForDeleteion; i < userRequestedTicketIds.size(); i++) {
				nextBulkDeletionIds.add( allElements.get( i ) );
			}

			allElements.removeAll( nextBulkDeletionIds );
			deleteTicketsPermanently( nextBulkDeletionIds, maxElementsForDeleteion );
		}

		String formattedTicketIds = allElements.stream().map( String::valueOf ).collect( Collectors.joining( "," ) );
		System.err.println( "poziv: " + formattedTicketIds );
	}

	public static String replaceAllHTMLLinksInContentWith(String contentToReplace, String expression, String replaceWith) {
		if (contentToReplace == null || contentToReplace.trim().isEmpty()) {
			return "";
		}

		String regex = "(http(s)?:\\/\\/)?(www.)?([a-zA-Z0-9])+([\\-\\.]{1}[a-zA-Z0-9]+)*\\.[a-zA-Z]{2,5}(:[0-9]{1,5})?([^\\s]*)?$";
		final Pattern pattern = Pattern.compile( regex, Pattern.MULTILINE );
		final Matcher matcher = pattern.matcher( contentToReplace );

		while (matcher.find()) {
			String htmlLinkGroup = matcher.group( 0 );
			contentToReplace = contentToReplace.replace( htmlLinkGroup, MessageFormat.format( expression, replaceWith ) );
		}

		return contentToReplace;
	}

	public static String replaceAllHTMLLinksInContentWith(String contentToReplace, String replaceWith) {
		return replaceAllHTMLLinksInContentWith( contentToReplace, "{0}", replaceWith );
	}

	public static String replaceAllHTMLLinksInContentWith(String contentToReplace, MessageFormat messageFormat) {
		if (contentToReplace == null || contentToReplace.trim().isEmpty()) {
			return "";
		}

		String regex = "(http(s)?:\\/\\/)?(www.)?([a-zA-Z0-9])+([\\-\\.]{1}[a-zA-Z0-9]+)*\\.[a-zA-Z]{2,5}(:[0-9]{1,5})?([^\\s]*)?$";
		final Pattern pattern = Pattern.compile( regex, Pattern.MULTILINE );
		final Matcher matcher = pattern.matcher( contentToReplace );

		while (matcher.find()) {
			String htmlLinkGroup = matcher.group( 0 );
			contentToReplace = contentToReplace.replace( htmlLinkGroup, messageFormat.toPattern() );
		}

		return contentToReplace;
	}

	public static void main(String[] args) {

		String threeCalendarYears = AnonymizationReason.THREE_CALENDAR_YEARS.name();
		System.err.println( threeCalendarYears );

		String RFC1123_DATE_FORMAT_PATTERN = "EEE, dd MMM yyyy HH:mm:ss zzz";
		System.err.println( new SimpleDateFormat( RFC1123_DATE_FORMAT_PATTERN ).format( new Date() ) );

		String RFC1036_DATE_FORMAT_PATTERN = "EEEE, dd-MMM-yy HH:mm:ss zzz";
		System.err.println( new SimpleDateFormat( RFC1036_DATE_FORMAT_PATTERN ).format( new Date() ) );

		String ANSI_C_ASCTIME_DATE_FORMAT_PATTERN = "EEE MMM d HH:mm:ss yyyy";
		System.err.println( new SimpleDateFormat( ANSI_C_ASCTIME_DATE_FORMAT_PATTERN ).format( new Date() ) );

		// ovo ne radi :
		String JIRA_DATETIME_PATTERN = "dd-MM-yyyy hh:mm a";
		System.err.println( new SimpleDateFormat( JIRA_DATETIME_PATTERN ).format( new Date() ) );

		String JIRA_MISTERY_DATETIME_PATTERN1 = "dd/MMM/yy+hh:mm+a";
		System.err.println( new SimpleDateFormat( JIRA_MISTERY_DATETIME_PATTERN1 ).format( new Date() ) );

		String JIRA_MISTERY_DATETIME_PATTERN2 = "yyyy-MM-dd hh:mm a";
		System.err.println( new SimpleDateFormat( JIRA_MISTERY_DATETIME_PATTERN2 ).format( new Date() ) );

		String JIRA_MISTERY_DATETIME_NOC_LOSS_SERVICE_PATTERN = "yyyy-MM-dd'T'hh:mm:ss.SSSZ";
		System.err.println( new SimpleDateFormat( JIRA_MISTERY_DATETIME_NOC_LOSS_SERVICE_PATTERN ).format( new Date() ) );

		String GB_DATETIME_PATTERN = "d/MMM/yy hh:mm:SSSZ";
		System.err.println( new SimpleDateFormat( GB_DATETIME_PATTERN ).format( new Date() ) );

		String SER_DATETIME_PATTERN = "dd.MM.yyyy HH:mm:ss";
		System.err.println( new SimpleDateFormat( SER_DATETIME_PATTERN ).format( new Date() ) );

		List<String> l1 = new ArrayList<>();
		List<String> l2 = Arrays.asList( "1", "2", "3" );
		String result1 = l1.stream().collect( Collectors.joining( ", " ) );
		String result2 = l2.stream().collect( Collectors.joining( ", " ) );
		System.err.println( "result1 = " + result1 + ", lenght: " + l1.size() );
		System.err.println( "result2 = " + result2 + ", lenght: " + l2.size() );

		Collection<Integer> userRequestedTicketIds = Arrays.asList( 1, 4, 5, 8, 9, 110 );
		String res2 = userRequestedTicketIds.stream().map( String::valueOf ).collect( Collectors.joining( "," ) );
		System.err.println( "res2 : " + res2 );

		System.err.println( "test 2 - rekurzivno brisanje 100 elemenata 6 elemenata" );
		Set<Integer> test1 = new HashSet<>( Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 ) );
		deleteTicketsPermanently( test1, 6 );

		System.err.println( "test 2 - rekurzivno brisanje 100 elemenata : " );
		Set<Integer> test2 = IntStream.rangeClosed( 1, 271 ).boxed().collect( Collectors.toSet() );
		deleteTicketsPermanently( test2, 100 );

		System.err.println( "--------------------------" );

		LocalDateTime createdAt = LocalDateTime.of( 2014, 10, 18, 6, 58, 10 );
		long yearsBetween = ChronoUnit.YEARS.between( createdAt, LocalDateTime.now() );
		boolean is7YearsPlus = yearsBetween >= 7;
		System.err.println( "yearsBetween ? " + yearsBetween );
		System.err.println( "is7YearsPlus ? " + is7YearsPlus );

		LocalDateTime ldt7 = LocalDateTime.now().minusYears( 7 );
		System.err.println( ldt7 );

		System.err.println( "--------------------------" );

		LocalDateTime lll7 = LocalDateTime.now().minusYears( 7 );
		System.err.println( lll7 );

		String s1 = "dobrivoje __/ / ok";
		String res1 = s1.replaceAll( "[_/]+", "and" );
		System.err.println( "res = " + res1 );

		String s2 = "dobrivoje &&&& &    ok 77    ";
		String res21 = s2.replaceAll( "[&]+", "and" );
		res21 = res21.replaceAll( "[ ]+", " " );
		System.err.println( "res = " + res21 );

		System.err.println( "-------------------" );
		System.err.println( "briši sve između dva stringa" );
		String html = "Chat started: 2021-05-14 05:23 PM UTC\n"
				+ "Served by: Dino Mujanovic\n"
				+ "\n"
				+ "IP: 37.156.72.75\n"
				+ "User Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 14_4_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.3 Mobile/15E148 Safari/604.1\n"
				+ "Country: United Kingdom\n"
				+ "City: London\n"
				+ "URL: https://www.hyperoptic.com/?utm_medium=email&utm_campaign=best_offer_eoc_notice_dynamic_email_template&utm_content=best_offer_eoc_notice_dynamic_email_template%20ID_cb1895cf-1c7f-4184-a158-31ef36c26d3a&utm_source=Email%20CM&utm_term=website\n"
				+ "\n"
				+ "Chat ID: 2105.781583.SXPJV3nQPjnIT\n"
				+ "\n"
				+ "The chat transcript will be appended when the agent or visitor leaves the chat.";

		html += " 22222222222222222222222222222222222222222 " +
				"Chat started: 2019-01-16 08:11 PM UTC\n"
				+ "Served by: -\n"
				+ "\n"
				+ "IP: 90.218.94.54\n"
				+ "User Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Mobile/15E148 Safari/604.1\n"
				+ "Country: United Kingdom\n"
				+ "City: Ipswich\n"
				+ "URL: https://hyperoptic.com/\n"
				+ "\n"
				+ "Chat ID: 1901.781583.RFLjQI1R0psGi ";

		html += " 333333333333333333333333333333333333333333 " +
				"Chat started: 2019-01-16 08:11 PM UTC\n"
				+ "Served by: -\n"
				+ "\n"
				+ "IP: 90.218.94.54\n"
				+ "User Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Mobile/15E148 Safari/604.1\n"
				+ "Country: United Kingdom\n"
				+ "City: Ipswich\n"
				+ "URL: https://hyperoptic.com/\n"
				+ "\n"
				+ "Chat ID: 1901.781583.RFLjQI1R0psGi ";

		String res5 = "stackoverflow is really awesomeURL:remove123/n I love it";
		res5 = res5.replaceAll( "/n.*/n *", " " );
		System.err.println( "res5" );
		System.err.println( res5 );

		System.err.println( "******************************" );
		System.err.println( "******************************" );
		System.err.println();

		// String res = replaceAllHTMLLinksInContentWith( html, "<a link=\"{0}\" <a/>", "www.HO.com" );
		// String res = replaceAllHTMLLinksInContentWith( html, "www.HO.com" );
		String messageFormat = MessageFormat.format( "<a link=\"{0}\" <a/>", "www.HO.com" );
		String res = replaceAllHTMLLinksInContentWith( html, messageFormat );

		System.err.println( res );

		System.err.println();
		System.err.println();
		System.err.println( "******************************************************" );

		String ipv6smece = "list: <01 160 customer450897 2a01:4b00:e054:8300::/56>} "
				+ "system-view Enter system view, return user view with Ctrl+Z"
				+ ". [m41ph-p]clear configura"
				+ "......"
				+ "list: <01 160 customer450897 2a01:4b00:e054:8300::/56>} \" \n"
				+ "\t\t\t\t+ \"system-view Enter system view, return user view with Ctrl+Z\" \n"
				+ "\t\t\t\t+ \". [m41ph-p]clear configura";

		System.err.println( "-------------" );
		String res8 = ipv6smece.replaceAll( "<", "" ).replaceAll( ">", "" )
							   .replaceAll( "/", "" );
		System.err.println( res8 );

		System.err.println();
		System.err.println( "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" );
		System.err.println();

		Set<Long> customersForDeletion = LongStream.range( 1L, 1320L ).boxed()
												   .collect( Collectors.toCollection( LinkedHashSet::new ) );

		double parallelCustomersForExecution = 10d;
		double singleCustomerAnonymizationTimeExecution = 60d;

		double batchesPerParallelCustomers = ((double) customersForDeletion.size()) / parallelCustomersForExecution;
		batchesPerParallelCustomers = batchesPerParallelCustomers < 1 ? 1 : batchesPerParallelCustomers;
		int multiJobsInSingleExecution = (int) (singleCustomerAnonymizationTimeExecution * batchesPerParallelCustomers);

		LocalDateTime ldtEndTime = java.time.LocalDateTime.now().plusSeconds( multiJobsInSingleExecution );
		System.err.println( "ldtEndTime : " + ldtEndTime );

		String JSON_DESERIALIZATION_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
		System.err.println( new SimpleDateFormat( JSON_DESERIALIZATION_DATE_FORMAT ).format( new Date() ) );

		String DATEPARAM_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
		System.err.println( new SimpleDateFormat( DATEPARAM_DATE_FORMAT ).format( new Date() ) );
		try {
			System.err.println( new SimpleDateFormat( DATEPARAM_DATE_FORMAT ).parse( "2021-11-03T18:35:53.973+0100" ) );
		} catch (ParseException e) {
			System.err.println( "greška za : " + DATEPARAM_DATE_FORMAT );
		}

		Double workDaysInSeconds = 57600.0 / 12;

		BigDecimal bd = new BigDecimal( Double.toString( workDaysInSeconds / (3600 * 8) ) );
		bd = bd.setScale( 2, RoundingMode.HALF_UP );
		double twoDecimal = bd.doubleValue();

		System.err.println( workDaysInSeconds / (3600 * 8) );
		System.err.println( "twoDecimal = " + twoDecimal );

		String summary = "IPv4 assignment failed for customer id: 493035";
		String xxx = "search?jql=project = NOC AND issuetype=\\\"Customer related issues\\\" "
				+ "AND summary~\\\"" + summary + "\\\" ORDER BY updated DESC";
		System.err.println( "xxx :" );
		System.err.println( xxx );
	}
}
