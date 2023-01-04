package algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer2.kast;

import algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer2.kast.infra.Enumable;
import lombok.Getter;

@Getter
public enum ET implements Enumable {
	OK( "sve je u redu" ),
	NO( "katastrofa" ),
	MESANO( "mešano meso" );

	public final String details;

	ET(String details) {
		this.details = details;
	}

	@Override
	public Integer getColumnNo() {
		return this.ordinal();
	}

	@Override
	public String getName() {
		return this.details;
	}
}
