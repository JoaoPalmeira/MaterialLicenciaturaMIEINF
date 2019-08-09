#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char* argv[])
{
	//argv[0] = "ls";

	execvp(argv[0],argv); // temos de remover sempre o primeiro argumento que é dado
	                      // pois é o argumento do ficheiro executavel
	
	return 0;
}