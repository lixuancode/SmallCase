package com.rj.bd.test;

import com.rj.bd.utils.PasswordUtils;

/**
 * @desc ���Թ�Կ��˽Կ
 * @author ����
 * @time 2020-04-08
 *
 */
public class Test {
	public static void main(String[] args) {
		int size = 50;
		String publicKey = PasswordUtils.ShengCheng(size);
		System.out.println("��Կ��"+publicKey);
		
		String privateKey = PasswordUtils.jiaMi(publicKey);
		
		System.out.println("˽Կ��"+privateKey);
		
		System.out.println("˽Կ���ܣ�"+PasswordUtils.jieMi(privateKey));
		
		
	}
}
