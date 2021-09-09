import socket

s= socket.socket()
print('Socket created')

s.bind(('localhost',9999))
s.listen(1)
print('waiting for connections')

c , add = s.accept()
print("Connected with",add)

info = []
while True:
    message = c.recv(1024).decode()
    n = int(message[0])
    if message == "4exit":
        c.send(bytes("closing connection" ,'utf-8'))
        c.close()
        s.close() 
        break
    else:
        if len(message) > n :
            info.append(message[1:n+1])
            c.send(bytes("we have received your message" ,'utf-8'))
        else:
            c.send(bytes("Rewrite the prev message" ,'utf-8'))

   
print(info)

