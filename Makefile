CXX=g++
CXXFLAGS=-std=c++20 -Wall -Wextra -O2
TARGET=nicnac16-asm
SRC=$(wildcard src/*.cpp)

all: $(TARGET)

$(TARGET): $(SRC)
	$(CXX) $(CXXFLAGS) -o $@ $(SRC)

test: $(TARGET)
	./tests/run_tests.sh

clean:
	rm -f $(TARGET)
