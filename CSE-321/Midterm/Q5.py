arraySize = 4

try:
	import Queue as Q # ver. < 3.0
except ImportError:
	import queue as Q

class Obj:
	def __init__(self):
		self.parent = None
		self.pathCost = 0
		self.cost = 0
		self.workerID = 0
		self.jobID = 0
		self.assigned = [False]*arraySize

	def __lt__(self, other):
		return self.cost < other.cost

	def __gt__(self, other):
		return self.cost > other.cost

	def __eq__(self, other):
		return self.cost == other.cost


def newObj(j1, j2, assigned, parent):

	returnObj = Obj()

	returnObj.assigned = assigned

	returnObj.assigned[j2] = True

	returnObj.parent = parent
	returnObj.workerID = j1
	returnObj.jobID = j2

	return returnObj

def costCalculator(arr, j1, j2, assigned):
	
	cost = 0
	available = [True]*arraySize

	i = j1 + 1
	while (i < arraySize):
		minn = 999999999
		minIndex = -1

		j = 0
		while(j < arraySize):

			if ((not assigned[j]) and available[j] and (arr[i][j] < minn)):
				minIndex = j
				minn = arr[i][j]

			j += 1

		cost += minn
		available[minIndex] = False
		i += 1

	return cost

def findMinCost(arr):

	queue = Q.PriorityQueue()

	assigned = [False]*arraySize

	root = newObj(-1, -1, assigned, Obj())
	root.pathCost = root.cost = 0
	root.workerID = -1

	queue.put(root)

	while (not queue.empty()):

		minn = queue.get()

		i = minn.workerID + 1
		if (i == arraySize):
			return minn.cost

		for j in range(0, arraySize):
			if (not minn.assigned[j]):
				child = newObj(i, j, minn.assigned, minn)
				child.pathCost = minn.pathCost + arr[i][j]
				child.cost = child.pathCost + costCalculator(arr, i, j, child.assigned)
				queue.put(child)


arr = [[9,2,7,8],
		[6,4,3,7],
		[5,8,1,8],
		[7,6,9,4]]

print(findMinCost(arr))