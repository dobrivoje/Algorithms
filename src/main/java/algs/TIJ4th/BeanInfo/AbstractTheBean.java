package algs.TIJ4th.BeanInfo;

import algs.TIJ4th.str785.annotations.Constraints;
import algs.TIJ4th.str785.annotations.SQLInteger;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@ChangeLog
public abstract class AbstractTheBean {

    protected String someStr;

    @SQLInteger(constraints = @Constraints(primaryKey = true, unique = true))
    protected Integer value;

    //    @ChangeLog(skip = true)
    @ChangeLog.Exclude
    protected Date modifiedAt;

    protected List<Integer> indeksi = new ArrayList<>();

    public AbstractTheBean(String someStr, Integer value) {
        this.someStr = someStr;
        this.value = value;
    }
}
