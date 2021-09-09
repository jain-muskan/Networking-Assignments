import socket

c= socket.socket()
c.connect(('localhost',9999))

key = int(input("Your key:"))
message = input("Message:")
enc_message = ""
for x in message.lower():
    if x!=' ':
        enc_message = enc_message + chr((ord(x) -97 + key)% 26 + 97)
    else:
        enc_message = enc_message + " "

c.send(bytes(str(key),'utf-8'))
c.send(bytes(enc_message,'utf-8'))

print(c.recv(1024).decode())

