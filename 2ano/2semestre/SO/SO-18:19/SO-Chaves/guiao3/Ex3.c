#include <unistd.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
	int i=0;
	for(i=0;i<argc;i++)
		printf("%dº argumento: %s\n",i+1, argv[i]);
	return 0;
}