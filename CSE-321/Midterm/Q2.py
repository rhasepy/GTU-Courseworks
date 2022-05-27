def binary(arr, bitIndex, bitSize):
	index = bitIndex - (bitSize - len(arr))

	if arr[index-1] == "0":
		return 0
	elif arr[index-1] == "1":
		return 1
	else:
		raise Exception("Invalid Array...")

def findMissing(arr, bitPos, bitSize):

	if bitPos < 0:
		return 0

	oddArr = []
	evenArr = []

	index = 0
	bound = len(arr)
	while (index < bound):

		if binary(arr[index], bitPos, bitSize) == 0:
			evenArr.append(arr[index])
		else:
			oddArr.append(arr[index])
		index += 1

	if len(oddArr) >= len(evenArr):
		return findMissing(evenArr, bitPos - 1, bitSize) << 1 | 0
	else:
		return findMissing(oddArr, bitPos - 1, bitSize) << 1 | 1



def main():

	arr = ["0000", "0001", "0010", "0011", "0100", "0101", "1110"]
	bitSize = 32
	print(findMissing(arr, bitSize, bitSize))

main()