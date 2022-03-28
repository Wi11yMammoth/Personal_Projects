import unittest
from bubble_sort import bubble_sort
from insertion_sort import insertion_sort
from merge_sort import merge_sort

class TestSorting(unittest.TestCase):
    
    def test_bubble_sort(self):
        
        self.assertEqual([], bubble_sort([]))
        self.assertEqual([1,2,3],bubble_sort([3,2,1]))
        self.assertEqual([1,2,3,4,5], bubble_sort([2,3,1,5,4]))
        self.assertEqual([1,2,3,4], bubble_sort([1,2,3,4]))
        
    
    def test_merge_sort(self):
        self.assertEqual([], merge_sort([]))
        self.assertEqual([1,2,3], merge_sort([3,2,1]))
        self.assertEqual([1,2,3,4,5], merge_sort([2,3,1,5,4]))
        self.assertEqual([1,2,3,4], merge_sort([1,2,3,4]))        
    
    def test_insertion_sort(self):
        self.assertEqual([], insertion_sort([]))
        self.assertEqual([1,2,3],insertion_sort([3,2,1]))
        self.assertEqual([1,2,3,4,5], insertion_sort([2,3,1,5,4]))
        self.assertEqual([1,2,3,4], insertion_sort([1,2,3,4]))
        

if __name__ == '__main__':
    
    unittest.main()
    