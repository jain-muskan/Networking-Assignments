import socket

c= socket.socket()
c.connect(('localhost',9999))
keyword = input("Enter a Keyword:")
message = input("Type Message:")

c.send(bytes(keyword,'utf-8'))
keys = []
for i in range(26):
    key_row = []
    for j in range(26):
        key_row.append(chr(97+(i+j)%26))
    keys.append(key_row)


while len(keyword) < len(message):
    keyword += keyword

enc_message = ''
for row,col in zip(message,keyword):
    if row.isalpha():
        enc_message += keys[ord(row)-97][ord(col)-97] 
    else:
        enc_message += row

c.send(bytes(enc_message,'utf-8'))
print("Given message:",message)
print("Encoded form:",enc_message)

print(c.recv(1024).decode())