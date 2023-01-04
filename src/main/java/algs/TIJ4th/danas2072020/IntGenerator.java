package algs.TIJ4th.danas2072020;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.DayOfWeek.*;

public abstract class IntGenerator {

	private volatile boolean canceled = false;

	public abstract int next();

	public void cancel() {
		canceled = true;
	}

	public boolean isCanceled() {
		return canceled;
	}

	static class ObjHolder<T> {

		private T value;

		public ObjHolder() {
		}

		public ObjHolder(T value) {
			this.value = value;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value == null ? "n/a" : value.toString();
		}
	}

	public static class Test {

		int value;

		public int getValue() {
			return value;
		}

		public void reset() {
			value = 0;
		}

		// Calculates without exception
		public void method1(int i) {
			value = ((value + i) / i) << 1;
			// Will never be true
			if ((i & 0xFFFFFFF) == 1000000000) {
				System.out.println( "You'll never see this!" );
			}
		}

		// Could in theory throw one, but never will
		public void method2(int i) throws Exception {
			value = ((value + i) / i) << 1;
			// Will never be true
			if ((i & 0xFFFFFFF) == 1000000000) {
				throw new Exception();
			}
		}

		// This one will regularly throw one
		public void method3(int i) throws Exception {
			value = ((value + i) / i) << 1;
			// i & 1 is equally fast to calculate as i & 0xFFFFFFF; it is both
			// an AND operation between two integers. The size of the number plays
			// no role. AND on 32 BIT always ANDs all 32 bits
			if ((i & 0x1) == 1) {
				throw new Exception();
			}
		}

		public void method4(int i) {
			value = ((value + i) / i) << 1;
			// i & 1 is equally fast to calculate as i & 0xFFFFFFF; it is both
			// an AND operation between two integers. The size of the number plays
			// no role. AND on 32 BIT always ANDs all 32 bits
			if ((i & 0x1) == 1) {
				throw new RuntimeException();
			}
		}

		public static void main(String[] args) {
			int i;
			long l;
			Test t = new Test();

			l = System.currentTimeMillis();
			t.reset();
			int noOfIterations = 10_000_000;

			for (i = 1; i < noOfIterations; i++) {
				t.method1( i );
			}
			l = System.currentTimeMillis() - l;
			System.out.println(
					"method1 took " + l + " ms, result was " + t.getValue()
			);

			l = System.currentTimeMillis();
			t.reset();
			for (i = 1; i < noOfIterations; i++) {
				try {
					t.method2( i );
				} catch (Exception e) {
					System.out.println( "You'll never see this!" );
				}
			}
			l = System.currentTimeMillis() - l;
			System.out.println(
					"method2 took " + l + " ms, result was " + t.getValue()
			);

			l = System.currentTimeMillis();
			t.reset();
			for (i = 1; i < noOfIterations; i++) {
				try {
					t.method3( i );
				} catch (Exception e) {
					// Do nothing here, as we will get here
				}
			}
			l = System.currentTimeMillis() - l;
			System.out.println(
					"method3 took " + l + " ms, result was " + t.getValue()
			);

			l = System.currentTimeMillis();
			t.reset();
			for (i = 1; i < noOfIterations; i++) {
				try {
					t.method4( i );
				} catch (Exception e) {
					// Do nothing here, as we will get here
				}
			}
			l = System.currentTimeMillis() - l;
			System.out.println(
					"method4 took " + l + " ms, result was " + t.getValue()
			);
		}
	}

	public static void main(String[] args) {
		boolean isErrSupportType = "SUPPORT TYPE" == null;
		boolean isErrNmeTeam = "NME" == null;
		boolean isErrHandOverDate = "null" == null;

		StringBuilder errSb = new StringBuilder();
		errSb.append( isErrSupportType ? "Support type, " : "" )
			 .append( isErrNmeTeam ? "NME team, " : "" )
			 .append( isErrHandOverDate ? "Handover date" : "" );
		String errNew = errSb.toString();
		String newErrMsg = errNew.substring( 0, ((isErrSupportType || isErrNmeTeam) && !isErrHandOverDate) ?
										 errNew.length() - 2 : errNew.length() )
								 .concat( " must be defined." );

		System.err.println( "-----------------------------------" );
		System.err.println( "newErrMsg: " );
		System.err.println( newErrMsg );
		System.err.println( "-----------------------------------" );

		LocalDate now = LocalDate.now();
		LocalDate for4Days = now.plusDays( 4 );

		String JIRA_MISTERY_DATETIME_PATTERN2 = "yyyy-MM-dd hh:mm a";
		//        Date jiraRequiredDate = new SimpleDateFormat(JIRA_MISTERY_DATETIME_PATTERN2 ).parse(  );
		LocalDateTime ldt1 = LocalDateTime.now();
		String format = ldt1.format( DateTimeFormatter.ofPattern( JIRA_MISTERY_DATETIME_PATTERN2 ) );
		System.err.println( "vreme1: " + format );

		for (LocalDate sd = now; sd.isBefore( now.plusDays( 4 ) ); sd = sd.plusDays( 1 )) {
			System.err.println( sd );
		}

		List<Integer> L = Arrays.asList( 1, 2, 3, 4, 5 );
		System.err.println( L.contains( null ) );
		System.err.println( L.contains( 2 ) );
		System.err.println( L.containsAll( Arrays.asList( 2, 4 ) ) );
		System.err.println( L.containsAll( Arrays.asList( 7, 8, 9 ) ) );
		System.err.println( L.contains( 7 ) );

		ObjHolder<Integer> oh = new ObjHolder<>();
		try {
			oh.setValue( 7 );
			int x = 12 / 0;
		} catch (Exception e) {
			System.err.println( "catch:" + oh.getValue() );
		}
		System.err.println( "van try/catch:" + oh.getValue() );

		List<Integer> l = Arrays.asList( 1, 2, 3 );
		// l = null;
		for (Integer e : l) {
			System.err.println( e );
		}

		/*Optional.of(BigDecimal.valueOf(7))*/
		Optional<BigDecimal> a = Optional.of( BigDecimal.valueOf( 337 ) );
		Optional<Object> b = Optional.ofNullable( null );
		BigDecimal res1 = !a.isPresent() && !b.isPresent() ? null : BigDecimal.TEN;
		System.err.println( res1 );

		Optional<BigDecimal> a2 = Optional.empty();
		Optional<BigDecimal> b2 = Optional.of( BigDecimal.valueOf( 55 ) );
		BigDecimal res2 = b2.orElse( a2.orElse( null ) );
		System.err.println( res2 );

		Map<Integer, List<ObjHolder<String>>> m = new HashMap<>();
		m.computeIfAbsent( 1, k -> new ArrayList<>( Arrays.asList( new ObjHolder<>( "1-1" ), new ObjHolder<>( "1-2" ) ) ) );
		System.err.println( m );
		m.computeIfAbsent( 2, k -> new ArrayList<>( Arrays.asList( new ObjHolder<>( "2-1" ) ) ) );
		System.err.println( m );
		m.computeIfAbsent( 2, k -> new ArrayList<>( Arrays.asList( new ObjHolder<>( "2-2" ) ) ) );
		System.err.println( m );

		System.err.println( "---> compute 2 : " );
		m.compute( 2, (k, v) -> new ArrayList<>( Arrays.asList( new ObjHolder<>( "2-2" ) ) ) );
		System.err.println( m );

		System.err.println( "---> compute 2 : " );
		m.compute( 2, (k, v) -> {
			if (v == null) {
				v = new ArrayList<>();
			} else {
				v.add( new ObjHolder<>( "2-2" ) );
			}
			return v;
		} );
		System.err.println( m );

		try {
			throw new RuntimeException( "123", new IndexOutOfBoundsException( "77" ) );
		} catch (RuntimeException e) {
			System.err.println( e.getCause() instanceof IOException );
			System.err.println( e.getCause() instanceof IndexOutOfBoundsException );
		}

		System.err.println( "null == null ? " + (null == null) );
		System.err.println( "Objects.equals(null, null) ? " + (Objects.equals( null, null )) );

		Set<Integer> db = new HashSet<>( Arrays.asList( 1, 2, 3, 4 ) );
		Set<Integer> dto = new HashSet<>( Arrays.asList( 3, 4, 1, 2 ) );
		System.err.println( "db.size() == dto.size() ?" );

		Set<Integer> dtoRemoval = new HashSet<>( dto );
		dtoRemoval.removeAll( db );
		System.err.println( dtoRemoval );

		System.setSecurityManager( new SecurityManager() );
		List<Integer> numbers = new ArrayList<>();
		Collections.addAll( numbers, 3, 1, 4, 1, 5, 5, 9 );
		Iterator<Integer> it = numbers.iterator();
		System.out.print( it.next() ); // 3
		System.out.print( '.' );
		System.out.print( it.next() ); // 1
		System.out.print( it.next() ); // 4
		System.out.print( it.next() ); // 1
		System.out.print( it.next() ); // 5
		it.next();
		it.remove();
		//        doSomething(numbers);
		System.out.println( it.next() );
		if (!numbers.equals( Arrays.asList( 3, 1, 4, 1, 5, 9 ) )) {
			throw new AssertionError();
		}

		Map<LocalDate, DayOfWeek> cache = Stream.of(
				LocalDate.of( 2016, 1, 1 ), LocalDate.of( 2016, 1, 2 )
		).collect( Collectors.toMap( k -> k, LocalDate::getDayOfWeek ) );

		System.err.println( "cache: " + cache );

		boolean obaDana = cache.values().containsAll( Arrays.asList( DayOfWeek.FRIDAY, THURSDAY,
																	 DayOfWeek.WEDNESDAY ) );
		System.err.println( obaDana );

		cache.values().removeAll( Arrays.asList( DayOfWeek.SATURDAY, DayOfWeek.SUNDAY ) );
		System.err.println( cache );

		int end = 3;
		boolean prviuslov = true;
		boolean drugiuslov = true;
		for (int i = 1; i <= end; i++) {
			if (prviuslov) {
				prviuslov = false;
				end++;
			}
			if (drugiuslov) {
				drugiuslov = false;
				end += 2;
			}
			System.err.println( "i=" + i );
		}

		System.err.println( "================================================" );
		System.err.println( "              Nađi 32.12.  da je ponedeljak     " );
		System.err.println( "================================================" );

		for (LocalDateTime day = LocalDateTime.of( 2000, 1, 1, 11, 45, 17 );
			 day.isBefore( LocalDateTime.of( 2020, 12, 31, 11, 45, 17 ) );
			 day = day.plusDays( 1 )) {

			if (day.getDayOfWeek().equals( MONDAY ) && day.getMonth().equals( Month.DECEMBER )
					&& day.getDayOfMonth() == 30) {
				System.err.println( "date: " + day + ", day = " + day.getDayOfWeek() );
			}
		}

		System.err.println( "================================================" );
		System.err.println( "           testiraj algoritam                   " );
		System.err.println( "================================================" );

		List<DayOfWeek> weekendDaysOfWeek = Arrays.asList( SATURDAY, SUNDAY );
		Map<LocalDate, DayOfWeek> currentYearHolidayCache = Stream.of(
																		  LocalDate.of( 2020, 1, 1 ), LocalDate.of( 2020, 1, 2 ), LocalDate.of( 2020, 1, 7 ) )
																  .collect( Collectors.toMap(
																		  k -> k,
																		  LocalDate::getDayOfWeek ) );

		int ONE_BDAY = 1;
		int FIVE_BDAY = 5;
		int END_BDAY = FIVE_BDAY;

		LocalDateTime startingDayLdt = LocalDateTime.of( 2019, 12, 30, 11, 45, 11 );
		//        LocalDateTime startingDayLdt = LocalDateTime.of(2020, 1, 24, 11, 45, 11);
		LocalDateTime endsIn5WorkingDay = startingDayLdt.plusDays( END_BDAY + 2 ).minusSeconds( 1 );

		int currDay = 0;
		LocalDateTime result = endsIn5WorkingDay;

		//<editor-fold desc="verzija 1">
        /*for (LocalDateTime day = startingDayLdt;
             day.isBefore(endsIn5WorkingDay) *//*|| day.isEqual(endsIn5WorkingDay)*//*; day = day.plusDays(1)) {

            if (currentYearHolidayCache.containsKey(day.toLocalDate())) {
                endsIn5WorkingDay = endsIn5WorkingDay.plusDays(1);
            } else if (weekendDaysOfWeek.contains(day.getDayOfWeek())) {
                continue;
            } else {
                ++currDay;
            }

            if (*//*END_BDAY == 1 ? currDay > END_BDAY :*//* currDay == END_BDAY) {
                result = endsIn5WorkingDay;
                break;
            }
        }*/
		//</editor-fold>

		for (LocalDateTime day = startingDayLdt; ; day = day.plusDays( 1 )) {
            /*
            if (currentYearHolidayCache.containsKey(day.toLocalDate())) {
                // endsIn5WorkingDay = endsIn5WorkingDay.plusDays(1);
                continue;
            } else if (weekendDaysOfWeek.contains(day.getDayOfWeek())) {
                continue;
            } else {
                ++currDay;
            }
            */
			if (!currentYearHolidayCache.containsKey( day.toLocalDate() ) &&
					!weekendDaysOfWeek.contains( day.getDayOfWeek() )) {
				++currDay;
			}

			if (END_BDAY == 1 ? currDay > END_BDAY : currDay == END_BDAY) {
				result = day;
				break;
			}
		}

		System.err.println( "datum : " + result );

		System.err.println( "**********************" );
		System.err.println();

		Integer hour = 1;
		String left = "00".concat( hour == null ? "" : hour.toString() ).substring( hour==null ? 0: hour.toString().length() );
		System.err.println( "left = " + left );
	}

	private static void doSomething(List<Integer> list) {
		// how???
		list.remove( 5 );
		for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
			((ArrayList<Integer>) list).trimToSize();
		}
	}
}
