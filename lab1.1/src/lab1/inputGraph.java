package lab1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import lab1.SimpleGUI.ButtonEventListener;

public class inputGraph extends JFrame{
	private JLabel label1 = new JLabel("Input adjacency table:");
	private JLabel label2 = new JLabel("Input weight:");
	private JLabel lNon = new JLabel("Unweight!");
	private GridBagConstraints  gbc = new GridBagConstraints();
	private JButton b1 = new JButton("Ok");
	private JTextField  matrixA[];
	private JTextField  weightVertexA[];
	private JLabel  labelNumA[];
	private int numTopsA;
	private boolean ch;
	private Graph gr;
	private boolean ok = false;
	 
	public Graph getGraph() {
	 return gr;
	}
	public boolean getOk() {
		return ok;
	}
	
	public void setOk(boolean o) {
		ok = o;
	}
	
	public inputGraph(int numTops, boolean check, boolean isStruck) {
		super("Input Graph");
		numTopsA = numTops;
		ch = check;
		setLayout(new GridBagLayout());
		this.setBounds(500, 500, 500, 300);
		 JTextField  matrix[] = new JTextField[numTops*numTops];
		int i,j;
		for (i = 0; i < numTops*numTops; i++) {
			matrix[i] = new JTextField("",5);	
		}
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		add(label1, gbc);
		gbc.gridwidth = 1;
		//номерки
		String st ="";
		int help = 0;
		JLabel  labelNum[] = new JLabel[numTops*3+1];
		for (i = 1; i < numTops*3+1; i++) {
			if (i > numTops*2) {
				help = i - 2*numTops;
				st = Integer.toString(help);
			} else if (i > numTops) {
				help = i - numTops;
				st = Integer.toString(help);
			}else {
				st = Integer.toString(i);
			}
			
			labelNum[i] = new JLabel(st);	
		}
		gbc.gridx = 1;
		gbc.gridy = 1;
		for (i = 1; i < numTops+1; i++) {//горизонтальные циферки
			add(labelNum[i], gbc);
			gbc.gridx = 1+i;
		}
		gbc.gridy = 2;
		gbc.gridx = 0;
		for (i = 1; i < numTops+1; i++) {//вертикальные циферки
			add(labelNum[i+numTops], gbc);
			gbc.gridy = 2+i;
		}
		//
		
		for (i = 0;i < numTops;i++) {
			for (j = 0;j < numTops;j++) {
			
			gbc.gridx = i+1;
			gbc.gridy = j+2;
			add(matrix[i*numTops + j], gbc);
			if (i <= j) {
				matrix[i*numTops + j].setEnabled(false);
			}
			}
		}
		//тут начал
		
		if (!isStruck) {   //иземенение структуры
		if (check == false) {  //проверка взвешенного графа
		gbc.gridy = numTops+2;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		add(label2, gbc); //кнопка 2
		
		for (i = 1; i <= numTops; i++) {  //цифры над весом
			gbc.gridx = i;
			add(labelNum[numTops*2+i], gbc);
		}
		
		
		gbc.gridwidth = 1;  //ввод весов
		 JTextField  weightVertex[] = new JTextField[numTops];
		 for (i = 0; i < numTops; i++) {
			 weightVertex[i] = new JTextField("",5);	
			}
		 gbc.gridy++;
		 gbc.gridx = 1; 
		 
		 for (i = 0;i < numTops;i++) {
				gbc.gridx = i+1;
				add(weightVertex[i], gbc);
				}
		 //кнопка
		 weightVertexA = weightVertex;
		} else {gbc.gridy = numTops+2;
			gbc.gridx = 0;
			gbc.gridwidth = 1;
			add(lNon, gbc); }
		} else {   //тут надо заполнить элементы матрицы
			int tableSmej[][] = gr.getTable_Smej();
			for (i = 0; i < numTops; i++ ) {
				for (j = 0; j < numTops; j++ ) {
					matrix[i*numTops+j].setText(Integer.toString(tableSmej[i][j]));
				}
			}
			
		}
		
		//gbc.gridy = numTops+2;
		//gbc.gridx = 0;
		//gbc.gridwidth = 1;
		//add(label2, gbc); 
		
		
		gbc.gridy = numTops+4;
		add(b1, gbc);
		
		matrixA = matrix;
		labelNumA = labelNum;
		b1.addActionListener(new ButtonEventListener1());	
	}
	
	
	
	 class ButtonEventListener1 implements ActionListener {
			/**
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {  //какое то событие оно сюда передается в качестве параметра
			    
				int graphSmej[][] = new int[numTopsA][numTopsA];  
				int numTops = 0;
				int i = 0,j = 0;
				boolean flCorrTops = false, flZero = false;
				for (i = 0; i < numTopsA; i++ ) {
					for (j = 0; j < numTopsA; j++ ) {
				try {
		            numTops = Integer.parseInt(matrixA[i*numTopsA +j].getText());
		            flCorrTops = true;
		            
		        } catch (NumberFormatException e1) {
		        	if (matrixA[i*numTopsA +j].getText().length() == 0) {
		        		//graphSmej[i][j] = 0;
		        		flZero = true;
		        		flCorrTops = true;
		        	}else {
		        	JOptionPane.showMessageDialog(null, "Incorrect value!", "Error", JOptionPane.PLAIN_MESSAGE);
		            flCorrTops = false;
		            break;}
		        }	
					if (numTops >=100 ) { flCorrTops = false;
						JOptionPane.showMessageDialog(null, "Value need be in range[0..99]!", "Error", JOptionPane.PLAIN_MESSAGE);
						break;
					}
					if (flCorrTops == true) {
						if (!flZero) {
						graphSmej[i][j] = numTops;
						}else {graphSmej[i][j] = 0 ; flZero = false; }
					}
				}if (flCorrTops == false) {break;}
		
			}
				for ( i=0; i < numTopsA;i++) {
					for ( j=i+1; j < numTopsA;j++) {
					if ( i != j) {
						graphSmej[i][j] = graphSmej[j][i];
					}
				  }	
				}
				//проверка есть ли весы
				int weight[] = new int[numTopsA];
				boolean flCorrW = false;
				
				if (ch == false) {
				for (i = 0; i < numTopsA; i++) {
					try {
						numTops = 0;
			            numTops = Integer.parseInt(weightVertexA[i].getText());
			            flCorrW = true;
			            
			        } catch (NumberFormatException e1) {
			        	if (weightVertexA[i].getText().length() == 0) {
			        		weight[i] = 0;
			        		flCorrW = true;
			        	}else {
			        	JOptionPane.showMessageDialog(null, "Incorrect value!", "Error", JOptionPane.PLAIN_MESSAGE);
			            flCorrW = false;
			            break;}
			        }	
						if (numTops >=100 ) { flCorrW = false;
							JOptionPane.showMessageDialog(null, "Value need be in range[0..99]!", "Error", JOptionPane.PLAIN_MESSAGE);
							break;
						}
						if (flCorrW == true) {
							weight[i] = numTops;
						}	if (flCorrW == false) {break;}
				}	
					
				}
				//если оба true, тогда надо заполнить граф и потом закрыть
				if (ch == false) {
					Graph grHelp = new Graph();
					gr = grHelp;
					gr.setIsWeight(true);
					gr.SetTablesWeight(weight, graphSmej, numTopsA);
				}else {
					Graph grHelp = new Graph();
					gr = grHelp;
					gr.SetTableNonWeight( graphSmej, numTopsA);
					gr.setIsWeight(false);
				}
				if (!ch) {
				if (flCorrW == true && flCorrTops == true) {setOk(true);gr.setIsCreated(true);dispose();}		
		    }  else if (flCorrTops == true) {setOk(true); gr.setIsCreated(true); dispose();}
				
			
	 }
	 }
}
