#include <stdio.h>
#include <stdlib.h>

typedef struct ArrayList
{
    int *items;
    int capacity;
    int length;
    int (*get)(struct ArrayList *, int);
    int (*remove)(struct ArrayList *, int);
    void (*add)(struct ArrayList *, int);
    void (*print)(struct ArrayList *);
} ArrayList;

// Functions declerations
ArrayList newArrayList();
int getItem(ArrayList *self, int index);
void addItem(ArrayList *self, int num);
int removeItem(ArrayList *self, int index);
void print(ArrayList *self);

int main()
{
    ArrayList list = newArrayList();
    list.add(&list, 1);
    list.add(&list, 2);
    list.add(&list, 3);
    list.add(&list, 4);
    list.add(&list, 5);
    list.add(&list, 6);
    list.add(&list, 7);
    list.add(&list, 8);
    list.add(&list, 9);
    list.add(&list, 10);
    list.add(&list, 11);

    printf("\n%d, %d, %d, %d\n", list.get(&list, 4),
           list.get(&list, 4),
           list.get(&list, 7),
           list.get(&list, 3));
    list.print(&list);
    printf("\n%d\n", list.remove(&list, 4));
    printf("\n%d\n", list.remove(&list, 5));
    list.print(&list);
    return 0;
}

ArrayList newArrayList()
{
    ArrayList arrayList;
    arrayList.length = 0;
    arrayList.capacity = 10;
    arrayList.items = malloc(arrayList.capacity * sizeof(int));
    arrayList.add = &addItem;
    arrayList.get = &getItem;
    arrayList.remove = &removeItem;
    arrayList.print = &print;
    return arrayList;
}

int getItem(ArrayList *self, int index)
{
    if (index >= 0 && index < self->length)
    {
        return *(self->items + index);
    }
    printf("\nOUT OF BOUND EXEPTION - index (%d) for length (%d)\n", index, self->length);
    return -1;
}
void addItem(ArrayList *self, int num)
{
    if (self->capacity > self->length)
    {
        *(self->items + self->length) = num;
    }
    else
    {
        int newCapacity = self->capacity * 2;
        int *newItems = malloc(newCapacity * sizeof(int));
        int *oldItems = self->items;

        int i;
        for (i = 0; i < self->length; i++)
        {
            *(newItems + i) = *(oldItems + i);
        }
        *(newItems + i) = num;
        self->items = newItems;
        free(oldItems);
        self->capacity = newCapacity;
    }
    self->length += 1;
}
void print(ArrayList *self)
{
    printf("Capacity: %d\n", self->capacity);
    printf("Length: %d\n", self->length);

    int i;
    for (i = 0; i < self->length; i++)
    {
        printf("(%d) ", *(self->items + i));
    }
    printf("\n");
}
int removeItem(ArrayList *self, int index)
{
    if (index >= 0 && index < self->length)
    {
        int i, removedItem = *(self->items + index);
        for (i = index; i < self->length - 1; i++)
        {
            *(self->items + i) = *(self->items + i + 1);
        }
        self->length -= 1;
        return removedItem;
    }
    printf("\nOUT OF BOUND EXEPTION - index (%d) for length (%d)\n", index, self->length);
    return -1;
}
