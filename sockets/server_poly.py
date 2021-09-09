import socket

def find_char(j_index,char):
    for i in range(26):
        if keys[i][j_index] == char:
            return chr(97+i)

s= socket.socket()
print('Socket created')

s.bind(('localhost',9999))
s.listen(1)
print('waiting for connections')

c , add = s.accept()
keyword = c.recv(1024).decode()

message = c.recv(1024).decode()

keys = []
for i in range(26):
    key_row = []
    for j in range(26):
        key_row.append(chr(97+(i+j)%26))
    keys.append(key_row)

while len(keyword) < len(message):
    keyword += keyword

dec_message = ''
for x,y in zip(message,keyword):
    if x.isalpha():
        dec_message += find_char(ord(y)-97,x)
    else:
        dec_message += x

c.send(bytes("server has received your message" ,'utf-8'))

print("Received message:",message)
print("Decoded form:",dec_message)

c.close()
s.close()
