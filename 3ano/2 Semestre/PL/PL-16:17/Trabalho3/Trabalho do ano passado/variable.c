#include <stdlib.h>
#include <string.h>
#include "variable.h"

Variable initializeVariable(int iCategory, int iLevel, char *sAssociation, 
    int iType, char *sIdentifier, int iAddress, int iSize, int iDimension1, int iDimension2)
{
    Variable v = (Variable) malloc(sizeof(struct variable));

    if (v != NULL)
    {
        v->category = iCategory;
        v->level = iLevel;
        v->association = strdup(sAssociation);
        v->type = iType;
        v->identifier = strdup(sIdentifier);
        v->address = iAddress;
        v->size = iSize;
        v->dimension1 = iDimension1;
        v->dimension2 = iDimension2;
    }

    return v;
}

Variable deleteVariable(Variable v)
{
    if (v != NULL)
    {
        if (v->association != NULL)
            free(v->association);
        
        if (v->identifier != NULL)
            free(v->identifier);
    
        free(v);
    }

    return NULL;
}
