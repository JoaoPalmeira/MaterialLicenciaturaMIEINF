#include <unistd.h>
#include <stdio.h>
int main(){
	printf("Vai executar em execlp\n");
	execlp("ps","ps","-l",NULL);
	printf("isto nao devia aparecer\n");
}
