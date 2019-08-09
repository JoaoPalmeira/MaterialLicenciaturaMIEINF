#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<unistd.h>
#include<fcntl.h>


int main(int argc,char* argv[]){

		if(argc > 2) {
			struct buffer_t *buff = NULL;
			int f = open(argv[1],O_RONLY);
			if(f){
			int r = create_buffer(f,buff,argv[2]);
			void ** endereco;
			if (r) {
				if((r = readln(buff,endereco)) > 0)
					write(1,&endereco,r);
			
			}	
			close(f);
			_exit(1);
			
			}
		}





	_exit(0);
}
ssize_t readln(struct buffer_t *bufer,void **buf){
	int i=0;
	

	*buf = (int) malloc(sizeof(int) *1024);
	while(bufer->linha[i] != '\n' && i<bufer->n_bytes && j<1024){ buf[i] = linhas[i];  i++; }  
	
	r = read(bufer->fich,bufer->linha,bufer -> n_bytes);
				

	_exit(1);
}
	struct buffer_t{
		int fich;
		int n_bytes;
		char linha[n_bytes];
	}
  int create_buffer(int filedes, struct buffer_t *buffer, size_t nbyte){
	
	buffer = (struct buffer_t*) malloc(sizeof(structbuffer_t));
	buffer->fich = filedes;
	buffer->n_bytes = nbyte;
	buffer->linha = (int) malloc(sizeof(int) * nbyte);		
	_exit(1);
 }
  
int destroy_buffer(struct buffer_t *buffer){
	free(buffer->linha);
	free(buffer);
	_exit(1);	
  }	
