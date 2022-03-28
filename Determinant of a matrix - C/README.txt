
# PROJECT: SYSC 4001 Assignment 1
# AUTHOR: WILLOUGHBY PEPPLER-MANN (101147027)

# Description:
 A C program that uses multi processing to calculate
 the determinant of a 3x3 matrix (M) and the largest  
 element in the matrix M. 

 The determinant of a 3x3 matrix is calculated as follows:
	    a b c
	M = d e f
            g h i

	D1 =  a(ei-fh)
	D2 = -b(fg-di)
	D3 =  c(dh-eg)
        
	R = D1 + D2 + D3
        
        R in this expression is the determinant


 This program creates 3 child processes (P1, P2, and P3) 
 using fork(). The three processes share a shared_matrix_data_t
 structure in shared memory. 

	struct shared_matrix_data_t{
		long int M[3][3];
		long int D[3]; 
		long int L[3]; 
	
		char flag; 
        };

 This structure has 4 attributes. 
 
 M - holds the 3x3 matrix. 
 D - holds the D1, D2, and D3 values.
 L - holds the largest elements from each row. 
 flag - holds a flag to indicate if an error has occurred in any of the processes
 

 The P1 process calculates D1 and stores the result in D[0].
 P1 also determines the largest element in row 1 of M and stores the result in L[0].

 The P2 process calculates D2 and stores the result in D[1].
 P2 also determines the largest element in row 2 of M and stores the result in L[1].

 The P3 process calculates D3 and stores the result in D[2].
 P3 also determines the largest element in row 3 of M and stores the result in L[2].

 The parent process waits for the 3 child processes to finish and calculates
 the determinant by adding up the D1 + D2 + D3 values stored in the array D. 
 The parent process also compares all the elements in the L array and determines 
 which one is the largest. 

 The program outputs the matrix M, the D array, the determinant R, and the largest element L.


# FILES
- DET.c

# HOW TO RUN THE DET PROGRAM 
1. Use the Linux terminal to navigate to the directory where the DET.c file is located. 
2. Use the GNU Compiler to compile the C code into an executable.
	command: gcc DET.c -o DET.o
   this creates an executable DET.o file.
3. Run the program. 
	command: ./DET.o
   this runs the DET.o executable in the terminal


# HOW TO CHANGE THE MATRIX ELEMENT VALUES
1. Open the DET.c file
2. Find the following line of code in the source file. 
	// INPUT MATRIX
	long int matrix[3][3] = {{20, 20, 50},\
	        		 {10, 6, 70}, \
				 {40, 3,  2}};

3. Change the respective values in the assignment expression and save the DET.c file. 
          	  a b c   {{a, b, c}, \
	matrix => d e f => {d, e, f}, \
          	  g h i    {g, h, i}};

4. To run the DET program with the new matrix compile and execute the updated DET.c file.
	gcc DET.c -o DET.o
	./DET.o
