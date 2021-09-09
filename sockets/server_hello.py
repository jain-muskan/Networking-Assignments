import socket

s= socket.socket()
print('Socket created')

s.bind(('localhost',9999))
s.listen(1)
print('waiting for connections')

c , add = s.accept()
c_name = c.recv(1024).decode()
print("Connected with",c_name,"-",add)
c.send(bytes("Hello, " + c_name,'utf-8'))

c.close()
s.close()