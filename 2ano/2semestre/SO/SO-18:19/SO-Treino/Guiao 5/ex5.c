#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main(int argc, char const *argv[]){
	char *cmd[5] = {"grep","-v","ˆ#","/etc/passwd",NULL};
	char *cmd1[4] = {"cut","-f7","-d:",NULL};
	char *cmd2[2] = {"uniq",NULL};
	char *cmd3[3] = {"wc","-l",NULL};

    int pd1[2], pid, pd2[2], pid1, pd3[2], pid2, pid3, status;
    pipe(pd1);
    pipe(pd2);
    pipe(pd3);

    pid = fork();



    if(pid==0){
        close(pd1[0]);
        dup2(pd1[1],1);
        close(pd1[1]);
        execvp(cmd[0],cmd);
        _exit(0);
    }

    wait(&status);
    close(pd1[1]);
    pid1 = fork();


    if(pid1==0){
        close(pd2[0]);
        dup2(pd1[0],0);
        close(pd1[0]);
        dup2(pd2[1],1);
        close(pd2[1]);
        execvp(cmd1[0],cmd1);
        _exit(0);
    }

    wait(&status);
    close(pd2[1]);
    close(pd1[0]);
    pid2 = fork();
    
    if(pid2==0){
        close(pd2[1]);
        close(pd3[0]);
        dup2(pd2[0],0);
        close(pd2[0]);
        dup2(pd3[1],1);
        close(pd3[1]);
        execvp(cmd2[0],cmd2);
        exit(0);
    }

    wait(&status);
    close(pd3[1]);
    close(pd2[0]);
    pid3 = fork();
    
    if(pid3==0){
        close(pd3[1]);
        dup2(pd3[0],0);
        close(pd3[0]);
        execvp(cmd3[0],cmd3);
        exit(0);
    }
    
    close(pd3[0]);
    wait(&status);

    return 0;
}