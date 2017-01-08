package whiteblackchet;

import java.io.File;

interface GameFactory {

	public Player[] creatPlayer();

	public Chess[][] creatChess(Player[] p);

	public Rule creatRule(Chess[][] allChess, Player[] p);

	public ChessBoard creatChessBoard(Game g);

}

class ChetGame implements GameFactory {

	@Override
	public Chess[][] creatChess(Player[] p) {
		Chess[][] allChess = new Chess[8][8];// 新增棋子

		for (int i = 0; i < allChess.length; i++) {
			for (int j = 0; j < allChess[i].length; j++) {
				File picture = new File("D:/編輯程式/eclipse/workspace/whiteblackchet/src/whiteblackchet/picture/桌面.jpg");
				allChess[i][j] = new Chess(picture, 0, p[0]);
			}
		}

		return allChess;

	}

	@Override
	public Player[] creatPlayer() {
		// TODO Auto-generated method stub
		Player[] p = new Player[2];
		p[0] = new Player("玩家-黑色",1);
		p[1] = new Player("玩家-白色",2);
		return p;

	}

	@Override
	public Rule creatRule(Chess[][] allChess, Player[] p) {
		Rule rule = Rule.instance(allChess, p[0], p[1]);
		return rule;
	}

	@Override
	public ChessBoard creatChessBoard(Game g) {
		// TODO Auto-generated method stub
		ChessBoard chessBoard = ChessBoard.instance(g);
		return null;
	}

}