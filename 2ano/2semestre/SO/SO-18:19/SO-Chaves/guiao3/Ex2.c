#include <unistd.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
	if(fork()==0)
		execlp("ls","ls","-l", NULL);
	return 0;
}