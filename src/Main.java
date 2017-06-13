import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

class A {
	
}
class B extends A {
	
}
public class Main {

	public static void main(String[] args) {
		
		A a  = new A();
		B b  = new B();
		System.out.println(b instanceof A);
		
	}

}
