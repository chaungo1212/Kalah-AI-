
public class House {
	public int nseed;
	public int type; // 0>: normal house(same as index in vector), -1: store1, -2:store2
	public House(){
		nseed = 0;
		type = 0;
	}
	public House(int seed){ // Set type outside the constructor
		nseed = seed;
	}
	public House(House h){
		nseed = h.nseed;
		type = h.type;
	}
}
