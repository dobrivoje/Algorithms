package algs.Util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class TemporalInterval {

	public final static long          nanoSecs = 999999999L;
	private             LocalDateTime start;
	private             LocalDateTime end;

	//<editor-fold desc="infra">
	public TemporalInterval() {
	}

	public TemporalInterval(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public Date getStartAsDate() {
		return DateUtil.FromLocalDateTime( start );
	}

	public Date getEndAsDate() {
		return DateUtil.FromLocalDateTime( end );
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		TemporalInterval other = (TemporalInterval) o;

		if (!Objects.equals( start, other.start )) {
			return false;
		}
		return Objects.equals( end, other.end );
	}

	@Override
	public int hashCode() {
		int result = start != null ? start.hashCode() : 0;
		result = 31 * result + (end != null ? end.hashCode() : 0);
		return result;
	}
	//</editor-fold>

	public static TemporalInterval of(Date date, int startHour, int startMin, int endHour, int endMin) {
		return TemporalInterval.of( date, startHour, startMin, 0, endHour, endMin, 0 );
	}

	public static TemporalInterval asWholeDay(Date startDate, Date endDate) {
		return new TemporalInterval( DateUtil.ToLocalDate( startDate ).atTime( getTimeForStartOfDay() ),
									 DateUtil.ToLocalDate( endDate ).atTime( getTimeForEndOfDay() ) );
	}

	public static Date getDateForEndOfDay(Date date) {
		return getDateWithTime( date, getTimeForEndOfDay() );
	}

	public static Date getDateWithTime(Date date, LocalTime localTime) {
		LocalDateTime ldt = DateUtil.ToLocalDateTime( date ).with( localTime );
		return DateUtil.FromLocalDateTime( ldt );
	}

	public static LocalTime getTimeForStartOfDay() {
		return LocalTime.of( 0, 0, 0 );
	}

	public static LocalTime getTimeForEndOfDay() {
		return LocalTime.of( 23, 59, 59 ).plusNanos( nanoSecs );
	}

	public static TemporalInterval of(Date date, int startHour, int startMin, int startSec, int endHour, int endMin, int endSec) {
		LocalDateTime ldtStart = DateUtil.ToLocalDate( date ).atTime( startHour, startMin, startSec );
		LocalDateTime ldtEnd = DateUtil.ToLocalDate( date ).atTime( endHour, endMin, endSec == 0 ? 59 : endSec )
									   .plusNanos( endSec == 0 ? nanoSecs : 0 );

		return new TemporalInterval( ldtStart, ldtEnd );
	}
}