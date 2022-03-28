import time
from bubble_sort import bubble_sort 
from insertion_sort import insertion_sort
from merge_sort import merge_sort

'''
Timing Sorting Algorithms

Worst case scenario of a list with 100 elements in decending order
'''
if __name__ == '__main__':
    
    lst = []
    for i in range(1000,-1,-1):
        lst.append(i)
    
    # bubble sort 
    
    t1 = time.perf_counter()
    lst = bubble_sort(lst)
    t2 = time.perf_counter()
    total_time = (t2 - t1) * 1000
    
    print("Bubble Sort took {:.2f}ms to sort the list".format(total_time))
    
    lst = []
    for i in range(1000,-1,-1):
        lst.append(i)    
    
    t1 = time.perf_counter()
    lst = insertion_sort(lst)
    t2 = time.perf_counter()
    total_time = (t2 - t1) * 1000
    
    print("Insertion Sort took {:.2f}ms to sort the list".format(total_time))
    
    lst = []
    for i in range(1000,-1,-1):
        lst.append(i)    
    t1 = time.perf_counter()
    lst = merge_sort(lst)
    t2 = time.perf_counter()
    total_time = (t2 - t1) * 1000
    
    print("Merge Sort took {:.2f}ms to sort the list".format(total_time))
    