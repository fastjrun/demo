package com.fastjrun.util.springext;

import java.util.Scanner;

import com.fastjrun.helper.DESedeCodec;


public class EncryptPass {
	public static void main(String[] args) throws Exception {
		Scanner cin = new Scanner(System.in);
		System.out.println("Please input source text(within 128 characters)");
		String plaintext = cin.nextLine();

		String ciphertext = DESedeCodec.encrypt(plaintext);

		System.out.println("your cipher text as follow below");
		System.out.println(ciphertext);
		cin.close();

	}
}
