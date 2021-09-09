import socket

c= socket.socket()
c.connect(('localhost',9999))
message = input("Enter the message").upper()
key = input("Give key")

c.send(bytes(key,'utf-8'))
key = int(key)
rail = [['\n' for i in range(len(message))] 
                  for j in range(key)] 
      
flag = False
row, col = 0, 0
    
for i in range(len(message)): 
    if (row == 0) or (row == key - 1): 
        flag = not flag 
        
    rail[row][col] = message[i] 
    col += 1
        
    if flag: 
        row += 1
    else: 
        row -= 1

enc_message = ''
for i in range(key): 
    for j in range(len(message)): 
        if rail[i][j] != '\n': 
            enc_message += rail[i][j] 

c.send(bytes(enc_message,'utf-8'))
print("Given message:",message)
print("Encoded form:",enc_message)

print(c.recv(1024).decode())