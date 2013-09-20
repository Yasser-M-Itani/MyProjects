package PKM;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

public class KeyTest extends Core implements KeyListener {
	public static void main(String[] args){
		new KeyTest().run();
		//classvd.sumdmg.add(damagecalculation.Dmg);
		//classvd.runs();
	}
	
	public static int c = 0;
	private String mess = "";
	private String mess2 = "";
	private String mess3 = "";
	
	public static int keyCd;
	public static int M = 0;
	public static LinkedList <Integer>  damages = new LinkedList <Integer>();
	public static int tempHP = 0;
	public static int sum = 0;
	
	public static int tempKeyCode;
	
	public static Random randGen = new Random();
	public static int randInt = randGen.nextInt(4);
	
	//init also call init from superclass
	public void init(){
		super.init();
		Window w = s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(this);
		//mess = "press escape to exit";
		mess = "Welcome to the Pokemon Game";
		mess2 ="Blue vs Yellow Pkm Battle";
	}
	
	//keyPressed
	public void keyPressed(KeyEvent e){
		int keyCode = e.getKeyCode();
		tempKeyCode = keyCode;
		
		System.out.println("tempkeycode = " + tempKeyCode);
		if (keyCode == KeyEvent.VK_N)
			keyCd = 10;
		
		if (keyCode == KeyEvent.VK_P)
			keyCd = 11;
		if (keyCode == KeyEvent.VK_B)
			keyCd = 12;
		if (keyCode == KeyEvent.VK_C)
			keyCd = 13;
		if (keyCode == KeyEvent.VK_V)
			keyCd = 14;
		if (keyCode == KeyEvent.VK_S)
			keyCd = 15;
		if (keyCode == KeyEvent.VK_G)
			keyCd = 16;
		
		
		if (keyCode == KeyEvent.VK_1)
			keyCd = 1;
		if (keyCode == KeyEvent.VK_2)
			keyCd = 2;
		if (keyCode == KeyEvent.VK_3)
			keyCd = 3;
		if (keyCode == KeyEvent.VK_4)
			keyCd = 4;
		
		if(keyCode == KeyEvent.VK_ESCAPE){
			stop();
		}	
		else{
			mess3 = "Pressed : "+ KeyEvent.getKeyText(keyCode);
			e.consume();
		}
	}
	
	//keyReleased
	public void keyReleased(KeyEvent e){
		int keyCode = e.getKeyCode();
		mess3 = "Released : "+ KeyEvent.getKeyText(keyCode);
		e.consume();
	}
	
	//last method from interface
	public void keyTyped(KeyEvent e){
		e.consume();
	}
	
	/*
	 * new game draw class
	 * update pokemon draw class
	 * update move draw class
	 */
	public synchronized void movedraw(Graphics2D g){
		
		Window w = s.getFullScreenWindow();
		g.setColor(w.getBackground());
		g.fillRect(0, 0, s.getWidth(), s.getHeight());
		g.setColor(w.getForeground());
		g.drawString(mess,30,30);
		g.drawString(mess2, 30, 60);
		g.drawString(mess3,300,50);

		g.drawString("User used "+movesets.Name, 200, 200);
		System.out.println(bluespkm.HP);
	}
	
	public synchronized void updatepkmdraw(Graphics2D g){
		
		Window w = s.getFullScreenWindow();
		g.setColor(w.getBackground());
		g.fillRect(0, 0, s.getWidth(), s.getHeight());
		g.setColor(w.getForeground());
		g.drawString(mess,30,30);
		g.drawString(mess2, 30, 60);
		g.drawString(mess3,300,50);
		
		g.drawString("User Current Pokemon: " + yellowspkm.Name, 100, 100);
		g.drawString("User Move Set:",100,125);
		g.drawString("HP: " + yellowspkm.HP,100,350);
		
		g.drawString("Foe Current Pokemon: " + bluespkm.Name, 500, 100);
		g.drawString("Foe Move Set:",500,125);
		g.drawString("HP: " + bluespkm.HP,500,350);
		
		for (int i = 0; i < 4; i++){
			g.drawString(i+1 + " - " + yellowspkm.MoveSet.get(i), 100, 160 + 40*i);
			g.drawString(bluespkm.MoveSet.get(i), 500, 160 + 40*i);
		}
	}
	
	//draw
	public synchronized void draw(Graphics2D g){
		
		Window w = s.getFullScreenWindow();
		g.setColor(w.getBackground());
		g.fillRect(0, 0, s.getWidth(), s.getHeight());
		g.setColor(w.getForeground());
		g.drawString(mess,30,30);
		g.drawString(mess2, 30, 60);
		g.drawString(mess3,300,50);
		
		System.out.println("keyCD = "+keyCd);
		System.out.println("name "+yellowspkm.Name);
		
		bluespkm.none();
		yellowspkm.none();
			
		g.drawString("User Current Pokemon: " + yellowspkm.Name, 100, 100);
		g.drawString("User Move Set:",100,125);
		g.drawString("HP: " + yellowspkm.HP,100,350);
		
		g.drawString("Foe Current Pokemon: " + bluespkm.Name, 500, 100);
		g.drawString("Foe Move Set:",500,125);
		g.drawString("HP: " + bluespkm.HP,500,350);
		
		for (int i = 0; i < 4; i++){
			g.drawString(i+1 + " - " + yellowspkm.MoveSet.get(i), 100, 160 + 40*i);
			g.drawString(bluespkm.MoveSet.get(i), 500, 160 + 40*i);
		}
	}
}
