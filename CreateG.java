import java.util.*;
import java.util.ArrayList;

class CreateG{

	public static void associateAsNeighbors(Node firstNode, Node secondNode, int weight){ // for bi directional graphs
		firstNode.addNeighbor(secondNode,weight);
		secondNode.addNeighbor(firstNode,weight);
	}

	public static void main(String[] args){
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
		secondNode.prim();

		//creating a list of nodes (a graph)
		ArrayList <Node> graph = new ArrayList();
		graph.add(firstNode);
		graph.add(secondNode);
		graph.add(thirdNode);
		graph.add(fourthNode);

		fourthNode.getReversedGraph(graph);

	}

}
