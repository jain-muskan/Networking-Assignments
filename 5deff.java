import java.util.Scanner;
import java.lang.Math;
import java.math.*;
class A
{
		double Ya;
		int Xa;
		int alp,q;
		public double public_key_gen()
		{
			Scanner s = new Scanner(System.in);
			System.out.println("ENter private key of A");
			Xa = s.nextInt();

			Ya = Math.pow(alp, Xa) % q;	
			System.out.println("XA "+Xa);
			System.out.println("alp "+alp);
			System.out.println("q "+q);

			System.out.println("Public key of A Ya"+Ya);
			return Ya;

		}
		public BigInteger secret_key_gen(double Yb)
		{
			
			System.out.println("XA "+Xa);
			System.out.println("Yb "+Yb);
			System.out.println("q "+q);

			BigInteger Q = BigInteger.valueOf(q);
			//BigInteger XA = BigInteger.valueOf(Xa);

			BigInteger YB = BigDecimal.valueOf( Yb ).toBigInteger();
			BigInteger res = (YB.pow(Xa)).mod(Q);
			System.out.println("Key = "+res);

			return res;	
		}
		A(int alp, int q)
		{
			this.alp = alp;
			this.q = q;
		}
}
class B
{
	double Yb;
	int Xb;
	int alp,q;
	
		public double public_key_gen()
		{
			Scanner s = new Scanner(System.in);
			System.out.println("ENter private key of B");
			Xb = s.nextInt();	

			Yb = Math.pow(alp, Xb)%q;	

			System.out.println("Xb "+Xb);
			System.out.println("alp "+alp);
			System.out.println("q "+q);


			System.out.println("Public key of B Yb"+Yb);
			return Yb;

		}
		public BigInteger secret_key_gen(double Ya)
		{
			System.out.println("YA "+Ya);
			System.out.println("Xb "+Xb);
			System.out.println("q "+q);

			BigInteger Q = BigInteger.valueOf(q);
			//BigInteger XB = BigInteger.valueOf(Xb);

			BigInteger YA = BigDecimal.valueOf( Ya ).toBigInteger();
			BigInteger res = (YA.pow(Xb)).mod(Q);

			System.out.println("Key = "+res);

			return res;	

		}
		B(int alp, int q)
		{
			this.alp = alp;
			this.q = q;
		}
}

class deff
{	

	int is_primitive_root(int a, int q)
	{
		return 1;
	}
	double Ya, Yb;
	public int q, alp;
	deff()
	{
		System.out.println("Enter a prime number");
		Scanner s = new Scanner(System.in);
		q = s.nextInt();
		/*a = 1;
		while(a<q)
		{
			if( is_primitive_root(a,q) ==1 )
				break;
			else a++;	 
		}*/

		System.out.println("Enter primitive root");
		alp = s.nextInt();


		A a = new A(alp,q);
		B b = new B(alp,q);
		
		Ya = a.public_key_gen();
		Yb = b.public_key_gen();

		BigInteger k1,k2;
		k1 = a.secret_key_gen(Yb);
		k2 = b.secret_key_gen(Ya);
		System.out.println("k1 = "+k1+"   "+ "k2 = "+k2);
		if(k1.compareTo(k2) ==0 ) 
			System.out.println(" KEys matched !");
		else
			System.out.println("Key exchange failed");
		
	}
	public static void main(String[] args)
	{
		deff df = new deff();
	}
}