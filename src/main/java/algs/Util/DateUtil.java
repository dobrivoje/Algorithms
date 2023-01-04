package algs.Util;

import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.*;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateUtil {

	public enum State {
		UK,
		SERBIA;
	}

	public static boolean isWeekend(Date date) {
		if (date == null) {
			return false;
		}
		DateTime dateTime = new DateTime( date );
		return dateTime.getDayOfWeek() == DateTimeConstants.SATURDAY || dateTime.getDayOfWeek() == DateTimeConstants.SUNDAY;
	}

	public static boolean isSaturday(Date date) {
		if (date == null) {
			return false;
		}
		DateTime dateTime = new DateTime( date );
		return dateTime.getDayOfWeek() == DateTimeConstants.SATURDAY;
	}

	public static boolean isSunday(Date date) {
		if (date == null) {
			return false;
		}
		DateTime dateTime = new DateTime( date );
		return dateTime.getDayOfWeek() == DateTimeConstants.SUNDAY;
	}

	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		DateTime dateTime1 = new DateTime( date1 ).withTimeAtStartOfDay();
		DateTime dateTime2 = new DateTime( date2 ).withTimeAtStartOfDay();
		return dateTime1.equals( dateTime2 );
	}

	public static boolean isBankHoliday(Date date, List<Date> bankHolidays) {
		if (date == null) {
			return false;
		}
		if (bankHolidays == null || bankHolidays.isEmpty()) {
			return false;
		}
		for (Date bankHoliday : bankHolidays) {
			if (isSameDay( date, bankHoliday )) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNonWorkingDay(Date date, List<Date> bankHolidays) {
		if (date == null) {
			return false;
		}
		return isWeekend( date ) || isBankHoliday( date, bankHolidays );
	}

	public static long getDifferenceInMilliseconds(Date startDate, Date endDate) {
		return endDate.getTime() - startDate.getTime();
	}

	public static int getDifferenceInSeconds(Date startDate, Date endDate) {
		return (int) TimeUnit.MILLISECONDS.toSeconds( Math.abs( getDifferenceInMilliseconds( startDate, endDate ) ) );
	}

	public static int getDifferenceInMinutes(Date startDate, Date endDate) {
		if (startDate.before( endDate )) {
			return Minutes.minutesBetween( new org.joda.time.LocalDate( startDate ), new org.joda.time.LocalDate( endDate ) ).getMinutes();
		} else {
			return Minutes.minutesBetween( new org.joda.time.LocalDate( endDate ), new org.joda.time.LocalDate( startDate ) ).getMinutes();
		}
	}

	public static int getDifferenceInHours(Date startDate, Date endDate) {
		if (startDate.before( endDate )) {
			return Hours.hoursBetween( new org.joda.time.LocalDate( startDate ), new org.joda.time.LocalDate( endDate ) ).getHours();
		} else {
			return Hours.hoursBetween( new org.joda.time.LocalDate( endDate ), new org.joda.time.LocalDate( startDate ) ).getHours();
		}
	}

	public static int getDifferenceInDays(Date startDate, Date endDate) {
		if (startDate.before( endDate )) {
			return Days.daysBetween( new org.joda.time.LocalDate( startDate ), new org.joda.time.LocalDate( endDate ) ).getDays();
		} else {
			return Days.daysBetween( new org.joda.time.LocalDate( endDate ), new org.joda.time.LocalDate( startDate ) ).getDays();
		}
	}

	public static int getDifferenceInMonths(Date startDate, Date endDate) {
		int months = 0;
		DateTime d1 = new DateTime( startDate );
		DateTime d2 = new DateTime( endDate ).plusMinutes( 1 );
		while (d1.plusMonths( 1 ).toDate().before( d2.toDate() )) {
			d1 = d1.plusMonths( 1 );
			months++;
		}
		return months;
	}

	public static List<Date> getDaysBetween(Date startDate, Date endDate, boolean includeStartDay, boolean includeEndDay) {
		DateTime startDay = new DateTime( startDate ).withTimeAtStartOfDay();
		DateTime endDay = new DateTime( endDate ).withTimeAtStartOfDay();

		List<Date> days = new ArrayList<>();

		DateTime currentDay;
		if (includeStartDay) {
			currentDay = startDay;
		} else {
			currentDay = startDay.plusDays( 1 );
		}
		while (currentDay.toDate().before( endDay.toDate() ) || (includeEndDay && currentDay.toDate().equals( endDay.toDate() ))) {
			days.add( currentDay.toDate() );
			currentDay = currentDay.plusDays( 1 );
		}

		return days;
	}

	public static Date getTimeOfDay(Date date, int hours, int minutes, int seconds, int miliseconds) {
		if (date == null) {
			return null;
		}

		DateTime dateTime = new DateTime( date );
		dateTime = dateTime.withTime( hours, minutes, seconds, miliseconds );

		return dateTime.toDate();
	}

	public static String getCurrentTimeForFileName() {
		SimpleDateFormat formatter = new SimpleDateFormat( "ddMMyyyy_HHmmss" );
		return formatter.format( new Date() );
	}

	/**
	 * Month is 0 based (January = 0, February = 1...).
	 */
	public static Date getDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis( 0 );
		calendar.set( year, month, day, hour, minute, second );
		return calendar.getTime();
	}

	//<editor-fold desc="First and last day dates in a week for a selected date">

	/**
	 * Return Monday's date in a week for a selected date
	 */
	public static Date getMondayInWeekFor(Date selectedDate) {
		LocalDateTime ldt = LocalDateTime.ofInstant( selectedDate.toInstant(), ZoneId.systemDefault() );
		return getMondayInWeekFor( ldt );
	}

	public static Date getMondayInWeekFor(LocalDateTime dateTime) {
		LocalDateTime monday = dateTime.with( DayOfWeek.MONDAY ).withHour( 0 ).withMinute( 0 ).withSecond( 0 );
		return Date.from( monday.atZone( ZoneId.systemDefault() ).toInstant() );
	}

	/**
	 * Return Sunday's date in a week for a selected date.<br>
	 * Sunday end values (in terms of hours/seconds) is 23:59:59.999<br>
	 */
	public static Date getSundayInWeekFor(Date selectedDate) {
		LocalDateTime mondayBeginningFromZeroNanos = ToLocalDateTime( selectedDate ).with( DayOfWeek.MONDAY );
		LocalDateTime sundayEnd = mondayBeginningFromZeroNanos.plusDays( 7 ).minusNanos( 1 );
		return FromLocalDateTime( sundayEnd );
	}
	//</editor-fold>

	public enum INTERVAL {
		DATES( new Date( 0 ), new Date() );
		public Date start;
		public Date end;

		INTERVAL(Date start, Date end) {
			this.start = start;
			this.end = end;
		}
	}

	public enum WEEK {
		DAYS( LocalDate.MIN, LocalDate.MAX );

		public LocalDate start;
		public LocalDate end;

		WEEK(LocalDate start, LocalDate end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Starting date : " + start + ", ending date : " + end;
		}
	}

	public static Date FromLocalDate(LocalDate localDate) {
		return Date.from( localDate.atStartOfDay().atZone( ZoneId.systemDefault() ).toInstant() );
	}

	public static Date FromLocalDateTime(LocalDateTime localDateTime) {
		return Date.from( localDateTime.atZone( ZoneId.systemDefault() ).toInstant() );
	}

	public static LocalDate ToLocalDate(Date dateToConvert) {
		return dateToConvert.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
	}

	public static String WithDefaultZoneFrom(LocalDateTime localDateTime, String dateTimeFormat) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( dateTimeFormat );
		return formatter.format( ZonedDateTime.of( localDateTime, ZoneId.systemDefault() ) );
	}

	public static String WithZoneFrom(LocalDateTime localDateTime, String dateTimeFormat, ZoneId zoneId) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( dateTimeFormat );
		return formatter.format( ZonedDateTime.of( localDateTime, zoneId ) );
	}

	public static LocalDateTime ToLocalDateTime(@NonNull Date dateToConvert) {
		ZonedDateTime zonedDateTime = dateToConvert.toInstant().atZone( ZoneId.systemDefault() );
		LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
		localDateTime.withHour( 0 ).withMinute( 0 ).withSecond( 0 ).withNano( 0 );

		return localDateTime;
	}

	/**
	 * Day intervals from 1.st to last.
	 */
	public static INTERVAL get(int year, int month) {
		return get( year, month, 1, -1 );
	}

	/**
	 * Arbitrary interval.<br>
	 * First and last day must belong to the interval for that month.<br>
	 * <p>
	 * If interval is missed, throw RuntimeException<br>
	 *
	 * @param endDay -1 : last day for current calendar month.
	 */
	public static INTERVAL get(int year, int month, int startDay, int endDay) {
		LocalDate ldPoc = LocalDate.of( year, month, startDay );

		int lastPossibleCalendaDayOfTheMonth = ldPoc.lengthOfMonth();
		int finalDayOfTheMonth;

		if (endDay == -1) {
			finalDayOfTheMonth = lastPossibleCalendaDayOfTheMonth;
		} else if (endDay > lastPossibleCalendaDayOfTheMonth) {
			throw new RuntimeException( "End day must not exceed : " + lastPossibleCalendaDayOfTheMonth );
		} else {
			finalDayOfTheMonth = endDay;
		}

		LocalDate ldEnd = LocalDate.of( year, month, finalDayOfTheMonth );
		Date chosenDateFrom = FromLocalDate( ldPoc );
		Date chosenDateTo = FromLocalDate( ldEnd );

		INTERVAL interval = INTERVAL.DATES;
		interval.DATES.start = chosenDateFrom;
		interval.DATES.end = chosenDateTo;
		return interval;
	}

	public static WEEK WorkWeek(LocalDate localDate) {
		return WorkWeek( localDate, DayOfWeek.MONDAY, 5 );
	}

	public static WEEK WorkWeek(Date date) {
		return WorkWeek( ToLocalDate( date ), DayOfWeek.MONDAY, 5 );
	}

	public static WEEK PreviuosWorkWeek(LocalDate localDate) {
		return WorkWeek( localDate.minusDays( 7 ) );
	}

	public static WEEK PreviuosWorkWeek(Date date) {
		return WorkWeek( ToLocalDate( date ).minusDays( 7 ) );
	}

	//<editor-fold desc="helper">
	private static WEEK WorkWeek(LocalDate ld, DayOfWeek startingDayOfWeek, int minimalDaysInFirstWeek) {
		LocalDate first = ld.with( WeekFields.of( startingDayOfWeek, minimalDaysInFirstWeek ).getFirstDayOfWeek() );

		LocalDate last = first.plusDays( 4 );

		WEEK w = WEEK.DAYS;
		w.start = first;
		w.end = last;
		return w;
	}
	//</editor-fold>

	/**
	 * For a startDay, calculate end business date, for demanded noOfWorkDays.<br>
	 */
	public static LocalDateTime getEndBusinessDayFor(@NonNull LocalDateTime startDay, int noOfWorkDays) {
		int WORK_WEEK_NO_OF_DAYS = 5;

		LocalDateTime result;

		boolean isWeekendDay = EnumSet.of( DayOfWeek.SATURDAY, DayOfWeek.SUNDAY ).contains( startDay.getDayOfWeek() );
		if (isWeekendDay) {
			startDay = startDay.plusDays( 1 ).plusDays( DayOfWeek.SUNDAY.getValue() - startDay.getDayOfWeek().getValue() );
		}
		int diffToFirstMonday = Math.abs( DayOfWeek.FRIDAY.getValue() - startDay.getDayOfWeek().getValue() );

		if (diffToFirstMonday >= noOfWorkDays) {
			result = startDay.plusDays( diffToFirstMonday ).minusDays( diffToFirstMonday > noOfWorkDays ? 1 : 0 );
		} else {
			int includedDaysToSunday = 1 + DayOfWeek.SUNDAY.getValue() - startDay.getDayOfWeek().getValue();
			int remainingDaysAfterFirstWeek = noOfWorkDays - diffToFirstMonday - (isWeekendDay ? 1 : 0);
			int remainingForFullWeek = remainingDaysAfterFirstWeek % WORK_WEEK_NO_OF_DAYS;
			int noOfWeekBetween = (remainingDaysAfterFirstWeek) / WORK_WEEK_NO_OF_DAYS;
			int remainingDaysInLastWeek = (remainingDaysAfterFirstWeek - WORK_WEEK_NO_OF_DAYS * noOfWeekBetween)
					% WORK_WEEK_NO_OF_DAYS;

			LocalDateTime firstNextMonday = startDay.plusWeeks( noOfWeekBetween - (remainingForFullWeek == 0 ? 1 : 0) )
													.plusDays( includedDaysToSunday );
			result = firstNextMonday.plusDays( remainingForFullWeek == 0 ? WORK_WEEK_NO_OF_DAYS : remainingDaysInLastWeek )
									.minusDays( 1 );
		}

		return result;
	}

	public static Date getEndBusinessDayFor(@NonNull Date startDay, int noOfWorkDays) {
		LocalDateTime startLdt = ToLocalDateTime( startDay );
		return FromLocalDateTime( getEndBusinessDayFor( startLdt, noOfWorkDays ) );
	}

	public static boolean isDatesOverlapping(Date startDate, Date endDate, Date otherStart, Date otherEnd) {
		if (startDate.after( endDate ) || otherStart.after( otherEnd )) {
			throw new UnsupportedOperationException( "Start/end dates must be properly set." );
		}

		return startDate.equals( otherEnd ) || endDate.equals( otherStart ) ||
				startDate.before( otherEnd ) && endDate.after( otherStart );
	}

	/**
	 * Custom date interval overlapping test intersecting some interval, with interval bounds.<br>
	 * Start/end interval dates: intervalStartDate/intervalEndDate, may be included or excluded,<br>
	 * Recommended usage: interval dates, and bound parameters should be "constant",<br>
	 * and subject of testing, and custom date may be changeable (in iterations for example)<br>
	 */
	public static boolean isDatesOverlapping(Date startDate, Date endDate, Date intervalStartDate, Date intervalEndDate,
											 boolean intervalStartIncluded, boolean intervalEndIncluded) {
		if (intervalStartDate.after( intervalEndDate ) || startDate.after( endDate )) {
			throw new UnsupportedOperationException( "Start/end dates must be properly set." );
		}

		boolean isIntervalEndEquals = intervalEndIncluded && startDate.equals( intervalEndDate );
		boolean isIntervalStartEquals = intervalStartIncluded && endDate.equals( intervalStartDate );

		return isIntervalEndEquals || isIntervalStartEquals ||
				startDate.before( intervalEndDate ) && endDate.after( intervalStartDate );
	}

	/**
	 * Custom date interval overlapping test intersecting some interval, with interval bounds.<br>
	 * Start/end interval dates: intervalStartDate/intervalEndDate, may be included or excluded,<br>
	 * Recommended usage: interval dates, and bound parameters should be "constant",<br>
	 * and subject of testing, and custom date may be changeable (in iterations for example)<br>
	 */
	public static boolean isDatesOverlapping(LocalDateTime start, LocalDateTime end,
											 LocalDateTime intervalStartDate, LocalDateTime intervalEndDate,
											 boolean intervalStartIncluded, boolean intervalEndIncluded) {

		if (intervalStartDate.isAfter( intervalEndDate ) || start.isAfter( end )) {
			throw new UnsupportedOperationException( "Start/end dates must be properly set." );
		}

		boolean isIntervalEndEquals = intervalEndIncluded && start.equals( intervalEndDate );
		boolean isIntervalStartEquals = intervalStartIncluded && end.equals( intervalStartDate );

		return isIntervalEndEquals || isIntervalStartEquals ||
				start.isBefore( intervalEndDate ) && end.isAfter( intervalStartDate );


	}

	public static boolean isDatesOverlapping(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime,
											 LocalDateTime otherStartLocalDateTime, LocalDateTime otherEndLocalDateTime) {
		return isDatesOverlapping( FromLocalDateTime( startLocalDateTime ), FromLocalDateTime( endLocalDateTime ),
								   FromLocalDateTime( otherStartLocalDateTime ), FromLocalDateTime( otherEndLocalDateTime ) );
	}

	/**
	 * Get datetime interval for date without time set by setting exact time values.
	 */
	public static INTERVAL getInterval(Date start, int startHour, int startMin, int startSec,
									   Date end, int endHour, int endMin, int endSec) {
		LocalDateTime ldNewStart = ToLocalDateTime(
				start ).withHour( startHour ).withMinute( startMin ).withSecond( startSec );
		LocalDateTime ldNewEnd = ToLocalDateTime(
				end ).withHour( endHour ).withMinute( endMin ).withSecond( endSec );

		INTERVAL.DATES.start = FromLocalDateTime( ldNewStart );
		INTERVAL.DATES.end = FromLocalDateTime( ldNewEnd );
		return INTERVAL.DATES;
	}

	public static List<TemporalInterval> getTemporalIntervalsInBetweenExceptForDays(Date start, Date end, DayOfWeek... daysToSkip) {
		return getTemporalIntervalsInBetweenExceptForDays( ToLocalDateTime( start ), ToLocalDateTime( end ), daysToSkip );
	}

	public static List<TemporalInterval> getTemporalIntervalsInBetweenExceptForDays(LocalDateTime start, LocalDateTime end,
																					DayOfWeek... daysToSkip) {
		List<TemporalInterval> res = new ArrayList<>();

		if (daysToSkip == null || daysToSkip.length == 0) {
			LocalTime time = end.toLocalTime();
			boolean zeroTime = time.getHour() == 0 && time.getMinute() == 0;
			end = (LocalDate.from( end ).atTime( zeroTime ? TemporalInterval.getTimeForEndOfDay() : time ));
			res.add( new TemporalInterval( start, end ) );
			return res;
		}

		boolean isStartChecked = false;
		boolean isFirstStart = true;

		Set<DayOfWeek> daysToSkipSet = Stream.of( daysToSkip ).collect( Collectors.toSet() );

		TemporalInterval currTempInt = new TemporalInterval();
		LocalDateTime currLdt = LocalDateTime.from( start );
		do {
			if (!daysToSkipSet.contains( currLdt.getDayOfWeek() )) {
				if (!isStartChecked) {
					isStartChecked = true;

					LocalDateTime tmp = LocalDateTime.from( currLdt );
					currTempInt.setStart( isFirstStart ? tmp : tmp.with( TemporalInterval.getTimeForStartOfDay() ) );

					isFirstStart = false;
				}
			} else {
				if (isStartChecked) {
					LocalDateTime tmp = LocalDateTime.from( currLdt );
					currTempInt.setEnd( LocalDate.from( tmp.minusDays( 1 ) ).atTime( TemporalInterval.getTimeForEndOfDay() ) );
					res.add( currTempInt );

					isStartChecked = false;
					currTempInt = new TemporalInterval();
				}
			}

			if (isStartChecked) {
				if (currLdt.toLocalDate().equals( end.toLocalDate() )) {
					if (!currTempInt.getStart().toLocalDate().isBefore( end.toLocalDate() )) {
						currTempInt.setStart( currTempInt.getStart().with( TemporalInterval.getTimeForStartOfDay() ) );
					}
					LocalDateTime tmp = LocalDateTime.from( currLdt );
					currTempInt.setEnd( tmp.minusNanos( 1 ) );

					res.add( currTempInt );
					break;
				}
			}

			currLdt = currLdt.plusDays( 1 );
		} while (currLdt.isBefore( end ) || currLdt.isEqual( end ));
		if (!res.isEmpty()) {
			TemporalInterval lastEnd = res.get( res.size() - 1 );
			LocalTime time = end.toLocalTime();
			boolean zeroTime = time.getHour() == 0 && time.getMinute() == 0;
			lastEnd.setEnd( LocalDate.from( lastEnd.getEnd() ).atTime( zeroTime ? TemporalInterval.getTimeForEndOfDay() : time ) );
		}

		return res;
	}

	public static Date subtractWorkingDays(Date startingDate, int numberOfDays) {
		int daysSubtracted = 0;
		DateTime result = new DateTime( startingDate );
		while (daysSubtracted < numberOfDays) {
			result = result.minusDays( 1 );
			if (!isWeekend( result.toDate() )) {
				daysSubtracted++;
			}
		}
		return result.toDate();
	}

	/**
	 * @see <a href="https://stackoverflow.com/a/64043708/1551368</a>
	 */
	public static LocalDate getEasterSundayDate(int year) {
		int a = year % 19,
				b = year / 100,
				c = year % 100,
				d = b / 4,
				e = b % 4,
				g = (8 * b + 13) / 25,
				h = (19 * a + b - d - g + 15) % 30,
				j = c / 4,
				k = c % 4,
				m = (a + 11 * h) / 319,
				r = (2 * e + 2 * j - k - h + m + 32) % 7,
				month = (h - m + r + 90) / 25,
				day = (h - m + r + month + 19) % 32;

		return LocalDate.of( year, month, day );
	}

	public static List<LocalDate> getPublicNonWorkingDays(int year, State state) {
		if (!state.equals( State.SERBIA )) {
			throw new RuntimeException( "Public holidays for now only implemented for Serbia." );
		}

		List<LocalDate> res = new ArrayList<>();

		//<editor-fold desc="New Year">
		LocalDate nyDay1 = LocalDate.of( year, 1, 1 );
		LocalDate nyDay2 = LocalDate.of( year, 1, 2 );
		if (nyDay1.getDayOfWeek().equals( DayOfWeek.FRIDAY )) {
			res.add( nyDay1 );
		} else if (nyDay1.getDayOfWeek().equals( DayOfWeek.SUNDAY )) {
			res.add( nyDay2 );
		} else if (!nyDay1.getDayOfWeek().equals( DayOfWeek.SATURDAY )) {
			res.addAll( Arrays.asList( nyDay1, nyDay2 ) );
		}
		//</editor-fold>

		//<editor-fold desc="Christmas">
		LocalDate christMass = LocalDate.of( year, 1, 7 );
		if (christMass.getDayOfWeek().equals( DayOfWeek.SUNDAY )) {
			res.add( christMass.plusDays( 1 ) );
		} else if (!christMass.getDayOfWeek().equals( DayOfWeek.SATURDAY )) {
			res.add( christMass );
		}
		//</editor-fold>

		//<editor-fold desc="Statehood Day">
		LocalDate natDay1 = LocalDate.of( year, 2, 15 );
		LocalDate natDay2 = LocalDate.of( year, 2, 16 );
		if (natDay1.getDayOfWeek().equals( DayOfWeek.FRIDAY )) {
			res.add( natDay1 );
		} else if (natDay1.getDayOfWeek().equals( DayOfWeek.SUNDAY )) {
			res.add( natDay2 );
		} else if (!natDay1.getDayOfWeek().equals( DayOfWeek.SATURDAY )) {
			res.addAll( Arrays.asList( natDay1, natDay2 ) );
		}
		//</editor-fold>

		//<editor-fold desc="Orthodox Easter">
		LocalDate orthodoxEasterBigFridayDate = getEasterSundayDate( year ).minusDays( 2 );
		res.add( orthodoxEasterBigFridayDate );
		//</editor-fold>

		//<editor-fold desc="Labour Day">
		LocalDate labDay1 = LocalDate.of( year, 5, 1 );
		LocalDate labDay2 = LocalDate.of( year, 5, 2 );
		if (labDay1.getDayOfWeek().equals( DayOfWeek.FRIDAY )) {
			res.add( labDay1 );
		} else if (labDay1.getDayOfWeek().equals( DayOfWeek.SUNDAY )) {
			res.add( labDay2 );
		} else if (!labDay1.getDayOfWeek().equals( DayOfWeek.SATURDAY )) {
			res.addAll( Arrays.asList( labDay1, labDay2 ) );
		}
		//</editor-fold>

		//<editor-fold desc="World war one Armistice Day">
		LocalDate wwOneArmisticeDay = LocalDate.of( year, 11, 11 );
		if (wwOneArmisticeDay.getDayOfWeek().equals( DayOfWeek.SUNDAY )) {
			res.add( wwOneArmisticeDay.plusDays( 1 ) );
		} else if (!wwOneArmisticeDay.getDayOfWeek().equals( DayOfWeek.SATURDAY )) {
			res.add( wwOneArmisticeDay );
		}
		//</editor-fold>

		return res;
	}

	public static String resolveDayOrdering(Integer dayNum) {
		String day = "unknown";
		if (dayNum != null) {
			String dayStr = String.valueOf( dayNum );
			if (dayStr.endsWith( "1" ) && !dayNum.equals( 11 )) {
				day = dayStr + "st";
			} else if (dayStr.endsWith( "2" ) && !dayNum.equals( 12 )) {
				day = dayStr + "nd";
			} else if (dayStr.endsWith( "3" ) && !dayNum.equals( 13 )) {
				day = dayStr + "rd";
			} else {
				day = dayStr + "th";
			}
		}
		return day;
	}

	public static String getDayOfMonthSuffix(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime( date );
		int dayOfMonth = c.get( Calendar.DAY_OF_MONTH );

		return getDayOfMonthSuffix( dayOfMonth );
	}

	public static String getDayOfMonthSuffix(int dayOfMonth) {
		String[] suffixes = {
				"st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
				"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th", "st" };

		return suffixes[dayOfMonth - 1];
	}

	public static Pair<Date, Date> getWeekIntervalDates(Date date) {
		LocalDateTime ldStart = ToLocalDateTime( date );
		LocalDateTime ldtLastWeekStart = ldStart.minusDays( 7 ).withHour( 0 ).withMinute( 0 ).withSecond( 0 );
		LocalDateTime ldtTodayEnd = ldStart.withHour( 23 ).withMinute( 59 ).withSecond( 59 );

		return Pair.of( FromLocalDateTime( ldtLastWeekStart ), FromLocalDateTime( ldtTodayEnd ) );
	}

	public static Date dateToUTC(Date date) {
		if (date != null) {
			return new Date( date.getTime() - Calendar.getInstance().getTimeZone().getOffset( date.getTime() ) );
		}
		return null;
	}

	/**
	 * @param hourAndMinuteAsStr should be in form 19:15, or 3:45, or 07:59<br>
	 *                           returns Pair of (Hour, Minute)
	 */
	public static Optional<Pair<Integer, Integer>> HourMinuteOf(String hourAndMinuteAsStr) {
		String hourMinute = Optional.ofNullable( hourAndMinuteAsStr ).map( String::trim )
									.orElse( "" );
		if (hourMinute.isEmpty()) {
			return Optional.empty();
		}

		try {
			String[] hm = hourMinute.split( ":" );
			int hour = Integer.parseInt( hm[0] );
			int minute = Integer.parseInt( hm[1] );

			return Optional.of( Pair.of( hour, minute ) );
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

}