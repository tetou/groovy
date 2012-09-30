package tetou.groovy.kakeibo.entity

/**
 * <dl>
 * 	<dt>���׃e�[�u��Entity</dt>
 * 	<dd>�w���������i�̏����ꊇ�Ǘ�����e�[�u��</dd>
 * </dl>
 * @author tetou
 *
 */
class DetailedStatement {
	BigDecimal id
	Integer shpId
	String prdctName
	Integer prdctPrice
	String pstYmd
	String insId
	Date insYmd
	String updId
	Date updYmd
	String deleted
	
	//�Q�b�^�[
	def getId(){
		id
	}
	def getShpid(){
		shpId
	}
	def getPrdctname(){
		prdctName
	}
	def getPrdctprice(){
		prdctPrice
	}
	def getPstymd(){
		pstYmd
	}
	def getInsid(){
		insId
	}
	def getInsymd(){
		insYmd
	}
	def getUpdid(){
		updId
	}
	def getUpdymd(){
		updYmd
	}
	def getDeleted(){
		deleted
	}
	
	//�Z�b�^�[
	def setId(id){
		this.id=id
	}
	def setShpid(shpId){
		this.shpId=shpId
	}
	def setPrdctname(prdctName){
		this.prdctName=prdctName
	}
	def setPrdctprice(prdctPrice){
		this.prdctPrice=prdctPrice
	}
	def setPstymd(pstYmd){
		this.pstYmd=pstYmd
	}
	def setInsid(insId){
		this.insId=insId
	}
	def setInsymd(insYmd){
		this.insYmd=insYmd
	}
	def setUpdid(updId){
		this.updId=updId
	}
	def setUpdymd(updYmd){
		this.updYmd=updYmd
	}
	def setDeleted(deleted){
		this.deleted=deleted
	}
}
