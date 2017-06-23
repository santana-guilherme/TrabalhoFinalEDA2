import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class CreateG{


	public static void main(String[] args){
		
		Graph newGraph = new Graph();
		
		//read the Nodes.txt files to create nodes a add them to the Graph
		try {
		      FileReader arq = new FileReader("Nodes.txt");
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String names = lerArq.readLine();
		      int aux = 0;
		      while (names != null) {
		        Node newNode = new Node (aux);
		        newNode.setName(names);
		        newGraph.addNode(newNode); 

		        names = lerArq.readLine();
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
		
		JGraphXFrame.createFrame(newGraph);

		newGraph.getNodeList().get(0).buscaProfundidade();
		
		/*
		
		Node firstNode = new Node(5);
		Node secondNode = new Node(7);
		associateAsNeighbors(firstNode,secondNode,3);
		Node thirdNode = new Node(10);
		associateAsNeighbors(secondNode,thirdNode,7);
		associateAsNeighbors(thirdNode,firstNode,10);
		Node fourthNode = new Node(15);
		associateAsNeighbors(fourthNode,firstNode,5);
		firstNode.showNeighbors();
		secondNode.showNeighbors();
		thirdNode.showNeighbors();
		fourthNode.showNeighbors();
		associateAsNeighbors(fourthNode,thirdNode,18);
		Node fifthNode = new Node(3);
		associateAsNeighbors(fifthNode,fourthNode,9);
		associateAsNeighbors(fifthNode,thirdNode,6);
		System.out.println("Busca em Profundidade for secondNode");
		//secondNode.bFS();
		//secondNode.buscaProfundidade();
		//fourthNode.buscaProfundidade();
		System.out.println(" ");

		//creating a list of nodes (a graph)
		Graph firstGraph = new Graph();
		firstGraph.addNode(firstNode);
		firstGraph.addNode(secondNode);
		firstGraph.addNode(thirdNode);
		firstGraph.addNode(fourthNode);
		firstGraph.prim();
		
		*/
	}

}
