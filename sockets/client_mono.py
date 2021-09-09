import socket
import pickle

c= socket.socket()
c.connect(('localhost',9999))


message = input("Type Message:")
key = {'a':'o','b':'j','c':'t','d':'c','e':'h','f':'r','g':'f','h':'k','i':'v','j':'w','k':'b','l':'m','m':'q','n':'i','o':'s','p':'z','q':'d','r':'u','s':'y','t':'n','u':'p','v':'g','w':'a','x':'l','y':'x','z':'e'}
key_dmp = pickle.dumps(key)
print("key(byte form): ",key_dmp)
c.send(key_dmp)

enc_message = ""
for x in message.lower():
    if x in key.keys():
        enc_message = enc_message + key[x]
    else:
        enc_message = enc_message + x

c.send(bytes(enc_message,'utf-8'))
print("Given message:",message)
print("Encoded form:",enc_message)

print(c.recv(1024).decode())

