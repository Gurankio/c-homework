/*
 * Jacopo Del Granchio
 * #076 18.12.2019
 *
 * Copia i pari e i dispari di un vettore in altri due vettori appositi.
 */

#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <locale.h>
#include <stdarg.h>
#include <stdbool.h>
#include <time.h>

// Macro
#define chiedi(msg, format, ...) \
  printf(msg); \
  scanf(format, __VA_ARGS__);

// Prototipi
void caricaRand(int v[], int n, int);
void scarica(int v[], int n);
void calcola(int v[], int n, int pari[], int *iP, int dispari[], int *iD);

// Funzioni
int main() {
  setlocale(LC_ALL, "en_US");

  int n;
  chiedi("Inserisci la lunghezza: ", "%d", &n);

  int v[n];
  caricaRand(v, n, n);
  scarica(v, n);

  int pari[n], iP = 0, dispari[n], iD = 0;
  calcola(v, n, pari, &iP, dispari, &iD);

  printf("Pari: ");
  scarica(pari, iP);

  printf("Dispari: ");
  scarica(dispari, iD);

  // getchar();
  // system("pause");
  return 0;
}

void calcola(int v[], int n, int pari[], int *iP, int dispari[], int *iD) {
  for (int i = 0; i < n; ++i) {
    if (v[i] % 2 == 0) pari[(*iP)++] = v[i];
    else dispari[(*iD)++] = v[i];
  }
}

void caricaRand(int v[], int n, int max) {
  srand((unsigned)time(NULL) + rand());

  for (int i = 0; i < n; i++)
    v[i] = rand() % max;
}

void scarica(int v[], int n) {
  printf("[ ");

  for (int i = 0; i < n; i++)
    printf("%d%s", v[i], i != n - 1 ? ", " : "");

  printf(" ]\n");
}
