import java.util.Scanner;

public class Playfair{
	
	static char[][] mat = new char[5][5];
	public static int[] find_pos(char x)
	{
		int ans[] = new int[2];
		for(int i=0; i<5; i++)
			for(int j=0; j<5; j++)
			{
				if( mat[i][j] == x )
				{
					ans[0] = i;
					ans[1] = j;
				}
			}
		return ans;
	}

	public static String encrypt(String pt)
	{
		int pos1[] = new int[2];
		int pos2[] = new int[2];
		String enc = "";
		for(int i=0; i<pt.length(); i=i+2)
		{
			if( pt.charAt(i) == pt.charAt(i+1) )
			{
				pt = pt.substring(0,i+1) + "x" + pt.substring(i+1,pt.length());
			}
		}
		System.out.println(pt);
		if( pt.length() % 2 !=0 )
			pt = pt + "x";

		System.out.println(pt);
		for(int i=0; i<pt.length(); i=i+2)
		{
			pos1 = find_pos(pt.charAt(i));
			pos2 = find_pos(pt.charAt(i+1));


			if( pos1[0] == pos2[0] )
				enc = enc + mat[pos1[0]][(pos1[1]+1)%5] + mat[pos2[0]][(pos2[1]+1)%5];
			else if ( pos1[1] == pos2[1] )
				enc = enc + mat[(pos1[0]+1)%5][pos1[1]] + mat[(pos2[0]+1)%5][pos2[1]];
			else
				enc = enc + mat[pos1[0]][pos2[1]] + mat[pos2[0]][pos1[1]];

		}
		return enc;
	
	}
	public static String decrypt(String ct)
	{
		int pos1[] = new int[2];
		int pos2[] = new int[2];
		String dec = "";
		for(int i=0; i<ct.length(); i=i+2)
		{
			pos1 = find_pos(ct.charAt(i));
			pos2 = find_pos(ct.charAt(i+1));

			System.out.println(pos1[0] + " " +pos1[1] + "   " + pos2[0] + "" + pos2[1]);
			if( pos1[0] == pos2[0] )
				dec = dec + mat[pos1[0]][(pos1[1]-1+5)%5] + mat[pos2[0]][(pos2[1]-1+5)%5];
			else if ( pos1[1] == pos2[1] )
				dec = dec + mat[(pos1[0]-1+5)%5][pos1[1]] + mat[(pos2[0]-1+5)%5][pos2[1]];
			else
				dec = dec + mat[pos1[0]][pos2[1]] + mat[pos2[0]][pos1[1]];
		}

		return dec;
		
	}

	public static void main(String[] args) 
	{
		String pt, enc, dec, key;
		pt = "balloon";
		key = "monarchy";
		int alpha[] = new int[26];
		String new_key = key;

		for(int i=0; i<key.length(); i++)
		{
			 alpha[  key.charAt(i) - 'a' ] = 1;
		}
		alpha['j'-'a'] = 1;
		for(int i=0; i<26; i++)
		{
			if(alpha[i]==0)
				new_key = new_key + (char)(i+'a');
		}
		System.out.println("KEYY"+new_key);
		int ind = 0;
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<5; j++)
			{
				mat[i][j] = new_key.charAt(ind);
				ind = ind + 1;
			}
		}


		for(int i=0; i<5; i++)
		{
			for(int j=0; j<5; j++)
			{
				System.out.print(mat[i][j] + "  ");
			}
			System.out.println("");
		}
		enc = encrypt(pt);

		System.out.println("Encrypted text is " + enc);

		dec = decrypt(enc);

		System.out.println("Decrypted text is "+ dec);
	}
}