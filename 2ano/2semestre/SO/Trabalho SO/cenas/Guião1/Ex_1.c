#include <unistd.h>   
#include <fcntl.h>


//Ex.1

int main(int argc, char const *argv[])
{

char save;

while(read(0,&save,1)){


	write(1,&save,1);
}

return 0;

}

