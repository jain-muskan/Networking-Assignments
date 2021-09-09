import socket
import numpy as np
s= socket.socket()
print('Socket created')

s.bind(('localhost',9999))
s.listen(1)
print('waiting for connections')

c , add = s.accept()
keyword = c.recv(1024).decode()

message = c.recv(1024).decode()

def modInverse(a, m) : 
    a = a % m; 
    for x in range(1, m) : 
        if ((a * x) % m == 1) : 
            return x 
    return 1

keyMatrix = [[0] * 3 for i in range(3)] 
messageVector = [[0] for i in range(3)] 
decipherMatrix = [[0] for i in range(3)] 
  

def decrypt(messageVector): 
    m = 26
    inverse = np.linalg.inv(keyMatrix)
    det = int(np.linalg.det(keyMatrix))
    mi = modInverse(int(det), m)
    
    keyInverse = (inverse * det * mi)%26
    print(messageVector)
    for i in range(3): 
        for j in range(1): 
            decipherMatrix[i][j] = 0
            for k in range(3): 
                decipherMatrix[i][j] += (int(round(keyInverse[i][k])) * 
                                       messageVector[k][j]) 
        decipherMatrix[i][j] = int(round(decipherMatrix[i][j])%26)
            
    print(keyInverse,decipherMatrix)
    
k = 0

for i in range(3): 
    for j in range(3): 
        keyMatrix[i][j] = ord(keyword[k]) % 65
        k += 1

for i in range(3): 
    messageVector[i][0] = ord(message[i]) % 65

decrypt(messageVector) 

dec_message = ''
for i in range(3): 
    dec_message += chr(decipherMatrix[i][0] + 65)

c.send(bytes("server has received your message" ,'utf-8'))

print("Received message:",message)
print("Decoded form:",dec_message)

c.close()
s.close()
