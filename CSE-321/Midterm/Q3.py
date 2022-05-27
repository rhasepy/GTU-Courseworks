def parseArr(arr, l, h): 

    piv = arr[h] 
    i = j = l 

    for i in range(l, h): 
        if arr[i] < piv: 

            a[i], a[j]= a[j], a[i] 
            j+= 1

    a[j], a[h]= a[h], a[j] 

    return j 
 
def quickS(arr, l, h): 
    if l<h: 

        piv = parseArr(arr, l, h) 
        quickS(arr, l, piv-1) 
        quickS(arr, piv + 1, h) 

        return arr 

def insertionS(arr, l, n): 

    for i in range(l + 1, n + 1): 

        value = arr[i] 
        j = i 

        while j > l and arr[j-1] > value: 
            arr[j]= arr[j-1] 
            j-= 1

        arr[j]= value 
  
def hybrid(arr, l, h): 

    while l < h: 
  
        if h-l + 1 > 15: 
            piv = parseArr(arr, l, h) 

            if piv-l > h-piv: 
                hybrid(arr, piv + 1, h) 
                h = piv-1

            else: 
                hybrid(arr, l, piv-1) 
                l = piv + 1

        else: 
            insertionS(arr, l, h) 
            break

  
arr = [ 45, 23, 90, 18, 49, 85, 15, 66, 53, 44, 80, 23, 26, 48, 16, 52, 24, 97, 40, 67, 88 ] 
hybrid(arr, 0, 20) 
print(arr) 