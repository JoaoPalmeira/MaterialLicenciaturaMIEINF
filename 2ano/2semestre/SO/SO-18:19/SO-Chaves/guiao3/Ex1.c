#include <unistd.h>

int main(int argc, char const *argv[])
{
	execlp("ls","ls","-l", NULL);
	return 0;
}