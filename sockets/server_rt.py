import math
import socket
s= socket.socket()
print('Socket created')

s.bind(('localhost',9999))
s.listen(1)
print('waiting for connections')

c , add = s.accept()
keyword = c.recv(1024).decode()
message = c.recv(1024).decode()

keyword = list(keyword) 

cols = len(keyword)
key = [0] * cols
for i in range(cols):
    key[i] = int(ord(keyword[i])-97)

l = len(message)
rows = math.ceil(l/cols)

decArray = [['x' for j in range(cols)] for i in range(rows)]

ord = [0] * cols
for i in range(cols):
    ord[i] = i

for i in range(cols):
    for j in range(cols-i-1):
        if key[j+1] < key[j]:
            key[j],key[j+1] = key[j+1],key[j]
            ord[j],ord[j+1] = ord[j+1],ord[j]

print("order:",ord)         

k = 0
for j in ord:
    for i in range(rows):
        decArray[i][j] = message[k]
        k += 1

dec_message = ''

for i in range(rows):
    for j in range(cols):
        dec_message += decArray[i][j]

print("Decoded matrix:",decArray)
c.send(bytes("server has received your message" ,'utf-8'))

print("Received message:",message)
print("Decoded form:",dec_message)

c.close()
s.close()
