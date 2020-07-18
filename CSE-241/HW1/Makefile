all: main

main: 171044033_main.o source.o
	g++ 171044033_main.o source.o -o main

171044033_main.o: 171044033_main.cpp
		g++ -c 171044033_main.cpp

source.o: source.cpp
	g++ -c source.cpp

clean:
	rm *.o main
