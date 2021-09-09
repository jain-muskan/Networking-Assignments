public class hill {
    public static void main(String args[]){
        HillCipher h = new HillCipher("ehqc");

        String msg = "abcd";
        if(msg.length()%2==1){
            msg+='x';
        }
        System.out.println(msg);
        msg = h.encrypt(msg);
        System.out.println(msg);
        msg = h.decrypt(msg);
        System.out.println(msg);

    }
}

class HillCipher{

    int key[][];
    int invKey[][];
    int det;

    HillCipher(String k){
        this.key = new int[2][2];
        this.key[0][0] = k.charAt(0)-'a'+1;
        this.key[0][1] = k.charAt(1)-'a'+1;
        this.key[1][0] = k.charAt(2)-'a'+1;
        this.key[1][1] = k.charAt(3)-'a'+1;

        det = key[0][0]*key[1][1]-key[1][0]*key[0][1];

//        inverse key
        int d = modInverse(det,26);
        d = det<0?-1*d:d;
        invKey = new int[2][2];
        invKey[0][0] = makePos(key[1][1]*d,26);
        invKey[1][1] = makePos(key[0][0]*d,26);
        invKey[0][1] = makePos(-1*key[0][1]*d,26);
        invKey[1][0] = makePos(-1*key[1][0]*d,26);

    }

    int makePos(int a,int b){
        while(a<0){
            a+=b;
        }
        return a%26;
    }

    String encrypt(String msg){
        String res = "";
        int n = msg.length();
        int temp[] = new int[2];

        for(int i=0;i<n;i+=2){
            temp[0] = msg.charAt(i)-'a'+1;
            temp[1] = msg.charAt(i+1)-'a'+1;
            temp = matMul(key,temp);
            res+=(char)(temp[0]+'a'-1)+"";
            res+=(char)(temp[1]+'a'-1)+"";
        }
        return res;
    }

    String decrypt(String msg){
        String res = "";
        int n = msg.length();
        int temp[] = new int[2];

        for(int i=0;i<n;i+=2){
            temp[0] = msg.charAt(i)-'a'+1;
            temp[1] = msg.charAt(i+1)-'a'+1;
            temp = matMul(invKey,temp);

            res+=(char)(temp[0]+'a'-1)+"";
            res+=(char)(temp[1]+'a'-1)+"";
        }
        return res;
    }

    int modInverse(int a,int m){
        a = a<0?-1*a:a;
        for(int i=0;i<m;i++){
            if((a*i)%m==1){
                return i;
            }
        }
        return -1;
    }

    int[] matMul(int A[][], int []x){
        int[] res = new int[2];
        res[0] = (A[0][0]*x[0]+A[0][1]*x[1])%26;
        res[1] = (A[1][0]*x[0]+A[1][1]*x[1])%26;
        return res;
    }
}

