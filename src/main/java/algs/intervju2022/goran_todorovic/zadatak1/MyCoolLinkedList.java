package algs.intervju2022.goran_todorovic.zadatak1;

import java.util.Optional;

public interface MyCoolLinkedList<T> extends Iterable<T> {

	MyCoolLinkedList<T> add(T value);

	Optional<T> head();

	Optional<T> tail();

	boolean lookup(T element);
}
