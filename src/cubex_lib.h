#define NULL ((void*)0)

typedef struct top {} Top;

typedef struct integer {
	int value;
	int nrefs;
} Integer;

typedef struct string {
	char* value;
	int nrefs;
	int len;
} String;

typedef struct boolean {
	int value;
	int nrefs;
} Boolean;

typedef struct character {
	char value;
	int nrefs;
} Character;

//typedef enum {eBoolean, eInteger, eCharacter, eMixed} type_t;

typedef struct iter{
	void* value;
	int nref;
	void* additional;
	void* (*next)(void*);
	struct iter* concat;
}Iterable;


Iterable* iterGetNext(Iterable* last){
	Iterable* this=malloc(sizeof(Iterable));
	if (last->next!= NULL){	
		//onwards&thru
		this = (last->next)(last);
	}
	else {
		//enumerable
		this = (Iterable*) last->additional;
	}
	//end of first Iterable
	
	if (this==NULL && last->concat==NULL){
		return NULL;
	}
	else if (this==NULL){
		//concatenation
		this=last->concat;
	}
	
	if (last->nref==1)
		x3free(last);
	else 
		(last->nref)--;
	
	return (this);
}

void concatenate(Iterable* fst, Iterable* snd){
	while(fst->concat!=NULL){
		fst=fst->concat;
	}
	fst->concat=snd;
}

Iterable* integer_onwards(Iterable* last){
	Iterable* this=malloc(sizeof(Iterable));
	this->nref=1; 
	(((Integer*)(last->value))->value)++;
	this->value = last->value;
	(((Integer*)(last->value))->nrefs)++;	
	this->additional = last->additional;
	this->next = last->next;	
	this->concat = last->concat;

	return this;
}

Iterable* integer_through(Iterable* last){
	if ((((Integer*) last->value)->value) == (((Integer*) last->additional)->value)){
		return NULL;
	}
	else {
		Iterable* this=malloc(sizeof(Iterable));
		this->nref=1;
		(((Integer*)(last->value))->value)++; 
		this->value = last->value; 
		(((Integer*)(last->value))->nrefs)++;
		this->additional = last->additional;
		this->next = last->next;	
		this->concat = last->concat;
		return this;
	}
}

Iterable* input_onwards(Iterable* last){
	int len = next_line_length();
	Iterable* this = NULL;
	if (len != 0) {
		this = malloc(sizeof(Iterable));
		this->nref=1; 
		((String*) last->value)->nrefs--;
		if (((String*) last->value)->nrefs == 1)
			free((String*) last->value);
		last->value = malloc(sizeof(String));
		((String*) last->value)->value = (char*) malloc(len* sizeof(char));
		read_line(((String*) last->value)->value);
		((String*) last->value)->nrefs = 0;
		this->additional=last->additional;
		this->next=last->next;	
		this->concat=last->concat;
	}

	return this;
}


int mystrcmp(const char *s1, const char *s2) 
{ 
    while (*s1==*s2) 
    { 
        if(*s1=='\0') 
           return(0); 
        s1++; 
        s2++; 
    } 
    return(*s1-*s2); 
}

void mystrcpy(char *dst, const char *src) {
   while (*src != '\0') {
      *dst++ = *src++; 
   }
   *dst = '\0';
}

typedef void (*generic_fp)(void);