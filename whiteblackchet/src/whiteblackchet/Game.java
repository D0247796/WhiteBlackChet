package whiteblackchet;

import java.io.File;
import java.util.Observable;

import whiteblackchet.ChessBoard.MyButton;

public class Game extends java.util.Observable {
	File picture4 = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/桌面.jpg");

	private Chess[][] allChess;
	private MyButton[] chooseButton = new MyButton[2];
	private Player[] p;
	private Player firstPlayer, secondPlayer;
	private Rule rule;
	private int playerNumber = 1;
	private ChessBoard chessBoard;

	public Game(ChetGame cg) {
		this.p = cg.creatPlayer();
		this.allChess = cg.creatChess(p);
		this.rule = cg.creatRule(allChess, p);
		this.firstPlayer = p[0];
		this.secondPlayer = p[1];
		this.chessBoard = cg.creatChessBoard(this);
	}

	public void chooseButton(MyButton b) {
		int a=0;
		if (b.getChess().getState() != 0) {
			System.out.println("請選空白處");
		} else {
			b.getChess().setState(whoPlay().getState());
			System.out.println("判斷U"+rule.isUp(b.getChess()));
			if (rule.isUp(b.getChess())) {
				a=1;
				int x = b.getChess().getCoordinate().getX();
				int y = b.getChess().getCoordinate().getY();
				for (int i = y + 1; i < allChess[x].length; i++) {
					if (allChess[x][i].getState() != b.getChess().getState()) {
						allChess[x][i].setState(b.getChess().getState());
					} else {
						break;
					}
				}
			}
			System.out.println("判斷D"+rule.isDown(b.getChess()));
			if (rule.isDown(b.getChess())) {
				a=1;
				int x = b.getChess().getCoordinate().getX();
				int y = b.getChess().getCoordinate().getY();
				for (int i = y - 1; i >= 0; i--) {
					if (allChess[x][i].getState() != b.getChess().getState()) {
						allChess[x][i].setState(b.getChess().getState());
					} else {
						break;
					}
				}
			}
			System.out.println("判斷L"+rule.isLeft(b.getChess()));
			if (rule.isLeft(b.getChess())) {
				a=1;
				int x = b.getChess().getCoordinate().getX();
				int y = b.getChess().getCoordinate().getY();
				for (int i = x - 1; i >= 0; i--) {
					if (allChess[i][y].getState() != b.getChess().getState()) {
						allChess[i][y].setState(b.getChess().getState());
					} else {
						break;
					}
				}
			}
			System.out.println("判斷R"+rule.isRight(b.getChess()));
			if (rule.isRight(b.getChess())) {
				a=1;
				int x = b.getChess().getCoordinate().getX();
				int y = b.getChess().getCoordinate().getY();
				for (int i = x + 1; i < allChess.length; i++) {
					if (allChess[i][y].getState() != b.getChess().getState()) {
						allChess[i][y].setState(b.getChess().getState());
					} else {
						break;
					}
				}
			}
			if (rule.isRU(b.getChess())) {
				a=1;
				int x = b.getChess().getCoordinate().getX();
				int y = b.getChess().getCoordinate().getY();
				for (int i = 1; i + x < 8 && i + y < 8; i++) {
					int c = x + i, d = y + i;

					if (allChess[c][d].getState() != b.getChess().getState()) {
						allChess[c][d].setState(b.getChess().getState());
					} else {
						break;
					}

				}
			}
			if (rule.isRD(b.getChess())) {
				a=1;
				int x = b.getChess().getCoordinate().getX();
				int y = b.getChess().getCoordinate().getY();
				for (int i = 1; i + x < 8 && y - i >= 0; i++) {
					int c = x + i, d = y - i;
					
					if (allChess[c][d].getState() != b.getChess().getState()) {
						allChess[c][d].setState(b.getChess().getState());
					} else {
						break;
					}

				}
			}
			if (rule.isLU(b.getChess())) {
				a=1;
				int x = b.getChess().getCoordinate().getX();
				int y = b.getChess().getCoordinate().getY();
				for (int i = 1; x - i >= 0 && i + y < 8; i++) {
					int c = x - i, d = y + i;

					if (allChess[c][d].getState() != b.getChess().getState()) {
						allChess[c][d].setState(b.getChess().getState());
					} else {
						break;
					}
				}
			}
			if (rule.isLD(b.getChess())) {
				a=1;
				int x = b.getChess().getCoordinate().getX();
				int y = b.getChess().getCoordinate().getY();
				for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
					int c = x - i, d = y - i;

					if (allChess[c][d].getState() != b.getChess().getState()) {
						allChess[c][d].setState(b.getChess().getState());
					} else {
						break;
					}

				}
			}
			if (a==0) {
				b.getChess().setState(0);
			}else{
				changePlayer();
				setChanged();
				this.notifyObservers("翻棋");
				System.out.println("跑完");
				if(rule.isVictory()){
					int p1Chess=0, p2Chess=0,noChess=0;
					for (int i = 0; i < allChess.length; i++) {
						for (int j = 0; j < allChess[i].length; j++) {
							if(allChess[i][j].getState()==1){
								p1Chess++;
							}
							if(allChess[i][j].getState()==2){
								p2Chess++;
							}
							
						}
					}
					if(p1Chess>p2Chess){
						System.out.println(firstPlayer+"勝利");
					}else if(p1Chess<p2Chess){
						System.out.println(secondPlayer+"勝利");
					}else{
						System.out.println("平手");
					}
				}
			}

		}
	}

	public Player whoPlay() {
		if (playerNumber == 1) {
			return firstPlayer;
		} else {
			return secondPlayer;
		}
	}

	public void changePlayer() {

		if (playerNumber == 1) {
			playerNumber = 2;
			System.out.println("換玩家" + secondPlayer.getName());
		} else {
			playerNumber = 1;
			System.out.println("換玩家" + firstPlayer.getName());
		}
	}

	public Chess[][] getAllChess() {
		return allChess;
	}

	public Rule getRule() {
		return rule;
	}

}
