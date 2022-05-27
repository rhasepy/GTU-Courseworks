def divide(arr, lowerB, upperB, returnVal):

    if (lowerB == upperB):
        return min(returnVal, arr[upperB])
 
    if (upperB - lowerB == 1):
        if arr[lowerB] < arr[upperB]:
            returnVal = min(returnVal, arr[lowerB])
        else:
            returnVal = min(returnVal, arr[upperB])

        return returnVal
 
    mid = (lowerB + upperB) // 2
    returnVal = divide(arr, lowerB, mid, returnVal)
    returnVal = divide(arr, mid + 1, upperB, returnVal)
 
    return returnVal

def query(arr, queryArr):

    for i in range(0, len(queryArr)):
        lowerB = queryArr[i][0]
        upperB = queryArr[i][1]
        x = divide(arr, lowerB, upperB, arr[lowerB])
        
        print("returnVal of Intervals of [%d, %d] -> %d" % (lowerB, upperB, x))
    
 
arr = [7, 2, 9, 3, 1, 6, 7, 8, 4]
queryArr = [ [0, 7], [4, 7], [5, 8], [0, 3]]

query(arr, queryArr)