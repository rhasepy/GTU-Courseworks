all: main

main: 171044033_main.o NPuzzle.o
	g++ 171044033_main.o NPuzzle.o -o main

171044033_main.o: 171044033_main.cpp
		g++ -c 171044033_main.cpp

NPuzzle.o: NPuzzle.cpp
	g++ -c NPuzzle.cpp

clean:
	rm *.o main
