#include <stdlib.h>
#include <string.h>
#include "program.h"

Program initializeProgram(int iCategory, char *sIdentifier)
{
    Program p = (Program) malloc(sizeof(struct program));

    if (p != NULL)
    {
        p->category = iCategory;
        p->identifier = strdup(sIdentifier);
    }

    return p;
}

Program deleteProgram(Program p)
{
    if (p != NULL)
    {
        if (p->identifier != NULL)
            free(p->identifier);
        
        free(p);
    }

    return NULL;
}
