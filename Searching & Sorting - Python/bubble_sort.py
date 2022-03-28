
'''
Bubble Sort Algorithm

Willoughby Peppler-Mann
'''
def bubble_sort(lst : list) -> list:
    '''sorts a list of integers in accending order'''
    
    for i in range(len(lst)-1):
        for j in range(i+1,len(lst)):
            if lst[i] > lst[j]:
                lst[i], lst[j] = lst[j], lst[i]
    
    return lst

