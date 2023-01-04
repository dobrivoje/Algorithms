package algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer2.kast;

import algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer2.kast.infra.Enumable;
import lombok.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportResultDto<T> {

    T        result;
    Enumable column;
    @Builder.Default
    boolean successful = false;
    String    errorMessage;
    Exception importException;

    //<editor-fold desc="helpers">
    public static <T> ImportResultDto<T> CreateError(Enumable column, String errorMessage) {
        return ImportResultDto.<T>builder().column( column )
                              .errorMessage( errorMessage ).importException( new RuntimeException( errorMessage ) )
                              .build();
    }
    //</editor-fold>
}
