package whiteblackchet;

interface AbstractRule {
	public Boolean isMove();

	public Boolean isEat();

	public Boolean isVictory();
}

// 規則
public class Rule implements AbstractRule {
	private Chess[][] allChess;
	private Player p1, p2;
	private static Rule singleRule = null;// singleton

	static public Rule instance(Chess[][] allChess, Player p1, Player p2) {
		if (singleRule == null) {
			singleRule = new Rule(allChess, p1, p2);
		}
		return singleRule;
	}

	private Rule(Chess[][] allChess, Player p1, Player p2) {
		this.allChess = allChess;
		this.p1 = p1;
		this.p2 = p2;
	}

	public Boolean isMove() {

		return false;

	}

	public Boolean isUp(Chess a) {
		int ready = 0;
		int x = a.getCoordinate().getX();
		int y = a.getCoordinate().getY();
		
		
		for (int i = y + 1; i < allChess[x].length; i++) {// 上
			if (allChess[x][i].getState() != 0 && allChess[x][i].getState() != a.getState() && i == y + 1) {
				ready = 1;
			} else if (allChess[x][i].getState() == a.getState() && ready == 1 && i != y + 1) {
				return true;
			} else if (allChess[x][i].getState() != 0 && allChess[x][i].getState() != a.getState() && ready == 1) {
				ready = 1;
			}

		}
		
		return false;
	}
	public Boolean isDown(Chess a) {
		int ready = 0;
		int x = a.getCoordinate().getX();
		int y = a.getCoordinate().getY();
		for (int i = y - 1; i >= 0; i--) {// 下
			if (allChess[x][i].getState() != 0 && allChess[x][i].getState() != a.getState() && i == y - 1) {
				ready = 1;
			} else if (allChess[x][i].getState() == a.getState() && ready == 1 && i != y - 1) {
				return true;
			} else if (allChess[x][i].getState() != 0 && allChess[x][i].getState() != a.getState() && ready == 1) {
				ready = 1;
			}

		}
		
		return false;
	}
	public Boolean isLeft(Chess a) {
		int ready = 0;
		int x = a.getCoordinate().getX();
		int y = a.getCoordinate().getY();
		for (int i = x - 1; i >= 0; i--) {// 左
			if (allChess[i][y].getState() != 0 && allChess[i][y].getState() != a.getState() && i == x - 1) {
				ready = 1;
			} else if (allChess[i][y].getState() == a.getState() && ready == 1 && i != x - 1) {
				return true;
			} else if (allChess[i][y].getState() != 0 && allChess[i][y].getState() != a.getState() && ready == 1) {
				ready = 1;
			}
		}
		
		return false;
	}
	public Boolean isRight(Chess a) {
		int ready = 0;
		int x = a.getCoordinate().getX();
		int y = a.getCoordinate().getY();
		for (int i = x + 1; i < allChess.length; i++) {// 右
			if (allChess[i][y].getState() != 0 && allChess[i][y].getState() != a.getState() && i == x + 1) {
				ready = 1;
			} else if (allChess[i][y].getState() == a.getState() && ready == 1 && i != x + 1) {
				return true;
			} else if (allChess[i][y].getState() != 0 && allChess[i][y].getState() != a.getState() && ready == 1) {
				ready = 1;
			}

		}
		
		return false;
	}
	public Boolean isRU(Chess a) {
		int ready = 0;
		int x = a.getCoordinate().getX();
		int y = a.getCoordinate().getY();
		for (int i = 1; i+x <8 && i+y<8; i++) {// 右上
			int c = x + i, d = y + i;

			if (allChess[c][d].getState() != 0 && allChess[c][d].getState() != a.getState() && i == 1) {
				ready = 1;
			} else if (allChess[c][d].getState() == a.getState() && ready == 1 && i != 1) {
				return true;
			} else if (allChess[c][d].getState() != 0 && allChess[c][d].getState() != a.getState() && ready == 1) {
				ready = 1;
			}

		}
		
		return false;
	}
	public Boolean isRD(Chess a) {
		int ready = 0;
		int x = a.getCoordinate().getX();
		int y = a.getCoordinate().getY();
		for (int i = 1; i+x <8 && y-i>=0; i++) {// 右下
			int c = x + i, d = y - i;

			if (allChess[c][d].getState() != 0 && allChess[c][d].getState() != a.getState() && i == 1) {
				ready = 1;
			} else if (allChess[c][d].getState() == a.getState() && ready == 1 && i != 1) {
				return true;
			} else if (allChess[c][d].getState() != 0 && allChess[c][d].getState() != a.getState() && ready == 1) {
				ready = 1;
			}

		}
		
		return false;
	}
	public Boolean isLU(Chess a) {
		int ready = 0;
		int x = a.getCoordinate().getX();
		int y = a.getCoordinate().getY();
		for (int i = 1;x-i >=0 && i+y<8; i++) {// 左上
			int c = x - i, d = y + i;

			if (allChess[c][d].getState() != 0 && allChess[c][d].getState() != a.getState() && i == 1) {
				ready = 1;
			} else if (allChess[c][d].getState() == a.getState() && ready == 1 && i != 1) {
				return true;
			} else if (allChess[c][d].getState() != 0 && allChess[c][d].getState() != a.getState() && ready == 1) {
				ready = 1;
			}

		}
		
		return false;
	}
	public Boolean isLD(Chess a) {
		int ready = 0;
		int x = a.getCoordinate().getX();
		int y = a.getCoordinate().getY();
		for (int i = 1; x-i >=0 && y-i>=0; i++) {// 左下
			int c = x - i, d = y - i;

			if (allChess[c][d].getState() != 0 && allChess[c][d].getState() != a.getState() && i == 1) {
				ready = 1;
			} else if (allChess[c][d].getState() == a.getState() && ready == 1 && i != 1) {
				return true;
			} else if (allChess[c][d].getState() != 0 && allChess[c][d].getState() != a.getState() && ready == 1) {
				ready = 1;
			}

		}
		return false;
	}
	
	public Boolean isVictory() {// 找尋是否一方有存活棋子
		int p1Chess=0, p2Chess=0,noChess=0;
		for (int i = 0; i < allChess.length; i++) {
			for (int j = 0; j < allChess[i].length; j++) {
				if(allChess[i][j].getState()==1){
					p1Chess++;
				}
				if(allChess[i][j].getState()==2){
					p2Chess++;
				}
				if(allChess[i][j].getState()==0){
					noChess++;
				}
			}
		}
		if (p1Chess == 0 || p2Chess == 0||noChess==0) {
			return true;

		} else {
			return false;
		}

	}

	@Override
	public Boolean isEat() {
		// TODO Auto-generated method stub
		return null;
	}

}
