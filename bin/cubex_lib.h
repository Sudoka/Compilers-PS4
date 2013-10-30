typedef struct iter {
	void** value;
	int size;
	int nrefs;
} Iterable;

typedef struct integer {
	int nrefs;
	int value;
} Integer;

typedef struct string {
	int nrefs;
	char* value;
	int size;
} String;

typedef struct boolean {
	int nrefs;
	int value;
} Boolean;

typedef struct character {
	int nrefs;
	char value;
} Character;