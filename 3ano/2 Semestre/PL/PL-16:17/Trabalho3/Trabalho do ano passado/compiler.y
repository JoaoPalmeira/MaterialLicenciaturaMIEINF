%{
#include <glib.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "program.h"
#include "variable.h"
#include "function.h"

#define CATEGORY_PROGRAM 0
#define CATEGORY_VARIABLE 1
#define CATEGORY_FUNCTION 2
#define TYPE_INTEGER 0
#define TYPE_ARRAY 1
#define TYPE_MATRIX 2
#define TYPE_BOOL 3
#define RETURN_EMPTY 0
#define RETURN_INTEGER 1
#define SIZE 1

extern int yylex(void);
extern int yylineno;
extern char *yytext;

int yyerror(char *s);
void changeAssociation(char *association);
void createVariable(int type, char *identifier, int dimension1, int dimension2);
int getVariableAddress(char *identifier);
int getVariableType(char *identifier);
int getVariableDimension(int numberDimension, char *identifier);
void createFunction(char *identifier, int result);
int getFunctionResult(char *identifier);
gboolean verifyFunction (char *identifier);

Program p = NULL;
GHashTable *variablesHash = NULL;
GHashTable *functionsHash = NULL;

int nextAddress = 0;
int currentLevel = 0;
char *currentAssociation = NULL;

int conditionNumber = 0;
int boolSimples = 0;
int boolEstruturada = 0;
%}

%union {
    struct information {
        union {
            char *identifier;
            int number;
            char *string;
        };
        int type;
    } value;
}

%token ELSE
%token EMPTY
%token FUNCTION
%token IF
%token INSTRUCTIONS
%token INTEGER
%token PROGRAM
%token READ
%token RETURN
%token VARIABLE
%token WHILE
%token WRITE
%token AND_OP
%token OR_OP
%token LE_OP
%token GE_OP
%token EQ_OP
%token NE_OP
%token CONCAT
%token SEMICOLON
%token O_CURLY
%token C_CURLY
%token COMMA
%token COLON
%token EQUALS
%token O_BRACKETS
%token C_BRACKETS
%token O_SQUARE
%token C_SQUARE
%token NOT_OP
%token ADD_OP
%token SUB_OP
%token MUL_OP
%token DIV_OP
%token MOD_OP
%token LT_OP
%token GT_OP
%token ID
%token NUMERO
%token STRING

%type <value.identifier>ID
%type <value.number>NUMERO
%type <value.number>IF
%type <value.number>WHILE
%type <value.number>Variavel
%type <value.string>STRING
%type <value>ExpressaoRelacional
%type <value>Expressao
%type <value>Termo
%type <value>Factor

%expect 43

%start Programa

%%
Programa
    : PROGRAM ID {
        p = initializeProgram(CATEGORY_PROGRAM, $2);
        changeAssociation($2);
    } O_CURLY ConteudoPrograma C_CURLY {
        printf("stop\n");
    } ;

ConteudoPrograma
    : DeclaracaoVariaveis {
        printf("jump inic\n");
    } DefinicaoFuncoes {
        printf("inic: start\n");
    } BlocoInstrucoes
    ;

DeclaracaoVariaveis
    : DeclaracaoVariaveis Declaracao
    | /* Epsilon */
    ;

Declaracao
    : VARIABLE ListaVariaveis COLON INTEGER SEMICOLON
    ;

ListaVariaveis
    : ListaVariaveis COMMA NomeVariavel
    | NomeVariavel
    ;

NomeVariavel
    : ID {
        createVariable(TYPE_INTEGER, $1, 0, 0);
        printf("\tpushi 0\n");
        nextAddress += SIZE;
    } | ID O_SQUARE NUMERO C_SQUARE {
        createVariable(TYPE_ARRAY, $1, $3, 0);
        printf("\tpushn %d\n", SIZE * $3);
        nextAddress += SIZE * $3;
    } | ID O_SQUARE NUMERO C_SQUARE O_SQUARE NUMERO C_SQUARE {
        createVariable(TYPE_MATRIX, $1, $3, $6);
        printf("\tpushn %d\n", SIZE * $3 * $6);
        nextAddress += SIZE * $3 * $6;
    } ;

DefinicaoFuncoes
    : DefinicaoFuncoes Funcao
    | /* Epsilon */
    ;

Funcao
    : FUNCTION ID O_BRACKETS C_BRACKETS COLON EMPTY {
        createFunction($2, RETURN_EMPTY);
        changeAssociation($2);
        printf("%s: nop\n", $2);
    } O_CURLY CorpoFuncao RETURN SEMICOLON C_CURLY {
        printf("endFunction%s: return\n", $2);
        changeAssociation(p->identifier);
    } | FUNCTION ID O_BRACKETS C_BRACKETS COLON INTEGER {
        createFunction($2, RETURN_INTEGER);
        changeAssociation($2);
        printf("%s: nop\n", $2);
    } O_CURLY CorpoFuncao RETURN { boolSimples = 1; } Expressao SEMICOLON C_CURLY {
        printf("\tswap\n");
        printf("endFunction%s: return\n", $2);
        changeAssociation(p->identifier);
        boolSimples = 0;
    } ;

CorpoFuncao
    : BlocoInstrucoes
    ;

BlocoInstrucoes
    : INSTRUCTIONS O_CURLY ListaInstrucoes C_CURLY
    | /* Epsilon */
    ;

ListaInstrucoes
    : ListaInstrucoes Instrucao
    | Instrucao
    ;

Instrucao
    : Atribuicao
    | Leitura
    | Escrita
    | Condicional
    | Ciclica
    | Invocacao
    ;

Atribuicao
    : Variavel EQUALS {
        boolSimples = 1;
        boolEstruturada = 1;
    } Expressao SEMICOLON {
        if ($4.type != TYPE_INTEGER) {
            yyerror("Incompatible Type!");
        } else {
            if ($1 != -1) printf("\tstoreg %d\n", $1);
            else printf("\tstoren\n");
            boolSimples = 0;
            boolEstruturada = 0;
        }
    } ;

Leitura
    : READ O_BRACKETS Variavel C_BRACKETS SEMICOLON {
        printf("\tread\n\tatoi\n");
        if ($3 != -1) printf("\tstoreg %d\n", $3);
        else printf("\tstoren\n");
    } ;

Escrita
    : WRITE O_BRACKETS {
        boolSimples = 1;
        boolEstruturada = 1;
    } ListaParametros {
        boolSimples = 0;
        boolEstruturada = 0;
    } C_BRACKETS SEMICOLON
    ;

ListaParametros
    : ListaParametros CONCAT Parametro
    | Parametro
    ;

Parametro
    : Expressao {
        if ($1.type != TYPE_INTEGER)
            yyerror("Incompatible Type!");
        else printf("\twritei\n");
    } | STRING {
        printf("\tpushs %s\n\twrites\n", $1);
    } ;

Condicional
    : IF {
        boolSimples = 1;
        boolEstruturada = 1;
        $1 = ++conditionNumber;
        printf("ifCondition%d:\n", $1);
    } O_BRACKETS ExpressaoRelacional {
        if ($4.type != TYPE_BOOL)
            yyerror("Incompatible Type!");
    } C_BRACKETS {
        boolSimples = 0;
        boolEstruturada = 0;
        printf("\tjz ifOpposite%d\n", $1);
    } O_CURLY ListaInstrucoes C_CURLY {
        printf("\tjump ifEnd%d\n", $1);
        printf("ifOpposite%d:\n", $1);
    } Contrario {
        printf("ifEnd%d:\n", $1);
    } ;

Contrario
    : ELSE O_CURLY ListaInstrucoes C_CURLY
    | /* Epsilon */
    ;

Ciclica
    : WHILE {
        boolSimples = 1;
        boolEstruturada = 1;
        $1 = ++conditionNumber;
        printf("whileCondition%d:\n", $1);
    } O_BRACKETS ExpressaoRelacional {
        if ($4.type != TYPE_BOOL)
            yyerror("Incompatible Type!");
    } C_BRACKETS {
        boolSimples = 0;
        boolEstruturada = 0;
        printf("\tjz whileEnd%d\n", $1);
        printf("whileLoop%d:\n", $1);
    } O_CURLY ListaInstrucoes C_CURLY {
        printf("\tjump whileCondition%d\n", $1);
        printf("whileEnd%d:\n", $1);
    } ;

Invocacao
    : ID O_BRACKETS C_BRACKETS SEMICOLON {
        if (verifyFunction($1) == FALSE) {
            yyerror("Function Doesn't Exists!");
        } else if (getFunctionResult($1) != RETURN_EMPTY) {
            yyerror("Incompatible Type!");
        } else {
            printf("\tpusha %s\n\tcall\n\treturnFunction%d: nop\n", $1, conditionNumber);
        }
    } ;

ExpressaoRelacional
    : Expressao EQ_OP Expressao {
        if (($1.type != TYPE_INTEGER) || ($3.type != TYPE_INTEGER)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_BOOL;
            printf("\tequal\n");
        }
    } | Expressao NE_OP Expressao {
        if (($1.type != TYPE_INTEGER) || ($3.type != TYPE_INTEGER)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_BOOL;
            printf("\tequal\n\tpushi 0\n\tequal\n");
        }
    } | Expressao LT_OP Expressao {
        if (($1.type != TYPE_INTEGER) || ($3.type != TYPE_INTEGER)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_BOOL;
            printf("\tinf\n");
        }
    } | Expressao LE_OP Expressao {
        if (($1.type != TYPE_INTEGER) || ($3.type != TYPE_INTEGER)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_BOOL;
            printf("\tinfeq\n");
        }
    } | Expressao GT_OP Expressao {
        if (($1.type != TYPE_INTEGER) || ($3.type != TYPE_INTEGER)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_BOOL;
            printf("\tsup\n");
        }
    } | Expressao GE_OP Expressao {
        if (($1.type != TYPE_INTEGER) || ($3.type != TYPE_INTEGER)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_BOOL;
            printf("\tsupeq\n");
        }
    } | Expressao {
        $$.type = $1.type;
    } ;

Expressao
    : Expressao ADD_OP Termo { 
        if (($1.type != TYPE_INTEGER) || ($3.type != TYPE_INTEGER)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_INTEGER;
            printf("\tadd\n");
        }
    } | Expressao SUB_OP Termo {
        if (($1.type != TYPE_INTEGER) || ($3.type != TYPE_INTEGER)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_INTEGER;
            printf("\tsub\n");
        }
    } | Expressao OR_OP Termo {
        if (($1.type != TYPE_BOOL) || ($3.type != TYPE_BOOL)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_BOOL;
            printf("\tadd\n\tpushi 0\n\tsup\n");
        }
    } | Termo {
        $$.type = $1.type;
    } ;

Termo
    : Termo MUL_OP Factor {
        if (($1.type != TYPE_INTEGER) || ($3.type != TYPE_INTEGER)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_INTEGER;
            printf("\tmul\n");
        }
    } | Termo DIV_OP Factor {
        if (($1.type != TYPE_INTEGER) || ($3.type != TYPE_INTEGER)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_INTEGER;
            printf("\tdiv\n");
        }
    } | Termo MOD_OP Factor {
        if (($1.type != TYPE_INTEGER) || ($3.type != TYPE_INTEGER)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_INTEGER;
            printf("\tmod\n");
        }
    } | Termo AND_OP Factor {
        if (($1.type != TYPE_BOOL) || ($3.type != TYPE_BOOL)) {
            yyerror("Incompatible Type!");
        } else {
            $$.type = TYPE_BOOL;
            printf("\tadd\n\tpushi 2\n\tequal\n");
        }
    } | Factor {
        $$.type = $1.type;
    } ;

Factor
    : O_BRACKETS ExpressaoRelacional C_BRACKETS {
        $$.type = $2.type;
    } | NOT_OP ExpressaoRelacional {
        if ($2.type != TYPE_BOOL) {
            yyerror("Incompatible Type!");
        } else {
            printf("\tpushi 0\n\tequal\n");
            $$.type = TYPE_BOOL;
        }
    } | ID O_BRACKETS C_BRACKETS {
        if (verifyFunction($1) == FALSE) {
            yyerror("Function Doesn't Exists!");
        } else if (getFunctionResult($1) != RETURN_INTEGER) {
            yyerror("Incompatible Type!");
        } else {
            printf("\tpushi 0\n\tpusha %s\n\tcall\nreturnFunction%d: nop\n", $1, conditionNumber);
            $$.type = TYPE_INTEGER;
        }
    } | NUMERO {
        printf("\tpushi %d\n", $1);
        $$.type = TYPE_INTEGER;
    } | SUB_OP NUMERO {
        printf("\tpushi 0\n\tpushi %d\n\tsub\n", $2);
        $$.type = TYPE_INTEGER;
    } | Variavel {
        $$.type = TYPE_INTEGER;
    } ;

Variavel
    : ID {
        if (getVariableType($1) != TYPE_INTEGER) {
            yyerror("Incompatible Type!");
        } else {
            $$ = getVariableAddress($1);
            if (boolSimples != 0)
                printf("\tpushg %d\n", $$);
        }
    } | ID O_SQUARE {
        if ((getVariableType($1) != TYPE_ARRAY) && (getVariableType($1) != TYPE_MATRIX)) {
            yyerror("Incompatible Type!");
        } else {
            printf("\tpushgp\n\tpushi %d\n\tpadd\n", getVariableAddress($1));
            boolSimples = 1;
        }
    } Expressao C_SQUARE {
        if ($4.type != TYPE_INTEGER) {
            yyerror("Invalid Index!");
        } else {
            if (getVariableType($1) == TYPE_MATRIX)
                printf("\tpushi %d\n\tmul\n", getVariableDimension(2, $1));
        }
    } SegundaDimensao {
        boolSimples = 0;
        $$ = -1;
    } ;

SegundaDimensao
    : O_SQUARE Expressao C_SQUARE {
        if ($2.type != TYPE_INTEGER) {
            yyerror("Invalid Index!");
        } else {
            printf("\tadd\n");
            if (boolEstruturada != 0)
                printf("\tloadn\n");
        }
    } | /* Epsilon */ {
        if (boolEstruturada != 0)
            printf("\tloadn\n");
    } ;

%%
void changeAssociation(char *association)
{
    if (currentAssociation != NULL)
        free(currentAssociation);

    currentAssociation = strdup(association);
}

void createVariable(int type, char *identifier, int dimension1, int dimension2)
{
    Variable v = initializeVariable(CATEGORY_VARIABLE, currentLevel, currentAssociation, 
        type, identifier, nextAddress, SIZE, dimension1, dimension2);

    if (v == NULL)
        yyerror("Couldn't Create Variable!");

    if (g_hash_table_contains(variablesHash, identifier) != 0)
        yyerror("Variable Already Exists!");

    g_hash_table_insert(variablesHash, v->identifier, v);
}

int getVariableAddress(char *identifier)
{
    Variable v = (Variable) g_hash_table_lookup(variablesHash, identifier);

    if (v == NULL)
        yyerror("Couldn't Find Variable!");

    return v->address;
}

int getVariableType(char *identifier)
{
    Variable v = (Variable) g_hash_table_lookup(variablesHash, identifier);

    if (v == NULL)
        yyerror("Couldn't Find Variable!");

    return v->type;
}

int getVariableDimension(int numberDimension, char *identifier)
{
    Variable v = (Variable) g_hash_table_lookup(variablesHash, identifier);

    if (v == NULL)
        yyerror("Couldn't Find Variable!");

    if (numberDimension == 1)
        return v->dimension1;

    return v->dimension2;
}

void createFunction(char *identifier, int result)
{
    Function f = initializeFunction(CATEGORY_FUNCTION, identifier, result);

    if (f == NULL)
        yyerror("Couldn't Create Variable!");

    if (g_hash_table_contains(functionsHash, identifier) != 0)
        yyerror("Function Already Defined!");

    g_hash_table_insert(functionsHash, f->identifier, f);
}

int getFunctionResult(char *identifier)
{
    Function f = (Function) g_hash_table_lookup(functionsHash, identifier);

    if (f == NULL)
        yyerror("Couldn't Find Function!");

    return f->result;
}

gboolean verifyFunction(char *identifier)
{
    return g_hash_table_contains (functionsHash,identifier);
}

int yyerror(char *s)
{
    printf("ERROR: %s { Line %d, %s }\n", s, yylineno, yytext);
    fprintf(stderr, "ERROR: %s { Line %d, %s }\n", s, yylineno, yytext);
    
    return 1;
}

int main(int argc, char **argv)
{
    variablesHash = g_hash_table_new(g_str_hash, g_str_equal);
    functionsHash = g_hash_table_new(g_str_hash, g_str_equal);

    yyparse();

    p = deleteProgram(p);
    g_hash_table_destroy(variablesHash);
    g_hash_table_destroy(functionsHash);
    
    return 0;
}
