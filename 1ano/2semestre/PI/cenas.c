#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct aluno {
    char nome[60];
    int num;
    int nota;
    struct aluno *esq, *dir;
} Aluno;

typedef Aluno* Turma;

void imprime_notas(FILE* f, Turma t){
	if (t!=NULL){
		imprime_notas (f, t-> esq);

		fprintf(f, "%d %s", t -> num, t -> nome);
		if(t -> nota >= 10 && t -> nota <= 20)
			fprintf(f, "%d\n", t -> nota );
		else if(t -> nota >= 0 && t -> nota < 10)
			fprintf(f, "R\n");
		else fprintf(f, "F\n");


		imprime_notas(f, t -> dir);
	}

}

void apaga_turma (Turma t){
	if(t) {
		apaga_turma(t->esq);
		apaga_turma(t->dir);
		free(t);
	}
}

void fdump(FILE* f, Turma t){
	if(t!=NULL){
		fdump (f, t-> esq);

		fwrite(&(t->num), sizeof(int), 1, f);
		fwrite(&(t->nome), sizeof(char), 60, f);
		fwrite(&(t->nota), sizeof(int), 1, f);

		fdump (f, t -> dir);
	}
}

void imprime_notas_fich (FILE* fo, FILE* ft, int nalunos){
	int num, nota, i;
	char nome[60];

	fseek(ft, 0, SEEK_SET);

	for(i=0, i<nalunos, i++){
		fread(&num, sizeof(int), 1, ft);
		fread(nome, sizeof(char), 60, ft);
		fread(&nota, sizeof(int), 1, ft);

		fprintf(fo, "%d %s", num, nome);
		if(nota >= 10 && nota <= 20)
			fprintf(fo, "%d\n", nota);
		else if(nota >= 0 && nota <10)
			fprintf(fo, "R\n");
		else fprintf(fo, "F\n");
	}
}








