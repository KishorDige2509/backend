package com.mockTests.Classess;

public class Interview2 {
	public static void main(String[] args) {
	
			int[] arr = {0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1};
			int length = arr.length;
			for(int i=0; i<arr.length; i++){
				
				if(arr[i] == 0 && arr[length-(i+1)]==1){
					
					continue;
				} else {
					arr[i]=0;
					arr[length-(i+1)]=1;
				}
			}

			for(int i=0; i<arr.length; i++){
				System.out.print(arr[i] + " ");
			}
			
		
	}

}
