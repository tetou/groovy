package tetou.groovy.encode

public class Util{
	public static String getEncode(String str) throws Exception{
		def encName = ["EUC_JP","UTF8","MS932","Windows-31J","SJIS"]
		for (enc in encName) {
			if (new String(str.getBytes(enc),enc) == str) return enc
		}
		"UNKNOWN" // ã‹L‚É“–‚Ä‚Í‚Ü‚ç‚È‚¢ê‡‚Í–¢’m
	}
	
	/**
	 * Unicode•¶Žš—ñ‚É•ÏŠ·‚·‚é("‚ " -> "\u3042")
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