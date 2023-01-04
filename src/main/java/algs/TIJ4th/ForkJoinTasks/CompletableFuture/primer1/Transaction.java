package algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer1;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Transaction {

	long   id;
	String description;

	public Transaction(long id) {
		this.id = id;
		this.description = "Transaction-" + id;
	}
}
