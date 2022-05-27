def algorithm3(advertisements, revenues, sizeB, M, t):

	table = [0 for i in range(M+1)]

	j = 0
	for i in range(1, M + 1):

		if(j < sizeB):
			if (advertisements[j] != i):
				table[i] = table[i - 1]

			else:
				if (i <= t):
					table[i] = max(table[i-1], revenues[j])
				else:
					table[i] = max(table[i-t-1] + revenues[j], table[i-1])

				j += 1

		else:
			table[i] = table[i - 1]


	return table[M]

advertisements = [5, 8, 15, 23, 24, 30]
revenues = [1, 3, 5, 12, 4, 18]

print(algorithm3(advertisements, revenues, len(advertisements), 20, 4))