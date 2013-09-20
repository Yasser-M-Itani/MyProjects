package PKM;

import java.awt.*;

public abstract class Core {
	
	private static DisplayMode modes[] = {
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(640,480,24,0),
		new DisplayMode(640,480,16,0),
	};

	private boolean running;
	protected ScreenManager s;
	
	//stop method
	public void stop(){
		running = false;
	}
	
	//call init and gameloop
	public void run(){
		try{
			init();
			gameLoop();
		}finally{
			s.restoreScreen();
		}
	}
	
	//set to full screen
	public void init(){
		s = new ScreenManager();
		DisplayMode dm = s.findFirstCompatibleMode(modes);
		s.setFullScreen(dm);
		
		Window w = s.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN,15));
		w.setBackground(Color.BLUE);
		w.setForeground(Color.WHITE);
		running = true;
	}
	
	//main gameLoop
	public void gameLoop(){
		long startTime = System.currentTimeMillis();
		long cumTime = startTime;
		
		while(running){
			long timePassed = System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			
			update(timePassed);
			
			Graphics2D g = s.getGraphics();
			
			classvd.setkeyCode(KeyTest.tempKeyCode);
			classvd.finaldamage(KeyTest.tempKeyCode);
			
			//if key pressed then draw ******change
			if(KeyTest.keyCd == 10)	
				draw(g);
			if(KeyTest.keyCd == 11 || KeyTest.keyCd == 12 || KeyTest.keyCd == 13 || KeyTest.keyCd == 14 || KeyTest.keyCd == 15 || KeyTest.keyCd == 16)
				updatepkmdraw(g);
			if(KeyTest.keyCd == 1 || KeyTest.keyCd == 2 || KeyTest.keyCd == 3 || KeyTest.keyCd == 4){
				bluespkm.HP -= movesets.MovePower;
				movedraw(g);
			}
			
			g.dispose();
			s.update();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex){}
		}
	}
	
	//update animation
	public void update(long timePassed){
	}
	
	//draws to screen
	public abstract void draw(Graphics2D g);
	
	public abstract void updatepkmdraw(Graphics2D g);
	
	public abstract void movedraw(Graphics2D g);
}
