import socket

c= socket.socket()
c.connect(('localhost',9999))


while True :
    message = input("Type 'exit' to end or enter the message:")
    chstf_message = "7e" 
    i = 0
    while i < len(message):
        if (message[i] == "7" and i+1 < len(message) and message[i+1] == "e") or message[i]=="#":
            chstf_message += "#"
        chstf_message += message[i]
        i += 1
    chstf_message += "7e"
    c.send(bytes(chstf_message,'utf-8'))
    print(c.recv(1024).decode())
    if message =="exit":
        break




