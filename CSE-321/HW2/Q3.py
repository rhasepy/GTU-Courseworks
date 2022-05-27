"""
	Author: Muharrem Ozan Yesiller 171044033
"""

def merge(arr, left, middle, right):

	num1 = middle - left + 1
	num2 = right - middle

	L_sub = [0] * (num1)
	R_sub = [0] * (num2)

	for i in range(0, num1):
		L_sub[i] = arr[left + i]

	for i in range(0, num2):
		R_sub[i] = arr[middle + 1 + i]

	i = 0 # init index of left subarray
	j = 0 # init index of right subarray
	k = left # init index of merged subarray

	while i < num1 and j < num2:

		if L_sub[i] <= R_sub[j]:
			arr[k] = L_sub[i]
			i += 1

		else:
			arr[k] = R_sub[j]
			j += 1

		k += 1

	while i < num1:
		arr[k]  = L_sub[i]
		i += 1
		k += 1

def mergeSort(arr, left, right):

	if left < right:
		middle = (left + (right - 1)) // 2

		mergeSort(arr, left, middle)
		mergeSort(arr, middle + 1, right)
		merge(arr, left, middle, right)

def productPairs_helper(arr, target, pairs):

	mergeSort(arr, 0, (len(arr) - 1))

	beg = 0
	end = len(arr) - 1

	while beg < end :
		temp_t = arr[beg] * arr[end]

		if temp_t == target:
			pairs.append([arr[beg], arr[end]])

		if temp_t < target:
			beg += 1

		else:
			end -= 1

def productPairs(arr, target, pairs):
	productPairs_helper(arr, target, pairs)

	if len(pairs) == 0:
		print("No Pairs...")

	else:
		print("Pairs: " + str(pairs))

arr = [1, 2, 3, 6, 5, 4]
target = 6
pairs = []

productPairs(arr, target, pairs)
