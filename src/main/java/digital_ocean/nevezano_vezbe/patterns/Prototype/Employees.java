package digital_ocean.nevezano_vezbe.patterns.Prototype;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Employees implements Cloneable {

	private final List<String> empList;

	public Employees() {
		empList = new ArrayList<>();
	}

	public Employees(List<String> empList) {
		this.empList = empList;
	}

	public void loadData() {
		empList.add( "Pankaj" );
		empList.add( "Raj" );
		empList.add( "David" );
		empList.add( "Lisa" );
	}

	@Override
	public Employees clone() {
		// shallow copy !!!
		/*try {
			return (Employees) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}*/

		return new Employees( new ArrayList<>( empList ) );
	}

	public static void main(String[] args) {
		Employees employees1 = new Employees();
		employees1.loadData();
		Employees employees2 = employees1.clone();

		employees1.getEmpList().forEach( System.out::println );
		System.err.println( "--------" );
		employees2.getEmpList().forEach( System.out::println );

		System.err.println( "#############################################" );
		boolean isShallow = employees1.getEmpList() == employees2.getEmpList();
		System.err.println( isShallow );

		System.err.println( "removal....." );
		employees1.getEmpList().removeIf( s -> s.equalsIgnoreCase( "Raj" ) || s.equalsIgnoreCase( "Lisa" ) );
		employees1.getEmpList().forEach( System.out::println );
		employees2.getEmpList().forEach( System.out::println );
	}
}
