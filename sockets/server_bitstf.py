import socket

s= socket.socket()
print('Socket created')

s.bind(('localhost',9999))
s.listen(1)
print('waiting for connections')

c , add = s.accept()
bs_message = c.recv(1024).decode()

print("Connected with",add)
message = ""
count = i = 0
while i < len(bs_message):
    message = message + bs_message[i]
    if bs_message[i] == '1':
        count += 1
        if count == 5:
            i += 1
            count = 0
    i += 1

c.send(bytes("we have received your message" ,'utf-8'))

print(bs_message,"--->",message)

c.close()
s.close()