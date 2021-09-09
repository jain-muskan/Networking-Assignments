import binascii 
import random 

def key_generator(x): 
	key = "" 
	for i in range(int(x)): 
		temp = str(random.randint(0,1))
		key += temp 
	
	return(key) 

def find_xor(x,y): 
	temp = "" 
	for i in range(n): 
		if (x[i] == y[i]): 
			temp += "0"
		else: 
			temp += "1"
			
	return temp 


input_text = input("Enter text: ")
binary_vals = [] 

for x in input_text:
    ascii_val = ord(x)
    binary_vals.append(format(ascii_val,'08b'))

binary_vals = "".join(binary_vals) 

hf = int(len(binary_vals)//2) 
L1 , R1 = binary_vals[0:hf] , binary_vals[hf::]

n = len(R1) 
K1= key_generator(n) 
f1 = find_xor(R1,K1) 
R2 = find_xor(f1,L1) 
L2 = R1 

K2= key_generator(n) 
f2 = find_xor(R2,K2) 
R3 = find_xor(f2,L2) 
L3 = R2 

bin_data = L3 + R3 
enc_val =' '

for i in range(0, len(bin_data), 7): 
	temp = bin_data[i:i + 7]  
	decimal_val = int(temp, 2)
	enc_val += chr(decimal_val) 
	
print("Encrypted Text:", enc_val) 

L4 , R4 = L3 , R3

f3 = find_xor(L4,K2) 
L5 = find_xor(R4,f3) 

R5 = L4 
f4 = find_xor(L5,K1) 
L6 = find_xor(R5,f4) 

R6 = L5 
final = int(L6+R6, 2) 
dec_val = str(binascii.unhexlify( '%x'% final))
print("Decrypted Text: ", dec_val[2:-1]) 
