CC = gcc
CFLAGS = -Wall -Wextra
OBJ = main.o resource.o
OUT = terminal_engine.exe

all: $(OUT)

$(OUT): $(OBJ)
	$(CC) -o $@ $^ 

main.o: main.c
	$(CC) $(CFLAGS) -c $< -o $@

resource.o: resource.rc
	windres $< -o $@

clean:
	del $(OBJ) $(OUT)