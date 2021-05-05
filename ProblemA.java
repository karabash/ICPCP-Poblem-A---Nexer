package programming_aptitude_test;


import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.function.Predicate;

// jag har inte haft tid pga skolan annars kunde jag skriva koden med Design Patterns  och design prenciples
public class ProblemA {

	public static void main(String[] args) {
		new ProblemA().runner();
	}

	private static final String flip(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<s.length(); i++)
			if (s.charAt(i) == '0')
				sb.append("1");
			else
				sb.append("0");
		return sb.toString();
	}


	
	private static final void check(String inp1, String inp2) {
		if ((inp1.equals(inp2)) || (inp1.equals(ProblemA.flip(inp2) )))
			System.out.println("Deletion succeeded");
		else
			System.out.println("Deletion failed");
	}

	private void runner() {
		try (Scanner stdin = new Scanner(System.in)) {
			int n = stdin.nextInt();
			String s1 = stdin.next();
			String s2 = stdin.next();

			final boolean checkN = n>=1 && n<=20;
			if(checkN) {
			    	InputChecker inpChecker = (v1, v2) -> {
					Character[] charObjectArray1 = v1.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
					Character[] charObjectArray2 = v2.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
					Stream<Character> stream1 = Arrays.stream(charObjectArray1);
					Stream<Character> stream2 = Arrays.stream(charObjectArray2);
					Predicate<Character> p1 = value1 -> value1 == '0';
					Predicate<Character> p2 = value2 -> value2 == '1';
					return stream1.filter(p1.or(p2)).count() ==  stream2.filter(p1.or(p2)).count();

				};
				check(flipNtimes(inpChecker.check(s1, s2), s1, n), s2);

			}

		};

	} 


	private static final String flipNtimes(boolean checks,String s1, int n){
		for(int i = 0; i<n; i++) {
			String res =  flip(s1);
			s1 = res;
		}   
		return s1;
	}
  
	@FunctionalInterface
	private interface InputChecker{
		boolean check(String s1, String s2);

	}
       
}
