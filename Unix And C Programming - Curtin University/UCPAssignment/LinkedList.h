#ifndef LINKEDLIST_H
#define LINKEDLIST_H

/*struct delcarations*/

typedef struct LinkedListNode
{
    void* data;
    struct LinkedListNode* next;
    struct LinkedListNode* prev;
} 
LinkedListNode;

typedef struct
{
    LinkedListNode* head;
    LinkedListNode* tail;
    int count;
}
LinkedList;

/*foward declartions*/
LinkedList* createLinkedList();
void insertStart(LinkedList* list, void* entry);
void* removeStart(LinkedList* list);
void insertLast(LinkedList* list, void* entry);
void* removeLast(LinkedList* list);
typedef void (*listFunc)(void* data);
void printLinkedList(LinkedList* list, listFunc funcPtr);
void freeLinkedList(LinkedList* list);
void freeLinkedList2(LinkedList* list);

#endif
