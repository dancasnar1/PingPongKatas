package FizzBuzz;
import java.util.Scanner;  

public class FizzBuzz {
	public static void main(String args[])   {
		Scanner sc = new Scanner(System.in);  
		System.out.print("Enter the number: "); 
		int n = sc.nextInt(); 
		if (n%3==0) {
			System.out.print("fizz ");  
		} 
		if (n%5==0) {
			System.out.print("buzz ");  
		}
		if (n%3!=0 && n%5!=0) {
			System.out.print(n);
		}
		sc.close();
	}
}   

