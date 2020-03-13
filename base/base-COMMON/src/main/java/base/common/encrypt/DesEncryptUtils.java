package base.common.encrypt;

import java.nio.charset.Charset;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * @author yshi
 *
 */
public final class DesEncryptUtils {
	
	/** Secure key. */
	private static final String SECURE_KEY = "12345678";

	/** Default charset. */
	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	private DesEncryptUtils() {}
	
	/**
	 * Encrypt the input string.
	 *
	 * @param srcStr
	 * 
	 * @return encrypted string value
	 */
	public static String encrypt(String srcStr) {
		return encrypt(srcStr, DEFAULT_CHARSET);
	}

	/**
	 * Decrypt the input string.
	 *
	 * @param string
	 *            need to be decrypt
	 * 
	 * @return decrypt string value
	 * 
	 * @throws InvalidKeyException
	 *             NoSuchAlgorithmException InvalidKeySpecException
	 *             NoSuchPaddingException InvalidAlgorithmParameterException
	 *             IllegalBlockSizeException BadPaddingException
	 */
	public static String decrypt(String hexStr) throws Exception {
		return decrypt(hexStr, DEFAULT_CHARSET);
	}

	/**
	 * Encrypt the input string.
	 *
	 * @param String
	 * @param Charset
	 * 
	 * @return encrypted string value
	 */
	public static String encrypt(String srcStr, Charset charset) {
		return encrypt(srcStr, charset, SECURE_KEY);
	}

	/**
	 * Decrypt the input string.
	 *
	 * @param string
	 *            need to be decrypt
	 * @param Charset
	 * 
	 * @return decrypt string value
	 * 
	 * @throws InvalidKeyException
	 *             NoSuchAlgorithmException InvalidKeySpecException
	 *             NoSuchPaddingException InvalidAlgorithmParameterException
	 *             IllegalBlockSizeException BadPaddingException
	 */
	public static String decrypt(String hexStr, Charset charset) throws Exception {
		return decrypt(hexStr, charset, SECURE_KEY);
	}

	/**
	 * Encrypt the input string.
	 *
	 * @param String
	 * @param Charset
	 *            charset
	 * @param String
	 *            secureKey
	 * 
	 * @return encrypted string value
	 */
	public static String encrypt(String srcStr, Charset charset, String sKey) {
		byte[] src = srcStr.getBytes(charset);
		byte[] buf = encrypt(src, sKey);
		return parseByteToHexStr(buf);
	}

	/**
	 * Decrypt the input string.
	 *
	 * @param string
	 *            need to be decrypt
	 * @param Charset
	 * @param String 
	 * 			  secure key
	 * 
	 * @return decrypt string value
	 * 
	 * @throws InvalidKeyException
	 *             NoSuchAlgorithmException InvalidKeySpecException
	 *             NoSuchPaddingException InvalidAlgorithmParameterException
	 *             IllegalBlockSizeException BadPaddingException
	 */
	public static String decrypt(String hexStr, Charset charset, String sKey) throws Exception {
		byte[] src = parseHexStrToByte(hexStr);
		byte[] buf = decrypt(src, sKey);
		return new String(buf, charset);
	}

	/**
	 * Encrypt the input byte array.
	 *
	 * @param byte[]
	 * @param String
	 *            secureKey
	 * 
	 * @return encrypted byte array
	 */
	private static byte[] encrypt(byte[] data, String sKey) {
		try {
			byte[] key = sKey.getBytes();
			IvParameterSpec iv = new IvParameterSpec(key);
			DESKeySpec desKey = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
			return cipher.doFinal(data);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Decrypt the input byte array.
	 *
	 * @param byte[]
	 * @param String
	 *            secureKey
	 * 
	 * @return encrypted byte array
	 */
	private static byte[] decrypt(byte[] src, String sKey) throws Exception {
		byte[] key = sKey.getBytes();
		IvParameterSpec iv = new IvParameterSpec(key);
		DESKeySpec desKey = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(desKey);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
		return cipher.doFinal(src);
	}

	/**
	 * Parse byte array to Hex string.
	 *
	 * @param byte[]
	 * 
	 * @return string
	 */
	private static String parseByteToHexStr(byte buf[]) {
		if (Objects.isNull(buf)) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * Parse Hex string to byte array.
	 *
	 * @param string
	 * 
	 * @return byte[]
	 */
	private static byte[] parseHexStrToByte(String hexStr) {
		if (Objects.isNull(hexStr)) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	

}
