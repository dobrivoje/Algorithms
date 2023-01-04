package algs.intervju2022.tredovi;

interface sestric extends rodjak1,
						  rodjak2 {

	String izgovori(String param);

	static void main(String[] args) {
		sestric s1 = new sestric() {
			@Override
			public String izgovori(String param) {
				return "sestric !!!";
			}

			@Override
			public void r1_f1(int param) {
				System.err.println( "r1_f1 - " + param );
			}

			@Override
			public void r2_f1(int param) {
				System.err.println( "r2_f1 - " + param );
			}
		};

		System.err.println(s1.izgovori( "aaaa" ));
		s1.r1_f1( 11 );
		s1.r2_f1( 69 );
	}
}