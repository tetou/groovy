package tetou.groovy.encode

public class Util{
	public static String getEncode(String str) throws Exception{
		def encName = ["EUC_JP","UTF8","MS932","Windows-31J","SJIS"]
		for (enc in encName) {
			if (new String(str.getBytes(enc),enc) == str) return enc
		}
		"UNKNOWN" // 上記に当てはまらない場合は未知
	}
	
	/**
	 * Unicode文字列に変換する("あ" -> "\u3042")
	 * @param original
	 * @return
	 */
	public static String convertToUnicode(String original)
	{
		StringBuilder sb = new StringBuilder()
		for (int i = 0; i < original.length(); i++) {
			sb.append(String.format("\\u%04X", Character.codePointAt(original, i)))
		}
		sb.toString()
	}
}