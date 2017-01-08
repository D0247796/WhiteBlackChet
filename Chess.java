package whiteblackchet;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

interface AbstractChet {
	public void setPicture(File picture) ;

	public void setPlayer(Player p);

	public void setWeight(int w);

	public void setState(int s);

	public void setCoordinate(Coordinate c);

	public ImageIcon getPicture();

	public Player getPlayer();

	public int getState();

	public int getWeight();

	public Coordinate getCoordinate();
}

public class Chess implements AbstractChet {
	private File picture;
	private Player player;
	private int weight;
	private int state = 0;
	private Coordinate c;
	File picture1 = new File("D:/編輯程式/eclipse/workspace/whiteblackchet/src/whiteblackchet/picture/黑棋.jpg");
	File picture2 = new File("D:/編輯程式/eclipse/workspace/whiteblackchet/src/whiteblackchet/picture/白棋.jpg");
	public Chess(File picture, int weight, Player player) {
		this.picture = picture;
		this.player = player;
		this.weight = weight;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public void setPlayer(Player p) {
		this.player = p;
	}

	public void setWeight(int w) {
		this.weight = w;
	}

	public void setState(int s) {// 狀態 0:未翻 1:黑 2:白
		this.state = s;
	}

	public void setCoordinate(Coordinate c) {
		this.c = c;

	}

	public ImageIcon getPicture() {
		try {
			if(state==1){
			return new ImageIcon(ImageIO.read(picture1));
			}else if(state==2){
				return new ImageIcon(ImageIO.read(picture2));
			}else{
				return new ImageIcon(ImageIO.read(picture));
			}
		} catch (IOException e) {
			System.out.println("棋子圖片讀取錯誤");
			return null;
		}
	}

	public Player getPlayer() {
		return player;
	}

	public int getWeight() {
		return weight;
	}

	public int getState() {
		return state;
	}

	public Coordinate getCoordinate() {
		return c;
	}

}

