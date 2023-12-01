#include <stdio.h>
#include <stdlib.h>
#include "LinkedList.h"

LinkedList* createLinkedList()
{
    LinkedList* list = (LinkedList*)malloc(sizeof(LinkedList));
    list->head = NULL;
    list->tail = NULL;
    list->count = 0;
    return list;
}

void insertStart(LinkedList* list, void* entry)
{

    LinkedListNode* newNode = (LinkedListNode*)malloc(sizeof(LinkedListNode));
    newNode->data = entry;

    if(list->head == NULL)
    {
	list->head = newNode;
    	list->tail = list->head;
    }
    else
    {
 	list->head->prev = newNode;
	newNode->next = list->head;
	list->head = newNode;
	list->head->prev = NULL;	
    }
    list->count = list->count + 1;
 
}

void* removeStart (LinkedList* list)
{

    void* data; 

    if(list == NULL)
    {
    	data = NULL;
    }
    else
    {
	data = list->head->data;
	list->head = list->head->next;
	
	if(list->head != NULL)
	{

		free(list->head->prev);
		list->head->prev = NULL;
		
	}
	
    }
	
    list->count -= 1;
    return data;

}

void insertLast(LinkedList* list, void* entry)
{
    LinkedListNode* newNode = (LinkedListNode*)malloc(sizeof(LinkedListNode));    
    newNode->data = entry;
    newNode->next = NULL;
    if(list->head == NULL)
    {
	list->head = newNode;
	list->tail = list->head;
    } 
    else
    {
        newNode->prev = list->tail;
        list->tail->next = newNode;
        list->tail = newNode;
    }

    list->count += 1;
}

void* removeLast(LinkedList* list)
{
    void* data;

    if(list->head == NULL)
    {
        data  = NULL;
    }
    else
    {
  
     data = list->tail->data;
	list->tail = list->tail->prev;

	if(list->tail != NULL)
	{
		free(list->tail->next);
		list->tail->next = NULL;
	}
       
         
    }
    list->count -= 1; 
    return data;
      
}

void printLinkedList(LinkedList* list, listFunc funcPtr)
{

    LinkedListNode* newNode;

    if(list->head == NULL)
    {
	printf("List is empty\n");
    }
    else
    {
        newNode = list->head;

        while(newNode != NULL)   
        {
             funcPtr(newNode->data); 
	        newNode = newNode->next;  
            

        }

    }

  
   free(newNode);
}


void freeLinkedList(LinkedList* list)
{
	LinkedListNode *node, *nextNode;

	node = (*list).head;
	while(node != NULL)
	{
		nextNode = (*node).next;
		free((*node).data);
		free(node);
		node = nextNode;
	}

	free(list->tail);

	free(list);
}

void freeLinkedList2(LinkedList* list)
{
	LinkedListNode *node, *nextNode;

	node = (*list).head;
	while(node != NULL)
	{
		nextNode = (*node).next;
		free((*node).data);
		free(node);
		node = nextNode;
	}
	free(list);
}
