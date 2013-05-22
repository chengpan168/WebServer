package com.eden.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.lang.StringUtils;

public class ValidateUtil {

	/**
	 * 将字符转换为bit类型数据
	 * 
	 * @param input
	 * @return
	 */
	public static String stringToBits(String input) {
		String output = "";
		String[] inputTemp = input.split(" ");
		for (int i = 0; i < inputTemp.length; i++) {
			if (inputTemp[i].trim().equals(""))
				continue;
			else {
				String word = inputTemp[i];
				for (int j = 0; j < word.length(); j++) {
					String code = Integer.toBinaryString(word.codePointAt(j));
					code = String.format("%08d", Integer.parseInt(code));
					output += code;
				}
				output += "\n";
			}
		}
		return output;
	}

	/**
	 * 判断字符串中是否包含汉字
	 * @param str
	 * @return
	 */
	private static Pattern chinesePattern = Pattern.compile("[\\u4e00-\\u9fa5]");
	public static boolean containChinese(String str) {
		if (StringUtils.isBlank(str))
			return false;
		Matcher m = chinesePattern.matcher(str);
		while (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断某个字符是否是汉字
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isChinese(char a) {
		int x = (int) a;
		return (x >= 19968 && x <= 171941);
	}

	/**
	 * 获取字符串长度(包含字母，数字，汉字)
	 * 一个中文字符占2个字节
	 * @param str
	 * @return
	 */
	public static int getStrCount(String str) {
		if (StringUtils.isBlank(str))
			return 0;
		final int n = 2; 
		int totalCount = 0;
		for (int i = 0; i < str.length(); i++) {
			int c = str.charAt(i);
			if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
				totalCount++;
			} else {
				totalCount += n;
			}
		}
		return totalCount;
	}

	/**
	 * 判断字符串el中是否包含s2字符串
	 * @param isUpper
	 *            是否区分大小写:true区分，false不区分
	 * @param el
	 * @param s2
	 * @return
	 */
	public static boolean isSub(String el, String s2, boolean isUpper) {
		if (StringUtils.isBlank(el) || StringUtils.isBlank(s2)) {
			return false;
		}
		if (!isUpper) {
			el = el.toUpperCase();
			s2 = s2.toUpperCase();
		}
		if (el.indexOf(s2) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串是否为纯数字
	 */
	public static boolean isNumber(String str) {
		if (StringUtils.isBlank(str))
			return false;
		for (int j = 0; j < str.length(); j++) {
			if (!(str.charAt(j) >= 48 && str.charAt(j) <= 57)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否是数字 且在0-255
	 */
	public static boolean isIn255(String text) {
		if (StringUtils.isBlank(text)) {
			return false;
		}
		final String NUMBER_255 = "^0{0,17}(([0-9])|([0-9][0-9])|(2[0-4][0-9])|(25[0-5])|([0-1]\\d{2}))$";
		boolean bool = false;
		if (text != null && !"".equals(text)) {
			Pattern p = Pattern.compile(NUMBER_255); // 正则表达式
			Matcher m = p.matcher(text); // 操作的字符串
			bool = m.matches();
		}
		return bool;
	}

	/**
	 * 判断参数是否是Integer
	 */
	public static Boolean isInteger(Object obj) {
		if(obj == null ) return false ;
		if (StringUtils.isBlank(obj.toString())) {
			return false;
		}
		try {
			Integer.parseInt(obj.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断参数是否是Double
	 */
	public static Boolean isDouble(String s) {
		if (StringUtils.isBlank(s)) {
			return false;
		}
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断参数是否是Float
	 */
	public static Boolean isFloat(String s) {
		if (StringUtils.isBlank(s)) {
			return false;
		}
		try {
			Float.parseFloat(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断参数是否是Byte
	 */
	public static Boolean isByte(String s) {
		if (StringUtils.isBlank(s)) {
			return false;
		}
		try {
			Byte.parseByte(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断参数是否是Short
	 */
	public static Boolean isShort(String s) {
		if (StringUtils.isBlank(s)) {
			return false;
		}
		try {
			Short.parseShort(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断参数是否是Long
	 */
	public static Boolean isLong(String s) {
		if (StringUtils.isBlank(s)) {
			return false;
		}
		try {
			Long.parseLong(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断一个字符串是否同字母和数字组成
	 *  isNumChar(null) = false ; 
	 *  isNumChar("") = false ;
	 *  isNumChar(" ") = false ;
	 * @param sequence
	 * @return
	 */
	private static Pattern  numCharPattern = Pattern.compile("[\\w|\\d]+");
	public static boolean isNumChar(CharSequence sequence) {
		if (sequence == null)
			return false;
		Matcher matcher = numCharPattern.matcher(sequence);
		return matcher.matches();
	}

	public static String removeStartZero(String input) {
		if (StringUtils.isBlank(input))
			return null;
		return input.replaceAll("^0*", "");
	}

	public static final Pattern emailPattern = Pattern
			.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
	public static final Pattern numberPattern = Pattern.compile("[0-9]*");

	public static final boolean isEmail(String email) {
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
	}

	public static final Pattern phonePattern = Pattern
	.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
	public static final boolean isPhone(String phone) {
		if (phone == null || phone.equals("")) {
			return false;
		}
		Matcher matcher = numberPattern.matcher(phone);
		if (!matcher.matches()) {
			return false;
		}
		if (phone.trim().length() == 11 && phone.startsWith("1")) {
			return true;
		}
		return false;
	}


	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return
	 */
	public static String FormetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 判断是否是图片文件
	 * 
	 * @param o
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isImageFile(File f) {
		if (f.exists()) {
			try {
				ImageInputStream iis = ImageIO.createImageInputStream(f);
				Iterator iter = ImageIO.getImageReaders(iis);
				if (!iter.hasNext()) {
					return false;
				}
				ImageReader reader = (ImageReader) iter.next();
				iis.close();
				String suf = ("" + reader.getFormatName()).toLowerCase();

				String[] sufs = { "jpg", "gif", "bmp", "png", "jpeg" };
				for (String s : sufs) {
					if (s.equals(suf))
						return true;
				}
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
		return false;
	}

	static Pattern pattern = Pattern.compile("^[1-9]\\d{0,3}(\\.\\d{1,8})?$") ;
	public static boolean isLnglat(String lnglat){
		return pattern.matcher(lnglat).matches() ;
	}
}
