def merge(arr, tempArr, l, m, r): 
    count = 0
  
    i = l  
    j = m 
    k = l 
    while (i <= (m - 1) and j <= r):

        if (arr[i] > 2 * arr[j]): 
            count += m - i
            j += 1

        else: 
            i += 1

    i = l 
    j = m 
    k = l 
    while (i <= (m - 1) and j <= r): 

        if (arr[i] <= arr[j]): 
            tempArr[k] = arr[i] 
            i += 1
            k += 1

        else: 
            tempArr[k] = arr[j] 
            k += 1
            j += 1

    while (i <= m - 1): 
        tempArr[k] = arr[i] 
        i += 1
        k += 1

    while (j <= r): 
        tempArr[k] = arr[j] 
        j += 1
        k += 1

    for i in range(l, r + 1): 
        arr[i] = tempArr[i] 
  
    return count 

def mergeSort(arr, tempArr, l, r): 
    m, count = 0, 0
    if (r > l): 
        m = (r + l) // 2
        count = mergeSort(arr, tempArr, l, m) 
        count += mergeSort(arr, tempArr, m + 1, r) 
        count += merge(arr, tempArr, l, m + 1, r) 
    return count 


def algorithm5(arr): 
    tempArr = [0 for i in range(len(arr))] 
    return mergeSort(arr, tempArr, 0, len(arr) - 1)


arr = [1, 20, 6, 4, 5]
print(algorithm5(arr)) 