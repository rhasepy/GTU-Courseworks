all: main

main: GTUContainer.o GTUIterator.o GTUVector.o GTUSet.o 171044033_driver.o GTUIteratorConst.o
		g++ GTUContainer.o GTUIterator.o GTUVector.o GTUSet.o 171044033_driver.o GTUIteratorConst.o -o main

171044033_driver.o: 171044033_driver.cpp
		g++ -c -std=c++11 171044033_driver.cpp
		
GTUContainer.o: GTUContainer.cpp
		g++ -c -std=c++11 GTUContainer.cpp

GTUVector.o: GTUVector.cpp
		g++ -c -std=c++11 GTUVector.cpp

GTUSet.o: GTUSet.cpp
		g++ -c -std=c++11 GTUSet.cpp

GTUIterator.o: GTUIterator.cpp
		g++ -c -std=c++11 GTUIterator.cpp

GTUIteratorConst.o: GTUIteratorConst.cpp
		g++ -c -std=c++11 GTUIteratorConst.cpp

clean:
	rm *.o main
