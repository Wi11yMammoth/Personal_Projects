#include<stdio.h>
#include<string.h>
#include<stdlib.h>

/*
	Each element in a list is a person, with an attribute "name"
	And an emergency contact, who can be anyone else on the list

	"next" is standard linked list field, to connect each element to the next one
*/

struct list_node
{
	char *name;
	struct list_node *contact;
	struct list_node *next;	
};


/*
	This function creates a linked list
*/
struct list_node *create_list()
{
	struct list_node *nodes[10];
	int i;

	//Allocates memory for all nodes
	for(i=0; i<10; i++)
	{
		nodes[i] = (struct list_node *)malloc(sizeof(struct list_node));
	}

	//links all the "next" fields
	for(i=0; i<9; i++)
	{
		nodes[i]->next = nodes[i+1];
	}
	nodes[9]->next = NULL;

	nodes[0]->name = (char *)malloc(strlen("John")+1);
	strcpy(nodes[0]->name,"John");
	nodes[0]->contact = nodes[1];

	nodes[1]->name = (char *)malloc(strlen("Mary")+1);
	strcpy(nodes[1]->name,"Mary");
	nodes[1]->contact = nodes[9];

	nodes[2]->name = (char *)malloc(strlen("Mohamed")+1);
	strcpy(nodes[2]->name,"Mohamed");
	nodes[2]->contact = nodes[0];

	nodes[3]->name = (char *)malloc(strlen("Liza")+1);
	strcpy(nodes[3]->name,"Liza");
	nodes[3]->contact = nodes[4];

	nodes[4]->name = (char *)malloc(strlen("Osenya")+1);
	strcpy(nodes[4]->name,"Osenya");
	nodes[4]->contact = nodes[4];

	nodes[5]->name = (char *)malloc(strlen("Peter")+1);
	strcpy(nodes[5]->name,"Peter");
	nodes[5]->contact = nodes[7];

	nodes[6]->name = (char *)malloc(strlen("Mahala")+1);
	strcpy(nodes[6]->name,"Mahala");
	nodes[6]->contact = nodes[2];

	nodes[7]->name = (char *)malloc(strlen("Rita")+1);
	strcpy(nodes[7]->name,"Rita");
	nodes[7]->contact = nodes[4];

	nodes[8]->name = (char *)malloc(strlen("Jacques")+1);
	strcpy(nodes[8]->name,"Jacques");
	nodes[8]->contact = nodes[8];

	nodes[9]->name = (char *)malloc(strlen("Paul")+1);
	strcpy(nodes[9]->name,"Paul");
	nodes[9]->contact = nodes[1];


	return nodes[0];
}

/*
	Prints the list and connections
*/
void print_list(struct list_node *l)
{
	int i=0;
	while(l != NULL)
	{
		printf("Entry %d is %s, contact is %s\n",i,l->name,l->contact->name);
		i++;
		l = l->next;
	}
	printf("\n");
}


/*
	Creates a copy of a linked list
*/
struct list_node *copy_list(struct list_node *original)
{
    struct list_node *copy; // the head of the copy list
    
    struct list_node *node; // a node in the copy list
    struct list_node *tmp; // a temp node 
    struct list_node *curr_node; // current node for original list
    struct list_node *place_holder; // a place holder for a node in the list
    int i;
    int j; 
    
    curr_node = original; 
    
    // Find out how many nodes are in the list
    i = 0;
    while(curr_node != NULL){
        i = i + 1; 
        curr_node = curr_node -> next;
    }
    
    // i now holds the number of elements in the list
    
    if (i>0){
        // Create an empty linked list to hold the copy
        copy = (struct list_node *)malloc(sizeof(struct list_node));
        node = copy;
        
        
        for (j=1; j<i; j++){
            node -> next = (struct list_node *)malloc(sizeof(struct list_node));
            node = node -> next;
        }
        node -> next = NULL;
        
        
        // copy names of original list to the copy list
        node = copy; 
        curr_node = original;
        while(curr_node != NULL){
            node->name = (char*)malloc(sizeof(strlen(curr_node->name)+1));
            strcpy(node->name,curr_node->name);
            
            node = node -> next;
            curr_node = curr_node -> next;
        }
        
        
        // assign contacts
        
        curr_node = original;
        place_holder = copy;
        
        while(curr_node != NULL){
            tmp = curr_node->contact; 
        
            node = original; 
            i = 0;
            while(node != tmp){
                i = i + 1;
                node = node->next;
            }
            
            node = copy;
            for (j=0; j<i; j++){
                node = node->next;
            }
            
            place_holder -> contact = node;
            place_holder = place_holder -> next;
            curr_node = curr_node->next;
            
        } 
        
        
        
        
    }else{
        copy = NULL;
    }
    
	return copy;
}


/*
	Deletes a list
*/
struct list_node *delete_list(struct list_node *l)
{
	struct list_node *tmp;
	while(l != NULL)
	{
		tmp = l->next;

		free(l->name);
		l->name = NULL;
		l->contact = NULL;
		l->next = NULL;

		free(l);

		l=tmp; 
	}
	return NULL;
}

int main()
{
	struct list_node *original = NULL;
	struct list_node *copy = NULL;

	//create linked list
	original = create_list();

	//print linked list
	printf("Original:\n");
	print_list(original);

	//copy linked list
	copy = copy_list(original);

	//delete original linked list
	original = delete_list(original);

	//print copy
	printf("Copy:\n");
	//should print exactly the same thing as the original
	print_list(copy);
}
