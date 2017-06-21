//Undirected grap
import java.util.*;
public class Node{

	private int id;
	private String name;
	private boolean visited;
	private ArrayList <Edge> neighbors;

	public Node(int value){
		setNodeId(value);
		this.visited = false;
		neighbors = new ArrayList<Edge>();
	}

	public void  addNeighbor(Edge newNeighbor){
		neighbors.add(newNeighbor);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public void setNodeId(int value){
		this.id = value;
	}

	public int getNodeId(){
		return this.id;
	}

	public boolean hasBeenVisited(){
		return this.visited;
	}

	public void markVisited(){
		this.visited = true;
	}

	public void clearNeighborhood(){
				this.neighbors = new ArrayList <Edge>();

	}

	public ArrayList<Edge> getNeighbors(){
				return neighbors;
	}
	
	//TODO rebuild these methods to the new graph structure

	/*
	public void showNeighbors(){
		System.out.printf("%d ->",id);
		for(Map.Entry<Integer, Node> node : neighbors.entrySet()){
			System.out.printf("%d ",node.getValue().getNodeId() );
		}
		System.out.printf("\n");
	}

	public void bFS(){
		System.out.printf("%d ->",this.getNodeId());
		Stack<Node> stack = new Stack<Node>();
		stack.push(this);
		this.markVisited();

		while(!stack.empty()){

			Node stackNode = stack.pop();
			for(Map.Entry<Integer, Node> node: stackNode.neighbors.entrySet()){
				Node neighborNode = node.getValue();
				if(neighborNode.hasBeenVisited() == false){
					neighborNode.markVisited();
					stack.push(neighborNode);
					System.out.printf("%d ",neighborNode.getNodeId());
				}
			}
			System.out.printf("|");
		}
		System.out.printf("\n");
	}

	public void buscaProfundidade(){
		System.out.printf("%s ",this.getName());
		this.markVisited();
		for(Map.Entry<Integer, Node> node: this.neighbors.entrySet()){
			Node neighborNode = node.getValue();
			if(neighborNode.hasBeenVisited() == false ){
				neighborNode.buscaProfundidade();
			}
		  }
	}

	public void buscaProfundidadeByGraph(ArrayList<Node> graph){
		for (int aux=(graph.size()-1); aux>=0; aux--){
			System.out.printf("%d ",graph.get(aux).getNodeId());
			graph.get(aux).markVisited();
			for(Map.Entry<Integer, Node> node: graph.get(aux).neighbors.entrySet()){
				Node neighborNode = node.getValue();
				if(neighborNode.hasBeenVisited() == false ){
					neighborNode.buscaProfundidadeByGraph(graph);
				}
			}
		}
	}

	*/
}
