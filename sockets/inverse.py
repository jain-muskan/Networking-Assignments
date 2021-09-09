import numpy as np
A = [[6,24,1],[13,16,10],[20,17,15]]
A = [[1,5],[3,4]]
def modInverse(a, m) : 
    a = a % m; 
    for x in range(1, m) : 
        if ((a * x) % m == 1) : 
            return x 
    return 1
  
m = 26
inverse = np.linalg.inv(A)
det = int(np.linalg.det(A))
mi_det = modInverse(det, m)
modular_inverse = (inverse * det * mi_det)%26
print(mi_det) 
print(inverse)
print(det)
print(modular_inverse)