import java.util.Scanner;
import java.lang.Math;

public class Railfence{
	
	public static String encrypt(String pt, int key)
	{
		String enc = "";
		for(int i=0; i<key; i++)
		{
			for(int j=i; j<pt.length(); j=j+key)
				enc = enc + pt.charAt(j);
		}
		return enc;
	}
	public static String decrypt(String ct, int key)
	{
		String dec = "";
		int n = (int)Math.ceil((float)ct.length()/key);
		System.out.println(ct +"   "+ct.length() +"    "+ n + " " +key);
		for(int i=0; i<n; i++)
		{
			for(int j=i; j < ct.length() ; j=j+n)
				dec = dec + ct.charAt(j);
		}
		return dec;	
	}

	public static void main(String[] args) 
	{
		
		String pt, enc, dec, ct;
		int key;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter plain text");
		pt = s.next();
		System.out.println("Enter key(Integer)");
		key = s.nextInt();

		enc = encrypt(pt,key);

		System.out.println("Encrypted text is " + enc);

		dec = decrypt(enc, key);

		System.out.println("Decrypted text is "+ dec);
	}
}