#include <stdio.h>
#include <stdlib.h>


int checkliv(int** gen, int b, int h, int i, int j);
int fate(int cell, int livNeigh);

int** nextgen(int** gen, int m, int n)
{
	int** newGen = malloc(m * sizeof(**newGen)); // nuova generazione
	for (int i = 0; i < m; i += 1) { newGen[i] = malloc(n * sizeof(*newGen[i])); }
	int livNeigh; // numero di caselle vive adiacenti ad ogni casella
	for (int i = 0; i < m; i += 1)
	{
		for (int j = 0; j < n; j += 1)
		{
			livNeigh = checkliv(gen, m, n, i, j); //controllo caselle adiacenti
			newGen[i][j] = fate(gen[i][j], livNeigh); //decido il destino della cella
		}
	}
	return newGen;
}

int fate(int cell, int livNeigh)
{
	if (cell)
	{
		return livNeigh == 2 || livNeigh == 3;
	}
	return livNeigh == 3;
}

int checkliv(int** gen, int m, int n, int i, int j)
{
	int count = 0;
	for (int k = i - 1; k <= i + 1; k += 1)
	{
		for (int d = j - 1; d <= j + 1; d += 1)
		{
			if ((k != i || d != j) && k >= 0 && d >= 0 && k < m && d < n && gen[k][d])
			{
				count += 1;
			}
		}
	}
	return count;
}


void printuniverse(int** tab, int m, int n)
{ //stampa universo
	for (int i = 0; i < m; i += 1) {
		for (int j = 0; j < n; j += 1) {
			printf(tab[i][j] ? "0" : " ");
		}
		printf("\n");
	}
}


void randmat(int** a, int m, int n)
{
	srand(time(NULL));
	for (int i = 0; i < m; i += 1)
	{
		for (int j = 0; j < n; j += 1)
		{
			a[i][j] = rand() % 101 < 10;
		}
	}

}
