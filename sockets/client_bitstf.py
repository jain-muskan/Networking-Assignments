import socket
c= socket.socket()
c.connect(('localhost',9999))
message = input("Message:")
bs_message = ""
count = 0
for x in message:
    bs_message = bs_message + x
    if x == '1':
        count += 1
        if count == 5:
            bs_message = bs_message + '0'
            count = 0
c.send(bytes(bs_message,'utf-8'))
print(c.recv(1024).decode())

