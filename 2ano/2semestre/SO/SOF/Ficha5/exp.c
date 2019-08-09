#include <stdio.h>
#include <unistd.h>

int main(){
	int pfd1[2];
	int pfd2[2];
	int n;
	char c;

	pipe(pfd1);
	if(fork()==0){
		close(pfd1[0]);
	
		getchar();
		close(pfd1[1]);
		_exit(1);
	}
	else {
		close(pdf1[1]);

		pipe(pfd2);
		if(fork()==0){
			close(pfd1[0]);
			close(pfd2[0]);

			getchar();

			close(pfd2[1]);
			_exit(1);
		}
		else {
			close(pfd2[1]);
		}
	}
return 0;
}
