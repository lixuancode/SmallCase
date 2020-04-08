package com.rj.bd.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @desc ���ܹ�����
 * @author ����
 * @time 2020-04-08
 *
 */
public class PasswordUtils {
	/**
	 * @desc ���ɹ�Կ
	 * @param wei
	 * @return
	 */
	public static String ShengCheng(int wei){
		int daCount = 0;//��д��ĸ����
		int xiaoCount = 0;//Сд��ĸ����
		int numCount = 0;//���ָ���
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 2; i++) 
		{
			int ge = (new Random().nextInt((int)(wei*0.5)))+1;//����1�� λ��һ�����
			if(i==0)
			{
				daCount=ge;
			}else
			{
				xiaoCount=ge;
			}
			wei-=ge;//��ȥ�Ѿ����ɵĴ�С��Сд�ĸ���
		}
		numCount=wei;
		//��д��ĸ
		for (int i = 0; i < daCount; i++) 
		{
			int da = (int) (Math.random()*26);
			char str =(char) (da+'A');
			list.add(String.valueOf(str));
		}
		//Сд��ĸ
		for (int i = 0; i < xiaoCount; i++) 
		{
			int xiao = (int) (Math.random()*26);
			char str =(char) (xiao+'a');
			list.add(String.valueOf(str));
		}
		//����
		for (int i = 0; i < numCount; i++) 
		{
			int num = (int) (Math.random()*10);
			list.add(String.valueOf(num));
		}
		//�������
		Collections.shuffle(list);
		//ƴ��
		String passWord = "";
		for (int i = 0; i < list.size(); i++) 
		{
			passWord+=list.get(i);
		}
		return passWord;
	}
	
	 /**
     * ���ַ���תΪ16����
     * @param str
     * @return
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }
    /**
     * ��16����תΪ�ַ���
     * @param str
     * @return
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }
    
    /**
     * @desc ����   �ı�洢����������Կ��
     * @param str
     * @return
     */
    public static String jiaMi(String str){
    	StringBuilder stringBuilder = new StringBuilder();//ʹ��stringBuiderƴ���ַ��� Ч�ʸ���
		char ch[] = str.toCharArray();//���ַ���תΪ�ַ�����
		for (int i = 0; i < ch.length; i++) {
			if(String.valueOf(ch[i]).matches("[a-zA-Z]+")){//ʹ��������ʽ�ж� ������Ϊ��ĸʱ ����תΪascllֵ
				stringBuilder.append(ch[i]+0);//ƴ��
				stringBuilder.append((char)((int) (Math.random()*26)+'A'));//�������һ����д��ĸ�������Ǻ��ڷָ�
			}else{
				stringBuilder.append(String.valueOf((char)(ch[i]+'A')));//���Ϊ���ֵĻ������洢Ϊ��ĸ char���͵�����0����48
				//�� ��ch[i] ��ֵ����0ʱ ���մ洢 0+65+48 �洢ΪСдq ��д��A��ֵ��65����ch[i]�����ֵΪ9 ���õ�������޶�Ӧֵ
			}
		}
		//System.out.println(stringBuilder.toString());
		String mi = str2HexStr(stringBuilder.toString());//��ƴ������ַ��� ת��Ϊ16����
		return mi;
    }
    /**
     * @desc ���������ַ������н���
     * @param str
     * @return
     */
    public static String jieMi(String str){//�������16�����ַ���
    	StringBuilder stringBuilder = new StringBuilder();//�ַ���ƴ��
    	String mi = hexStr2Str(str);//��16���ƣ�ת����Ĭ�������趨�Ĺ�����ַ��� �У�ww90Sy97O115B97J100U
    	char[] ch = mi.toCharArray();//ת�����ַ�����
    	for (int i = 0; i < ch.length; i++) {
			if(String.valueOf(ch[i]).matches("[a-z]")){//�����ж��Ƿ�Ϊ��ĸ ����ĸ �ͽ�ch[i] ��Ӧ��ascllֵ��ȥ48��65 ���ǳ�ʼ������
				stringBuilder.append(String.valueOf(ch[i]-48-65));//��ȥ֮����ƴ��
			}else if(String.valueOf(ch[i]).matches("[0-9]")){//�ж��Ƿ�������
				String bc = String.valueOf(ch[i]);//���浱ǰֵ
				while(true){//ѭ��
					if(String.valueOf(ch[i]).matches("[A-Z]")){//��ʱ���Ǿ��õ��˴�д��ĸ�ķָ��ch[i]��ֵΪ��д��ĸ����ֹѭ��
						break;
					}
					i++;//�������ǽ���ĸתΪ������ ����ת��Ϊ�ַ����� ����65����Ӧ��ֵΪA  ��6��5������λ�ô洢 ����Ҫwhileѭ�������ô�д��ĸ�ķָ� ʵ�� 65��һ�����ղ�Ӱ��ֵ��ת��
					if(String.valueOf(ch[i]).matches("[0-9]")){
						bc+=String.valueOf(ch[i]);//����i����һ��ֵ
					}
				}
				int num = Integer.valueOf(bc);//�������ֵת��Ϊ����
				stringBuilder.append(String.valueOf((char)num));//����תΪchar���͵��ַ� Ȼ��ƴ��
			}
		}
    	return stringBuilder.toString();
    }
}
