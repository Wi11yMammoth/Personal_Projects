'''
Binary Search

Willoughby Peppler-Mann
'''
def binary_search(lst:list, target:int)->int:
    '''
    lst - a sorted list of integers
    target - an element to search for in the list
    returns the index of the location of the element in the list if found
    returns -1 if the element is not in the list
    '''
    
    if len(lst) > 0:
        
        high = len(lst) - 1
        low = 0
        
        while high >= low:
            
            mid = (high+low) // 2
            
            if lst[mid] < target:
                low = mid + 1
            elif lst[mid] > target:
                high = mid - 1
            else:
                return mid
    
    return -1
    
    