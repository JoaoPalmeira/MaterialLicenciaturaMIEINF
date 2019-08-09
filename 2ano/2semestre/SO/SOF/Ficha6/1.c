#include <sys/types.h>
#include <sys/stat.h>

void main(){
    mkfifo("pipe",0666);
}