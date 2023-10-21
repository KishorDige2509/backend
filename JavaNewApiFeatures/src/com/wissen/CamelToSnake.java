package com.wissen;

public class CamelToSnake {

	public static void main(String[] args) {
		// Given string str
        String str = "whatIsYourNameKishorIsMyNameRshelSekSetinSt";
 
        // Print the modified string
        System.out.print(camelToSnake(str));
	}
	
	public static String camelToSnake(String str) {
        String result = str;
        
        //TODO Write code here  
        String[] split = result.split("[A-Z]");
        
        // what, s, our, ame
        
        StringBuilder build = new StringBuilder();
        int j = 0;
        for(int i=0; i<split.length; i++) {
            char c;
            if(i==0) {
                j = split[i].length();
            } else {
                if(j<result.length()) { 
                     j = j + split[i].length()+1;
                } 
            }
            System.out.println("j" + j);
            if(j<result.length()) {
                c = result.charAt(j);
                System.out.println("" + c);
                build.append(split[i]).append("_").append(c); 
            }
                
        }
 
    
        return build.append(split[split.length-1]).toString().toUpperCase();
    }
}


