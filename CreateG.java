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
		
		
		// Simple Menu

		JPanel panel = new JPanel();
		JButton plotGraphButton = new JButton("Plotar Grafo");
		JButton minimumTreeButton = new JButton("Árvore Mínima");
		JButton searchDistanceButton = new JButton("Distância entre nós");
		JButton bFSButton = new JButton ("Busca em Largura");
		JButton dFSButton = new JButton("Busca em Profundidade");

		plotGraphButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JGraphXFrame.createFrame(newGraph);
			}
		});
		minimumTreeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String primPath = newGraph.prim();
				JOptionPane.showMessageDialog(null,primPath);
				newGraph.resetGraph();
			}
		});
		searchDistanceButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String originNodeName = JOptionPane.showInputDialog(null,"Nó origem:","Torrhen Stark");
				String seekNodeName = JOptionPane.showInputDialog(null,"Nó destino:","Benjen Stark");
				if(originNodeName != null && seekNodeName != null){
					int value = newGraph.distanceBetweenNodes(newGraph.findNodebyName(originNodeName),newGraph.findNodebyName(seekNodeName));
					String msg;
					if(value == -1)
						msg = "There are no conection between this two nodes";
					else
						msg = String.format("Origin node: %s\nEnd node: %s\nDistance: %d\n",originNodeName,seekNodeName,value);
					JOptionPane.showMessageDialog(null,msg);
				}
				newGraph.resetGraph();
			}
		});
		bFSButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String originNodeName = JOptionPane.showInputDialog(null, "Nó de origem", "Torrhen Stark");
				String msg = newGraph.findNodebyName(originNodeName).bFS();
				JOptionPane.showMessageDialog(null,msg);
				newGraph.resetGraph();
			}
			
		});
		dFSButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String targetNode = JOptionPane.showInputDialog(null,"Nó:", "Torrhen Stark");
				if(!targetNode.isEmpty()){
					Node node = newGraph.findNodebyName(targetNode);
					String dfs = node.buscaProfundidade();
					JOptionPane.showMessageDialog(null,dfs);
					newGraph.resetGraph();
				}else{
					JOptionPane.showMessageDialog(null,"É necessário inserir o nome de um nó");
				}

			}
		});

		panel.add(plotGraphButton);
		panel.add(minimumTreeButton);
		panel.add(searchDistanceButton);
		panel.add(bFSButton);
		panel.add(dFSButton);

		JFrame mainWindow = new JFrame("TF EDA 2");
		mainWindow.add(panel);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.pack();
		mainWindow.setVisible(true);

	}

}
