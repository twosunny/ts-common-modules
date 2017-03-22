package woosun.common.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AuthUtils {

	private static String[] randomStrList = new String[] { "0", "1", "2", "3", "4", "5", "6", "7",  
            "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z" };  
	
	
	public static String getRandomStr(int size){
		
		String randomStr = null;
		
		try{
			List<String> list = Arrays.asList(randomStrList.clone());  
		    Collections.shuffle(list);
		    
		    StringBuilder sb = new StringBuilder();  
		    for (int i = 0; i <list.size(); i++) {  
		        sb.append(list.get(i));  
		    }
		    
		    randomStr = sb.toString().substring(0,size);
		}catch(Exception e){
			return randomStr;
		}
		
		return randomStr;
	}
}
