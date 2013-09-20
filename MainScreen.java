package PKM;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainScreen extends JFrame{
	public static void main (String[] args){
		
		DisplayMode displayMode = new DisplayMode(800,600,16, DisplayMode.REFRESH_RATE_UNKNOWN);
		MainScreen m = new MainScreen();
		m.run(displayMode);
	}
	
	private Screen screen;
	private Image bg;
	private Animation animation;
	
	//loads pictures from computer to java and adds scene
	public void loadPics(){
		bg = new ImageIcon("C:\\Users\\Yasser Is Awesome!!!\\Pictures\\pkmfield1.jpg").getImage();
		Image star1 =  new ImageIcon("C:\\Users\\Yasser Is Awesome!!!\\Pictures\\star.jpg").getImage();
		Image star2 =  new ImageIcon("C:\\Users\\Yasser Is Awesome!!!\\Pictures\\star2.jpg").getImage();
		animation = new Animation();
		animation.addScene(star1, 250);
		animation.addScene(star2, 250);
	}
	
	//main engine to run
	public void run(DisplayMode dm){
		screen = new Screen();
		try{
			screen.setFullScreen(dm, new JFrame());
			loadPics();
			movieLoop();
		}finally{
			screen.restoreScreen();
		}
	}
	
	//main movie loop
	public void movieLoop(){
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;
		
		while(cumTime - startingTime < 5000){
			long timePassed =  System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			animation.update(timePassed);
			
			Graphics g = screen.getFullScreenWindow().getGraphics();
			draw(g);
			g.dispose();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex){}
		}
	}
	
	//draw method
	public void draw(Graphics g){
		g.drawImage(bg, 0,0, null);
		g.drawImage(animation.getImage(),0,0,null);
	}
	
	/*
	public void run(DisplayMode displayMode){
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.RED);
		setFont(new Font("Arial", Font.PLAIN, 24));
		
		Screen s = new Screen();
		try{
			s.setFullScreen(displayMode, this);
			try{
				Thread.sleep(5000);
			}catch(Exception ex){}
		}finally{
			s.restoreScreen();
		}
	}
	
	public void paint(Graphics g){
		if(g instanceof Graphics2D){
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
		g.drawString("This is gonna be awesome", 200, 200);
	}
	*/
}
