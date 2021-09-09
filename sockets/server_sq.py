import socket

s= socket.socket()
print('Socket created')

s.bind(('localhost',9999))
s.listen(1)
print('waiting for connections')

c , add = s.accept()
keyword = c.recv(1024).decode()

message = c.recv(1024).decode()

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

dec_message = ''
for i in range(len(message)//2):
    r1,c1 = find_pos(message[2*i])
    r2,c2 = find_pos(message[2*i+1])

    if r1 == r2 :
        dec_message += key[r1][(c1-1 + m_dim)%m_dim] + key[r1][(c2-1 + m_dim)%m_dim]
    elif c1 == c2 :
        dec_message += key[(r1-1 + m_dim)%m_dim][c1] + key[(r2-1 + m_dim)%m_dim][c1]
    else:
        dec_message += key[r1][c2] + key[r2][c1]

c.send(bytes("server has received your message" ,'utf-8'))

print("Received message:",message)
print("Decoded form:",dec_message)

c.close()
s.close()
