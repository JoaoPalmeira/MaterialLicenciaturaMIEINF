#include<stdio.h>
#include<unistd.h>


int main(){
	printf("Antes\n");
	execlp("ps","ps","-l",NULL);
		
	// execl("/bin/ps","ps","-l",0);
	//system("ps -l");

	printf("Depois\n");


}
