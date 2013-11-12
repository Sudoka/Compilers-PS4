#include "cubex_external_functions.h"
#include "cubex_main.h"
#include "cubex_private.h"

extern int nmalloc = 0;
extern int nfree = 0;

int main(int argc, char** argv) {
  initialize(argc, argv);
  cubex_main();
  printf("malloc is called %d times, free is called %d times\n", nmalloc, nfree);
  return 0;
}
