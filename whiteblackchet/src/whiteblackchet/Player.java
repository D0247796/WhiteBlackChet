package whiteblackchet;

interface AbstractPlayer {
	public void setName(String name);

	public String getName();
}

public class Player implements AbstractPlayer {
	private String name;
	private int state;
	public Player(String name, int s) {
		this.name = name;
		this.state=s;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public int getState() {
		return state;
	}

	public String toString() {
		return name;
	}

}