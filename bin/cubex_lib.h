typedef struct iter {
<<<<<<< HEAD
	void* value = NULL;
=======
	void** value = NULL;
>>>>>>> 6b1009a2b7d6cea0a18a8bfd4273ae0bbfcf41d9
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