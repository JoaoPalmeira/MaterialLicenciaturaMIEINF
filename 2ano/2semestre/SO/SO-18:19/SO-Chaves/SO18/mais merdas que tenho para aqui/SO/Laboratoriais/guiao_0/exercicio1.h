typedef struct intv{

	int inf;
	int sup;
	struct intv* next;


}* IntV;

int reserva(struct intv** livres, int n);
void liberta(struct intv** livres, int lugar, int n);

// IntV reserva(IntV livres,int n,int* reservado);
// IntV liberta(IntV livres,int lugar,int n);