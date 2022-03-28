/* SYSC 2006 Lab 8
   A module that implements a singly-linked list of integers. 
 */

#include <assert.h>  // assert
#include <stdlib.h>  // malloc, free
#include <stdbool.h>
#include <stdio.h>   // printf

#include "singly_linked_list.h"

/*************************************************************
 * Functions presented in lectures.
 *************************************************************/

/* Return a pointer to a new intnode_t (a node in a singly-linked list).

   Parameter value: the integer to be stored in the node.
   Parameter next: a pointer to the node that the new node should point to.
   This pointer should be NULL if the new node is the last node in the 
   linked list.
   Terminate (via assert) if memory for the node cannot be allocated.
 */ 
intnode_t *intnode_construct(int value, intnode_t *next)
{
    intnode_t *p = malloc(sizeof(intnode_t));
    assert (p != NULL);
    p->value = value;
    p->next = next;
    return p;
}
/* Parameter head points to the first node in a linked list, or is NULL
   if the linked list is empty. Insert a node containing the specified 
   integer value at the front of the linked list, and return a pointer to 
   the first node in the modified list.
 */
intnode_t *push(intnode_t *head, int value)
{
    return intnode_construct(value, head);
}

/* Parameter head points to the first node in the list.
   Return the length of a linked list (0 if the linked list is empty). 
 */
int length(intnode_t *head)
{
    int count = 0;
    for (intnode_t * curr = head; curr != NULL; curr = curr->next) {
        count += 1;
    }
    return count;
}

/* Parameter head points to the first node in a linked list.
   Print the linked list, using the format:
       value1 -> value2 -> value3 -> ... -> last_value
 */
void print_list(intnode_t *head)
{
    if (head == NULL) {
        printf("empty list");
        return; 
    }

    intnode_t *curr;

    /* The loop prints a every node in the linked list except the last one,
       using the format "value -> ", where "->" represents the link from each node
       to the following node.

       Notice that the loop condition is:
           curr->next != NULL
       and not:
           curr != NULL

       During the last iteration, the value in the second-last node is
       printed, then curr is updated to point to the last node. The
       condition curr->next != NULL now evaluates to false, so the 
       loop exits, with curr pointing to the last node (which has  
       not yet been printed.) 
     */

    for (curr = head; curr->next != NULL; curr = curr->next) {
        printf("%d -> ", curr->value);
    }

    /* Print the last node. */
    printf("%d", curr->value);
}

/***

Lab Exercises 

*/


// Exercise 1

/* Parameter head points to the first node in a linked list, or is
 * NULL if the list is empty.
 *
 * Count the number of nodes in the linked list that contain an integer
 * equal to target, and return that count.
 * 
 * Return 0 if the linked list is empty.
 */ 
int count(intnode_t *head, int target)
{
	int count;
	intnode_t *current;

	current = head;

	count = 0;
	while(current != NULL){
		if (current->value == target){
			count += 1;
		}

		current = current->next;
	}

	
    return count;
} 

// Exercise 2

/* Parameter head points to the first node in a linked list, or is
 * NULL if the list is empty.
 *
 * Return the largest integer in the linked list.
 *
 * The function terminates (via assert) if the linked list is empty.
 */
int max(intnode_t *head)
{
	int maxVal;
	intnode_t *current;

	assert(head!=NULL);
	
	current = head->next;
	
	maxVal = head->value;

	while(current != NULL){
		if (current->value > maxVal){
			maxVal = current->value;
		}

		current = current->next;
	}

    return maxVal;
}


// Exercise 3

/* Parameter head points to the first node in a linked list, or is
 * NULL if the list is empty.
 *
 * Return the index (position) of the first node that contains an 
 * integer equal to target. The first node is at index 0, the second node
 * is at index 1, etc.
 *
 * Return -1 if target is not in the linked list, or if the linked list
 * is empty.
 */
int index(intnode_t *head, int target)
{
	int node;
	intnode_t *currentNode;

	currentNode = head;

	node = 0;
	
	while(currentNode != NULL){
		if (currentNode -> value == target){
			return node;
		}
		node += 1;
		currentNode = currentNode -> next;
	}

    return -1;
}


// Exercise 4

/* Parameter head points to the first node in a linked list, or is
 * NULL if the list is empty.
 *
 * Parameter other points to the first node in a linked list, or is
 * NULL if the list is empty.
 *
 * Extend the linked list pointed to by head so that it contains
 * copies of the values stored in the linked list pointed to by other.
 *
 * The function terminates (via assert) if the the linked list 
 * pointed to by head is empty.
 */
void extend(intnode_t *head, intnode_t *other)
{
	intnode_t *curr1; // current node for first list
	intnode_t *curr2; // current node for second list
	intnode_t *new;

	assert(head != NULL);
	
	curr1 = head;
	// find end of first list
	while(curr1->next != NULL){
		curr1 = curr1->next;
	}

	curr2 = other;

	while(curr2 != NULL){
		new = intnode_construct(curr2->value,NULL);
		curr1->next = new;
		curr1 = curr1->next;
		curr2 = curr2->next;
	}


}

// Exercise 5

/* Parameter head points to the first node in a linked list, or is
 * NULL if the list is empty.
 *
 * The function terminates (via assert) if the linked list is empty.
 *
 * Copy the value in the head node to the location pointed to by
 * parameter popped_value, delete the head node, and return a pointer
 * to the first node in the modified list.
 */
intnode_t *pop(intnode_t *head, int *popped_value)
{
	intnode_t *oldHead;

	assert(head!=NULL);

    *popped_value = head->value;

	oldHead = head;

	head = head->next;
	free(oldHead);
	
    return head;
}

