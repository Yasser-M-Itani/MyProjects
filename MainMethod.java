package PKM;

import java.awt.*;

import javax.swing.ImageIcon;

public class MainMethod {
	public static void main(String args[]){
		MainMethod m = new MainMethod();
		m.run();
	}
	
	private Animation animation;
	private ScreenManager screenmanager;
	private Image bg;
	
	private static final DisplayMode modes1[] = {
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(640,480,24,0),
		new DisplayMode(640,480,16,0),
	};
	
	//load images and add scenes
	public void loadImages(){
		bg = new ImageIcon("C:\\Users\\Yasser Is Awesome!!!\\Pictures\\pkmfield1.jpg").getImage();
		Image star1 = new ImageIcon("C:\\Users\\Yasser Is Awesome!!!\\Pictures\\star.jpg").getImage();
		Image star2 = new ImageIcon("C:\\Users\\Yasser Is Awesome!!!\\Pictures\\star2.jpg").getImage();
		
		animation = new Animation();
		animation.addScene(star1, 250);
		animation.addScene(star2, 250);
	}
	
	//main method called from main
	public void run(){
		screenmanager = new ScreenManager();
		try{
			//find perfect display mode for game
			DisplayMode dm = screenmanager.findFirstCompatibleMode(modes1);
			screenmanager.setFullScreen(dm);
			loadImages();
			movieLoop();
		}finally{
			screenmanager.restoreScreen();
		}
	}
	
	//play movie
	public void movieLoop(){
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;
		//movie lasts for 5 seconds
		while(cumTime - startingTime < 5000){
			long timePassed = System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			animation.update(timePassed);
			
			//draw and update screen
			Graphics2D g = screenmanager.getGraphics();
			draw(g);
			g.dispose();
			screenmanager.update();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex){}
		}
	}

	//draws graphics
	public void draw(Graphics2D g) {
		g.drawImage(bg,0,0,null);
		g.drawImage(animation.getImage(),0,0,null);
		
	}
	
}
