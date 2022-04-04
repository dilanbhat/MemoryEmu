public class Frame{
	String info;

	public Frame(String info){
		this.info = info;
	}
	/*public Frame(String info, int startWeight){
		this.info = info;
		this.weight = startWeight;
	}*/

	/*public int getWeight(){
		return weight;
	}*/

	public String getInfo(){
		return info;
	}

	public String toString(){
		return this.info;
	}

}