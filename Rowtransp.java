import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;
public class Rowtransp
{
	public static int findCurInd(String s_key, String key, int ind)
	{
		for(int i=0; i<key.length(); i++)
		{		

			if( key.charAt(i) == s_key.charAt(ind) )
				return i;
		}
		return -1;
	}
	
	public static String encrypt(String pt, String key)
	{
		String enc="";

		char mat[][] = new char[10][10];
		int rows = (int)Math.ceil( pt.length()/key.length() );
		int cols = key.length();
		int ind=0;
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<cols; j++)
			{
				//System.out.println("  ind "+ind);
				if(ind < pt.length())
				{
					mat[i][j] = pt.charAt(ind);
					ind = ind +1;
				}
				else break;
			}
		}
		String sorted_key = sort_key( key );
		System.out.println("sorted key  =  " +sorted_key);
		ind=0;
		for(int i=0; i<cols; i++)
		{
				int cur_ind = findCurInd(sorted_key, key, ind);
				ind = ind+1;
				System.out.println(" cur_ind =  " + cur_ind);
				key = key.substring(0,cur_ind) + "$" + key.substring(cur_ind+1, key.length());
				
				for(int j=0; j<rows; j++)
				{
					enc = enc + mat[j][cur_ind];
					
				}
		}
		return enc;
	}
	
	public static String decrypt(String ct, String key)
	{
		String dec="";

		char mat[][] = new char[10][10];
		int rows = (int)Math.ceil( (float)ct.length()/key.length() );
		int cols = key.length();
		int ind=0;
		String sorted_key = sort_key( key );
		System.out.println("sorted key  =  " +sorted_key);
		ind=0;
		int p=0;
		for(int i=0; i<cols; i++)
		{
				int cur_ind = findCurInd(sorted_key, key, ind);
				ind = ind+1;
				System.out.println(" cur_ind =  " + cur_ind);
				key = key.substring(0,cur_ind) + "$" + key.substring(cur_ind+1, key.length());
				
				for(int j=0; j<rows; j++)
				{
					mat[j][cur_ind] = ct.charAt(p);
					p = p+1;
				}
		}
		
		for(int i=0; i<rows; i++)
			for(int j=0; j<cols; j++)
				dec = dec + mat[i][j];
		
		return dec;
	}
	
	public static String sort_key(String key)
	{
		char temp[] = key.toCharArray(); 
          
        // sort temp
        Arrays.sort(temp); 
          
        // return new sorted string 
        return new String(temp); 
	}
	
	public static void main(String args[])
	{
		
		String pt, key, enc, dec;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter plaintext");
		pt = s.next();
		
		System.out.println("Enter key");
		key = s.next();
		
		enc = encrypt(pt, key);
		System.out.println("ENcrypted text is     " +enc);
		
		dec = decrypt(enc, key);
		System.out.println("Decrpyted text"  + dec);
		
	}
}