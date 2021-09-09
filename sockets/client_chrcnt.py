import socket

c= socket.socket()
c.connect(('localhost',9999))


while True :
    message = input("Type 'exit' to end or enter the message:")
    c.send(bytes(str(len(message))+message,'utf-8'))
    print(c.recv(1024).decode())
    if message =="exit":
        break




