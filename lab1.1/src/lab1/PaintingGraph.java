package lab1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PaintingGraph extends JPanel{
	private Graph gr;
	
	public  PaintingGraph(Graph gr) {
        this.gr = gr;		
	}
	public void paintComponent(Graphics g) {
		int numTops = gr.GetNumTops() ;
		this.setBackground(Color.WHITE);
		g.setColor(Color.BLACK);
		int x[] = new int[7];
		int y[] = new int[7];
		int cX[] = new int[7];
		int cY[] = new int[7];
		
		x[1] = 20; y[1] = 150; cX[1] = 55; cY[1] =185;
		x[2] = 130; y[2] = 30; cX[2] = 165; cY[2] =65;
		x[3] = 300; y[3] = 30; cX[3] = 335; cY[3] =65;
		x[4] = 410; y[4] = 150; cX[4] = 445; cY[4] =185;
		x[5] = 300; y[5] = 270; cX[5] = 335; cY[5] =305;
		x[6] = 130; y[6] = 270; cX[6] = 165; cY[6] =305;
		int i = 0;
		for (i = 1; i <= numTops;i++ ) {
			g.fillOval(x[i], y[i], 70, 70);	
			//if (i == 6) {
			//	g.drawLine(cX[i], cY[i], cX[1], cY[1]);
			//}	else {
			//	g.drawLine(cX[i], cY[i], cX[i+1], cY[i+1]);
			//}
			//JLabel number = new JLabel("Какая то цифра ывфавыфа");
		//number.setBounds(35, 170, 40, 100);
		//add(number);
		//	number.setVisible(true);	
		}
		int j;
	    int smej[] = new int[7];
		for (i = 1; i < numTops; i++){
			smej = gr.knowSmej(i); 
			for  (j = 1; j <= numTops; j++) {
				if (smej[j] != 0) {
					g.drawLine(cX[i], cY[i], cX[smej[j]], cY[smej[j]]);
				}else break;
			}
		}
		
		
		
	}
}


