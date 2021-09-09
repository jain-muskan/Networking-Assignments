class Mono 
{
	static char[] enc = {'B','C','E','F','A','D','G','I','H','L','M','J','N','K','Q','S','O','P','Y','X','Z','R','T','V','U','W'};
	public static StringBuffer encrypt(String text) 
	{ 
		StringBuffer result= new StringBuffer(); 

		for (int i=0; i<text.length(); i++) 
		{ 
			 
			char ch = enc[(int)(text.charAt(i)-65)]; 
			result.append(ch); 
			
		} 
		return result; 
	} 
	
	public static StringBuffer decrypt(String text) 
	{ 
		StringBuffer result= new StringBuffer(); 
		int pos = 0;
		for (int i=0; i<text.length(); i++) 
		{
			for (int j=0;j<enc.length;j++)
			{
				if(text.charAt(i) == enc[j])
				{
					pos = j;
					break;
				}
			}
			char ch = (char)(65+pos);
			result.append(ch);
		} 
		return result; 
	}

	public static void main(String[] args) 
	{ 
		String text = "ATTACKATONCE"; 
	
		System.out.println("Text : " + text); 
		
		String enc_text =  encrypt(text).toString();
		System.out.println("Cipher: " + enc_text);
		System.out.println("Cipher: " + decrypt(enc_text));
	} 
} 
