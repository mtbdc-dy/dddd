package com.lidehang.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


/**
 * @author lidehang-IT
 *
 */
public class RSAUtils {

	private static String RSA = "RSA";

	/**
	 * 随机生成RSA密钥对(默认密钥长度为1024)
	 * 
	 * @return
	 */
	public static KeyPair generateRSAKeyPair()
	{
		return generateRSAKeyPair(1024);
	}

	/**
	 * 随机生成RSA密钥对
	 * 
	 * @param keyLength
	 *            密钥长度，范围：512～2048<br>
	 *            一般1024
	 * @return
	 */
	public static KeyPair generateRSAKeyPair(int keyLength)
	{
		try
		{
			KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
			kpg.initialize(keyLength);
			return kpg.genKeyPair();
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 用公钥加密 <br>
	 * 每次加密的字节数，不能超过密钥的长度值减去11
	 * 
	 * @param data
	 *            需加密数据的byte数据
	 * @param pubKey
	 *            公钥
	 * @return 加密后的byte型数据
	 */
	public static byte[] encryptData(byte[] data, PublicKey publicKey)
	{
		try
		{
			Cipher cipher = Cipher.getInstance(RSA);
			// 编码前设定编码方式及密钥
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			// 传入编码数据并返回编码结果
			return cipher.doFinal(data);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 用私钥解密
	 * 
	 * @param encryptedData
	 *            经过encryptedData()加密返回的byte数据
	 * @param privateKey
	 *            私钥
	 * @return
	 */
	public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey)
	{
		try
		{
			Cipher cipher = Cipher.getInstance(RSA);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(encryptedData);
		} catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 通过公钥byte[](publicKey.getEncoded())将公钥还原，适用于RSA算法
	 * 
	 * @param keyBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException,
			InvalidKeySpecException
	{
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 通过私钥byte[]将公钥还原，适用于RSA算法
	 * 
	 * @param keyBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException,
			InvalidKeySpecException
	{
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 使用N、e值还原公钥
	 * 
	 * @param modulus
	 * @param publicExponent
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKey(String modulus, String publicExponent)
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		BigInteger bigIntModulus = new BigInteger(modulus);
		BigInteger bigIntPrivateExponent = new BigInteger(publicExponent);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 使用N、d值还原私钥
	 * 
	 * @param modulus
	 * @param privateExponent
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey getPrivateKey(String modulus, String privateExponent)
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		BigInteger bigIntModulus = new BigInteger(modulus);
		BigInteger bigIntPrivateExponent = new BigInteger(privateExponent);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 从字符串中加载公钥
	 * 
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static PublicKey loadPublicKey(String publicKeyStr) throws Exception
	{
		try
		{
			byte[] buffer = Base64Utils.decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e)
		{
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e)
		{
			throw new Exception("公钥非法");
		} catch (NullPointerException e)
		{
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 从字符串中加载私钥<br>
	 * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
	 * 
	 * @param privateKeyStr
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception
	{
		try
		{
			byte[] buffer = Base64Utils.decode(privateKeyStr);
			// X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e)
		{
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e)
		{
			throw new Exception("私钥非法");
		} catch (NullPointerException e)
		{
			throw new Exception("私钥数据为空");
		}
	}

	/**
	 * 从文件中输入流中加载公钥
	 * 
	 * @param in
	 *            公钥输入流
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static PublicKey loadPublicKey(InputStream in) throws Exception
	{
		try
		{
			return loadPublicKey(readKey(in));
		} catch (IOException e)
		{
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e)
		{
			throw new Exception("公钥输入流为空");
		}
	}

	/**
	 * 从文件中加载私钥
	 * 
	 * @param keyFileName
	 *            私钥文件名
	 * @return 是否成功
	 * @throws Exception
	 */
	public static PrivateKey loadPrivateKey(InputStream in) throws Exception
	{
		try
		{
			return loadPrivateKey(readKey(in));
		} catch (IOException e)
		{
			throw new Exception("私钥数据读取错误");
		} catch (NullPointerException e)
		{
			throw new Exception("私钥输入流为空");
		}
	}

	/**
	 * 读取密钥信息
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private static String readKey(InputStream in) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String readLine = null;
		StringBuilder sb = new StringBuilder();
		while ((readLine = br.readLine()) != null)
		{
			if (readLine.charAt(0) == '-')
			{
				continue;
			} else
			{
				sb.append(readLine);
				sb.append('\r');
			}
		}

		return sb.toString();
	}

	/**
	 * 打印公钥信息
	 * 测试使用
	 * @param publicKey
	 */
	public static void printPublicKeyInfo(PublicKey publicKey)
	{
		RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
		System.out.println("----------RSAPublicKey----------");
		System.out.println("Modulus.length=" + rsaPublicKey.getModulus().bitLength());
		System.out.println("Modulus=" + rsaPublicKey.getModulus().toString());
		System.out.println("PublicExponent.length=" + rsaPublicKey.getPublicExponent().bitLength());
		System.out.println("PublicExponent=" + rsaPublicKey.getPublicExponent().toString());
	}

	public static void printPrivateKeyInfo(PrivateKey privateKey)
	{
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
		System.out.println("----------RSAPrivateKey ----------");
		System.out.println("Modulus.length=" + rsaPrivateKey.getModulus().bitLength());
		System.out.println("Modulus=" + rsaPrivateKey.getModulus().toString());
		System.out.println("PrivateExponent.length=" + rsaPrivateKey.getPrivateExponent().bitLength());
		System.out.println("PrivatecExponent=" + rsaPrivateKey.getPrivateExponent().toString());

	}
	
	
	//测试加密
	private static String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgcglNlKnh2HJIoS+V53V4QvdftOpxu/VwDIqA4GD5sPmCf/xpvlBKrgSU2Cu2meCCPIqonMoEqagFVN69vI9ngNpF0IYagqIVAmEi6ka1bPHMvtHOveuQqGtBbVi7GcWqbwUeQTut6HiaPdPReSG0x4O1/AUox1kWeDb5D7aXbwIDAQAB";
	private static String PRIVATEKEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKByCU2UqeHYckihL5XndXhC91+06nG79XAMioDgYPmw+YJ//Gm+UEquBJTYK7aZ4II8iqicygSpqAVU3r28j2eA2kXQhhqCohUCYSLqRrVs8cy+0c6965Coa0FtWLsZxapvBR5BO63oeJo909F5IbTHg7X8BSjHWRZ4NvkPtpdvAgMBAAECgYEAmGkbWz4ATpCcp0+uc1jGpvH+KUUje9zPnpvbKed7V1V8/zFy5kQp43cA1R+wDqqHsU+va9JcuScse7wORvCKy4cl8f10AyrtUKdSjfNMkUfz9dS75YjQzZqtJ9Guvvprw2sHp7JmZIUc4ZOFSjWk/uxYa6vo9eDdOH0Tv02dW+ECQQDTMLQ8pLHCrTjCdjZjTqnZp3kmgIJ9uNfGlD6EjkkyLxEiih5cj2OeaQo0yY6bA6/WgwDhdpGR32cptK2dAJ/xAkEAwn0CWMbpiI9lQTEK7LNcudQATiX8541ByxFltj8VHjsu1jpapLVoNxkg8ujBux0y2x7qPTtdyKsR5L17phwNXwJACR6bSdsayftM7ijIpHUrJHj05FK8T82Bk9Om93ZldsNNjB5GvVx7wsVqaWkivVLlbu/tQ88o7hGllMl2jccPIQJAf2rW9sKajVUkySkQNzLrNPOnn4tTvX/V8uxa7qM1erDbZo6kg/Q1aqOT6pCDG8ttXhFK9QLH1of9GRbqw1DtkQJBAIvGRQw5jG0GE+VVFh1GxAjPdnKpLtYsa41ElJaG7mP1VJD26zypd2m8vgb0UOdOVpRLH5Z4DRqaiLS917HoHso=";
	
	public static void main(String[] args) throws Exception {
		PublicKey publicKey = loadPublicKey(PUBLICKEY);
		PrivateKey privateKey = loadPrivateKey(PRIVATEKEY);
		
		byte[] data = encryptData("册数吾问无为谓无数据".getBytes(), publicKey);
		System.out.println(new String(decryptData(data, privateKey),"utf-8"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
