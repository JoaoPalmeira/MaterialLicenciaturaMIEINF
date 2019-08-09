#ifndef _VARIABLE
#define _VARIABLE

typedef struct variable
{
    int category;
    int level;
    char *association;
    int type; /* Integer 0, Array 1, Matrix 2 */
    char *identifier;
    int address;
    int size;
    int dimension1;
    int dimension2;
} *Variable;

Variable initializeVariable(int iCategory, int iLevel, char *sAssociation, 
    int iType, char *sIdentifier, int iAddress, int iSize, int iDimension1, int iDimension2);
Variable deleteVariable(Variable v);

#endif
