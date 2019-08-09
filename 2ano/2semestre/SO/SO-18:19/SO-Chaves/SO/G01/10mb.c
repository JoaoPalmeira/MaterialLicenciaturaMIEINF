#include <unistd.h>
#include <fcntl.h>

int main (int argc, char* argv[]) {
	int fp = open(argv[1],O_RDONLY|O_WRONLY|O_CREAT,0666);

	if (fp != -1) {
		char ch ='a';
		int i;
		for (i=0;i<10000000;i++) {
			write(fp,&ch,1);
		}
	}
	close(fp);
	return 0;
}