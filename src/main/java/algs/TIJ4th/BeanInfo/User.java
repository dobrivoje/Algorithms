package algs.TIJ4th.BeanInfo;

import lombok.*;
import org.apache.commons.lang3.builder.*;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userId" /*{"userId", "emailAddress"}*/)
@ToString(of = { "userId", "firstName", "emailAddress" })
public class User implements java.io.Serializable,
							 Diffable<User> {

	private ToStringStyle style = ToStringStyle.MULTI_LINE_STYLE;

	@ChangeLog.Include
	Long userId;
	List<Integer> children = new ArrayList<>();

	Date   createDate;
	Date   modifiedDate;
	String password;
	Byte   passwordEncrypted;
	Date   passwordModifiedDate;
	String screenName;
	Date   screenNameModifiedDate;

	@ChangeLog.Include
	String emailAddress;
	@ChangeLog.Include
	String html;

	String phone;
	String firstName;
	String lastName;
	Date   lastLoginDate;
	Byte   active;
	String token;
	Date   tokenValidUntil;
	String sAMAccountName;
	String eightByEightId;

	public User(Long userId, String emailAddress) {
		this.userId = userId;
		this.emailAddress = emailAddress;
	}

	public User(Long userId, String emailAddress, Date createDate, List<Integer> children) {
		this.userId = userId;
		this.emailAddress = emailAddress;
		this.createDate = createDate;
		this.children = children;
	}

	public static List<User> users = Arrays.asList(
			new User( 1L, "dobrivoje.prtenjak@hyperoptic.com", new Date(), new ArrayList<>( Arrays.asList( 2, 4, 6, 8 ) ) ),
			new User( 2L, "xenija.prtenjak@hyperoptic.com", new Date( 3445654L ), new ArrayList<>( Arrays.asList( 1, 3, 5, 7 ) ) ),
			new User( 117L, "nina.prtenjak@hyperoptic.com", new Date( 2222222222L ), new ArrayList<>( Arrays.asList( 10, 11, 12 ) ) ),
			new User( 2800L, "stanko.krnjanjic@hyperoptic.com", new Date( 4444444444L ), new ArrayList<>( Arrays.asList( 20, 21, 22, 23, 24, 25 ) ) ),
			new User( null, "xxx.yyy@hyperoptic.com", new Date( 555555555555L ), null )
	);

	public static Optional<Boolean> getById(Long id) {
		return users.stream().map( u -> id.equals( u.getUserId() ) ).findFirst();
	}

	public static void main(String[] args) throws IllegalAccessException {
		Set<String> unames = users.stream().map( User::getEmailAddress )
								  .map( e -> e.split( "@hyperoptic.com" )[0] )
								  .collect( Collectors.toSet() );

		System.err.println( unames );

		User next = users.iterator().next();
		String un1 = next.getEmailAddress().split( "@hyperoptic.com" )[0];
		System.err.println( un1 );

		//        getById(1L).

		Long usersId = (Long) FieldUtils.readDeclaredField( next, "userId", true );
		System.err.println( "usersId = " + usersId );
	}

	@Override
	public DiffResult diff(User other) {
		DiffBuilder diffBuilder = new DiffBuilder( this, other, style );
		DiffResult diffResult = diffBuilder
				.append( "user id", userId, other.userId )
				.append( "email Address", emailAddress, other.emailAddress )
				.build();

		return diffResult;

		//        Arrays.stream(User.class.getDeclaredFields())
		//              .forEach(f -> diffBuilder.append(f.getName(),f,));
	}
}
