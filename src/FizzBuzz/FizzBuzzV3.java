package FizzBuzz;

import java.util.Scanner;

public class FizzBuzzV3 {
	public static void main(String args[])   {
		int fuzz = 0;
		int bizz = 0;
		int n = 0;
		Scanner sc = new Scanner(System.in);  
		System.out.print("Enter the number: ");
		n = sc.nextInt();
		System.out.print("Enter the fuzz: ");
		fuzz = sc.nextInt();
		System.out.print("Enter the bizz: ");
		bizz = sc.nextInt();
		if (fuzz <= 0 || bizz <= 0) {
			System.out.println("Introduce a number above 0");
			System.exit(0);
		}
		if (n%fuzz==0) {
			System.out.print("fuzz ");  
		} 
		if (n%bizz==0) {
			System.out.print("bizz ");  
		}
		if (n%fuzz != 0 && n%bizz != 0) {
			System.out.print(n);
		}
		sc.close();
	}
}
