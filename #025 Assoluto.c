/*
 * Jacopo Del Granchio
 * #000 GG.MM.YYYY
 *
 * Lorem ipsum dolor sit amet, consectetur adipisicing elit,
 * sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
 */

#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <locale.h>

int main() {
  setlocale(LC_ALL, "");

  int n;

  printf("Dammi un numero: ");
  scanf("%d", &n);

  printf("Il valore assoluto � %d\n", abs(n));

  // getchar();
  // system("pause");
  return 0;
}