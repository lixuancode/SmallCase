package com.rj.bd.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @desc 加密工具类
 * @author 漓炫
 * @time 2020-04-08
 *
 */
public class PasswordUtils {
	/**
	 * @desc 生成公钥
	 * @param wei
	 * @return
	 */
	public static String ShengCheng(int wei){
		int daCount = 0;//大写字母个数
		int xiaoCount = 0;//小写字母个数
		int numCount = 0;//数字个数
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 2; i++) 
		{
			int ge = (new Random().nextInt((int)(wei*0.5)))+1;//生成1到 位数一半的数
			if(i==0)
			{
				daCount=ge;
			}else
			{
				xiaoCount=ge;
			}
			wei-=ge;//减去已经生成的大小与小写的个数
		}
		numCount=wei;
		//大写字母
		for (int i = 0; i < daCount; i++) 
		{
			int da = (int) (Math.random()*26);
			char str =(char) (da+'A');
			list.add(String.valueOf(str));
		}
		//小写字母
		for (int i = 0; i < xiaoCount; i++) 
		{
			int xiao = (int) (Math.random()*26);
			char str =(char) (xiao+'a');
			list.add(String.valueOf(str));
		}
		//数字
		for (int i = 0; i < numCount; i++) 
		{
			int num = (int) (Math.random()*10);
			list.add(String.valueOf(num));
		}
		//随机排序
		Collections.shuffle(list);
		//拼接
		String passWord = "";
		for (int i = 0; i < list.size(); i++) 
		{
			passWord+=list.get(i);
		}
		return passWord;
	}
	
	 /**
     * 将字符串转为16进制
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
     * 将16进制转为字符串
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
     * @desc 加密   改变存储规则（生成秘钥）
     * @param str
     * @return
     */
    public static String jiaMi(String str){
    	StringBuilder stringBuilder = new StringBuilder();//使用stringBuider拼接字符串 效率更高
		char ch[] = str.toCharArray();//将字符串转为字符数字
		for (int i = 0; i < ch.length; i++) {
			if(String.valueOf(ch[i]).matches("[a-zA-Z]+")){//使用正则表达式判断 当密码为字母时 将他转为ascll值
				stringBuilder.append(ch[i]+0);//拼接
				stringBuilder.append((char)((int) (Math.random()*26)+'A'));//随机生成一个大写字母，作用是后期分割
			}else{
				stringBuilder.append(String.valueOf((char)(ch[i]+'A')));//如果为数字的话将他存储为字母 char类型的数字0代表48
				//列 当ch[i] 的值等于0时 最终存储 0+65+48 存储为小写q 大写的A的值是65，且ch[i]的最大值为9 不用担心溢出无对应值
			}
		}
		//System.out.println(stringBuilder.toString());
		String mi = str2HexStr(stringBuilder.toString());//将拼接完的字符串 转换为16进制
		return mi;
    }
    /**
     * @desc 传入密文字符串进行解密
     * @param str
     * @return
     */
    public static String jieMi(String str){//传入的是16进制字符串
    	StringBuilder stringBuilder = new StringBuilder();//字符串拼接
    	String mi = hexStr2Str(str);//将16进制，转换成默认我们设定的规则的字符串 列：ww90Sy97O115B97J100U
    	char[] ch = mi.toCharArray();//转换成字符数组
    	for (int i = 0; i < ch.length; i++) {
			if(String.valueOf(ch[i]).matches("[a-z]")){//正则判断是否为字母 是字母 就将ch[i] 对应的ascll值减去48与65 就是初始的明文
				stringBuilder.append(String.valueOf(ch[i]-48-65));//减去之后在拼接
			}else if(String.valueOf(ch[i]).matches("[0-9]")){//判断是否是数字
				String bc = String.valueOf(ch[i]);//保存当前值
				while(true){//循环
					if(String.valueOf(ch[i]).matches("[A-Z]")){//此时我们就用到了大写字母的分割，当ch[i]的值为大写字母就终止循环
						break;
					}
					i++;//由于我们将字母转为数字了 且右转换为字符数组 列如65他对应的值为A  但6跟5是两个位置存储 固需要while循环在利用大写字母的分割 实现 65在一起，最终不影响值得转换
					if(String.valueOf(ch[i]).matches("[0-9]")){
						bc+=String.valueOf(ch[i]);//加上i的下一个值
					}
				}
				int num = Integer.valueOf(bc);//将保存的值转换为数字
				stringBuilder.append(String.valueOf((char)num));//最终转为char类型的字符 然后拼接
			}
		}
    	return stringBuilder.toString();
    }
}
