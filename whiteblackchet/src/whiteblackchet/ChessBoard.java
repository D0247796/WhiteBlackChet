package whiteblackchet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ChessBoard implements java.util.Observer {
	private JFrame jFrame;
	private MyButton[][] MyButton;
	private Game game;
	private Chess moveChess;// 按下去的按鈕
	private int gameStart = 0;// 是否剛開始
	private static ChessBoard singleChessBoard = null;// singleton

	public static ChessBoard instance(Game game) {
		if (singleChessBoard == null) {
			singleChessBoard = new ChessBoard(game);
		}
		return singleChessBoard;
	}

	private ChessBoard(Game game) {
		// 介面創造
		File source = null;
		Image image = null;
		source = new File("D:/編輯程式/eclipse/workspace/whiteblackchet/src/whiteblackchet/picture/桌面.jpg");
		try {
			image = ImageIO.read(source);
		} catch (IOException e1) {
			System.out.println("圖檔獨入錯誤");
		}
		this.game = game;
		game.addObserver(this);
		jFrame = new JFrame("Board");
		GridLayout gridLayout = new GridLayout(8, 8);
		JPanel jPanel = new JPanel(gridLayout);
		JPanel topPanel = new JPanel(gridLayout);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(800, 900);
		topPanel.setPreferredSize(new Dimension(100, 100));
		topPanel.setBackground(Color.BLUE);
		topPanel.setLayout(new BorderLayout());
		jPanel.setPreferredSize(new Dimension(800, 800));
		jFrame.add(jPanel,BorderLayout.CENTER);
		jFrame.add(topPanel,BorderLayout.NORTH);

		MyButton = new MyButton[8][8];
		Random random = new Random();
		JButton button = new JButton();
		button.setText("沒法走，換人");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("沒步了，換人");
				game.changePlayer();
			}
		});
		button.setFont(new Font(null, 0, 30));
		topPanel.add(button);
		int b = 0;
		for (int i = MyButton.length-1; i >=0; i--) {
			for (int j = 0; j < MyButton[i].length; j++) {
				MyButton[j][i] = new MyButton(new Coordinate(j, i));
				MyButton[j][i].setChess(game.getAllChess()[j][i]);// Button中的Chess
				b++;

				MyButton[j][i].setMargin(new Insets(0, 0, 0, 0));

				MyButton[j][i].setBorder(new LineBorder(Color.BLACK));// 按鈕邊線

				try {
					// Image icon = ImageIO.read(new
					// File("bin/images/RedPawn.png"));// ?我不懂，別人打的
					MyButton[j][i].setIcon(new ImageIcon(image));
				} catch (Exception e) {
					System.out.println("棋子顏色失敗");
				}
				int p = i, q = j;// for迴圈數字會跑掉
				MyButton[j][i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("按下"+"("+q+","+p+")");
						game.chooseButton(MyButton[q][p]);
					
					}
				});
				jPanel.add(MyButton[j][i]);
			}
		}
		MyButton[3][3].getChess().setState(1);
		MyButton[3][3].setIcon(MyButton[3][3].getChess().getPicture());
		MyButton[4][3].getChess().setState(2);
		MyButton[4][3].setIcon(MyButton[4][3].getChess().getPicture());
		MyButton[3][4].getChess().setState(2);
		MyButton[3][4].setIcon(MyButton[3][4].getChess().getPicture());
		MyButton[4][4].getChess().setState(1);
		MyButton[4][4].setIcon(MyButton[4][4].getChess().getPicture());
		jFrame.setVisible(true);
	}

	// public getButtons(){}

	public class MyButton extends JButton {
		private Chess myChess;
		private Coordinate myCoordinate;

		public MyButton(Coordinate myCoordinate) {
			super();
			this.myCoordinate = myCoordinate;
		}

		public void setChess(Chess chess) {
			myChess = chess;
			chess.setCoordinate(myCoordinate);
		}

		public Coordinate getMyCoordinate() {
			return this.myCoordinate;
		}

		public Chess getChess() {
			return this.myChess;
		}
	}

	@Override
	public void update(Observable o, Object arg) {// 刷新介面
		for (int i = 0; i < MyButton.length; i++) {
			for (int j = 0; j < MyButton[i].length; j++) {
				
				MyButton[i][j].setIcon(MyButton[i][j].getChess().getPicture());
				
			}
		}

	}

}
