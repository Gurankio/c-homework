/*
 * Jacopo Del Granchio
 * #056 04.12.2019
 *
 * Inverte i valori di un vettore.
 */

#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <locale.h>
#include <stdarg.h>
#include <stdbool.h>

// Macro
#define chiedi(msg, format, ...) \
  printf(msg); \
  scanf(format, __VA_ARGS__);

// Prototipi
void carica(int v[], int n);
void scarica(int v[], int n);
void inverti(int v[], int n);


// Funzioni
int main() {
  setlocale(LC_ALL, "");

  int n;
  chiedi("Inserire la lunghezza del vettore: ", "%d", &n);

  int v[n];
  carica(v, n);
  inverti(v, n);
  scarica(v, n);
  // getchar();
  // system("pause");
  return 0;
}

void carica(int v[], int n) {
  for (int i = 0; i < n; i++) {
    printf("Inserisci il %d elemento del vettore: ", i);
    scanf("%d", &v[i]);
  }
}

void scarica(int v[], int n) {
  printf("[ ");

  for (int i = 0; i < n; i++)
    printf("%d%s", v[i], i != n - 1 ? ", " : "");

  printf(" ]\n");
}

void inverti(int v[], int n) {
  int t;

  for (int i = 0; i < (n / 2); i++) {
    t = v[i];
    v[i] = v[n - 1 - i];
    v[n - 1 - i] = t;
  }
}
