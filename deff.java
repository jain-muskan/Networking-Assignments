import java.util.Scanner;
import java.lang.Math;
import java.math.*;

class Node
{
		double Ya;
		int Xa;
		int alp,q;
		char x;
		public double public_key_gen()
		{
			
			Scanner s = new Scanner(System.in);
			System.out.println("Enter private key of "+x);
			Xa = s.nextInt();

			Ya = Math.pow(alp, Xa) % q;	
			System.out.println("X"+x+" :"+Xa);
			System.out.println("alp :"+alp);
			System.out.println("q :"+q);

			System.out.println("Public key of Y"+x+" :"+Ya);
			return Ya;

		}
		public BigInteger secret_key_gen(double Yb,char y)
		{
			
			System.out.println("X"+x+" :"+Xa);
			System.out.println("Y"+y+" :"+Yb);
			System.out.println("q "+q);

			BigInteger Q = BigInteger.valueOf(q);

			BigInteger YB = BigDecimal.valueOf( Yb ).toBigInteger();
			BigInteger res = (YB.pow(Xa)).mod(Q);
			System.out.println("Key = "+res);

			return res;	
		}
		Node(int alp, int q, char x)
		{
			this.alp = alp;
			this.q = q;
			this.x = x;
		}
}

class deff
{	

	double Ya, Yb;
	public int q, alp;
	deff()
	{
		System.out.println("Enter a prime number");
		Scanner s = new Scanner(System.in);
		q = s.nextInt();

		System.out.println("Enter primitive root");
		alp = s.nextInt();


		Node a = new Node(alp,q,'A');
		Node b = new Node(alp,q,'B');
		
		Ya = a.public_key_gen();
		Yb = b.public_key_gen();

		BigInteger k1,k2;
		k1 = a.secret_key_gen(Yb,'B');
		k2 = b.secret_key_gen(Ya,'A');
		System.out.println("k1 = "+k1+"   "+ "k2 = "+k2);
		if(k1.compareTo(k2) ==0 ) 
			System.out.println(" Keys matched !");
		else
			System.out.println("Key exchange failed");
		
	}
	public static void main(String[] args)
	{
	}
}