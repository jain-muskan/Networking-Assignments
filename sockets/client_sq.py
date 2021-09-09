import socket

c= socket.socket()
c.connect(('localhost',9999))
message = input("Enter a word").lower()
keyword = input("Give keyword").lower()

c.send(bytes(keyword,'utf-8'))
m_dim = 5
alp = [0]*26
alp[ord('j') - 97] = 1
count = 0
key = [['a' for i in range(m_dim)] for j in range(m_dim)]
i =  j = 0

for x in keyword:
    if alp[ord(x)-97] == 0:
        key[i][j] = x
        j += 1
        if j == m_dim:
            j = 0
            i += 1
        alp[ord(x)-97] = 1

for count in range(26):
    if alp[count] == 0 and i < m_dim:
        key[i][j] = chr(count+97)
        j += 1
        if j == m_dim:
            j = 0
            i += 1
        alp[count] = 1

print(key)

if len(message)%2 !=0 :
    message += 'z'

message.replace('j','i')

def find_pos(x):
    for i in range(len(key)):
        for j in range(len(key[0])):
            if key[i][j] == x :
                return i,j
    return -1,-1

enc_message = ''
for i in range(len(message)//2):
    r1,c1 = find_pos(message[2*i])
    r2,c2 = find_pos(message[2*i+1])

    if r1 == r2 :
        enc_message += key[r1][(c1+1)%m_dim] + key[r1][(c2+1)%m_dim]
    elif c1 == c2 :
        enc_message += key[(r1+1)%m_dim][c1] + key[(r2+1)%m_dim][c1]
    else:
        enc_message += key[r1][c2] + key[r2][c1]

c.send(bytes(enc_message,'utf-8'))
print("Given message:",message)
print("Encoded form:",enc_message)

print(c.recv(1024).decode())
    