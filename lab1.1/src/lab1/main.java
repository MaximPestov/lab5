package lab1;
import java.util.Scanner;

public class main {
	

		public static void main(String[] args) {
			// TODO Auto-generated method stub
		SimpleGUI app = new SimpleGUI();
		app.setVisible(true);
			Scanner s = new Scanner(System.in);
				Graph GR = new Graph();
				//GR.SetTablesWeight();
				int ch1, isWeight = 0; 
				boolean ok = false;
				do {
					System.out.println("1 - Entering a graph.");
					System.out.println("2 - Find out the weight of the vertex.");
					System.out.println("3 - Find out adjacent vertices.");
					System.out.println("4 - Сhange the graph structure.");
					System.out.println("0 - Exit.");
					do {
					ch1 = InputInt();
					if (ch1 <0 | ch1>4) 
					System.out.println("Input mean in range {0..4}");
					}
					while (ch1 <0 | ch1>4);
					switch(ch1) {
					case 1:  //ввод графа
						//очистить
						System.out.println("1 - Entering a unweighted graph.");
						System.out.println("2 - Entering a weighted graph.");
						int ch2;
						do {
							ch2 = InputInt();
							if (ch2 <0 | ch2>4) 
							System.out.println("Input mean in range {0..4}");
							}
							while (ch2 <1 | ch2>2);
					//	if (ch2 == 1) { GR.SetTableNonWeight(); isWeight = 0; ok = true; }
						//if (ch2 == 2) { GR.SetTablesWeight(); isWeight = 1; ok = true; }
						break; 
					case 2:    //узнать вес вершины
						//очистить
						if (ok){
						if (isWeight == 1) {
							System.out.println("Enter a vertex: ");
							int vertex = InputInt();
							
							System.out.println("Vertex weight "+vertex+": "+ GR.KnowWeight(vertex));
							
						} else {System.out.println("The graph is unweighted!");}
						} else {System.out.println("The graph isn`t entered!");}
						break;
						
					case 3:   //узнать смежные вершины
						if(ok) {
							int vertex;
							System.out.println("Enter a vertex: ");
							vertex = s.nextInt();
							GR.knowSmej(vertex);
						}else {System.out.println("The graph isn`t entered!");}
						break;
						
					case 4: 
						if(ok) {
						System.out.println("Input the first vertex: ");
						int firstTop = 0, secondTop = 0;
						do {
							firstTop = InputInt();
							 
							if ( firstTop <0 | firstTop > GR.GetNumTops() ) 
							System.out.println("Input mean less number vertex!");
							}
							while ( firstTop < 0 | firstTop > GR.GetNumTops());
						System.out.println("Input the second vertex: ");
						do {
							secondTop = InputInt();
							if ( secondTop <0 | secondTop > GR.GetNumTops() ) 
							System.out.println("Input mean less number vertex!");
							}
							while ( secondTop < 0 | secondTop > GR.GetNumTops());
						System.out.println("Input weight for" + firstTop+ "-" +secondTop+ ": ");
						int weight = InputInt();
						GR.changeStruct(firstTop,secondTop,weight);
						}else {System.out.println("The graph isn`t entered!");}
						break;
					default: 
						break;
					}
					
				}
				
				while(ch1 != 0);
				
			
			
		}
		
		public static int InputInt() {
			Scanner s = new Scanner(System.in);
			int numInt = 0;
			boolean flag  = true;
			  while (flag) {
			     flag = false;
			        String mean = s.nextLine();
			        try {
			            numInt = Integer.parseInt(mean);
			            
			        } catch (NumberFormatException e) {
			            System.err.println("Error: wrong integer");
			            flag = true; 
			        }
			    }
		return numInt;	
		}
		

	}


