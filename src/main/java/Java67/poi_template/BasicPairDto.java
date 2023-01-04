package Java67.poi_template;

import com.deepoove.poi.data.PictureRenderData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BasicPairDto {

	Object                  val1;
	Object                  val2;
	Object                  val3;
	List<PictureRenderData> sss;

	public static BasicPairDto Of(Object val1, Object val2) {
		return new BasicPairDto( val1, val2, null, null );
	}

	public static BasicPairDto Of(Object val1, Object val2, List<PictureRenderData> sss, Object val3) {
		return new BasicPairDto( val1, val2, val3, sss );
	}
}
