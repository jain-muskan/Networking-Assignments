import socket 
import math
c= socket.socket()
c.connect(('localhost',9999))
message = input("message")
keyword = input("Word of distinct letters").lower()
c.send(bytes(keyword,'utf-8'))

keyword = list(keyword) 

cols = len(keyword)
key = [0] * cols
for i in range(cols):
    key[i] = int(ord(keyword[i])-97)

l = len(message)
rows = math.ceil(l/cols)

text = message + 'x'*(rows*cols-l)

encArray = [['x' for j in range(cols)] for i in range(rows)]

ord = [0] * cols
for i in range(cols):
    ord[i] = i

for i in range(cols):
    for j in range(cols-i-1):
        if key[j+1] < key[j]:
            key[j],key[j+1] = key[j+1],key[j]
            ord[j],ord[j+1] = ord[j+1],ord[j]

print("order-",ord)         

k = 0
for i in range(rows):
    for j in range(cols):
        encArray[i][j] = message[k]
        k+=1
        if k >= l:
            break
    if k>=l:
        break


enc_message = ''
for j in ord:
    for i in range(rows):
        enc_message += encArray[i][j]

print("Encoded matrix",encArray)
c.send(bytes(enc_message,'utf-8'))
print("Given message:",message)
print("Encoded form:",enc_message)

print(c.recv(1024).decode())
