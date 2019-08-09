


int main(){

int fd;
getchar();
fd = open("output.txt", O_CREAT | O_WRONLY | O_TRUNC, 0666);
getchar();
dup2(fd,1);
getchar();
close(fd); // já não precisamos do fd para nada
getchar();
printf("Viva meus amigos\n");




}
