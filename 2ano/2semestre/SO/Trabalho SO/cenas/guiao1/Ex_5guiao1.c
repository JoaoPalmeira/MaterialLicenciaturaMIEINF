#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

#define MAXSIZE 100



ssize_t readln (int fd, void *buffer, ssize_t max)
{
      int i = 0;
      int n;

      while (i < max - 1){

            n = read (fd,buffer+i, 1);

            if (n <= 0)
                  break;
                  
            if (((char*)buffer)[i] == '\n')
                  break;
            i++;
      }

      ((char*)buffer)[i] = '\0';

      return 0;
}
int main (int argc, char **argv)
{
      char buffer[MAXSIZE];
      int fd;

      fd = open("texto.txt", O_RDONLY);

      readln(fd, buffer, MAXSIZE);
      printf("linha -> %s\n", buffer);

      return 0;
}