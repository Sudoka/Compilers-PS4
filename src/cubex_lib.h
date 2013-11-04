#include "cubex_external_functions.h"
#define NULL ((void*)0)

typedef struct top {
	int value;
} Top;

typedef struct integer {
	int nrefs;
	int value;	
} Integer;

typedef struct string {
	int nrefs;
	char* value;
	int len;
} String;

typedef struct boolean {
	int nrefs;
	int value;
} Boolean;

typedef struct character {
	int nrefs;
	char value;
} Character;

typedef struct iter{
	int nref;
	void* value;
	void* additional;
	struct iter* (*next)(void*);
	struct iter* concat;
}Iterable;


Iterable* iterGetNext(Iterable* last){
	Iterable* this;
	this = x3malloc(sizeof(Iterable));
	if (last->next!= NULL){	
		this = (last->next)(last);
	}
	else {
		this = (Iterable*) last->additional;
	}
	
	if (this==NULL && last->concat==NULL){
		return NULL;
	}
	else if (this==NULL){
		this=last->concat;
	}
	
	if (last->nref==1)
		x3free(last);
	else 
		(last->nref)--;
	
	return (this);
}

void concatenate(Iterable* fst, Iterable* snd){
	if (fst == NULL) {
		fst = snd;
		return;
	}
	while(fst->concat!=NULL) {
		fst=fst->concat;
	}
	fst->concat=snd;
}

Iterable* integer_onwards(void* head){
	Iterable* this;
	Iterable* last;
	last = (Iterable*) head;
	this = x3malloc(sizeof(Iterable));
	this->nref=1; 
	(((Integer*)(last->value))->value)++;
	this->value = last->value;
	(((Integer*)(last->value))->nrefs)++;	
	this->additional = last->additional;
	this->next = last->next;	
	this->concat = last->concat;

	return this;
}

Iterable* integer_through(void* head){
	Iterable* last;
	last = (Iterable*) head;
	if ((((Integer*) last->value)->value) == (((Integer*) last->additional)->value)){
		return NULL;
	}
	else {
		Iterable* this=x3malloc(sizeof(Iterable));
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

Iterable* input_onwards(void* head){
	int len;
	len = next_line_len();
	Iterable* last;
	Iterable* this;
	this = NULL;
	last = (Iterable*) head;
	if (len != 0) {
		this = x3malloc(sizeof(Iterable));
		this->nref=1; 
		
		last->value = x3malloc(sizeof(String));
		((String*) last->value)->value = (char*) x3malloc(len* sizeof(char));
		read_line(((String*) last->value)->value);
		((String*) last->value)->nrefs = 1;
		this->additional=NULL;
		this->next=last->next;	
		this->concat=last->concat;
		last->additional = this;		
		last->next = NULL;
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

char* concatChars(Iterable *charIter){
	char* combined;
	int count=0;
	while (charIter!=NULL){
		const char* prev=(const char* )combined;
		combined = x3malloc(count+1); 
		mystrcpy(combined,prev);
		free((char*)prev);
		const char* newChar=charIter->value;
		count++;
		combined[count]=newChar;
		Iterable* temp=iterGetNext(charIter);
		charIter=temp; 
	}
	const char* prev=(const char*)combined;
	combined = x3malloc(count+1); 
	mystrcpy(combined,prev);
	combined[count]='\0';
}
