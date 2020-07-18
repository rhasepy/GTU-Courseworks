all: main

main: 171044033_main.o NPuzzle.o
	g++ 171044033_main.o NPuzzle.o -o main

171044033_main.o: 171044033_main.cpp
		g++ -c -std=c++11 171044033_main.cpp

NPuzzle.o: NPuzzle.cpp
	g++ -c -std=c++11 NPuzzle.cpp

clean:
	rm *.o main
