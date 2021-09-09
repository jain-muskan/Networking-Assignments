
class HillCipher 
{ 

	static int[][] inv = new int[2][2];
	static int [][]keyMatrix = new int[2][2]; 
	static int [][]messageVector = new int[2][1];
	static int [][]cipherMatrix = new int[2][1];
	static int [][]decipherMatrix = new int[2][1];
	static int [][]decMatrix = new int[2][1];

static void getKeyMatrix(String key) 
{ 
	int k = 0; 
	for (int i = 0; i < 2; i++) 
	{ 
		for (int j = 0; j < 2; j++) 
		{ 
			keyMatrix[i][j] = (key.charAt(k)) - 65; 
			k++; 
		} 
	} 
} 


static void encrypt( 
			int keyMatrix[][], 
			int messageVector[][]) 
{ 
	int x, i, j; 
	for (i = 0; i < 2; i++) 
	{ 
		for (j = 0; j < 1; j++) 
		{ 
			cipherMatrix[i][j] = 0; 
		
			for (x = 0; x < 2; x++) 
			{ 
				cipherMatrix[i][j] += 
					keyMatrix[i][x] * messageVector[x][j]; 
			} 
		
			cipherMatrix[i][j] = cipherMatrix[i][j] % 26; 
		} 
	} 
} 

static void decrypt( 
			int inv[][], 
			int cipherMatrix[][]) 
{ 
	int x, i, j; 
	for (i = 0; i < 2; i++) 
	{ 
		for (j = 0; j < 1; j++) 
		{ 
			decipherMatrix[i][j] = 0; 
		
			for (x = 0; x < 2; x++) 
			{ 
				decipherMatrix[i][j] += 
					inv[i][x] * cipherMatrix[x][j]; 
			} 
		
			decipherMatrix[i][j] = decipherMatrix[i][j] % 26; 
		} 
	} 
} 

static void inverse(int key[][])
{
	inv[0][0] = -key[1][1];
	inv[1][1] = -key[0][0];
	inv[0][1] = key[0][1];
	inv[1][0] = key[1][0];
	for(int i=0;i<2;i++)
	{
		for(int j=0;j<2;j++)
		{
			System.out.println(inv[i][j]);
		}
	}
	int det = key[0][0]*key[1][1] - key[0][1]*key[1][0];
	System.out.println(det);
	int mul = det>0 ? 1:-1;
	det *= mul;
	int fin_det=-1 ;
	for(int k=1;k<26;k++)
	{
		if(det*k%26==1)
		{
			fin_det = k;
			break;
		}
	}
	fin_det *= mul;
	System.out.println("hey"+det+" "+fin_det);
	for (int i=0;i<2;i++)
	{
		for(int j=0;j<2;j++)
		{
			inv[i][j] = inv[i][j]*fin_det;
			while(inv[i][j]<0)
			{
				inv[i][j] += 26;
			}
			inv[i][j] = inv[i][j]%26;
		}
	}

}



// Function to implement Hill Cipher 
static void HillCipher(String message, String key) 
{ 

	getKeyMatrix(key); 

	  
	for (int i = 0; i < 2; i++) 
		messageVector[i][0] = (message.charAt(i)) % 65; 

	encrypt(keyMatrix, messageVector); 

	String CipherText=""; 
	for (int i = 0; i < 2; i++) 
		CipherText += (char)(cipherMatrix[i][0] + 65); 

	System.out.print(" Ciphertext:" + CipherText); 
	inverse(keyMatrix);

	decrypt(inv, cipherMatrix);
	for (int i=0;i<2;i++)
	{
		for(int j=0;j<2;j++)
		{
			System.out.println("key" + keyMatrix[i][j]);
			System.out.println("inv" + inv[i][j]);
		}
	}
	String decipherText=""; 
	for (int i = 0; i < 2; i++) 
		decipherText += (char)(decMatrix[i][0] + 65); 

	System.out.print(" Ciphertext:" + decipherText); 


} 

// Driver code 
public static void main(String[] args) 
{ 
	// Get the message to be encrypted 
	String message = "DC"; 

	// Get the key 
	String key = "CDFH"; 

	HillCipher(message, key); 
	} 
} 

// This code has been contributed by 29AjayKumar 
