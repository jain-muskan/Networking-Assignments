import socket

c= socket.socket()
c.connect(('localhost',9999))
message = input("Enter a word - 3 letters").upper()
keyword = input("Give key - 9 letters").upper()

c.send(bytes(keyword,'utf-8'))

keyMatrix = [[0] * 3 for i in range(3)] 
messageVector = [[0] for i in range(3)] 
cipherMatrix = [[0] for i in range(3)] 
  

def encrypt(messageVector): 
    for i in range(3): 
        for j in range(1): 
            cipherMatrix[i][j] = 0
            for k in range(3): 
                cipherMatrix[i][j] += (keyMatrix[i][k] * 
                                       messageVector[k][j]) 
            cipherMatrix[i][j] = cipherMatrix[i][j] % 26
    print(cipherMatrix)
    
k = 0
for i in range(3): 
    for j in range(3): 
        keyMatrix[i][j] = ord(keyword[k]) % 65
        k += 1

for i in range(3): 
    messageVector[i][0] = ord(message[i]) % 65

encrypt(messageVector) 
print(keyMatrix)
enc_message = ''
for i in range(3): 
    enc_message += chr(cipherMatrix[i][0] + 65)


c.send(bytes(enc_message,'utf-8'))
print("Given message:",message)
print("Encoded form:",enc_message)

print(c.recv(1024).decode())
    