#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <string.h>

void ex6(char* args)
{
	int i=0;
	char* str[10];
	str[i] = strtok(args, " \t\n");
	for(i=1;str[i-1] != NULL;i++)
		str[i] = strtok(NULL, " \t\n");
	if(fork()==0)
		execvp(str[0],str);
}

void ex7(){
	char str[1024];
	int i=0;
	while(fgets(str,1024,stdin))
		ex6(str);
}

int main()
{
	ex7();
}