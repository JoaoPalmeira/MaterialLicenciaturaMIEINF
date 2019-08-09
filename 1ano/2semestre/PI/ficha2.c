#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_SIZE 1024

//2 - ...

//ex1
void insere (int v[], int n, int x){
	int j = n-1;
	while (j>=0 && v[j]>x){
		v[j+1] = v[j];
		j--;
	}
	v[j+1] = x;
}

//ex2
void  iSort1(int v[], int n){
	int i, j, x;
	for (i =1; (i<n) ; i++){
		x=v[i];
		j = i-1;
		while(j>=0 && v[j]>x){
			v[j+1] = v[j];
			j--;
		}
		v[j+1] = x;
	}
}

//ex3
int maxInd (int v[], int n) {
	int i=0, r;
	for(i=1; i<n; i++){
		if(v[i]>v[r]) r=i;
	}
	return r;
}

//4
int  ordenaInts (int v[], int n){
	int i, t, max;
	for(i=n-1;i>0;i--){
		max = maxInd(v, i+1);
		t = v[i];
		v[i] = v[max];
		v[max] = t;
	}
}

//ex5
int ordenaInts2 (int v[], int n){
	int i, j=0, t, max;
	for(i=n-1;i>0;i--){
		max=0;
		for(j=1; j<i+1; j++){
			if(v[j]>v[max]) max=j;
		}
		t = v[i];
		v[i] = v[max];
		v[max] = t;
	}
}
