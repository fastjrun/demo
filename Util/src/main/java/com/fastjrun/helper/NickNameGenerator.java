package com.fastjrun.helper;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.mail.internet.MimeUtility;

public class NickNameGenerator {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println("Please input your nickName(within 50 characters)");
		String nickName = cin.nextLine();

		try {
			String nickNameEncode = MimeUtility
					.encodeText(nickName);
			System.out.println("your nickNameEncode as follow below");
			System.out.println(nickNameEncode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cin.close();

	}

}
