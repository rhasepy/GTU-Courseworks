# Muharrem Ozan Yesiller 171044033

insertionCtr = 0
quickCtr = 0

def insertionSort(arr): 

    global insertionCtr

    for i in range(1, len(arr)): 
        target = arr[i]

        j = i-1
        while (j >= 0 and target < arr[j]): # inserting
                insertionCtr += 1
                arr[j + 1] = arr[j] #swapping
                j -= 1

        arr[j + 1] = target

def partition(arr,low,high):

    global quickCtr
    i = low - 1 # index of small element
    pivot = arr[high] # pivot val 
  
    for j in range(low , high): 

        if (arr[j] < pivot):
            quickCtr += 1
            i = i+1
            arr[i],arr[j] = arr[j],arr[i] 
  
    quickCtr += 1
    arr[i+1],arr[high] = arr[high],arr[i+1]

    return (i+1) 


def quickSort(arr,low,high): 

    if low < high: 
        piv = partition(arr,low,high) 
        quickSort(arr, low, piv-1) 
        quickSort(arr, piv+1, high)


arr = [20, 17, 8, 15, 0, 6]
arr2 = [20, 17, 8, 15, 0, 6]

quickSort(arr, 0, len(arr) - 1)
print("After Quick Sort: ")
print(arr)
print("Swap counter: " + str(quickCtr))

print("---------------------------------")

insertionSort(arr2)
print("After Insertion Sort: ")
print(arr2)
print("Swap counter: " + str(insertionCtr))

print("---------------------------------")

arr = [1, 2, 3, 5, 4]
arr2 = [1, 2, 3, 5, 4]

quickSort(arr, 0, len(arr) - 1)
print("After Quick Sort: ")
print(arr)
print("Swap counter: " + str(quickCtr))

print("---------------------------------")

insertionSort(arr2)
print("After Insertion Sort: ")
print(arr2)
print("Swap counter: " + str(insertionCtr))
