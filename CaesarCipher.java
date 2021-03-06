class CaesarCipher 
{ 
	public static StringBuffer encrypt(String text, int s) 
	{ 
		StringBuffer result= new StringBuffer(); 

		for (int i=0; i<text.length(); i++) 
		{ 
			if (Character.isUpperCase(text.charAt(i))) 
			{ 
				char ch = (char)(((int)text.charAt(i) + 
								s - 65) % 26 + 65); 
				result.append(ch); 
			} 
			else
			{ 
				char ch = (char)(((int)text.charAt(i) + 
								s - 97) % 26 + 97); 
				result.append(ch); 
			} 
		} 
		return result; 
	} 
	
	public static StringBuffer decrypt(String text, int s) 
	{ 
		StringBuffer result= new StringBuffer(); 

		for (int i=0; i<text.length(); i++) 
		{ 
			if (Character.isUpperCase(text.charAt(i))) 
			{ 
				char ch = (char)(((int)text.charAt(i) - 
								s - 65 + 26 ) % 26 + 65); 
				result.append(ch); 
			} 
			else
			{ 
				char ch = (char)(((int)text.charAt(i) - 
								s - 97 + 26 ) % 26 + 97); 
				result.append(ch); 
			} 
		} 
		return result; 
	}
	public static void main(String[] args) 
	{ 
		String text = "ATTACKATONCE"; 
		int s = 4; 
		System.out.println("Text : " + text); 
		System.out.println("Shift : " + s); 
		String enc_text =  encrypt(text, s).toString();
		System.out.println("Cipher: " + enc_text);
		System.out.println("Cipher: " + decrypt(enc_text, s));
	} 
} 
