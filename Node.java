//Undirected grap
import java.util.*;
public class Node{

	private int value;
	private boolean visited;
	private Map<Integer ,Node> neighbors;

	public Node(int value){
		setValue(value);
		this.visited = false;
		neighbors = new HashMap<Integer ,Node>();
	}

	public void  addNeighbor(Node node, int weight){
		neighbors.put(weight,node);
		//node.addNeighbor(this,weight); Errado
		//TODO: Add this.node to neighbors hashmap of nodes
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return this.value;
	}

	public boolean hasBeenVisited(){
		return this.visited;
	}

	public void markVisited(){
		this.visited = true;
	}

	public void showNeighbors(){
		System.out.printf("%d ->",value);
		for(Map.Entry<Integer, Node> node : neighbors.entrySet()){
			System.out.printf("%d ",node.getValue().getValue() );
		}
		System.out.printf("\n");
	}

	public void bFS(){
		System.out.printf("%d ->",this.getValue());
		Stack<Node> stack = new Stack<Node>();
		stack.push(this);
		this.markVisited();

		while(!stack.empty()){

			Node stackNode = stack.pop();
			//System.out.printf("Top%d\n",stack.peek().getValue());
			for(Map.Entry<Integer, Node> node: stackNode.neighbors.entrySet()){
				Node neighborNode = node.getValue();
				if(neighborNode.hasBeenVisited() == false){
					neighborNode.markVisited();
					stack.push(neighborNode);
					System.out.printf("%d ",neighborNode.getValue());
				}
			}
			System.out.printf("|");
		}
		System.out.printf("\n");
	}

	public void buscaProfundidade(){
		System.out.printf("%d ",this.getValue());
		this.markVisited();
		for(Map.Entry<Integer, Node> node: this.neighbors.entrySet()){
			Node neighborNode = node.getValue();
			if(neighborNode.hasBeenVisited() == false ){
				neighborNode.buscaProfundidade();
			}
		  }
	}
	
	//TODO: Make Prin
	//TODO: Grafo Inverso
}
