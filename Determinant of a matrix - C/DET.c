/*

 SYSC 4001 Assignment 1
 
 Student: Willoughby Peppler-Mann
 
 Date: Oct. 26, 2021
 
 Description
 
 This program calculates the determinent of a 3x3 matrix. 
 
 The matrix is hard coded into the source code. To change the matrix
 change the hard coded values in this line of code. 
 
  long int matrix[3][3] = {{20, 20, 50},\
	                   {10, 6, 70}, \
		           {40, 3,  2}};
	
  
  NOTE: The matrix elements must be of type long int.
  
  This makes the matrix 
  
  20 20 50   a b c
  10  6 70 = d e f
  40  3  2   g h i
  
  The determinent gets calculated using
  
  D1 =  a(ei-fh)
  D2 = -b(fg-di)
  D3 =  c(dh-eg)
  
  R = D1 + D2 + D3 = The Determinant
  
  The program outputs the Caclucated Result (R). 
  
  The program also determines the largest value in the matrix and displays 
  that value as well. 
  
  L = largest value in M. 
  
*/


// INCLUDE FILES
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/shm.h>
#include <unistd.h>
#include <time.h>


// CONSTANTS
#define MICRO_SEC_IN_SEC 1000000



// STRUCTURES
struct shared_matrix_data_t{
	long int M[3][3]; // holds matrix M
	long int D[3]; // holds D array
	long int L[3]; // holds largest values in each row of M
	
	char flag;  // used to indicate if an error occured in any child process
};




// FUNCTION DEFINITIONS
void P1(struct shared_matrix_data_t *data);
void P2(struct shared_matrix_data_t *data);
void P3(struct shared_matrix_data_t *data);


// MAIN
int main(){

	/* Change the matrix values here */
	/* Matrix elements must be of type long int */
	// INPUT MATRIX
	///*
	long int matrix[3][3] = {{20, 20, 50},\
	        		 {10, 6, 70}, \
				 {40, 3,  2}};
	//*/
	
	// TESTS
	/*
	long int matrix[3][3] = {{1, 1, 1},\
	        		 {1, 1, 1}, \
				 {1, 1, 1}};
	*/
	/*			 
	long int matrix[3][3] = {{1, 2, 3},\
	        		 {0, 1, 5}, \
				 {5, 6, 0}};
	*/
	/*			 
	long int matrix[3][3] = {{80, -20, 50},\
	        		 {10, 16, 70}, \
				 {-40, 69, 11}};
	*/
	
	
	// A pointer to the shared data in shared memory
	struct shared_matrix_data_t *data; 
	
	// used to calculate the time a process takes to excecute
	struct timeval start, end;
	
	// variables used to wait() for a process to finish - child = wait(&stat_val);
	int stat_val;
	int child_pid;
	
	
	long int R; // holds the calculated determinant
	long int L; // holds the largest value in the matrix
	int x,y; // hold x and y coordinates for locations in the matrix

	pid_t pid; // holds the process id
	
	int matrixMemoryID; // holds the shared memory ID for the shared matrix data
	
	void *sharedMatrixMemory; // a pointer to the shared memory location
	
	 
	// create the shared memory location and get the ID for it
	matrixMemoryID = shmget(IPC_PRIVATE, sizeof(struct shared_matrix_data_t), 0666 | IPC_CREAT); 
	
	// check if shmget failed
	if (matrixMemoryID == -1){
		// shmget failed
		printf("shmget failed\n");
		exit(EXIT_FAILURE);
	}
	
	// make the shared memory accessible 
	sharedMatrixMemory = shmat(matrixMemoryID, (void *)0, 0); 
	
	// check if shmat failed
	if(sharedMatrixMemory == (void *)-1){
		printf("shmat failed\n");
		exit(EXIT_FAILURE);
	}
	
	// link the pointer matrix to the shared memory location
	data = (struct shared_matrix_data_t *)sharedMatrixMemory; 
	
		
	
	// PUT THE INPUT MATRIX INTO SHARED MEMORY
	for (y=0; y<3; y++){
		for(x=0; x<3; x++){
			data->M[y][x] = matrix[y][x];
		}
	}
	
	
	
	data->flag = 1; // flag used to ensure all results are correct
	pid = fork(); // create a child process
	gettimeofday(&start, NULL);
	switch(pid){
		case -1: // ERROR
			data->flag = -1; 
			break;
		case 0: 
			pid = fork(); // create a child process
			gettimeofday(&start, NULL);
			switch(pid){
				case -1: // ERROR
					data->flag = -1; 
					break;
				case 0: 
					pid = fork();
					gettimeofday(&start, NULL);
					switch(pid){
						case -1: // ERROR
							data->flag = -1; 
							break;
						case 0: // CHILD 3
							P1(data); 
							gettimeofday(&end, NULL);
							printf("Child Process 3 Finsihed - Elapsed Time was: %ld micro sec\n", ((end.tv_sec * MICRO_SEC_IN_SEC + end.tv_usec) - (start.tv_sec * MICRO_SEC_IN_SEC + start.tv_usec))); 
							break;
						default: // CHILD 2
							P2(data);
							gettimeofday(&end, NULL); 
							printf("Child Process 2 Finished - Elapsed Time was: %ld micro sec\n", ((end.tv_sec * MICRO_SEC_IN_SEC + end.tv_usec) - (start.tv_sec * MICRO_SEC_IN_SEC + start.tv_usec))); 
							child_pid = wait(&stat_val); // wait for child 3 to finish				
							break;
					}
					break;
				default: // CHILD 1
					P3(data);
					gettimeofday(&end, NULL);
					printf("Child Process 1 Finished - Elapsed Time was: %ld micro sec\n", ((end.tv_sec * MICRO_SEC_IN_SEC + end.tv_usec) - (start.tv_sec * MICRO_SEC_IN_SEC + start.tv_usec))); 
					child_pid = wait(&stat_val); // wait for child 2 to finish
					break;
				}
			break;
		default: // PARENT
			child_pid = wait(&stat_val); // wait for child 1 to finish
			if (data->flag == -1){
				printf("Failed to create one of the child processes\n");
				exit(EXIT_FAILURE);
			}
			
			
			R = data->D[0] + data->D[1] + data->D[2];
			
			L = data->L[0];
			for (x=1; x<3; x++){
				if (L < data->L[x]){
					L = data->L[x]; 
				}
			}
			
			gettimeofday(&end, NULL);
			
			
			printf("Calculations Finished - Program Total Elapsed Time: %ld micro sec\n\n", ((end.tv_sec * MICRO_SEC_IN_SEC + end.tv_usec) - (start.tv_sec * MICRO_SEC_IN_SEC + start.tv_usec))); 
			
			printf("\nINPUT MATRIX\n");
			for (y=0; y<3; y++){
				for(x=0; x<3; x++){
					printf(" %ld ",data->M[y][x]);
				}
				printf("\n");
			}
			printf("\n");
			
			printf("RESULTS\n");
			printf("D = [%ld, %ld, %ld]\n", data->D[0], data->D[1], data->D[2]); 
			printf("R = %ld\n",R); 
			printf("L = %ld\n", L);
			break;
	}
	
	if (shmdt(sharedMatrixMemory) == -1) {
		printf("shmdt failed\n"); 
		exit(EXIT_FAILURE);
	}
	return 0;
}

/*
Process 1

shared_matrix_data_t *data - holds the pointer to the shared memory location

This process calculates D1 and determines the largest element in row 1 of M.

*/
void P1(struct shared_matrix_data_t *data){
	long int x,y;
	
	printf("Child Process: working with element 1 of D\n"); 
	
	x = data->M[1][1] * data->M[2][2];
	y = data->M[2][1] * data->M[1][2];
	data->D[0] = data->M[0][0] * (x-y);
	
	data->L[0] = data->M[0][0]; 
	for (y=1; y<3; y++){
		for (x=1; x<3; x++){
			if (data->L[0] < data->M[0][x]){
				data->L[0] = data->M[0][x];
			}
		}
	}
	
}
/*
Process 2

shared_matrix_data_t *data - holds the pointer to the shared memory location

This process calculates D2 and determines the largest element in row 2 of M.

*/
void P2(struct shared_matrix_data_t *data){
	long int x,y;
	
	printf("Child Process: working with element 2 of D\n"); 
	
	x = data->M[1][0] * data->M[2][2];
	y = data->M[2][0] * data->M[1][2];
	data->D[1] = -1* data->M[0][1] * (x-y);
	
	
	data->L[1] = data->M[1][0]; 
	for (y=1; y<3; y++){
		for (x=1; x<3; x++){
			if (data->L[1] < data->M[1][x]){
				data->L[1] = data->M[1][x];
			}
		}
	}
}

/*
Process 3

shared_matrix_data_t *data - holds the pointer to the shared memory location

This process calculates D3 and determines the largest element in row 3 of M.

*/
void P3(struct shared_matrix_data_t *data){
	long int x,y;
	
	printf("Child Process: working with element 3 of D\n"); 
	
	x = data->M[1][0] * data->M[2][1];
	y = data->M[2][0] * data->M[1][1];
	data->D[2] = data->M[0][2] * (x-y);

	
	data->L[2] = data->M[2][0]; 
	for (y=1; y<3; y++){
		for (x=1; x<3; x++){
			if (data->L[2] < data->M[2][x]){
				data->L[2] = data->M[2][x];
			}
		}
	}
}




