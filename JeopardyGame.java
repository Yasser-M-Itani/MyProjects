/*
 * Jeopardy Game 
 * Graphical User Interface
 * Created by Yasser Mohamed Itani
 * (c) Yasser M Itani 2013
 * V 2.11
 */

package Jeopardy;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class JeopardyGame extends JFrame{
	
	public JButton cat1;
	public JButton cat2;
	public JButton cat3;
	public JButton cat4;
	public JButton cat5;
	
	public JButton b1001;
	public JButton b2001;
	public JButton b3001;
	public JButton b4001;
	public JButton b5001;
	
	public JButton b1002;
	public JButton b2002;
	public JButton b3002;
	public JButton b4002;
	public JButton b5002;
	
	public JButton b1003;
	public JButton b2003;
	public JButton b3003;
	public JButton b4003;
	public JButton b5003;
	
	public JButton b1004;
	public JButton b2004;
	public JButton b3004;
	public JButton b4004;
	public JButton b5004;

	public JButton b1005;
	public JButton b2005;
	public JButton b3005;
	public JButton b4005;
	public JButton b5005;
	
	public JButton bFinalJeopardy;
	
	Boolean b1001Op = false;
	Boolean b2001Op = false;
	Boolean b3001Op = false;
	Boolean b4001Op = false;
	Boolean b5001Op = false;
	
	Boolean b1002Op = false;
	Boolean b2002Op = false;
	Boolean b3002Op = false;
	Boolean b4002Op = false;
	Boolean b5002Op = false;
	
	Boolean b1003Op = false;
	Boolean b2003Op = false;
	Boolean b3003Op = false;
	Boolean b4003Op = false;
	Boolean b5003Op = false;
	
	Boolean b1004Op = false;
	Boolean b2004Op = false;
	Boolean b3004Op = false;
	Boolean b4004Op = false;
	Boolean b5004Op = false;
	
	Boolean b1005Op = false;
	Boolean b2005Op = false;
	Boolean b3005Op = false;
	Boolean b4005Op = false;
	Boolean b5005Op = false;
	
	Boolean bFinalJeopardyOp = false;
	
	
	public JeopardyGame(){
		
		//b#00#		#-category		#-question number 
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(6,5));
		p.add(cat1 = new JButton("CATEGORY 1"));
		p.add(cat2 = new JButton("CATEGORY 2"));
		p.add(cat3 = new JButton("CATEGORY 3"));
		p.add(cat4 = new JButton("CATEGORY 4"));
		p.add(cat5 = new JButton("CATEGORY 5"));
		p.add(b1001 = new JButton("100"));
		p.add(b2001 = new JButton("100"));
		p.add(b3001 = new JButton("100"));
		p.add(b4001 = new JButton("100"));
		p.add(b5001 = new JButton("100"));
		p.add(b1002 = new JButton("200"));
		p.add(b2002 = new JButton("200"));
		p.add(b3002 = new JButton("200"));
		p.add(b4002 = new JButton("200"));
		p.add(b5002 = new JButton("200"));
		p.add(b1003 = new JButton("300"));
		p.add(b2003 = new JButton("300"));
		p.add(b3003 = new JButton("300"));
		p.add(b4003 = new JButton("300"));
		p.add(b5003 = new JButton("300"));
		p.add(b1004 = new JButton("400"));
		p.add(b2004 = new JButton("400"));
		p.add(b3004 = new JButton("400"));
		p.add(b4004 = new JButton("400"));
		p.add(b5004 = new JButton("400"));
		p.add(b1005 = new JButton("500"));
		p.add(b2005 = new JButton("500"));
		p.add(b3005 = new JButton("500"));
		p.add(b4005 = new JButton("500"));
		p.add(b5005 = new JButton("500"));
		
		add(p);
		
		
		b1001.addActionListener(new OneOne());
		b2001.addActionListener(new TwoOne());
		b3001.addActionListener(new ThreeOne());
		b4001.addActionListener(new FourOne());
		b5001.addActionListener(new FiveOne());
		b1002.addActionListener(new OneTwo());
		b2002.addActionListener(new TwoTwo());
		b3002.addActionListener(new ThreeTwo());
		b4002.addActionListener(new FourTwo());
		b5002.addActionListener(new FiveTwo());
		b1003.addActionListener(new OneThree());
		b2003.addActionListener(new TwoThree());
		b3003.addActionListener(new ThreeThree());
		b4003.addActionListener(new FourThree());
		b5003.addActionListener(new FiveThree());
		b1004.addActionListener(new OneFour());
		b2004.addActionListener(new TwoFour());
		b3004.addActionListener(new ThreeFour());
		b4004.addActionListener(new FourFour());
		b5004.addActionListener(new FiveFour());
		b1005.addActionListener(new OneFive());
		b2005.addActionListener(new TwoFive());
		b3005.addActionListener(new ThreeFive());
		b4005.addActionListener(new FourFive());
		b5005.addActionListener(new FiveFive());

	}
	
	public class OneOne implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow1001 ans1001 = new AnswerWindow1001(JeopardyGame.this);
				ans1001.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				ans1001.setSize(1000,400);
				//ans1001.setSize(1000,400);
				ans1001.setLocation(200,200);
				ans1001.setVisible(true);
				b1001.setText("");
		}
	}
	public class TwoOne implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow2001 ans2001 = new AnswerWindow2001(JeopardyGame.this);
				ans2001.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans2001.setSize(1000,400);
				ans2001.setLocation(200,200);
				ans2001.setVisible(true);
				b2001.setText("");
		}
	}
	public class ThreeOne implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow3001 ans3001 = new AnswerWindow3001(JeopardyGame.this);
				ans3001.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans3001.setSize(1000,400);
				ans3001.setLocation(200,200);
				ans3001.setVisible(true);
				b3001.setText("");
		}
	}
	public class FourOne implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow4001 ans4001 = new AnswerWindow4001(JeopardyGame.this);
				ans4001.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans4001.setSize(1000,400);
				ans4001.setLocation(200,200);
				ans4001.setVisible(true);
				b4001.setText("");
		}
	}
	public class FiveOne implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow5001 ans5001 = new AnswerWindow5001(JeopardyGame.this);
				ans5001.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans5001.setSize(1000,400);
				ans5001.setLocation(200,200);
				ans5001.setVisible(true);
				b5001.setText("");
		}
	}
	public class OneTwo implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow1002 ans1002 = new AnswerWindow1002(JeopardyGame.this);
				ans1002.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans1002.setSize(1000,400);
				ans1002.setLocation(200,200);
				ans1002.setVisible(true);
				b1002.setText("");
		}
	}
	public class TwoTwo implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow2002 ans2002 = new AnswerWindow2002(JeopardyGame.this);
				ans2002.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans2002.setSize(1000,400);
				ans2002.setLocation(200,200);
				ans2002.setVisible(true);
				b2002.setText("");
		}
	}
	public class ThreeTwo implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow3002 ans3002 = new AnswerWindow3002(JeopardyGame.this);
				ans3002.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans3002.setSize(1000,400);
				ans3002.setLocation(200,200);
				ans3002.setVisible(true);
				b3002.setText("");
		}
	}
	public class FourTwo implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow4002 ans4002 = new AnswerWindow4002(JeopardyGame.this);
				ans4002.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans4002.setSize(1000,400);
				ans4002.setLocation(200,200);
				ans4002.setVisible(true);
				b4002.setText("");
		}
	}
	public class FiveTwo implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow5002 ans5002 = new AnswerWindow5002(JeopardyGame.this);
				ans5002.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans5002.setSize(1000,400);
				ans5002.setLocation(200,200);
				ans5002.setVisible(true);
				b5002.setText("");
		}
	}
	public class OneThree implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow1003 ans1003 = new AnswerWindow1003(JeopardyGame.this);
				ans1003.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans1003.setSize(1000,400);
				ans1003.setLocation(200,200);
				ans1003.setVisible(true);
				b1003.setText("");
		}
	}
	public class TwoThree implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow2003 ans2003 = new AnswerWindow2003(JeopardyGame.this);
				ans2003.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans2003.setSize(1000,400);
				ans2003.setLocation(200,200);
				ans2003.setVisible(true);
				b2003.setText("");
		}
	}
	public class ThreeThree implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow3003 ans3003 = new AnswerWindow3003(JeopardyGame.this);
				ans3003.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans3003.setSize(1000,400);
				ans3003.setLocation(200,200);
				ans3003.setVisible(true);
				b3003.setText("");
		}
	}
	public class FourThree implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow4003 ans4003 = new AnswerWindow4003(JeopardyGame.this);
				ans4003.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans4003.setSize(1000,400);
				ans4003.setLocation(200,200);
				ans4003.setVisible(true);
				b4003.setText("");
		}
	}
	public class FiveThree implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow5003 ans5003 = new AnswerWindow5003(JeopardyGame.this);
				ans5003.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans5003.setSize(1000,400);
				ans5003.setLocation(200,200);
				ans5003.setVisible(true);
				b5003.setText("");
		}
	}
	public class OneFour implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow1004 ans1004 = new AnswerWindow1004(JeopardyGame.this);
				ans1004.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans1004.setSize(1000,400);
				ans1004.setLocation(200,200);
				ans1004.setVisible(true);
				b1004.setText("");
		}
	}
	public class TwoFour implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow2004 ans2004 = new AnswerWindow2004(JeopardyGame.this);
				ans2004.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans2004.setSize(1000,400);
				ans2004.setLocation(200,200);
				ans2004.setVisible(true);
				b2004.setText("");
		}
	}
	public class ThreeFour implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow3004 ans3004 = new AnswerWindow3004(JeopardyGame.this);
				ans3004.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans3004.setSize(1000,400);
				ans3004.setLocation(200,200);
				ans3004.setVisible(true);
				b3004.setText("");
		}
	}
	public class FourFour implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow4004 ans4004 = new AnswerWindow4004(JeopardyGame.this);
				ans4004.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans4004.setSize(1000,400);
				ans4004.setLocation(200,200);
				ans4004.setVisible(true);
				b4004.setText("");
		}
	}
	public class FiveFour implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow5004 ans5004 = new AnswerWindow5004(JeopardyGame.this);
				ans5004.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans5004.setSize(1000,400);
				ans5004.setLocation(200,200);
				ans5004.setVisible(true);
				b5004.setText("");
		}
	}
	public class OneFive implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow1005 ans1005 = new AnswerWindow1005(JeopardyGame.this);
				ans1005.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans1005.setSize(1000,400);
				ans1005.setLocation(200,200);
				ans1005.setVisible(true);
				b1005.setText("");
		}
	}
	public class TwoFive implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow2005 ans2005 = new AnswerWindow2005(JeopardyGame.this);
				ans2005.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans2005.setSize(1000,400);
				ans2005.setLocation(200,200);
				ans2005.setVisible(true);
				b2005.setText("");
		}
	}
	public class ThreeFive implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow3005 ans3005 = new AnswerWindow3005(JeopardyGame.this);
				ans3005.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans3005.setSize(1000,400);
				ans3005.setLocation(200,200);
				ans3005.setVisible(true);
				b3005.setText("");
		}
	}
	public class FourFive implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow4005 ans4005 = new AnswerWindow4005(JeopardyGame.this);
				ans4005.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans4005.setSize(1000,400);
				ans4005.setLocation(200,200);
				ans4005.setVisible(true);
				b4005.setText("");
		}
	}
	public class FiveFive implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AnswerWindow5005 ans5005 = new AnswerWindow5005(JeopardyGame.this);
				ans5005.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				//ans1001.setSize(600,300);
				ans5005.setSize(1000,400);
				ans5005.setLocation(200,200);
				ans5005.setVisible(true);
				b5005.setText("");
		}
	}
	
	public static void main(String[] args) {

	    JeopardyGame Jeopardy = new JeopardyGame();
	    Jeopardy.pack();
	    Jeopardy.setLocationRelativeTo(null);
	    		Jeopardy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Jeopardy.setVisible(true);
	}

}
