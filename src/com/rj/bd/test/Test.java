package com.rj.bd.test;

import com.rj.bd.utils.PasswordUtils;

/**
 * @desc ≤‚ ‘π´‘ø”ÎÀΩ‘ø
 * @author ¿ÏÏ≈
 * @time 2020-04-08
 *
 */
public class Test {
	public static void main(String[] args) {
		int size = 50;
		String publicKey = PasswordUtils.ShengCheng(size);
		System.out.println("π´‘ø£∫"+publicKey);
		
		String privateKey = PasswordUtils.jiaMi(publicKey);
		
		System.out.println("ÀΩ‘ø£∫"+privateKey);
		
		System.out.println("Ω‚√‹£∫"+PasswordUtils.jieMi(privateKey));
		
		
	}
}
