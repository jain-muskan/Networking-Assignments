import socket
import pickle

def key_finder(val):
    for x in key.keys():
        if key[x] == val:
            return x

s= socket.socket()
print('Socket created')

s.bind(('localhost',9999))
s.listen(1)
print('waiting for connections')

c , add = s.accept()
key = pickle.loads(c.recv(1024))

message = c.recv(1024).decode()

print("Connected with",add)
dec_message = ""
for x in message.lower():
    if x in key.values():
        dec_message = dec_message + key_finder(x)
    else:
        dec_message = dec_message + x

c.send(bytes("server has received your message" ,'utf-8'))

print("Received message:",message)
print("Decoded form:",dec_message)

c.close()
s.close()