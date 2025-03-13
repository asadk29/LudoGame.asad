package LudoGame.ak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class LudoGame {
public static void main(String[] args) {
	JFrame frame = new JFrame("Ludo Game");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setBackground(new Color(219,169,209));
	frame.setResizable(false);
	GamePlay gp = new GamePlay();
	gp.setFocusable(true);
	gp.addKeyListener(gp);
	gp.addMouseListener(gp);
	frame.add(gp);
	frame.setBounds(10,10,1000,600);
	frame.setVisible(true);
	
}
}

class GamePlay extends JPanel implements KeyListener, MouseListener{
	boardLayout bl;
	makePlayers mp;
	int dice;
	int player = 0;
	int flag = 0;
	int roll = 0;
	int gameOver = 0;
	
	public GamePlay() {
		setFocusTraversalKeysEnabled(false);
		requestFocus();
		bl = new boardLayout(80,50);
		mp = new makePlayers(30,30);
		
	}
	public void paint(Graphics g) {
		bl.draw((Graphics2D)g);
		mp.draw((Graphics2D) g);
		
		if(mp.pl[player].coin == 4) {
			g.setColor(Color.MAGENTA);
			g.fillRect(0,230,1000,100);
			g.setColor(Color.BLACK);
			g.setFont(new Font("algerian",Font.BOLD + Font.ITALIC,55));
			g.drawString("Congratulations!!",210,280);
			g.setFont(new Font("algerian",Font.BOLD + Font.ITALIC,30));
			g.drawString("Player '"+player+"' made it home!!",280,320);
			gameOver = 1;
			
		}
		else if(dice!=0) {
			
			g.setColor(Color.ORANGE);
			g.fillRect(680,180,180,250);
			g.setColor(Color.BLACK);
			g.drawRect(700,250,140,140);
			g.setFont(new Font("algerian", Font.BOLD, 100));
			g.drawString(""+dice, 740, 350);
			
			g.setFont(new Font("arial", Font.BOLD, 26));
			if(player == 0) {
				g.setColor(Color.RED);
				g.drawString("Red's Turn:",697,220);
			}else if(player == 1) {
				g.setColor(Color.GREEN);
				g.drawString("Green's Turn:",685,220);
			}else if(player == 2){
				g.setColor(Color.YELLOW);
				g.drawString("Yellow's Turn:",682,220);
			}else {
				g.setColor(Color.BLUE);
				g.drawString("Blue's Turn:",697, 220);
						
			}
			if(flag == 0 && dice!=6) {  //player = (player + 1) % 4; this can also be used
				if(player==3) {
					player = 0;
				}
				else {
				player = player + 1;
			}
			}
			
		}
		
		
	}
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER && flag == 0 && gameOver == 0) {
			Random r = new Random();
			dice = r.nextInt(6) + 1;
            repaint();
            
           for(int i = 0; i<4; i++) {
			if(mp.pl[player].pa[i].current==-1 && dice == 6) {
				flag = 1;
				break;
			}
			if(mp.pl[player].pa[i].current+dice<=56 && mp.pl[player].pa[i].current!=-1) {
				flag = 1;
				break;
			}
			}
			
		
		}
		}
	
	public void mouseClicked(MouseEvent e) {
		if(flag == 1) {
			int x = e.getX();
			int y = e.getY();
			x = x - 80;
			y = y - 50;
			x = x/30;
			y = y/30;
			int move = 0;
			int value = 0;
			
			
	    if(dice==6) {
			for(int i = 0; i<4; i++) {
					
				if(mp.pl[player].pa[i].x == x && mp.pl[player].pa[i].y == y && mp.pl[player].pa[i].current != -1 && mp.pl[player].pa[i].current+dice<=56) {
					mp.pl[player].pa[i].current += dice;
					if(mp.pl[player].pa[i].current == 56) {
						mp.pl[player].coin++;
					}
					flag = 0;
					move = 1;
					value = i;
					break;
					}
				
					}
			if(move == 0) {	
		    for(int i = 0; i<4; i++) {
			if(mp.pl[player].pa[i].current == -1) {
				mp.pl[player].pa[i].current = 0;
			    flag = 0;
			    value = i;
			    break;
		    }
	     }
		    
      }     int k = 0;
            if(mp.pl[player].pa[value].current % 13 != 0 && mp.pl[player].pa[value].current % 13 != 8 && mp.pl[player].pa[value].current <51) {
			for(int i = 0; i<4; i++) {
				if(player == i) {
					continue;
				}
				
				for(int j = 0; j<4; j++) {
					int temp1 = Board.xC[player][mp.pl[player].pa[value].current];
					int temp2 = Board.yC[player][mp.pl[player].pa[value].current];
					
					if(mp.pl[i].pa[j].x == temp1 && mp.pl[i].pa[j].y == temp2) {
						mp.pl[i].pa[j].current = -1;
						k = 1;
						break;
					}
				
			}if(k == 1) {
				break;
			}
			}
	    }
	}
			else {
				for(int i = 0; i<4; i++) {
					if(mp.pl[player].pa[i].x == x && mp.pl[player].pa[i].y == y && mp.pl[player].pa[i].current != -1 && mp.pl[player].pa[i].current+dice<=56 ) {
						mp.pl[player].pa[i].current += dice;
						if(mp.pl[player].pa[i].current == 56) {
							mp.pl[player].coin++;
						}
						flag = 0;
						value = i;
						break;
					}
				}
				int k = 0;
	            if(mp.pl[player].pa[value].current % 13 != 0 && mp.pl[player].pa[value].current % 13 != 8 && mp.pl[player].pa[value].current <51) {
				for(int i = 0; i<4; i++) {
					if(player == i) {
						continue;
					}
					
					for(int j = 0; j<4; j++) {
						int temp1 = Board.xC[player][mp.pl[player].pa[value].current];
						int temp2 = Board.yC[player][mp.pl[player].pa[value].current];
						
						if(mp.pl[i].pa[j].x == temp1 && mp.pl[i].pa[j].y == temp2) {
							mp.pl[i].pa[j].current = -1;
							k = 1;
							break;
						}
					
				}if(k == 1) {
					break;
				}
				}
		    }
				
				
			}repaint();
	}
			
}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
class Board{
	static int[][] xC = { 
			{1,2,3,4,5,6,6,6,6,6,6,7,8,8,8,8,8,8,9,10,11,12,13,14,14,14,13,12,11,10,9,8,8,8,8,8,8,7,6,6,6,6,6,6,5,4,3,2,1,0,0,1,2,3,4,5,6},
			{8,8,8,8,8,9,10,11,12,13,14,14,14,13,12,11,10,9,8,8,8,8,8,8,7,6,6,6,6,6,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,6,6,6,6,6,7,7,7,7,7,7,7},
			{13,12,11,10,9,8,8,8,8,8,8,7,6,6,6,6,6,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,6,6,6,6,6,7,8,8,8,8,8,8,9,10,11,12,13,14,14,13,12,11,10,9,8},
			{6,6,6,6,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,6,6,6,6,6,7,8,8,8,8,8,8,9,10,11,12,13,14,14,14,13,12,11,10,9,8,8,8,8,8,8,7,7,7,7,7,7,7}
	};
	static int[][] yC = { 
			{6,6,6,6,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,6,6,6,6,6,7,8,8,8,8,8,8,9,10,11,12,13,14,14,14,13,12,11,10,9,8,8,8,8,8,8,7,7,7,7,7,7,7},
			{1,2,3,4,5,6,6,6,6,6,6,7,8,8,8,8,8,8,9,10,11,12,13,14,14,14,13,12,11,10,9,8,8,8,8,8,8,7,6,6,6,6,6,6,5,4,3,2,1,0,0,1,2,3,4,5,6},
			{8,8,8,8,8,9,10,11,12,13,14,14,14,13,12,11,10,9,8,8,8,8,8,8,7,6,6,6,6,6,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,6,6,6,6,6,7,7,7,7,7,7,7},
			{13,12,11,10,9,8,8,8,8,8,8,7,6,6,6,6,6,6,5,4,3,2,1,0,0,0,1,2,3,4,5,6,6,6,6,6,6,7,8,8,8,8,8,8,9,10,11,12,13,14,14,13,12,11,10,9,8}
	};
}
	

class boardLayout{
	int x,y;
	int height = 30;
	int width = 30;
	
	public boardLayout(int x, int y) {
	  this.x = x;
	  this.y = y;
	}
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(80, 50, width*15, height*15);
		
		for(int j = 0; j<6; j++) {
			g.setColor(Color.RED);
			g.fillRect(x, y+(30*j),width,height);
			g.fillRect(x+(30*j), y, width, height);
			g.fillRect(x+(30*j), y+30*5, width, height);
			g.fillRect(x+(30*5), y+(30*j), width, height);
			
			g.setColor(Color.GREEN);
			g.fillRect(x+(30*9), y+(30*j),width,height);
			g.fillRect(x+(30*9)+(30*j), y+(30*5), width, height);
			g.fillRect(x+(30*14), y+(30*j), width, height);
			g.fillRect(x+(30*9)+(j*30), y, width, height);
			
			g.setColor(Color.BLUE);
			g.fillRect(x, y+(30*9)+(30*j),width,height);
			g.fillRect(x+(30*5), y+(30*9)+(30*j), width, height);
			g.fillRect(x+(30*j), y+30*9, width, height);
			g.fillRect(x+(30*j), y+(30*14), width, height);
			
			g.setColor(Color.YELLOW);
			g.fillRect(x+(30*9)+(30*j), y+(30*9),width,height);
			g.fillRect(x+(30*9)+(30*j), y+(30*14), width, height);
			g.fillRect(x+(30*9), y+(30*9)+(30*j), width, height);
			g.fillRect(x+(30*14), y+(30*9)+(30*j), width, height);
			}
		   
		   g.setColor(Color.RED);
		   g.fillRect(x+30, y+(30*6), width, height);
		   g.setColor(Color.GREEN);
		   g.fillRect(x+(30*8), y+30, width, height);
		   g.setColor(Color.BLUE);
		   g.fillRect(x+(30*6), y+(30*13), width, height);
		   g.setColor(Color.YELLOW);
		   g.fillRect(x+(30*13), y+(30*8), width, height);
		   
		   for(int i = 1; i<6; i++) {
			   g.setColor(Color.RED);
			   g.fillRect(x+(i*30),y+(30*7), width, height);
			   g.setColor(Color.GREEN);
			   g.fillRect(x+(30*7),y+(i*30), width, height);
			   g.setColor(Color.BLUE);
			   g.fillRect(x+(30*7),y+(30*8)+(i*30), width, height);
			   g.setColor(Color.YELLOW);
			   g.fillRect(x+(30*8)+(i*30),y+(30*7), width, height);
			 }
		   int t1 = x + 45;
		   int t2 = y + 45;
		   for(int i = 0; i<2; i++) {
			   for(int j = 0; j<2; j++) {
				   g.setColor(Color.RED);
				   g.fillRect(t1+(60*j),t2+(60*i), width, height);
				   g.setColor(Color.GREEN);
				   g.fillRect(t1+(60*j)+(30*9),t2+(60*i), width, height);
				   g.setColor(Color.BLUE);
				   g.fillRect(t1+(60*j),t2+(60*i)+(30*9), width, height);			
				   g.setColor(Color.YELLOW);
				   g.fillRect(t1+(60*j)+(30*9),t2+(60*i)+(30*9), width, height);
			   }
		   }
		   
		   g.setColor(Color.RED);
		   int[] xP = new int[]{x+30*6, x+30*6, x+30*6+45};
		   int[] yP = new int[]{y+30*6, y+30*9, y+30*6+45};
		   g.fillPolygon(xP, yP, 3);
		   
		   g.setColor(Color.GREEN);
		   xP = new int[]{x+30*6, x+30*9, x+30*6+45};
		   yP = new int[]{y+30*6, y+30*6, y+30*6+45};
		   g.fillPolygon(xP, yP, 3);
		   
		   g.setColor(Color.BLUE);
		   xP = new int[]{x+30*6, x+30*9, x+30*6+45};
		   yP = new int[]{y+30*9, y+30*9, y+30*6+45};
		   g.fillPolygon(xP, yP, 3);
		   
		   g.setColor(Color.YELLOW);
		   xP = new int[]{x+30*9, x+30*9, x+30*6+45};
		   yP = new int[]{y+30*6, y+30*9, y+30*6+45};
		   g.fillPolygon(xP, yP, 3);
		   
		   g.setColor(Color.BLACK);
		   g.setStroke(new BasicStroke(2));
		   
		   g.drawRect(x,y,width*15, height*15);
		   
		   g.drawRect(x+30,y+30,width*4,height*4);
		   g.drawRect(x+(30*10),y+30,width*4,height*4);
		   g.drawRect(x+30,y+(30*10),width*4,height*4);
		   g.drawRect(x+(30*10),y+(30*10),width*4,height*4);
		   
		   for(int i = 0; i<3; i++) {
			   for(int j = 0; j<6; j++) {
				   g.drawRect(x+(j*30),y+(30*6)+(30*i), width, height);
				   g.drawRect(x+(30*6)+(i*30),y+(j*30), width, height);
				   g.drawRect(x+(30*9)+(j*30),y+(30*6)+(30*i), width, height);
				   g.drawRect(x+(30*6)+(i*30),y+(j*30)+(30*9), width, height);
				   
			   }
		   }
		   for(int i = 0; i<2; i++) {
			   for(int j = 0; j<2; j++) {
				   g.drawRect(t1+(60*j),t2+(60*i), width, height);
				   g.drawRect(t1+(60*j)+(30*9),t2+(60*i), width, height);
				   g.drawRect(t1+(60*j),t2+(60*i)+(30*9), width, height);			
				   g.drawRect(t1+(60*j)+(30*9),t2+(60*i)+(30*9), width, height);
			   }
		   }
		   
		   xP = new int[]{x+30*6, x+30*6, x+30*6+45};
		   yP = new int[]{y+30*6, y+30*9, y+30*6+45};
		   g.drawPolygon(xP, yP, 3);
		   
		   xP = new int[]{x+30*6, x+30*9, x+30*6+45};
		   yP = new int[]{y+30*6, y+30*6, y+30*6+45};
		   g.drawPolygon(xP, yP, 3);
		   
		   xP = new int[]{x+30*6, x+30*9, x+30*6+45};
		   yP = new int[]{y+30*9, y+30*9, y+30*6+45};
		   g.drawPolygon(xP, yP, 3);
		   
		   xP = new int[]{x+30*9, x+30*9, x+30*6+45};
		   yP = new int[]{y+30*6, y+30*9, y+30*6+45};
		   g.drawPolygon(xP, yP, 3);
		   
		   g.drawOval(x+(30*6)+5,y+(30*2)+5, width - 10, height - 10);
		   g.drawOval(x+(30*12)+5,y+(30*6)+5, width - 10, height - 10);
		   g.drawOval(x+(30*2)+5,y+(30*8)+5, width - 10, height - 10);
		   g.drawOval(x+(30*8)+5,y+(30*12)+5, width - 10, height - 10);
		   
		   g.setFont(new Font("algerian", Font.BOLD+Font.ITALIC, 50));
		   g.drawString("Red",90,42);
		   g.drawString("Green", 360, 42);
		   g.drawString("Blue", 90, 543);
		   g.drawString("Yellow", 325, 543);
		   
		   g.setFont(new Font("arial", Font.BOLD+Font.ITALIC, 30));
		   g.drawString("Press Enter to Roll", 620,80);
		   
	}
	
}
class makePlayers{
	int width, height;
	Player[] pl = new Player[4];
	//Initial coordinates of pawns
	int[][] xC = {
			{1,1,3,3},
			{10,10,12,12},
			{10,10,12,12},
			{1,1,3,3}
	             };
	int[][] yC = {
			{1,3,1,3},
			{1,3,1,3},
			{10,12,10,12},
			{10,12,10,12}
			     };
			     
	public makePlayers(int height, int width) {
		this.width = width;
		this.height = height;
		for(int i = 0; i<4; i++) {
			pl[i] = new Player();
		}
	}
	public void draw(Graphics2D g) {
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<4; j++) {
				pl[i].pa[j].draw(g, xC[i][j], yC[i][j], i);
			}
		}
	}
	
}
class Player{
	Pawn[] pa = new Pawn[4];
	int coin = 0;
	
	public Player() {
		for(int i = 0; i<4; i++) {
			pa[i] = new Pawn();
		}
	}
}
class Pawn{
	int current = -1;
	int height = 30;
	int width = 30;
	int xO = 80;
	int yO = 50;
	int x;
	int y;
	public void draw(Graphics2D g, int xi, int yi, int playerNo) {
		this.x = xi;
		this.y = yi;
		if(current == -1) {
		if(playerNo == 0) {
			g.setColor(Color.RED);
		}else if(playerNo == 1) {
			g.setColor(Color.GREEN);
		}else if(playerNo == 2) {
			g.setColor(Color.YELLOW);
		}else {
			g.setColor(Color.BLUE);
		}
		 
		g.fillOval(xO+(30*x+15+5), yO+(30*y+15+5), width - 10, height - 10);
		g.setColor(Color.BLACK);
	    g.setStroke(new BasicStroke(2));
	    g.drawOval(xO+(30*x+15+5), yO+(30*y+15+5), width - 10, height - 10);
	
		}
		else {
			x= Board.xC[playerNo][current];
			y = Board.yC[playerNo][current];
			if(playerNo == 0) {
				g.setColor(Color.RED);
			}else if(playerNo == 1) {
				g.setColor(Color.GREEN);
			}else if(playerNo == 2) {
				g.setColor(Color.YELLOW);
			}else {
				g.setColor(Color.BLUE);
			}
			
			g.fillOval(xO+(30*x+5), yO+(30*y+5), width - 10, height - 10);
			g.setColor(Color.BLACK);
		    g.setStroke(new BasicStroke(2));
		    g.drawOval(xO+(30*x+5), yO+(30*y+5), width - 10, height - 10);
			
		}
		
}
}
