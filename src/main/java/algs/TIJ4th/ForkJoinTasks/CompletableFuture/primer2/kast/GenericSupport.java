package algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer2.kast;

import algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer2.kast.infra.Enumable;
import lombok.NonNull;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class GenericSupport {

	public static <T, R extends Collection<T>> BiFunction<R, R, R> UniteCollectionsProcessor(T withElement) {
		return (c1, c2) -> {
			c2.add( withElement );
			c1.addAll( c2 );
			return c1;
		};
	}

	protected <T, R extends ImportResultDto<T>> void addRepeatableError(R error, Map<Enumable, List<R>> collectedErrors) {
		addErrorOn( error, collectedErrors, LinkedList::new );
	}

	protected <T, R extends ImportResultDto<T>> void addError(R error, Map<Enumable, Set<R>> collectedErrors) {
		addErrorOn( error, collectedErrors, LinkedHashSet::new );
	}

	protected <T, R extends ImportResultDto<T>, C extends Collection<R>> void addErrorOn(
			R error, Map<Enumable, C> collectedErrors, @NonNull Supplier<C> collectionSupplier) {

		C collection = collectionSupplier.get();
		collection.add( error );
		collectedErrors.merge( error.getColumn(), collection, UniteCollectionsProcessor( error ) );
	}
}
