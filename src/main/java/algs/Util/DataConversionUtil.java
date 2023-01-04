package algs.Util;

import algs.TIJ4th.BeanInfo.User;
import Java67.poi_template.BasicPairDto;
import lombok.NonNull;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DataConversionUtil {

	private static final String STRING_EMPTY       = "";
	private static final String REGEX_QS           = "QS";
	private static final String REGEX_FLAT         = "Flat ";
	private static final String KEY_INSTANCE       = "PBEWithMD5AndDES";
	private static final String CHARACTER_ENCODING = "UTF8";
	private static final char[] ENCRYPTION_KEY     = "Xdo4HmG6Ju".toCharArray();
	private static final byte[] SALT               = {
			(byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, };

	private DataConversionUtil() {
	}

	public static String extractApartmentNumber(String apartmentNumber) {

		apartmentNumber = apartmentNumber.replaceAll( REGEX_QS, STRING_EMPTY );
		apartmentNumber = apartmentNumber.replaceAll( REGEX_FLAT, STRING_EMPTY );
		while (apartmentNumber.length() < 3) {
			apartmentNumber = "0" + apartmentNumber;
		}
		return apartmentNumber;
	}

	public static String encrypt(String token) throws GeneralSecurityException, UnsupportedEncodingException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( KEY_INSTANCE );
		SecretKey key = keyFactory.generateSecret( new PBEKeySpec( ENCRYPTION_KEY ) );
		Cipher pbeCipher = Cipher.getInstance( KEY_INSTANCE );
		pbeCipher.init( Cipher.ENCRYPT_MODE, key, new PBEParameterSpec( SALT, 20 ) );
		String encodedToken = Base64.getEncoder().encodeToString( (pbeCipher.doFinal( token.getBytes( CHARACTER_ENCODING ) )) );
		return URLEncoder.encode( encodedToken, CHARACTER_ENCODING );
	}

	public static String decrypt(String token) throws GeneralSecurityException, UnsupportedEncodingException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( KEY_INSTANCE );
		SecretKey key = keyFactory.generateSecret( new PBEKeySpec( ENCRYPTION_KEY ) );
		Cipher pbeCipher = Cipher.getInstance( KEY_INSTANCE );
		pbeCipher.init( Cipher.DECRYPT_MODE, key, new PBEParameterSpec( SALT, 20 ) );
		//this line is commented out because url decode is done on front end
		//		String urlDecodedToken = URLDecoder.decode( token, CHARACTER_ENCODING );
		return new String( pbeCipher.doFinal( Base64.getDecoder().decode( token ) ), CHARACTER_ENCODING );
	}

	public static Long convertToLong(String value) {
		Long result = null;
		if (value != null) {
			try {
				result = Long.parseLong( value );
			} catch (NumberFormatException e) {
				try {
					result = new Double( value ).longValue();
				} catch (NumberFormatException e1) {
					try {
						result = new Float( value ).longValue();
					} catch (NumberFormatException e2) {
						e2.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	public static <T> Set<T> UniteSets(Supplier<Set<T>> setGenerator, Set<T>... sets) {
		Set<T> result = setGenerator == null ? new HashSet<>() : setGenerator.get();
		if (sets == null || sets.length == 0) {
			return result;
		}
		Arrays.stream( sets ).forEach( result::addAll );
		return result;
	}

	public static <T> Set<T> UniteSets(Set<T> s1, Set<T> s2, Supplier<Set<T>> setGenerator) {
		return UniteSets( setGenerator, s1, s2 );
	}

	public static <T> Set<T> UniteSets(Set<T> s1, Set<T> s2) {
		return UniteSets( s1, s2, HashSet::new );
	}

	public static <T> List<T> UniteLists(List<T> l1, List<T> l2) {
		return UniteLists( ArrayList::new, l1, l2 );
	}

	public static <T> List<T> UniteLists(Supplier<List<T>> listGenerator, List<T>... lists) {
		List<T> result = listGenerator == null ? new ArrayList<>() : listGenerator.get();
		if (lists == null || lists.length == 0) {
			return result;
		}
		Arrays.stream( lists ).forEach( result::addAll );
		return result;
	}

	public static <T> Collection<T> UniteCollections(@NonNull Supplier<Collection<T>> collectionSupplier, Collection<T>... collections) {
		Collection<T> result = collectionSupplier.get();
		if (collections == null || collections.length == 0) {
			return result;
		}
		Arrays.stream( collections ).forEach( result::addAll );
		return result;
	}

	public static <T> Set<T> SymmetricDifference(Set<T> s1, Set<T> s2) {
		return SymmetricDifference( s1, s2, HashSet::new );
	}

	public static <T> Set<T> SymmetricDifference(Set<T> s1, Set<T> s2, Supplier<Set<T>> setSupplier) {
		/*if (s1.size() == s2.size()) {
			return setSupplier.get();
		}*/
		Set<T> union = setSupplier.get();
		union.addAll( s1 );
		union.addAll( s2 );

		Set<T> intersected = setSupplier.get();
		intersected.addAll( s1 );
		intersected.retainAll( s2 );

		union.removeAll( intersected );
		return union;
	}

	/**
	 * Find symmetric difference between the two sets.
	 *
	 * @param errMsg is initial error message text, which is expanded with found different elements
	 */
	public static <T> void ValidateDifference(Set<T> s1, Set<T> s2, String errMsg /*, boolean includeDifferentOnes*/) {
		Set<T> diffElements = SymmetricDifference( s1, s2 );
		if (!diffElements.isEmpty()) {
			String joined = diffElements.stream().map( Objects::toString ).collect( Collectors.joining( ", " ) );
			throw new RuntimeException( errMsg + joined /*(includeDifferentOnes ? joined : "")*/ );
		}
	}

	public static <T> void ValidateNonExistingElements(Set<T> setForValidation, Set<T> setToValidateTo, String errMsg) {
		Set<T> nonExisting = new HashSet<>( setForValidation );
		nonExisting.removeAll( new HashSet<>( setToValidateTo ) );

		if (!nonExisting.isEmpty()) {
			String joined = nonExisting.stream().map( Objects::toString ).collect( Collectors.joining( ", " ) );
			throw new RuntimeException( errMsg + joined );
		}
	}

	public static <T> Set<T> Intersect(Set<T> s1, Set<T> s2) {
		Set<T> tmp = new HashSet<>( s1 );
		tmp.retainAll( s2 );
		return tmp;
	}

	public static <T> Set<T> IntersectAll(Collection<Set<T>> sets) {
		Set<T> res = new HashSet<>();
		if (sets == null || sets.size() == 0) {
			return res;
		}

		List<Set<T>> allSets = new ArrayList<>( sets );
		res = new HashSet<>( allSets.get( 0 ) );
		for (int i = 1; i < allSets.size(); i++) {
			res.retainAll( allSets.get( i ) );
		}

		return res;
	}

	public static <T> Set<T> IntersectAll(Set<T>... sets) {
		return IntersectAll( Arrays.asList( sets ) );
	}

	public static Long ToSafeLong(String valueAsStr, String errorMessage) {
		try {
			return Long.parseLong( valueAsStr );
		} catch (NumberFormatException e) {
			throw new RuntimeException( errorMessage );
		}
	}

	/**
	 * Intentionally set to return null if needed
	 */
	public static Long ToSafeLongOrDefault(String valueAsStr, Long defaultValue) {
		try {
			return Long.parseLong( valueAsStr );
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public static Long ToLong(String valueAsStr) {
		return ToSafeLongOrDefault( valueAsStr, null );
	}

	public static void main(String[] args) {
		// ATTR_ID like '%A7%'
		// and
		// ATTR_NAME = 'IS_SURVEY_FINISH'
		Predicate<String> p1 = s -> s.contains( "A7" );
		Predicate<String> p2 = s -> s.length() > 3;
		List<Predicate<String>> pss = Arrays.asList( p1, p2 );

		boolean res1 = p1.test( "neki A7" );
		System.err.println( "res1 : " + res1 );
		boolean res2 = p2.test( "neki A7" );
		System.err.println( "res2 : " + res2 );

		Predicate<String> resPred = pss.stream().reduce( e -> true, Predicate::and );
		boolean res = resPred.test( "  A7  " );
		System.err.println( "res : " + res );

		User dobrivoje = new User();
		dobrivoje.setFirstName( "Dobrivoje" );
		Predicate<User> up = u -> u.getFirstName().toLowerCase().contains( "dobri" );
		boolean resDobri = up.test( dobrivoje );
		System.err.println( "resDobri : " + resDobri );

		double d = (double) 100 * 634 / 1651;
		System.out.println( "Double upto 2 decimal places: " + String.format( "%.2f", d ) );

		double a = (double) 1664 / 100;
		double onePercent = Math.ceil( a );
		System.out.println( "onePercent : " + onePercent );

		String acaPath = "slike/regular/aca1.png";
		try (InputStream is = DataConversionUtil.class.getClassLoader().getResourceAsStream( acaPath );) {
			System.err.println( "is == null ? " + (is == null) );
			Resource resource = new ClassPathResource( acaPath );
			InputStream is2 = resource.getInputStream();
			System.err.println( "is2 == null ? " + (is2 == null) );
		} catch (Exception e) {
			throw new RuntimeException( e );
		}

		Supplier<BasicPairDto> s = () -> BasicPairDto.Of( 1, "111111" );
		Consumer<BasicPairDto> c = System.err::println;
		c.accept( s.get() );
	}
}
