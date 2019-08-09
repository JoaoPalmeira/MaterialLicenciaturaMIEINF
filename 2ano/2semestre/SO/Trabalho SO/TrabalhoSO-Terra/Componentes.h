#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* itoa(int i, char b[]);
ssize_t readln(int fildes, char *buf, size_t nbyte);
char** parser(char* str);
void escreve(char* buffer, int valor);
void getArgs(int argc,int index,char** res,int column,char* argv[],char* exec_args[]);
void consts(char* value, char* buffer, int fildes);
void filtro(int coluna, char* operador, int operando, char* buffer, int fildes);
void window(int coluna, char* operador, int linhas, char* buffer, int fildes);
void spaw(int argc, char* argv[], char* buffer, int fildes);
