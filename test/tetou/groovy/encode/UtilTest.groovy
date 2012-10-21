package tetou.groovy.encode

class UtilTest extends GroovyTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testConvertToUnicode() {
		//�Ǎ��t�@�C���I�u�W�F�N�g
		def file
		def outputFile
		
		//�e�X�g�p������z��(Java�����ł�UTF16�ŕ���������Ă���)
		def expectedLines=["����������","����������","����������","�����Ă�"]
		
		//EUC_JP�ŕ��������ꂽ�������UTF16�֕ϊ�
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
		//EUC_JP�ŃG���R�[�h���ꂽ���������s���Ǎ�
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
