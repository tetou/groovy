package tetou.groovy.encode

class UtilTest extends GroovyTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testConvertToUnicode() {
		//読込ファイルオブジェクト
		def file
		def outputFile
		
		//テスト用文字列配列(Java内部ではUTF16で符号化されている)
		def expectedLines=["あいうえお","かきくけこ","さしすせそ","たちつてと"]
		
		//EUC_JPで符号化された文字列をUTF16へ変換
		/*
		file = new File("test/tetou/groovy/encode/EUC_JP.txt")
		FileInputStream fis = new FileInputStream(file)
		InputStreamReader isr = new InputStreamReader(fis,"EUC_JP")
		isr.eachLine { line ->
			print Util.convertToUnicode(line) +"\n"
			print line +"\n"
		}
		*/
		
		//http://msugai.fc2web.com/java/IO/charset.html
		//EUC_JPでエンコードされた文字列を一行ずつ読込
		file = new File("test/tetou/groovy/encode/EUC_JP.txt")
		outputFile = new File("test/tetou/groovy/encode/EUC_JP_TMP.txt")
		file.eachLine { line ->
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)))
			pw.println(line)
			pw.close()
			
			FileInputStream fisTmp = new FileInputStream(outputFile)
			InputStreamReader isrTmp = new InputStreamReader(fisTmp,"EUC_JP")
			isrTmp.eachLine { lineTmp ->
				print Util.convertToUnicode(lineTmp) + "\n" 
				assert expectedLines.contains(lineTmp) == true
			}
		}
		if (outputFile.exists()) outputFile.delete()
	}
}
