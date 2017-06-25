import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class CreateG{


	public static void main(String[] args){
		
		Graph newGraph = new Graph();
		
		//read the Nodes.txt files to create nodes a add them to the Graph
		try {
		      FileReader arq = new FileReader("Nodes.txt");
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String nodeInfo = lerArq.readLine();
		      int aux = 0;
		      while (nodeInfo != null) {
		    	String splittedNodeInfo[] = nodeInfo.split("-");
		    	//splittedNodeInfo[0] = name
		    	//splittedNodeInfo[1] = generation
		        Node newNode = new Node (aux, 0);
		        newNode.setName(splittedNodeInfo[0]);
		        newNode.setGeneration(Integer.parseInt(splittedNodeInfo[1]));
		        newGraph.addNode(newNode); 

		        nodeInfo = lerArq.readLine();
		        aux++;
		      }
		 
		      arq.close();
		 } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		 }
		
		//read AdjacencyList to find each neighbor
		//each line of the adjancency list has the format: targetid.relation-targetid.relation ...
		try {
			
		      FileReader arq = new FileReader("AdjacencyList.txt");
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String line = lerArq.readLine();
		      int aux = 0;
		      while (line != null) {
		    	  
		    	  if (!line.trim().equals("")){ //making sure that the line isn't empty
		    		 
		    		  String [] neightborIdAndRelation = line.split("-");
		    		  for(int aux2=0; aux2<neightborIdAndRelation.length; aux2++){
		    			  
		    			  String [] neighbor = new String[2]; 
		    			  neighbor = neightborIdAndRelation[aux2].split("/");
		    			  //neighbor[0] = neighbor id, 
		    			  //neighbor[1] = neighbor relation

		    			  Edge newEdge = new Edge(newGraph.findNodebyId(aux),
		    					  				newGraph.findNodebyId(Integer.parseInt(neighbor[0])),
		    					  				neighbor[1]);
		    			  
		    			  //adding the new edge to the list of neighbor of the node
		    			  newGraph.findNodebyId(aux).addNeighbor(newEdge);
		    			  //adding the new edge to the list of edges of the graph
		    			  newGraph.addEdge(newEdge);
	
		    		  }
		    	  }
	    		  line = lerArq.readLine();
	    		  aux++;
		      }
		 
		      arq.close();
		      
		 } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		 }
		
		//testing new nodes search
		//newGraph.getNodeList().get(0).buscaProfundidade();
		
		// Simple Menu

		JPanel panel = new JPanel();
		JButton plotGraphButton = new JButton("Plotar Gráfico");
		JButton minimumTreeButton = new JButton("Árvore Mínima");
		JButton searchDistanceButton = new JButton("Distância entre nós");

		plotGraphButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JGraphXFrame.createFrame(newGraph);
			}
		});
		minimumTreeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				newGraph.prim();
			}
		});
		searchDistanceButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int value = newGraph.distanceBetweenNodes(newGraph.findNodebyId(3),newGraph.findNodebyId(0));
				System.out.printf("Origin node: %s, End node: %s, Distance: %d\n",newGraph.findNodebyId(3).getName(),newGraph.findNodebyId(0).getName(),value);

			}
		});


		panel.add(plotGraphButton);
		panel.add(minimumTreeButton);
		panel.add(searchDistanceButton);

		JFrame mainWindow = new JFrame("TF EDA 2");
		mainWindow.add(panel);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.pack();
		mainWindow.setVisible(true);

	}

}
