#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct node{
  int id;
  int pid;
  char** args;
}node, *Node;

void criaNode(char* argv[], int pid);