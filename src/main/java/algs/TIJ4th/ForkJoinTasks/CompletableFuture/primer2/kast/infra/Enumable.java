package algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer2.kast.infra;

import algs.Util.DateUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simulates {@link Enum}
 */
public interface Enumable extends Nameable,
								  Columnable {

	static void main(String[] args) {
		LocalDate incidentNetOutageCreationDate = LocalDate.now().minusDays( 1 );
		System.err.println( "incidentNetOutageCreationDate = " + incidentNetOutageCreationDate );

		LocalDate a17hours = LocalDate.from( incidentNetOutageCreationDate ).plusDays( 1 );
		System.err.println( "a17hours : " + a17hours );

		long netOutageDays = ChronoUnit.DAYS.between( incidentNetOutageCreationDate, a17hours );
		System.err.println( "netOutageDays = " + netOutageDays );

		LocalDateTime start = LocalDateTime.now().truncatedTo( ChronoUnit.HOURS ).plusDays( 1 )
										   .withHour( 7 ).minusNanos( 0 );

		LocalDateTime intervalDayStart = LocalDateTime.now().truncatedTo( ChronoUnit.HOURS ).withHour( 7 );
		LocalDateTime intervalDayEnd = LocalDateTime.now().truncatedTo( ChronoUnit.HOURS ).withHour( 21 );

		boolean isOverlapDaytime = DateUtil.isDatesOverlapping( start, start, intervalDayStart, intervalDayEnd, true, true );
		boolean isOverlapNighttime = !isOverlapDaytime;

		System.err.println( "isOverlapDaytime : " + isOverlapDaytime );
		System.err.println( "isOverlapNighttime : " + isOverlapNighttime );

		LocalDateTime ldt1 = LocalDateTime.now().truncatedTo( ChronoUnit.DAYS )
										  .minusNanos( 1 );
		LocalDate d1 = ldt1.toLocalDate();
		LocalDateTime ldt2 = LocalDateTime.now();
		LocalDate d2 = ldt2.toLocalDate();
		System.err.println( "ldt1 : " + ldt1 + ", ldt1: " + ldt2 );
		long between = 1 + Math.abs( ChronoUnit.DAYS.between( d2, d1 ) );
		System.err.println( "between : " + between );

		LocalDateTime ldt3 = ldt1.truncatedTo( ChronoUnit.MINUTES );
		System.err.println( ldt3 );

		String enoe = "12";
		String enoeIds = enoe.substring( 0, enoe.length() - 2 );
		System.err.println( "enoeIds = " + enoeIds );

		List<Integer> LL1 = Arrays.asList( 4, 5, 1, 12, 8, 7, 3 ).stream().sorted() // .sorted( Comparator.comparingInt( e -> -e ) )
								  .collect( Collectors.toList() );
		System.err.println( LL1 );

		List<Integer> ll2 = LL1.stream().filter( e -> e < 123_000_000 )
							   .collect( Collectors.toList() );
		System.err.println( ll2 );

		String TTT = "EEE, dd MMM yyyy HH:mm:ss zzz";
		System.err.println( new SimpleDateFormat( TTT ).format( new Date() ) );
		LocalDateTime ldt4 = LocalDateTime.now().plusDays( 4 );

		System.err.println( new SimpleDateFormat( TTT ).format( DateUtil.FromLocalDateTime( ldt4 ) ) );

		LocalDate ldt5 = DateUtil.ToLocalDate( new Date() );
		System.err.println( DateUtil.FromLocalDate( ldt5 ) );

		LocalDateTime ldt6 = DateUtil.ToLocalDateTime( new Date() ).truncatedTo( ChronoUnit.DAYS );
		System.err.println( DateUtil.FromLocalDateTime( ldt6 ) );

		String dayTime = "PM";
		boolean isAM = "AM".equalsIgnoreCase( dayTime );
		boolean isPM = "PM".equalsIgnoreCase( dayTime );
		boolean isEVE = "EVE".equalsIgnoreCase( dayTime );

		boolean isNotTimeOfDay = !isAM && !isPM && !isEVE;

		LocalDateTime now = LocalDateTime.now();

		int startHour = isAM ? 9 : isPM ? 13 : isEVE ? 17 : now.getHour();
		int startMinute = isNotTimeOfDay ? now.plusMinutes( 1 ).getMinute() : 0;

		int endHour = isAM ? 13 : isPM ? 17 : isEVE ? 20 : now.getHour();
		int endMinute = isNotTimeOfDay ? now.plusMinutes( 1 ).getMinute() : 0;

		LocalDateTime ldtStart = LocalDateTime.now().truncatedTo( ChronoUnit.DAYS )
											  .withHour( startHour )
											  .withMinute( startMinute );
		LocalDateTime ldtEnd = LocalDateTime.now().truncatedTo( ChronoUnit.DAYS )
											.withHour( endHour )
											.withMinute( endMinute );
		LocalDateTime ldtEnd2 = LocalDateTime.now().toLocalDate().atStartOfDay()
											 .withHour( endHour )
											 .withMinute( endMinute );

		System.err.println( ldtStart );
		System.err.println( ldtEnd );
		System.err.println( ldtEnd2 );
		System.err.println( "ldtEnd2 == ldtEnd ? " + ldtEnd2.equals( ldtEnd ) );
		System.err.println( "isNotTimeOfDay ? " + isNotTimeOfDay );

		LocalDateTime NOW = LocalDateTime.now().toLocalDate().atStartOfDay()
										 .plusHours( 23 )
										 .plusMinutes( 0 );
		boolean isNextDay = NOW.plusHours( 1 ).toLocalDate().isAfter( NOW.toLocalDate() );
		System.err.println( "isNextDay ? " + isNextDay );
	}
}
