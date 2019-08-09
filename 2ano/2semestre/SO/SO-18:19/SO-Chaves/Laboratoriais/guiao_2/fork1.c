#include<stdio.h>
#include<unistd.h>


int main(){
	write(1,"Be\n",4);
	fork();
	write(1,"Bop\n",4);
	fork();
	write(1,"Bap\n",4);

}
