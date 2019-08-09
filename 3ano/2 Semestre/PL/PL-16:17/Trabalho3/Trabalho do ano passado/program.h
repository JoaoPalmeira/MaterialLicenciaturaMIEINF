#ifndef _PROGRAM
#define _PROGRAM

typedef struct program
{
    int category;
    char *identifier;
} *Program;

Program initializeProgram(int iCategory, char *sIdentifier);
Program deleteProgram(Program p);

#endif
