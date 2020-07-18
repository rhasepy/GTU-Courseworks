all: main
all: driver

main: 171044033_main.o AbstractBoard.o BoardArray1D.o BoardArray2D.o BoardVector.o
		g++ 171044033_main.o AbstractBoard.o BoardArray1D.o BoardArray2D.o BoardVector.o -o main

driver: 171044033_driver.o AbstractBoard.o BoardArray1D.o BoardArray2D.o BoardVector.o
		g++ 171044033_driver.o AbstractBoard.o BoardArray1D.o BoardArray2D.o BoardVector.o -o driver

AbstractBoard.o: AbstractBoard.cpp
		g++ -c -std=c++11 AbstractBoard.cpp

BoardArray1D.o: BoardArray1D.cpp
		g++ -c -std=c++11 BoardArray1D.cpp

BoardArray2D.o: BoardArray2D.cpp
		g++ -c -std=c++11 BoardArray2D.cpp

BoardVector.o: BoardVector.cpp
		g++ -c -std=c++11 BoardVector.cpp

171044033_driver.o: 171044033_driver.cpp
		g++ -c -std=c++11 171044033_driver.cpp

171044033_main.o: 171044033_main.cpp
		g++ -c -std=c++11 171044033_main.cpp

clean:
	rm -rf *o
