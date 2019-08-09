#include<stdio.h>
#include<unistd.h>
#include<string.h>



int main(int argc,char* argv[]){  // argv é um array de strings


	char minibuff; 
	
	if(argc > 1){ write(1,argv[1],strlen(argv[1])); write(1,"\n",1); return 1;}
	
	while(read(0,&minibuff,1) > 0) // o &minibuff é do tipo char*, por isso funciona
		write(1,&minibuff,1);
	


return 1;
}
