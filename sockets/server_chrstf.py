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
    chstf_message = c.recv(1024).decode()
    if chstf_message == "7eexit7e":
        c.send(bytes("closing connection" ,'utf-8'))
        c.close()
        s.close() 
        break
    else:
        i = 0
        message = ""

        while i< len(chstf_message) and chstf_message[i] != "7" and i+1 < len(chstf_message) and chstf_message[i+1] !='e' :
            i += 1
        i += 2

        while i< len(chstf_message):
            if chstf_message[i] == "7" and i+1 < len(chstf_message) and chstf_message[i+1] =='e' :
                break
            if chstf_message[i] == '#':
                i += 1

            message += chstf_message[i]
            i += 1

        if i >= len(chstf_message):
            c.send(bytes("Rewrite the prev message" ,'utf-8'))
        else:
            info.append(message)
            c.send(bytes("we have received your message" ,'utf-8'))
   
print(info)

