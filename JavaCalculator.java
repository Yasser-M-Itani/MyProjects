package Calculator;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class NewJavaCalculator extends JFrame{

	public JButton jbNum1;
	public JButton jbNum2;
	public JButton jbNum3;
	public JButton jbNum4;
	public JButton jbNum5;
	public JButton jbNum6;
	public JButton jbNum7;
	public JButton jbNum8;
	public JButton jbNum9;
	public JButton jbNum0;
	public JButton jbDecimal;
	public JButton jbClear;
	public JButton jbAdd;
	public JButton jbSub;
	public JButton jbMult;
	public JButton jbDiv;
	public JButton jbEqual;
	public JButton jbSine;
	public JButton jbCosine;
	public JButton jbTangent;
	public JButton jbSinh;
	public JButton jbCosh;
	public JButton jbTanh;
	public JButton jbln;
	public JButton jbe;
	public JButton jbpow;
	public JButton jbsqrt;
	public JButton jbplusminus;
	public JButton jbreciprocal;
	
	double temp;
	double solution;
	
	private JTextField jtxtResult;
	
	Boolean addOp = false;
	Boolean subOp = false;
	Boolean multOp = false;
	Boolean divOp = false;
	Boolean decOp = false;	
	Boolean sinOp = false;
	Boolean cosOp = false;
	Boolean tanOp = false;
	Boolean sinhOp = false;
	Boolean coshOp = false;
	Boolean tanhOp = false;
	Boolean lnOp = false;
	Boolean eOp = false;
	Boolean powOp = false;
	Boolean sqrtOp = false;
	
	String display = "";
	
	public NewJavaCalculator() {
				
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout (4,4));
		p1.add(jbNum1 = new JButton("1"));
		p1.add(jbNum2 = new JButton("2"));
		p1.add(jbNum3 = new JButton("3"));
		p1.add(jbNum4 = new JButton("4"));
		p1.add(jbNum5 = new JButton("5"));
		p1.add(jbNum6 = new JButton("6"));
		p1.add(jbNum7 = new JButton("7"));
		p1.add(jbNum8 = new JButton("8"));
		p1.add(jbNum9 = new JButton("9"));
		p1.add(jbClear = new JButton("C"));
		p1.add(jbNum0 = new JButton("0"));
		p1.add(jbEqual = new JButton("="));
		
		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
	    p2.add(jtxtResult = new JTextField(20));
	    jtxtResult.setHorizontalAlignment(JTextField.RIGHT);
	    jtxtResult.setEditable(true);
	    
	    
	    JPanel p3 = new JPanel();
	    p3.setLayout(new GridLayout(3,2));
	    p3.add(jbSine = new JButton("sinx"));
	    p3.add(jbSinh = new JButton("sinhx"));
	    p3.add(jbCosine = new JButton("cosx"));
	    p3.add(jbCosh = new JButton("coshx"));
	    p3.add(jbTangent = new JButton("tanx"));
	    p3.add(jbTanh = new JButton("tanhx"));
	    
	    JPanel p4 = new JPanel();
	    p4.setLayout(new GridLayout(5,2));
	    p4.add(jbplusminus = new JButton("+/-"));
	    p4.add(jbsqrt = new JButton("R"));
	    p4.add(jbDiv = new JButton("/"));
	    p4.add(jbe = new JButton("x^y"));
	    p4.add(jbMult = new JButton("*"));
	    p4.add(jbreciprocal = new JButton("1/x"));
	    p4.add(jbSub = new JButton("-"));
	    p4.add(jbpow = new JButton("e^x"));
	    p4.add(jbAdd = new JButton("+"));
	    p4.add(jbln = new JButton("lnx"));
	    
	    
	    JPanel p = new JPanel();
	    p.add(p1, BorderLayout.EAST);
	    p.add(p4, BorderLayout.NORTH);
	    p.add(p3, BorderLayout.WEST);
	    p.add(p2, BorderLayout.WEST);
	    
	    add(p);
	    
	    jbNum1.addActionListener(new One());
	    jbNum2.addActionListener(new Two());
	    jbNum3.addActionListener(new Three());
	    jbNum4.addActionListener(new Four());
	    jbNum5.addActionListener(new Five());
	    jbNum6.addActionListener(new Six());
	    jbNum7.addActionListener(new Seven());
	    jbNum8.addActionListener(new Eight());
	    jbNum9.addActionListener(new Nine());
	    jbNum0.addActionListener(new Zero());
	    
	    jbAdd.addActionListener(new Add());
	    jbSub.addActionListener(new Subtract());
	    jbMult.addActionListener(new Multiply());
	    jbDiv.addActionListener(new Divide());
	    jbClear.addActionListener(new Clear());
	    jbEqual.addActionListener(new Equal());
	    /*
	     jbSine;
		 jbCosine;
		 jbTangent;
		 jbSinh;
		 jbCosh;
		 jbTanh;
		 jbln;
		 jbe;
		 jbpow;
		 jbsqrt;
		 jbplusminus;
		 jbreciprocal;
		*/
	    
	}
	
	class One implements ActionListener {
		public void actionPerformed(ActionEvent A){
			display = jtxtResult.getText();
			jtxtResult.setText(display + "1");
		}
	}
	
	class Two implements ActionListener {
		public void actionPerformed(ActionEvent A){
			display = jtxtResult.getText();
			jtxtResult.setText(display + "2");
		}
	}
	
	class Three implements ActionListener {
		public void actionPerformed(ActionEvent A){
			display = jtxtResult.getText();
			jtxtResult.setText(display + "3");
		}
	}
	
	class Four implements ActionListener {
		public void actionPerformed(ActionEvent A){
			display = jtxtResult.getText();
			jtxtResult.setText(display + "4");
		}
	}
	
	class Five implements ActionListener {
		public void actionPerformed(ActionEvent A){
			display = jtxtResult.getText();
			jtxtResult.setText(display + "5");
		}
	}
	
	class Six implements ActionListener {
		public void actionPerformed(ActionEvent A){
			display = jtxtResult.getText();
			jtxtResult.setText(display + "6");
		}
	}
	
	class Seven implements ActionListener {
		public void actionPerformed(ActionEvent A){
			display = jtxtResult.getText();
			jtxtResult.setText(display + "7");
		}
	}
	
	class Eight implements ActionListener {
		public void actionPerformed(ActionEvent A){
			display = jtxtResult.getText();
			jtxtResult.setText(display + "8");
		}
	}
	
	class Nine implements ActionListener {
		public void actionPerformed(ActionEvent A){
			display = jtxtResult.getText();
			jtxtResult.setText(display + "9");
		}
	}

	class Zero implements ActionListener {
		public void actionPerformed(ActionEvent A){
			display = jtxtResult.getText();
			jtxtResult.setText(display + "0");
		}
	}

	
	class Add implements ActionListener {
		public void actionPerformed(ActionEvent A){
			temp = Double.parseDouble(jtxtResult.getText());
			jtxtResult.setText("");
			addOp = true;
		}
	}
	
	class Subtract implements ActionListener {
		public void actionPerformed(ActionEvent A){
			temp = Double.parseDouble(jtxtResult.getText());
			jtxtResult.setText("");
			subOp = true;
		}
	}
	
	class Multiply implements ActionListener {
		public void actionPerformed(ActionEvent A){
			temp = Double.parseDouble(jtxtResult.getText());
			jtxtResult.setText("");
			multOp = true;
		}
	}
	
	class Divide implements ActionListener {
		public void actionPerformed(ActionEvent A){
			temp = Double.parseDouble(jtxtResult.getText());
			jtxtResult.setText("");
			divOp = true;
		}
	}
	
	class Clear implements ActionListener {
		public void actionPerformed(ActionEvent A){
			jtxtResult.setText("");
			addOp = false;
			subOp = false;
			multOp = false;
			divOp = false;
			decOp = false;
			sqrtOp = false;
			sinOp = false;
			cosOp = false;
			tanOp = false;
			sinhOp = false;
			coshOp = false;
			tanhOp = false;
			lnOp = false;
			eOp = false;
			powOp = false;
			sqrtOp = false;
		}
	}
	
	class Equal implements ActionListener {
		public void actionPerformed(ActionEvent A){
			solution = Double.parseDouble(jtxtResult.getText());
			
			if (addOp == true){
				solution += temp;
				display = String.valueOf(solution);
				jtxtResult.setText(display);
			}
			
			else if (subOp == true){
				solution -= temp;
				display = String.valueOf(solution);
				jtxtResult.setText(display);
			}
			
			else if (multOp == true){
				solution *= temp;
				display = String.valueOf(solution);
				jtxtResult.setText(display);
			}
			
			else if (divOp == true){
				if (temp > solution)
					solution = temp/solution;
				else
					solution /= temp;
				display = String.valueOf(solution);
				jtxtResult.setText(display);
			}
			
			addOp = false;
			subOp = false;
			divOp = false;
			multOp = false;
		}
	}
	
	
	public static void main(String[] args) {

	    NewJavaCalculator calc = new NewJavaCalculator();
	    calc.pack();
	    calc.setLocationRelativeTo(null);
	            calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    calc.setVisible(true);
	}
	
}
