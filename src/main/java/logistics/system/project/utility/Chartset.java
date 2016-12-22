package logistics.system.project.utility;

public class Chartset {
	
	/** 半角标点符号开始位置 */
	private static final int SINGLE_BYTE_SYMBOL_START = 0x0020;

	/** 半角标点符号结束位置 */
	private static final int SINGLE_BYTE_SYMBOL_END = 0x007E;

	/** 半角片假名开始位置 */
	private static final int SINGLE_BYTE_KATAKANA_START = 0xFF61;

	/** 半角片假名结束位置 */
	private static final int SINGLE_BYTE_KATAKANA_END = 0xFF9F;

	/** 半角空格 */
	private static final int SINGLE_BYTE_SPACE_END = 0x0020;

	/**
	 * 
	 * 半角数字判定
	 * 
	 * @return 判定結果 true:半角数字
	 */
	public static boolean isSingleByteDigit(final char c) {

		return ('0' <= c) && (c <= '9');

	}

	/**
	 * 
	 * 半角英字判定
	 * 
	 * @return 判定結果 true:半角英字
	 */
	public static boolean isSingleByteAlpha(final char c) {

		return (('a' <= c) && (c <= 'z')) || (('A' <= c) && (c <= 'Z'));

	}

	/**
	 * 
	 * 半角标点符号判定
	 * 
	 * @return 判定結果 true:半角标点符号
	 */
	public static boolean isSingleByteSymbol(final char c) {

		return (SINGLE_BYTE_SYMBOL_START <= c) &&

		(c <= SINGLE_BYTE_SYMBOL_END) &&

		!isSingleByteAlpha(c) &&

		!isSingleByteDigit(c);

	}

	/**
	 * 
	 * 半角片假名判定
	 * 
	 * @return 判定結果 true:半角片假名
	 */
	public static boolean isSingleByteKatakana(final char c) {

		return (SINGLE_BYTE_KATAKANA_START <= c)
				&& (c <= SINGLE_BYTE_KATAKANA_END);

	}

	/**
	 * 
	 * 半角空格判定
	 * 
	 * @return 判定結果 true:半角空格
	 */
	public static boolean isSingleByteSpace(final char c) {
		boolean bRet = false;
		if (c == SINGLE_BYTE_SPACE_END) {
			bRet = true;
		}
		return bRet;
	}
	
	public static boolean isSingleByte(final char c) {
		return Chartset.isSingleByteDigit(c) ||
				Chartset.isSingleByteSpace(c) ||
				Chartset.isSingleByteSymbol(c) ||
				Chartset.isSingleByteAlpha(c) ||
				Chartset.isSingleByteKatakana(c);
	}
	
	public static boolean isAllSingleByte(final String str) {
		for (int i = str.length() - 1; 0 <= i; i--) {
			if (!Chartset.isSingleByteDigit(str.charAt(i)) &&
			!Chartset.isSingleByteSpace(str.charAt(i)) &&
			!Chartset.isSingleByteSymbol(str.charAt(i)) &&
			!Chartset.isSingleByteAlpha(str.charAt(i)) &&
			!Chartset.isSingleByteKatakana(str.charAt(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	
	/** 全角标点符号开始位置 */
	private static final int DOUBLE_BYTE_SYMBOL_START = 0xFF01;

	/** 全角标点符号结束位置 */
	private static final int DOUBLE_BYTE_SYMBOL_END = 0xFF5E;

	/** 全角片假名开始位置 */
	private static final int DOUBLE_BYTE_KATAKANA_START = 0x30A0;

	/** 全角片假名结束位置 */
	private static final int DOUBLE_BYTE_KATAKANA_END = 0x30FF;

	/** 全角空格 */
	private static final int DOUBLE_BYTE_SPACE_END = 0x3000;

	/**
	 * 
	 * 全角数字判定
	 * 
	 * @return 判定結果 true:全角数字
	 */
	public static boolean isDoubleByteDigit(final char c) {

		return ('０' <= c) && (c <= '９');

	}

	/**
	 * 
	 * 全角英字判定
	 * 
	 * @return 判定結果 true:全角英字
	 */
	public static boolean isDoubleByteAlpha(final char c) {

		return (('ａ' <= c) && (c <= 'ｚ')) || (('Ａ' <= c) && (c <= 'Ｚ'));

	}

	/**
	 * 
	 * 全角标点符号判定
	 * 
	 * @return 判定結果 true:全角标点符号
	 */
	public static boolean isDoubleByteSymbol(final char c) {

		return (DOUBLE_BYTE_SYMBOL_START <= c) &&

		(c <= DOUBLE_BYTE_SYMBOL_END) &&

		!isDoubleByteAlpha(c) &&

		!isDoubleByteDigit(c);

	}

	/**
	 * 
	 * 全角片假名判定
	 * 
	 * @return 判定結果 true:全角片假名
	 */
	public static boolean isDoubleByteKatakana(final char c) {

		return (DOUBLE_BYTE_KATAKANA_START <= c)
				&& (c <= DOUBLE_BYTE_KATAKANA_END);

	}

	/**
	 * 
	 * 全角空格判定
	 * 
	 * @return 判定結果 true:全角空格
	 */
	public static boolean isDoubleByteSpace(final char c) {
		boolean bRet = false;
		if (c == DOUBLE_BYTE_SPACE_END) {
			bRet = true;
		}
		return bRet;
	}
}
