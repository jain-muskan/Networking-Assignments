import java.util.Scanner;

class Hill
{
	int[] matmul(int[][]K, int[]P)
	{
		int[] C = new int[2];
		for(int i=0; i<2; i++)
		{
			for(int j=0; j<2; j++)
			{
				C[i] = C[i] + K[i][j]*P[j];
			}
			C[i] = C[i]%26;
		}	
		return C;
	}

	String encrypt(String pt, String key)
	{
		int[] P = new int[2];
		int[][] K = new int[2][2];
		int[] C = new int[2];
		String enc = "";
		int ind=0;
		for(int i=0; i<2; i++)
		{	for(int j=0; j<2; j++)
			{
				K[i][j] = key.charAt(ind)-'a';
				ind = ind+1;
			}	
		}

		for(int i=0; i<pt.length(); i=i+2)
		{
			P[0] = (int)(pt.charAt(i)-'a');
			P[1] = (int)(pt.charAt(i+1)-'a');

			C = matmul(K,P);

			enc = enc + (char)(C[0]+'a') + (char)(C[1]+'a');
		}
		return enc;

	}

	int[][] inverse(int[][] K)
	{
		int[][] out=new int[2][2];
        out[0][0]=K[1][1];
        out[1][1]=K[0][0];
        out[0][1]=-K[0][1];
        out[1][0]=-K[1][0];

        int det=(K[0][0]*K[1][1])-(K[0][1]*K[1][0]);

        det=Math.floorMod(det,26);
        int invdet=0;

        while(  (det*invdet)%26!=1  )
        	{invdet++;}

        System.out.println("det"+det+"  invdet="+invdet);

        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {//System.out.print(out[i][j]+"  ");
            	out[i][j]=Math.floorMod((out[i][j]*invdet),26);
            	System.out.print(out[i][j]+"  ");}
        }
        return out;
	}

	String decrypt(String ct, String key)
	{
		int[] P = new int[2];
		int[][] K = new int[2][2];
		int[] C = new int[2];
		String dec = "";
		int ind=0;
		for(int i=0; i<2; i++)
		{	for(int j=0; j<2; j++)
			{
				K[i][j] = key.charAt(ind)-'a';
				ind = ind+1;
			}
		}

		K = inverse(K);

		for(int i=0; i<ct.length(); i=i+2)
		{
			C[0] = (int)(ct.charAt(i)-'a');
			C[1] = (int)(ct.charAt(i+1)-'a');

			P = matmul(K,C);

			dec = dec + (char)(P[0]+'a') + (char)(P[1]+'a');
		}	
		return dec;

	}


	Hill()
	{
		String pt, key, enc, dec;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter plain text");
		pt = s.next();

		System.out.println("Enter key");
		key = s.next();

		enc = encrypt(pt,key);

		System.out.println("Encrypted text is " + enc);

		dec = decrypt(enc, key);

		System.out.println("Decrypted text is "+ dec);

	}	

	public static void main(String[] args)
	{
		Hill hill = new Hill();
	}	
}	