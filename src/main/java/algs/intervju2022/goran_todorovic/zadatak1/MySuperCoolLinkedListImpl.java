package algs.intervju2022.goran_todorovic.zadatak1;

import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

/*
Please write PSEUDO code or write code in your STRONGEST language.
1.	Please write simple implementation for LinkedList data structure
    with Add, Head, Tail and Search (Lookup) methods.

    Please explain time complexity for Add and Search methods of your implementation.
 */
public class MySuperCoolLinkedListImpl<T> implements MyCoolLinkedList<T> {

	private Elements<T> nodes;

	public MySuperCoolLinkedListImpl() {
		nodes = null;
	}

	public MySuperCoolLinkedListImpl(T value) {
		this.nodes = new Elements<>( value );
	}

	/**
	 * Time complexity is O(n), because, {@link Elements#addElement)} <br>
	 * is iterated over on all node elements (in worst case scenario)
	 */
	@Override
	public MyCoolLinkedList<T> add(T value) {
		if (nodes != null) {
			nodes.addElement( value );
		} else {
			nodes = new Elements<>( value );
		}

		return this;
	}

	@Override
	public Optional<T> head() {
		return Optional.ofNullable( nodes ).map( Elements::getValue );
	}

	@Override
	public Optional<T> tail() {
		Elements<T> current = this.nodes;
		while (current.next != null) {
			current = current.next;
		}

		return Optional.of( current ).map( Elements::getValue );
	}

	/**
	 * Time complexity is O(n), similarly to {@link Elements#addElement)} <br>
	 * as again, in order to look up an element, we must iterate on all node elements (in worst case)
	 */
	@Override
	public boolean lookup(T element) {
		Elements<T> current = this.nodes;

		do {
			if (current.getValue().equals( element )) {
				return true;
			}
			current = current.next;
		} while (current != null && current.next != null);

		return false;
	}

	/* just for a demonstration.. */
	@Deprecated
	public void printAllElements() {
		StringBuilder sb = new StringBuilder();
		Elements<T> current = this.nodes;
		do {
			sb.append( current.getValue() ).append( ", " );
			current = current.next;
		} while (current != null);

		String res = sb.toString();
		System.err.println( res.substring( 0, res.length() - 2 ) );
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Elements<T> current;

			@Override
			public boolean hasNext() {
				current = current == null ? nodes : current.next;

				return current != null;
			}

			@Override
			public T next() {
				return current == null ? null : current.getValue();
			}
		};
	}

	@Getter
	@Setter
	private static class Elements<T> {

		private T           value;
		private Elements<T> next;

		public Elements(T value) {
			this.value = value;
			next = null;
		}

		public void addElement(T value) {
			Elements<T> current = this;
			while (current.next != null) {
				current = current.next;
			}

			current.next = new Elements<>( value );
		}
	}

	public static void main(String[] args) {
		MyCoolLinkedList<Integer> numbers = new MySuperCoolLinkedListImpl<>();
		numbers.add( 1 ).add( 11 ).add( 13 ).add( 20 );

		// demonstration :
		// numbers.printAllElements();

		// iterations examples :
		numbers.forEach( System.out::println );
		for (int e : numbers) {
			System.err.println( e );
		}

		MyCoolLinkedList<String> strings = new MySuperCoolLinkedListImpl<>( "begin >>> " );
		for (int i = 100; i < 105; i++) {
			strings.add( String.valueOf( i ) );
		}
		strings.add( "<<< end." );
		strings.forEach( System.out::println );
		for (String e : strings) {
			System.err.println( e );
		}

		System.err.println( "------OK------" );
		System.err.println( strings.lookup( "100" ) );
		System.err.println( strings.lookup( "105" ) );

		System.err.println( "------OK------" );
		numbers.head().map( Objects::toString ).map( "head: "::concat ).ifPresent( System.out::println );
		numbers.tail().map( Objects::toString ).map( "tail: "::concat ).ifPresent( System.out::println );

		strings.head().map( "head: "::concat ).ifPresent( System.out::println );
		strings.tail().map( "tail: "::concat ).ifPresent( System.out::println );
	}
}
