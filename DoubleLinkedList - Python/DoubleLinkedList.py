
'''
Class: Node

Author: Willoughby Peppler-Mann

The node class is for a node in a double linked list.

Attributes
_item; holds an object as the contents of the node. Can be any object.
_next; holds the reference to the next node in the list.
_prev; holds the reference to the previous node in the list.

Example, 

 <-> NODE <-> NODE <-> NODE <->
'''
class Node:
    
    def __init__(self, item):
        '''Constructor'''
        self._item = item
        self._next = None
        self._prev = None
    
    def get_item(self):
        '''returns the item this node holds'''
        return self._item
    
    def set_item(self, item):
        '''set the contents of this node'''
        self._item = item
    
    def get_next(self):
        '''returns the next node in the list'''
        return self._next
    
    def get_prev(self):
        '''returns the previous node in the list'''
        return self._prev
    
    def __str__(self)->str:
        return f"[{self._item}]"

'''
Class: DoubleLinkedList

This is my implementation of a double linked list.

This class uses the above Node class to create a double linked list.

A DoubleLinked List is a data structure in which each node in the list
points to the previous and next node in the list. 

For example,

START <-> [1] <-> [2] <-> [3] <-> [4] <-> END

My DoubleLinkedList is also circular so the last element points to the start.

'''
class DoubleLinkedList:
    
    def __init__(self):
        self._head = None
        self._count = 0
    
    def push_front(self, item):
        '''Adds an item to the front of the list'''
        if self._head is None:
            self._head = Node(item)
            self._head._next = self._head
            self._head._prev = self._head
        else:
            new_node = Node(item)
            new_node._next = self._head
            new_node._prev = self._head.get_prev()
            self._head._prev._next = new_node            
            self._head._prev = new_node
            self._head = new_node
            
        self._count += 1 # added an item to the list
    
    def push_end(self, item):
        '''Adds an item to the end of the list'''
        if self._head is None:
            self._head = Node(item)
            self._head._next = self._head
            self._head._prev = self._head
        else:
            new_node = Node(item)
            new_node._next = self._head
            new_node._prev = self._head.get_prev()
            self._head._prev._next = new_node            
            self._head._prev = new_node
        
        self._count += 1 # added an item to the list
    
    def insert_at(self, index:int, item) -> bool:
        '''
        inserts an item at the specified index position in the list.
        
        returns True if successful
        returns False if unsuccessful
        
        index starts at 0. If the index is negative then that means looking
        from the end of the list
        
        Example,
        
        START -> [1] <-> [2] <-> [3] <- END
        
        1 is at index 0 or -3
        2 is at index 1 or -2
        3 is at index 2 or -1
        
        if you insert_at(0, 4) the list would be
        
        START -> [4] <-> [1] <-> [2] <-> [3] <- END
        
        if you insert_at(-2,7) the list would be 
        
        START -> [1] <-> [2] <-> [7] <-> [3] <- END
        
        If you try to insert at a position that does not already exist in the
        list the item will not be added and the method will return false.
        
        if you tried to insert_at(5, 8) the list would remain 
        
        START -> [1] <-> [2] <-> [3] <- END
        
        and the function would return false
        
        '''
        
        if abs(index) < self._count:
            node = self._head
            if index > 0:
                for i in range(index):
                    node = node.get_next()
            else:
                for i in range(abs(index)-1):
                    node = node.get_prev()
            
            new_node = Node(item)
            new_node._next = node
            new_node._prev = node._prev
            node._prev._next = new_node
            node._prev = new_node
            
            if index == 0:
                self._head = new_node
            
            self._count += 1
            
            return True # item added
        else:
            return False # item not added
    
    def size(self):
        '''returns the number of items in the list'''
        return self._count
    
    def count(self, item) -> int:
        '''returns the number of item in the list'''
        node = self._head
        count = 0
        for i in range(self._count):
            if node.get_item() == item():
                count += 1
            node = node.get_next()
        return count
            
        
    def get(self, index:int):
        '''
        
        Param: index; holds the location of the item you want to get.
        
        returns the item at the specified index
        returns None if there is no item at that index location or 
        if that index location does not exist. 
        
        index can be positive or negative. 
        
        If negative it looks from the end of the list. 
        '''
        
        if abs(index) < self._count:
            node = self._head
            if index > 0:
                for i in range(index):
                    node = node.get_next()
            else:
                for i in range(abs(index)):
                    node = node.get_prev()  
                    
            return node.get_item()
        else:
            return None
        
    def get_front(self):
        '''
        returns the item at the front of the list
        returns None if the list is empty
        '''
        if self._head is None:
            return None
        return self._head.get_item()
    
    def get_end(self):
        '''
        returns the item at the end of the list
        returns None if the list is empty
        '''
        if self._head is None:
            return None
        
        return self._head._prev.get_item()
    
    
    def pop_front(self):
        '''
        removes and returns the item at the front of the list
        returns None if the list is empty
        '''
        
        if self._head is not None:
            item = self._head.get_item()
            
            node = self._head
            self._head._next._prev = self._head.get_prev()
            self._head._prev._next = self._head.get_next()
            self._head = self._head.get_next()
            
            
            node = None
            self._count -= 1
            return item
        else:
            return None
        
    def pop_end(self):
        '''
        removes and returns the item at the end of the list
        returns None if the list is empty
        '''
        if self._head is not None:
            item = self._head._prev.get_item()
            
            node = self._head.get_prev()
            node._next._prev = node.get_prev()
            node._prev._next = node.get_next()
            
            node = None
            self._count -= 1
            return item
        else:
            return None
        
        
    def remove(self, item):
        '''removes the first occurance of item from the list'''
        
        if self._head is not None:
            node = self._head
            for i in range(self._count):
                if node.get_item() == item:
                    node._prev._next = node.get_next()
                    node._next._prev = node.get_prev()
                    
                    self._count -= 1
                    
                    if i == 0:
                        self._head = self._head.get_next()
                    
                    node = None
                    break
                
                node = node.get_next()          
    
    def contains(self, item) -> bool:
        '''
        returns True if the item is in the list
        returns False if the item is not in the list
        '''
        
        node = self._head
        for i in range(self._count):
            if node.get_item() == item:
                return True
            node = node.get_next()
        
        return False
    
      
    
    def index_of(self, item) -> int:
        '''
        returns the index of the first occurance of item in the list
        returns -1 if the item is not in the list
        '''
        index = -1
        node = self._head
        
        for i in range(self._count):
            if node.get_item() == item:
                index = i;
                break
            node = node.get_next()
            
        return index
    
    def set(self, index:int, item) -> bool:
        '''
        sets the item at the index location in the list
        if the position exists.
        
        returns True if successful
        return False if failed
        '''
        
        if abs(index) < self._count:
            node = self._head
            if index > 0:
                for i in range(index):
                    node = node.get_next()
            else:
                for i in range(abs(index)):
                    node = node.get_prev()
            
            node.set_item(item)
            
            return True
        else:
            return False
    
    def max(self):
        '''
        returns the largest item in the list
        returns None if the list is empty
        '''
        if self._head is None:
            return None
        
        node = self._head
        largest = node.get_item()
        for i in range(1, self._count):
            node = node.get_next()
            if largest < node.get_item():
                largest = node.get_item()
        
        return largest
    
    def min(self):
        '''
        returns the smallest item in the list
        returns None if the list is empty
        '''
        
        if self._head is None:
            return None
        
        node = self._head
        smallest = node.get_item()
        for i in range(1, self._count):
            node = node.get_next()
            if smallest > node.get_item():
                smallest = node.get_item()
        
        return smallest
            
    def rotate_right(self):
        '''rotates every element in the list right'''
        if self._head is not None:
            self._head = self._head.get_next()
    
    def rotate_left(self):
        '''rotates every element in the list left'''
        if self._head is not None:
            self._head = self._head.get_prev()
    
    def sort(self):
        '''sorts the list'''
        
        
        # Find smallest item in list and make it the head
        node = self._head
        smallest = node
        for i in range(1,self._count):
            node = node.get_next()
            if smallest.get_item() > node.get_item():
                smallest = node
            
        
        self._head = smallest
        
        # bubble sort the list
        for i in range(self._count-1):
            node = self._head
            for j in range(i, self._count):
                nxt = node.get_next()
                
                if node.get_item() > nxt.get_item():
                    nxt._next._prev = node
                    node._prev._next = nxt
                    nxt._prev = node._prev
                    node._next = nxt._next
                    nxt._next = node
                    node._prev = nxt
                else:
                    node = node.get_next()
                    
        
    def __str__(self):
        string = "START"
        current = self._head
        for i in range(self._count):
            string = string+' <-> '+ str(current)
            current = current.get_next()
        string = string + " <-> END"
        
        return string
    