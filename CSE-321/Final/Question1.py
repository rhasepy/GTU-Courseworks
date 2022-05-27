def algorithm1(str_param):

    charArr = list(str_param)
    dbTable_size = len(charArr)
    dp_table = [[False for i in range(dbTable_size)] for j in range(dbTable_size)]
    
    longestStr = ""
    maxLen = 1

    for i in range(0, dbTable_size):
        dp_table[i][i] = True

    for i in range(0, dbTable_size-1):
        if (charArr[i] == charArr[i + 1]):
            dp_table[i][i + 1] = True
            maxLen = 2

    for index in range(0, dbTable_size):
        for i in range(0, dbTable_size - index):

            j = i + index

            if(charArr[i] == charArr[j] and (j-i <= 2 or dp_table[i+1][j-1])):
                dp_table[i][j] = True
                if (j-i+1 > maxLen):
                    maxLen = j - i + 1
                    longestStr = str_param[i:j+1]

    if (len(longestStr) == 0):
        return "There is not palindrome substring in " + str_param

    return longestStr

print(algorithm1("abbccbba"))
print(algorithm1("xYasdTALATqw232"))
print(algorithm1("ab"))