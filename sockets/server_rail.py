import socket
import numpy
s= socket.socket()
print('Socket created')

s.bind(('localhost',9999))
s.listen(1)
print('waiting for connections')

c , add = s.accept()
key = int(c.recv(1024).decode())

message = c.recv(1024).decode()

rail = [['\n' for i in range(len(message))]  
                  for j in range(key)] 
      
flag = None
row, col = 0, 0
    
for i in range(len(message)): 
    if row == 0: 
        flag = True
    if row == key - 1: 
        flag = False
        
    rail[row][col] = '*'
    col += 1
        
    if flag: 
        row += 1
    else: 
        row -= 1

index = 0
for i in range(key): 
    for j in range(len(message)): 
        if ((rail[i][j] == '*') and
            (index < len(message))): 
            rail[i][j] = message[index] 
            index += 1
        
dec_message = ''
row, col = 0, 0

for i in range(len(message)): 
    if row == 0: 
        flag = True
    if row == key-1: 
        flag = False
            
    if (rail[row][col] != '*'): 
        dec_message += rail[row][col]
        col += 1
        
    if flag: 
            row += 1
    else: 
            row -= 1

c.send(bytes("server has received your message" ,'utf-8'))

print("Received message:",message)
print("Decoded form:",dec_message)

c.close()
s.close()
