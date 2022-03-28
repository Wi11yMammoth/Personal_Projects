def insert(lst : list, b : int) -> None:
    '''
    insert function does the inserting
    '''
    i = b
    
    while i != 0 and lst[i-1] >= lst[b]:
        i = i - 1
    
    value = lst[b]
    del lst[b]
    lst.insert(i, value)

def insertion_sort(lst : list) -> list:
    '''
    sorts a list in accending order
    '''
    
    i = 0
    
    while i != len(lst):
        insert(lst, i)
        i += 1
    
    return lst

