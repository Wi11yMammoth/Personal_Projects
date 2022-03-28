'''
Merge Sort Algorithm

Willoughby Peppler-Mann
'''
def merge(lst1: list, lst2:list) -> list:
    sortedList = []
    i = 0 # index for list 1
    j = 0 # index for list 2
    
    while(i != len(lst1) and j != len(lst2)):
        if lst1[i] < lst2[j]:
            sortedList.append(lst1[i])
            i += 1
        else:
            sortedList.append(lst2[j])
            j += 1
    
    # append any remaining elements 
    sortedList.extend(lst1[i:])
    sortedList.extend(lst2[j:])
    
    return sortedList

def merge_sort(lst : list)->list:
    '''
    Sorts a list of integers in accending order
    '''
    # create a list of one element lists for each element in lst
    if len(lst) < 1:
        return []
    
    workspace = []
    for i in range(len(lst)):
        workspace.append([lst[i]])
    
    # merge algorithm
    i = 0
    while i < len(workspace) - 1:
        lst1 = workspace[i]
        lst2 = workspace[i+1]
        mergedList = merge(lst1, lst2)
        workspace.append(mergedList)
        i += 2
    
    
    sortedList = workspace[-1][:]
    
    return sortedList

