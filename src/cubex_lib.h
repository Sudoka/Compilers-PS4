typedef struct iter {
	void** value = NULL;
	int size;
} Iterable;

typedef struct int {
	int value;
} Integer;

typedef struct string {
	void* value = NULL;
	int size;
} String;

typedef struct boolean {
	int value;
} Boolean;