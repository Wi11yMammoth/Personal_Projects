
def fibonacci(n:int):
    '''
    returns the nth fibonacci number
    
    >>> fibonacci(0) 
    0
    >>> fibonacci(1)
    1
    >>> fibonacci(2)
    1
    >>> fibonacci(3)
    2
    >>> fibonacci(4)
    3
    
    The fibonacci sequence is as follows
    
    0 1 1 2 3 5 8 13 21 34 55 89 144 233 377...
    
    Where the next number in the sequence is the sum of the two 
    previous numbers
    
    
    '''
    if n < 1:
        return 0
    elif n < 2:
        return 1
    else:
        return fibonacci(n-1) + fibonacci(n-2)


