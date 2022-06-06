package lab1;

import java.util.Scanner;
public class Graph{
	private int Table_Smej[][];
	private int List_Edges[][];//1-из которого, 2 - в который, 3 - вес
	private int List_Smej[][];
	private int Weight[];
	private int numTops;
	private boolean isWeight;
	
	/**
	 * Finds out the number of vertices
	 * @return Number of vertices
	 * @author Pestov Maxim 
	 */
	
	 public int GetNumTops() {
		return numTops;
	}
	 /**
		 * Entering table values
		 * @author Pestov Maxim
		 */
		public boolean getIsWeight() {
			return isWeight;
		}
		public void setIsWeight(boolean w) {
			isWeight = w;
		}
		public boolean getIsCreated() {
			return isCreated;
		}
		public void setIsCreated(boolean w) {
			isCreated = w;
		}
		public int[][] getTable_Smej() {
			return Table_Smej;
		}
		
	public void SetTablesWeight(int[] weightIN, int[][] graphSmej, int numTops ) {
		this.numTops = numTops;
		//Scanner s = new Scanner(System.in);
		//System.out.println("Input number of vertex: ");
		//numTops = s.nextInt();
		int Table_smej[][]  = new int[numTops+1][numTops+1];
		int list_Edges[][] = new int[numTops*numTops][4];
		int List_smej[][] = new int[numTops+1][numTops+1];
		int weight[] = new int[numTops+1];
		
		int i, j = 1;
		
		for (i = 1; i<=numTops; ++i) {
			//System.out.println("Weight of the " + i +" vertex");
			weight[i] = weightIN[i-1];
		}
		
		
		for (i = 1; i <= numTops; ++i) {
			
			for (j = i+1; j <= numTops; ++j) {
				Table_smej[i][j] = 0;
			//	System.out.println("Аrc weight "+i +"-"+j+": ");
				Table_smej[i][j] =  graphSmej[i-1][j-1];
				Table_smej[j][i] =Table_smej[i][j];
			}	
		}
	
		int help = 0;
		//записать в список ребер
		 for (i = 1; i <= numTops; ++i) {
			 for (j = i+1; j <= numTops; j++ ) {
				 if (Table_smej[i][j] != 0) {
					 list_Edges[i+help][1] = i;
					 list_Edges[i+help][2] = j;
					 list_Edges[i+help][3] = Table_smej[i][j];
					 ++help;
				 }	 
			 }--help;
		 }
		 //список смежности
		 int ii = 0;
		 for (i = 1; i <= numTops; ++i) {
			 List_smej[i][1] = i;
		 }
		for (i = 1; i <= numTops; ++i) {ii =2;
			for (j =  1; j <=numTops; ++j) {
				if (Table_smej[i][j] != 0) {
				List_smej[i][ii] = j;++ii;
				}
			}
		}	
		Table_Smej =Table_smej;
		List_Edges = list_Edges;
		List_Smej = List_smej;
		Weight = weight;
	}
	/**
	 * Entering vertex weights
	 * @author Pestov Maxim
	*/
	public void SetTableNonWeight(int[][] Table_smejIN, int numTops) {
		//int numTops; //ввод количества вершин
		//Scanner s = new Scanner(System.in);
		//System.out.println("Input number of vertex: ");
		//numTops = s.nextInt();
		this.numTops = numTops;
		int Table_smej[][]  = new int[numTops+1][numTops+1];
		int list_Edges[][] = new int[numTops*numTops][4];
		int List_smej[][] = new int[numTops+1][numTops+1];
		
		int i,j =1;
		System.out.println("If there is a connection then 1, otherwise 0");
		for (i = 1; i <= numTops; ++i) {
			for (j = i+1; j <= numTops; ++j) {
				Table_smej[i][j] = 0;
				System.out.println("Аrc connection "+i +"-"+j+": ");
				Table_smej[i][j] = Table_smejIN[i-1][j-1];
				Table_smej[j][i] =Table_smej[i][j];
			}	
		}
		int help = 0;
		//записать в список ребер
		 for (i = 1; i <= numTops; ++i) {
			 for (j = i+1; j <= numTops; j++ ) {
				 if (Table_smej[i][j] != 0) {
					 list_Edges[i+help][1] = i;
					 list_Edges[i+help][2] = j;
					 list_Edges[i+help][3] = Table_smej[i][j];
					 ++help;
				 }	 
			 }--help;
		 }
		 
		 int ii = 0;
		 for (i = 1; i <= numTops; ++i) {
			 List_smej[i][1] = i;
		 }
		for (i = 1; i <= numTops; ++i) {ii =2;
			for (j = 1; j <=numTops; ++j) {
				if (Table_smej[i][j] != 0) {
				List_smej[i][ii] = j;++ii;
				}
			}
		}	
		Table_Smej =Table_smej;
		List_Edges = list_Edges;
		List_Smej = List_smej;
	}
	/**
	 * Graph output
	 * @param numTops Number vertexes
	 * @author Pestov Maxim
	 */
	public void outputAllTable(int numTops) {
		int i,j;
		//чисто проверить как записалось
				for (i = 1; i<=numTops;++i) {
					for (j = 1; j<=numTops;++j) {
					System.out.print(Table_Smej[i][j]+"  ");
					}
					System.out.println();
				}			
	
	 System.out.println();
	 //вывеk спика ребер
	 System.out.println(" u    v    w");
	 for( i = 1 ; i<=numTops; ++i) {
		 for( j = 1 ; j<=3; ++j) {
			 System.out.print(List_Edges[i][j]+"  ");
		 }
		 System.out.println();
	 }
	 System.out.println();
		for (i = 1; i <= numTops; ++i) {
			System.out.print(List_Smej[i][1] + "  {");
			for (j = 2; j <= numTops; ++j) {
				if (List_Smej[i][j] != 0) {
					System.out.print(List_Smej[i][j]+" ");
				}
			}System.out.println("}");
		}
	
	}	
	/**
	 * Allows you to find out the weight of the vertex of the graph
	 * @param top The vertex number of the graph
	 * @return Weight of the current vertex of the graph
	 * @author Pestov Maxim 
	 */
	public int KnowWeight(int top) {
		return Weight[top];
	}
	/**
	 * Find out adjacent vertices
	 * @param top The number of the vertex from which to find out adjacent vertices
	 * @author Pestov Maxim
	 */
	public int[] knowSmej(int top) {
		int i,j = 1;
		int trans[] = new int[7];
	
	    for (i = 2; i <= numTops; i++) {
	    	if (List_Smej[top][i] != 0) {
			 
	    		trans[j] =  List_Smej[top][i];
	    		j++;
			}
		}
	    return trans;
	    }
		/**
		 * Allows you to change the structure of the graph
		 * @param top1 The number of the graph from which the arc comes
		 * @param top2 The number of the graph into which the arc goes
		 * @param weight Arc weight of the new arc
		 */
	public void changeStruct(int top1, int top2,int weight ) {
		Table_Smej[top1][top2] = weight;
		Table_Smej[top2][top1] = weight;
		int help = 0;
		//записать в список ребер
		int i,j;
		for (i = 1; i <= numTops; ++i) {
			for (j = 1; j <= numTops; ++j) {
				 List_Edges[i][j] = 0;
				 List_Smej[i][j] = 0;
			}	
		}
		 for (i = 1; i <= numTops; ++i) {
			 for (j = i+1; j <= numTops; j++ ) {
				 if (Table_Smej[i][j] != 0) {
					 List_Edges[i+help][1] = i;
					 List_Edges[i+help][2] = j;
					 List_Edges[i+help][3] = Table_Smej[i][j];
					 ++help;
				 }
				 
			 }--help;
		 }
		  
		 //список смежности
		 int ii = 0;
		 for (i = 1; i <= numTops; ++i) {
			 List_Smej[i][1] = i;
		 }
		for (i = 1; i <= numTops; ++i) {ii =2;
			for (j = i + 1; j <=numTops; ++j) {
				if (Table_Smej[i][j] != 0) {
				List_Smej[i][ii] = j;++ii;
				}
			}
		}	 	
	}
}
