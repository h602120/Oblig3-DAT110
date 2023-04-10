package no.hvl.dat110.util;

/**
 * exercise/demo purpose in dat110
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	
	public static BigInteger hashOf(String entity) {	
		
		BigInteger hashint = null;
		
		/*
		Task: Hash a given string using MD5 and return the result as a BigInteger.
		we use MD5 with 128 bits digest
		compute the hash of the input 'entity'
		convert the hash into hex format
		convert the hex into BigInteger
		return the BigInteger
		 */
//		Task: Hash a given string using MD5 and return the result as a BigInteger
//		MessageDigest md = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(entity.getBytes(StandardCharsets.UTF_8));
			String hex = toHex(digest);
			hashint = new BigInteger(hex, 16);
//			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
//		md.update(entity.getBytes());
//		byte[] digest = md.digest();
//		String hex = toHex(digest);
//		hashint = new BigInteger(hex, 16);
//

		return hashint;
	}
	
	public static BigInteger addressSize() {
		
		// Task: compute the address size of MD5
		// compute the number of bits = bitSize()
		// compute the address size = 2 ^ number of bits
		// return the address size

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			int digestlen = md.getDigestLength();
			return BigInteger.valueOf(2).pow(digestlen*8);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}


		return null;
	}
	
	public static int bitSize() {
		
		int digestlen = 0;
		
		// find the digest length
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			digestlen = md.getDigestLength();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return digestlen*8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
