#include <unistd.h>
#include <sys/wait.h>

#define MAX_CHILD 128

int iterator;
int childs_num;
int alive;
int child_id[MAX_CHILD];

void start_child_iteration(int max) {
	iterator = 0;
	alive = childs_num = max;

	if (max > 0) {
		kill(child_id[0], SIGCONT);
		alarm(1);
	}
}

void next_child() {
	kill(child_id[iterator], SIGSTOP);

	if (++iterator == childs_num)
		iterator = 0;

	kill(child_id[iterator], SIGCONT);

	alarm(1);
}

void kill_child() {
	if(waitpid(-1, NULL, WNOHANG)){
		alive--;
		next_child();
	}
}

int main(int argc, char** argv) {
	int i, id;

	signal(SIGALRM, next_child);
	signal(SIGCHLD, kill_child);

	for(i = 1; i < argc; i++) {
		id = fork();

		if (id)
			child_id[i-1] = id;
		else{
			kill(getpid(), SIGSTOP);
			execlp(argv[i], argv[i], NULL);
			_exit(1);
		}

	}

	start_child_iteration(argc-1);

	while(alive)
		pause();

	return 0;
}