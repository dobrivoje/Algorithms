package algs.TIJ4th.BeanInfo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//@ChangeLog(includeAllFields = false)
public class TheBean extends AbstractTheBean {

    private Date   datum;
    private String imeIPrezime;

    //    @ChangeLog.Exclude
//    @ChangeLog.Include
    private Long mojaBakutaId;

    public TheBean(Date datum, String imeIPrezime, Long mojaBakutaId) {
        this.datum = datum;
        this.imeIPrezime = imeIPrezime;
        this.mojaBakutaId = mojaBakutaId;
    }

    public TheBean(String someStr, Integer value, Date datum) {
        super(someStr, value);
        this.datum = datum;
    }
}
