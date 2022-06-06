package lab1;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;

public class SimpleGUI extends JFrame {
	GridBagConstraints  gbc = new GridBagConstraints();
	private JButton b1 = new JButton("Enter");
	private JButton b2 = new JButton("Enter");
	private JButton bDraw = new JButton("  Draw graph   ");
	private JButton bWeight = new JButton("Wight of the vertex");
	private JButton bSmej = new JButton(" Аdjacent vertexes");
	private JButton bStruct = new JButton("Change struct");
	private JTextField input = new JTextField("",7);
	private JTextField input2 = new JTextField("",5);
	private JTextField inRes = new JTextField("",13);
	private JLabel labelNumVertex = new JLabel("Input number vertex: ");
	private JLabel lNum = new JLabel("№ vertex: ");
	private JLabel lRes = new JLabel("Resault: ");
	private JLabel lOperation = new JLabel("Operation: ");
	private JRadioButton radio1 = new JRadioButton("Select this");
	private JRadioButton radio2 = new JRadioButton("Select this");
	private JCheckBox  checkNonWeight = new JCheckBox("Unweighted graph", false);
	private Graph gr;
	private inputGraph appIn;
	public SimpleGUI () {
		super("Simlpe Example");//name window
		this.setBounds(100, 200, 300, 250);//последние 2 длина и высота, первые 2 положение
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		gr = new Graph();
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		add(labelNumVertex, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		add(input, gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		add(checkNonWeight, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridy = 1;
		gbc.gridx = 2;
		add(b1, gbc);
		//
		gbc.gridwidth = 1;
		gbc.gridy = 2;
		gbc.gridx = 0;
		add(lOperation, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy = 4;
		gbc.gridx = 0;
		add(bDraw, gbc);
		
		gbc.gridy = 4;
		gbc.gridx = 1;
	//	bWeight.gridwidth = GridBagConstraints.RELATIVE;
		gbc.gridwidth = 2;
		add(bWeight, gbc);
		
		gbc.gridy = 5;
		gbc.gridx = 1;
		add(bSmej, gbc);
		
		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		add(bStruct, gbc);
		
		
		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		add(lNum, gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 1;
		 // input2.setEnabled(false);
		add(input2, gbc);
		
		//gbc.gridy = 2;
		//gbc.gridx = 2;
		//b2.setEnabled(false);
		//add(b2, gbc);
		
		gbc.gridy = 6;
		gbc.gridx = 0;
		add(lRes, gbc);
		
		gbc.gridy = 6;
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		inRes.setEnabled(false);
		add(inRes, gbc);
		
		
		
		
		
		b1.addActionListener(new ButtonEventListener()); //ниже создадим этот класс	
		bDraw.addActionListener(new ButtonEventListener2());  //нарисовать граф
		bWeight.addActionListener(new ButtonEventListener3()); //узнать вес
		bSmej.addActionListener(new ButtonEventListener4());//узнать смежные
		bStruct.addActionListener(new ButtonEventListener5());//поменять структуру
	}
	
	 class ButtonEventListener implements ActionListener {
		/**
		 * @param e
		 */
		public void actionPerformed(ActionEvent e) {  //какое то событие оно сюда передается в качестве параметра
			boolean flCorrTops = false;
			
			String numTopsStr = input.getText();
			int numTops = 0;
			try {
	            numTops = Integer.parseInt(numTopsStr);
	            flCorrTops = true;
	            
	        } catch (NumberFormatException e1) {
	        	JOptionPane.showMessageDialog(null, "Incorrect value!", "Error", JOptionPane.PLAIN_MESSAGE);
	            flCorrTops = false;
	        }
			if (flCorrTops == true) {
				if (numTops <=0 || numTops >= 8) {
					JOptionPane.showMessageDialog(null, "Number vertex have to be in range[1..6]", "Error", JOptionPane.PLAIN_MESSAGE);
					flCorrTops = false;
				}
			}
			//открыть окно
			if (flCorrTops == true) {
				inputGraph appInput = new inputGraph(numTops,checkNonWeight.isSelected(), false);
				appIn = appInput;
				appInput.setVisible(true);
			}		
		}

	}
	 class ButtonEventListener2 implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 
				if (appIn != null) {
			 JFrame frame = new JFrame();
			 //GridBagConstraints  gbc1 = new GridBagConstraints();
			 Graph gr1 =appIn.getGraph();
			 PaintingGraph pg = new PaintingGraph(gr1);
			 pg.setBackground(Color.YELLOW);
			 pg.setLayout(null);
			 int[] x = new int[7];
			 int[] y = new int[7];
			 int[] cX = new int[7];
			 int[] cY = new int[7];
 			 x[1] = 50; y[1] = 125; cX[1] = 55; cY[1] =185;
			 x[2] = 157; y[2] = 10; cX[2] = 165; cY[2] =65;
			 x[3] = 330; y[3] = 10; cX[3] = 335; cY[3] =65;
			 x[4] = 440; y[4] = 125; cX[4] = 445; cY[4] =185;
			 x[5] = 330; y[5] = 245; cX[5] = 335; cY[5] =305;
			 x[6] = 157; y[6] = 245; cX[6] = 165; cY[6] =305;
			 JLabel numbers[] = new JLabel[gr1.GetNumTops()+1];
			 JLabel weights[] = new JLabel[gr1.GetNumTops()+1];
			 int i;
			 for (i = 1; i <= gr1.GetNumTops();i++) {
				 numbers[i] = new JLabel(Integer.toString(i));
				 numbers[i].setFont(new Font("Calibria", Font.PLAIN, 17));
				 numbers[i].setForeground(Color.white); 
				 numbers[i].setBounds(cX[i], cY[i], 40, 15);
				 pg.add(numbers[i]);
			 }
			 if (gr1.getIsWeight()) {
			 for (i = 1; i <= gr1.GetNumTops();i++) {
				 weights[i] = new JLabel(Integer.toString(gr1.KnowWeight(i)));
				 weights[i].setFont(new Font("Calibria", Font.PLAIN, 17));
				 weights[i].setBounds(x[i], y[i], 40, 15);
				 pg.add(weights[i]);
			 }
			 }
			 frame.add(pg);
			 frame.setSize(600, 400);
			 frame.setVisible(true);
				} else {JOptionPane.showMessageDialog(null, "The graph not enter!", "Error", JOptionPane.PLAIN_MESSAGE);}
		 }
	 }
	 
 class ButtonEventListener3 implements ActionListener {//узнать вес вершины
		 
		 public void actionPerformed(ActionEvent e) {
			 if (appIn != null) {
			 gr = appIn.getGraph();
			if (!checkNonWeight.isSelected()) {
			 String st = input2.getText();
			 int numTop;
			  numTop = inputIntOne(st,gr.GetNumTops());
			  if (numTop != -1 && numTop != -2) {   //если нормальное значение
				  int weight = gr.KnowWeight(numTop);
				  inRes.setText("Weight vertex "+numTop+" is: "+weight);
			  }  else {
				  if (numTop == -2) {
				  JOptionPane.showMessageDialog(null, "No such vertex!", "Error", JOptionPane.PLAIN_MESSAGE); }}
			 
		 }else {JOptionPane.showMessageDialog(null, "Graph non weight!", "Error", JOptionPane.PLAIN_MESSAGE);}
		 } else {JOptionPane.showMessageDialog(null, "The graph not enter!", "Error", JOptionPane.PLAIN_MESSAGE);}
		 }
	 }
	 
 class ButtonEventListener4 implements ActionListener {//Узнать смежнык кнопка
	 
	 public void actionPerformed(ActionEvent e) {
		 if (appIn != null) { 
	gr = appIn.getGraph();
	 String st = input2.getText();
	 int numTop;
	 
	  numTop = inputIntOne(st,gr.GetNumTops());
	  if (numTop != -1 && numTop != -2) {
		  int smejV[] = gr.knowSmej(numTop);
		  String string ="";
		  int i;
		 for (i = 1; i<=gr.GetNumTops();i++) {
			 if (smejV[i] != 0) {
				 if (i != 1) {string = string + ", ";}
				 string = string+Integer.toString(smejV[i]);
			 }
		 }  
		  inRes.setText("Vertex #"+numTop+" = {"+string+"}");
		  }else {
			  if (numTop == -2) {
			  JOptionPane.showMessageDialog(null, "No such vertex!", "Error", JOptionPane.PLAIN_MESSAGE);}}
	 }  else {JOptionPane.showMessageDialog(null, "The graph not enter!", "Error", JOptionPane.PLAIN_MESSAGE);}
	 }
 }
 
 class ButtonEventListener5 implements ActionListener { // изиенение структуры, вызвать то же окно но надо заполнить цифрами
		/**
		 * @param e
		 */
		public void actionPerformed(ActionEvent e) {  
			if (appIn != null) {
				inputGraph appInput = new inputGraph(gr.GetNumTops(),checkNonWeight.isSelected(), false);
				appIn = appInput;
				appIn.setVisible(true);
			} else {JOptionPane.showMessageDialog(null, "The graph not enter!", "Error", JOptionPane.PLAIN_MESSAGE);}
		}

	}
 
 
 
 
	 public static int inputInt(String mean) {
			int numInt = 0;
			boolean flag  = true;
			  while (flag) {
			     flag = false;
			        try {
			            numInt = Integer.parseInt(mean);
			            
			        } catch (NumberFormatException e) {
			            System.err.println("Error: wrong integer");
			            flag = true; 
			        }
			    }
		return numInt;	
		}
	 
	 public static int inputIntOne(String mean, int num) {
		 int numTop = 0;
		boolean flag = false;
	        try {
	            numTop = Integer.parseInt(mean);
	            
	        } catch (NumberFormatException e) {
	        	JOptionPane.showMessageDialog(null, "Incorrect value!", "Error", JOptionPane.PLAIN_MESSAGE);
	            flag = true; 
	        }
	        if ( flag == false) {
	        	if (numTop > num || numTop <= 0) { flag = true; 
	        		//JOptionPane.showMessageDialog(null, "Number vertex have to be in range[1.."+ num +"]", "Error", JOptionPane.PLAIN_MESSAGE);
	        		return -2;
	        	} else {return numTop;}
	        } else { return -1;} 
	 }
}






