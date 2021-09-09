key_size = int(input("Choose key size: 4,8 or 32: "))
bin_key = input("Enter binary key of size "+str(key_size) +": ")
key = int(bin_key,2)
msg = input("Your message: ")
enc_msg = ""
for x in msg.lower():
    if x!=' ':
        c = bin(( ord(x) + key - 97 )% 26 + 97 )[2:]
        if len(c) < 8:
            c = '0'*(8-len(c)) + c
        enc_msg += c
    else:
        enc_msg += " "

print(enc_msg)

for i in range(pow(2,key_size)):
    dec_msg = ""
    count = 0
    char = ''
    for y in enc_msg:
        if y!=' ':
            char += y
            count += 1
            if count == 8:
                dec_msg += chr((int(char,2) - 97 - i + 26 ) %26 + 97)
                count = 0
                char = ''
        else:
            dec_msg += " "
    if dec_msg == msg :
        print("MATCH FOUND, KEY = 00" + str(bin(i)[2:]))
        break