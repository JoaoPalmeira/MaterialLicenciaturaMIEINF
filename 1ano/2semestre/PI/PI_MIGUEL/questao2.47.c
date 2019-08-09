

int maiorAB (ABin a){
    int i;
    ABin b =a;
    if(a==NULL) return -1;
    while((a->dir)!=NULL){
        a=(a->dir);
    }
    i=(a->valor);
    a=b;
    return i;
}