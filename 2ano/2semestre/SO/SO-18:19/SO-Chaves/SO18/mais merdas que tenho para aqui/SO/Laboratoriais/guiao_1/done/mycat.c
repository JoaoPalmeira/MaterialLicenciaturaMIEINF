#include<unistd.h>
#include<fcntl.h>


int main(){
	char *buff;
	
	int r=1;
	while(r){
		r= read(0,buff,1);
		write(1,buff,1);		
		
	}

return 1;
}
