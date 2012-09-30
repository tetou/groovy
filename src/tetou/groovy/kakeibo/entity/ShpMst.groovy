package tetou.groovy.kakeibo.entity

/**
 * <dl>
 * 	<dt>店舗マスタテーブルEntity</dt>
 * 	<dd>商品を扱う店舗情報を管理するマスタテーブル</dd>
 * </dl>
 * @author tetou
 *
 */
class ShpMst {
	Integer id
	String shpName
	String insId
	Date insYmd
	String updId
	Date updYmd
	String deleted
	
	//ゲッター
	def getId(){
		id
	}
	def getShpname(){
		shpName
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
	
	//セッター
	def setId(id){
		this.id=id
	}
	def setShpname(shpName){
		this.shpName=shpName
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
