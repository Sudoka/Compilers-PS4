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
	int nrefs;
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
	  	if (last->nrefs==1) {
		      x3free(last);
		 }
		 else 
		     (last->nrefs)--;
		
		return NULL;
	}
	else if (this==NULL){
		this=last->concat;
	}
	
	if (last->nrefs==1) {
		x3free(last);
	}
	else 
		(last->nrefs)--;
	
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

Iterable* Integer_onwards(void* head){
	Iterable* this;
	Iterable* last;
	last = (Iterable*) head;
	this = x3malloc(sizeof(Iterable));
	this->nrefs=1; 
	(((Integer*)(last->value))->value)++;
	this->value = last->value;
	(((Integer*)(last->value))->nrefs)++;	
	this->additional = last->additional;
	this->next = last->next;	
	this->concat = last->concat;

	return this;
}

Iterable* Integer_through(void* head){
	Iterable* last;
	last = (Iterable*) head;
	if ((((Integer*) last->value)->value) == (((Integer*) last->additional)->value)){
		return NULL;
	}
	else {
		Iterable* this=x3malloc(sizeof(Iterable));
		this->nrefs=1;
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
		this->nrefs=1; 
		this->value = x3malloc(sizeof(String));
		((String*) this->value)->value = (char*) x3malloc(len* sizeof(char));
		read_line(((String*) this->value)->value);
		((String*) this->value)->nrefs = 1;
		((String*) this->value)->len = len;
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

String* concatChars(Iterable *charIter){
	char* combined = NULL;
	int count=0;
	while (charIter!=NULL){
		const char* prev=(const char* )combined;
		combined = x3malloc((count+1)*sizeof(char)); 
		mystrcpy(combined,prev);
		x3free((char*)prev);
		char newChar=((Character*)charIter->value)->value;
		combined[count]=newChar;
		count++;
		Iterable* temp=iterGetNext(charIter);
		charIter=temp; 
	}
	const char* prev=(const char*)combined;
	combined = x3malloc((count+1)*sizeof(char)); 
	mystrcpy(combined,prev);
	x3free((char*)prev);
	combined[count]='\0';
	String* new = (String*) x3malloc(sizeof(String));
	new->value = (char*) x3malloc(sizeof(char)*count);
	mystrcpy(new->value, combined);
	x3free(combined);
	new->len = count;
	return new;
}
