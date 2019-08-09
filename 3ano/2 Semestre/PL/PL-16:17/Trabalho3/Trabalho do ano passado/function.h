#ifndef _FUNCTION
#define _FUNCTION

typedef struct function
{
    int category;
    char *identifier;
    int result; /* Empty 0, Integer 1 */
} *Function;

Function initializeFunction(int iCategory, char *sIdentifier, int iResult);
Function deleteFunction(Function f);

#endif
