
def linear_search(lst:list, target:int):
    '''
    lst - a list of integers
    target - the element to search for in the lst
    
    returns the index of the target in the list
    returns -1 if target is not in the list
    '''
    for i in range(len(lst)):
        if lst[i] == target:
            return target
    return -1
        