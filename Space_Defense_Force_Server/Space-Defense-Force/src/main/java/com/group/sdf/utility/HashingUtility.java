package com.group.sdf.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class HashingUtility {
	public static String getHashValue(String data )
			throws NoSuchAlgorithmException
			{
				MessageDigest msg = MessageDigest.getInstance("SHA-256");
				msg.update(data.getBytes());
				byte[] byteData= msg.digest();
				StringBuffer hexString = new StringBuffer();
				
//				for(int bd : byteData ) 
//					{ 
//					String hex = Integer.toHexString(0xff & bd ); 
//					if ( hex.length()==1 ) hexString.append('0');
//					}
				
				
			   	for (int i=0;i<byteData.length;i++) {

		    		String hex=Integer.toHexString(0xff & byteData[i]);
		   	     	if(hex.length()==1) hexString.append('0');
		   	     	hexString.append(hex);
		    	}
				
				return hexString.toString();
			  }
	
	public static void main(String[] args) {
		try {
			System.out.println(HashingUtility.getHashValue("password"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}/// END CLASS 


//////////////////////////////////////////
// Notes 
//////////////////////////////////////////
/*
 * This is a utility class used to convert a 
 * string into its corresponding hashed format 
 * so that we can store the hashed data and not the actual data
 * 
 * This technique is used for storing the secured 
 * information about user like password, pin, CVV.
 * 
 * 	 * This MessageDigest class provides applications the functionality of a message digest algorithm, 
	 * such as SHA-1 or SHA-256. Message digests are secure one-way hash functions 
	 * that take arbitrary-sized data and output a fixed-length hash value. 
	 * msg.update:- Updates the digest using the specified array of bytes.
	   msg.didgest():- returns an array of 32 bytes if SHA-256 algorithm is used, 
	 * and array of 20 bytes if SHA-1 algorithm is used
	 * >> For Loop :
	 * convert the byte to hex format
	 * converting each byte data to its corresponding 
	 * positive number and storing the hexadecimal format of the same
	 * if the size of hexadecimal value is one, 
	 * then appending a '0' in front of it
	 * appending the hexadecimal value to a string buffer
*/