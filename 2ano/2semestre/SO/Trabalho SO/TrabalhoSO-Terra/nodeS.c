#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h> 
#include <ctype.h> 
#include <time.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>


//-------------------------------------------------
struct node
{
int nNode;
struct node *next;
}*start=NULL; 

//------------------------------------------------------------

void criaN(int numero) {
char ch='y';
  while(strcmp(&ch,"y")==0){

    struct node *new_node,*current=NULL;

    new_node=(struct node *)malloc(sizeof(struct node));

    new_node->nNode=numero;
    new_node->next=NULL;

    if(start==NULL)
    {
    start=new_node;
    current=new_node;
    }
    else
    {
    current->next=new_node;
    current=new_node;
    }

  printf("Do you want to creat another, yes (y) or no (n) :");
  ch=getchar();
  }
}

//------------------------------------------------------------------

/*void display()
{
struct node *new_node;
 printf("The Linked List : n");
 new_node=start;
 while(new_node!=NULL)
   {
   printf("%d--->",new_node->data);
   new_node=new_node->next;
   }
  printf("NULL");
}*/