package Java67.poi_template;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicDto {

	String name;

	public static BasicDto Of(String name) {
		return new BasicDto( name );
	}
}
