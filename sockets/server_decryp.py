import socket

s= socket.socket()
print('Socket created')

s.bind(('localhost',9999))
s.listen(1)
print('waiting for connections')

c , add = s.accept()
key = int(c.recv(1024).decode())
message = c.recv(1024).decode()

print("Connected with",add)
dec_message = ""
for x in message.lower():
    if x!=' ':
        dec_message = dec_message + chr((ord(x) -97 - key + 26 )% 26 + 97)
    else:
        dec_message = dec_message + " "

c.send(bytes("we have received your message" ,'utf-8'))

print(message,"--->",dec_message)

c.close()
s.close()