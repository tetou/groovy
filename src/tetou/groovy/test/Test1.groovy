package tetou.groovy.test

class Test1 {
	def id
	def name
	Test1(thing){
		this.id=thing.id
		this.name=thing.name
	}
	def getMyName(){
		print id + "�̖��O�́A" + name + "�ł��I"
	}
}