#include <stdlib.h>
#include <string.h>
#include "function.h"

Function initializeFunction(int iCategory, char *sIdentifier, int iResult)
{
    Function f = (Function) malloc(sizeof(struct function));

    if (f != NULL)
    {
        f->category = iCategory;
        f->identifier = strdup(sIdentifier);
        f->result = iResult;
    }

    return f;
}

Function deleteFunction(Function f)
{
    if (f != NULL)
    {
        if (f->identifier)
            free(f->identifier);
    
        free(f);
    }

    return NULL;
}
