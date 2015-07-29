CC = gcc
OPTIONS = -Wall
EXE = tri
OBJS = listeChainee.o gestionFichier.o gestionDesStatistiques.o main.o

.SUFFIXES: .c .o

all: link

compile: $(OBJS)

.c.o:
	$(CC) $(OPTIONS) -c $*.c

link: compile
	$(CC) $(OBJS) -o $(EXE)

clean:
	rm $(EXE)
	rm $(OBJS)
