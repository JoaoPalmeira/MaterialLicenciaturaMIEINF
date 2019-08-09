#include <stdio.h>
#include "btree.h"


int main(void)
{
	BTree b = NULL;

	printf("Size: %d\n", Size(b));
	printf("Height: %d\n", Height(b));

	b = Add(b, 20);
	b = Add(b, 10);
	b = Add(b, 25);
	b = Add(b, 21);
	b = Add(b, 26);

	printf("Size: %d\n", Size(b));
	printf("Height: %d\n", Height(b));
	printf("Search 4: %d\n", Search(b, 4));
	printf("Search 10: %d\n", Search(b, 10));
	printf("Max: %d\n", Max(b));
	printf("Is balanced: %d", IsBalanced(b));

	Destroy(b);

	getchar();
	return 0;
}