import java.util.Scanner;

class OTP
{	
	String encrypt(String pt, String key)
	{
		String ct = "";
		for(int i=0; i<pt.length(); i++)
		{
			int x = (pt.charAt(i)-'A') + (key.charAt(i)-'A');
			if( x>26 )
				x = x-26;
			ct = ct + (char)(x+'A');
		}
		return ct;
	}
	String decrypt(String ct, String key)
	{
		String pt = "";
		for(int i=0; i<ct.length(); i++)
		{
			int x = (ct.charAt(i)-'A') - (key.charAt(i)-'A');
			if( x<0 )
				x = x+26;
			pt = pt + (char)(x+'A');	
					System.out.println("Pt = "+pt);

		}
		System.out.println("Pt = "+pt);
		return pt;	
	}

	OTP()
	{
		String pt, enc, dec, ct, key;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter plain text");
		pt = s.next();
		System.out.println("Enter key (equal length of pt)");
		key = s.next();

		enc = encrypt(pt,key);

		System.out.println("Encrypted text is " + enc);

		dec = decrypt(enc, key);

		System.out.println("Decrypted text is "+ dec);

	}	
	public static void main(String[] args)
	{

		OTP otp = new OTP();
	}	
}