import java.util.*;
import java.math.*;

class RSA
{
	static int gcd(int e, int z)
	{
		if(e==0)
			return z;	
		else
			return gcd(z%e,e);
	}

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int p1,p2,n,z,d=0,e,k=5;
		System.out.println("Number: ");
		int msg=sc.nextInt();
		double enc_msg;
		BigInteger dec_msg;
		System.out.println("Prime number 1:");
		p1=sc.nextInt();
		System.out.println("Prime number 2:");
		p2=sc.nextInt();
		
		n=p1*p2;
		z=(p1-1)*(p2-1);
		for(e=2;e<z;e++)
		{
			if(gcd(e,z)==1)            
			{				
				break;
			}
		}
		System.out.println("e ="+e);
		d = ( k * z + 1 ) / e;				
		System.out.println("d ="+d);		
		enc_msg=(Math.pow(msg,e))%n;
		System.out.println("Encrypted message is :" + enc_msg);
		BigInteger N = BigInteger.valueOf(n);
		BigInteger C = BigDecimal.valueOf(enc_msg).toBigInteger();
		dec_msg = (C.pow(d)).mod(N);
		System.out.println("Derypted message is :" + dec_msg);
		sc.close();
	}

}