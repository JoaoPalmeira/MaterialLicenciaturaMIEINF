#include<unistd.h>
#include<fcntl.h>
// se inverter os papeis na ultima alinea é exatamente o mesmo
int main(){


	int fd[2];
	
	pipe(fd);
		
	if(fork()!=0) // pai
	{
		close(fd[0]);
		write(fd[1],"apanha\n",7);
		sleep(5);		

	}
	else{ // filho
		char ch;
		close(fd[1]);
		while(read(fd[0],&ch,1) > 0 && ch!=0) write(1,&ch,1); 


	    } 	

_exit(1);


}
