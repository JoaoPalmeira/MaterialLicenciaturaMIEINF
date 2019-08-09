#include<unistd.h>
#include<fcntl.h>
#include<stdlib.h>
#include<inttypes.h>



int main(int args,char* argv[])
	{
		long r,s=1024;
		

		if(args >= 2) s = strtol(argv[1],0,10);


		char buff[s];
		
		int file = open(argv[2],O_RDONLY);
		
		while((r = read(file,buff,s)) > 0)
			write(1,buff,r);	


		close(file);
	return 0;


	}
