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

	public void clearNeighborhood(){
				this.neighbors = new HashMap<Integer, Node>();

	}

	public Map<Integer ,Node> getNeighbors(){
				return neighbors;
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

	public void buscaProfundidadeByGraph(ArrayList<Node> graph){
		for (int aux=(graph.size()-1); aux>=0; aux--){
			System.out.printf("%d ",graph.get(aux).getValue());
			graph.get(aux).markVisited();
			for(Map.Entry<Integer, Node> node: graph.get(aux).neighbors.entrySet()){
				Node neighborNode = node.getValue();
				if(neighborNode.hasBeenVisited() == false ){
					neighborNode.buscaProfundidadeByGraph(graph);
				}
			}
		}
	}
		
	private int checkMinimum(ArrayList<Integer> cost){
		int minimumValue = 0;// index minimum value
		for(int i = 1; i < cost.size(); i++){
			if( cost.get(i) < cost.get(minimumValue) ){
				minimumValue = i;
			}
		}
		return minimumValue;
	}
    // @Test
	public void checkMinimumTest(){
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(002);
		numbers.add(300);
		numbers.add(-9);
		numbers.add(3);
		numbers.add(5);

		int minimumIndex = checkMinimum(numbers);
		if(minimumIndex == 3){
			System.out.printf(" true");
		}else{
			 System.out.printf("false");
		}
	}
	// endTest

	//TODO: Make Prim
	public void prim(){
		ArrayList<Integer> cost = new ArrayList<Integer>();
		ArrayList<Node> originNode = new ArrayList<Node>();
		ArrayList<Node> endNode = new ArrayList<Node>();

		this.markVisited();
		for(Map.Entry<Integer, Node> node: this.neighbors.entrySet()){
			originNode.add(this);
			cost.add(node.getKey());
			endNode.add(node.getValue());
		}

		while(!cost.isEmpty()){
			int minimumIndex = checkMinimum(cost);
			Node minimumCostEndNode = endNode.get(minimumIndex);

			if(!minimumCostEndNode.hasBeenVisited()){
				Node minimumCostOriginNode = originNode.get(minimumIndex);
				System.out.printf("%d ->%d ",minimumCostOriginNode.getValue(),minimumCostEndNode.getValue());
				minimumCostOriginNode.markVisited();
				minimumCostEndNode.markVisited();
				for(Map.Entry<Integer, Node> node: minimumCostEndNode.neighbors.entrySet()){
					if(!node.getValue().hasBeenVisited()){
						originNode.add(minimumCostEndNode);
						cost.add(node.getKey());
						endNode.add(node.getValue());
					}
				}
			}else{
				cost.remove(minimumIndex);
				originNode.remove(minimumIndex);
				endNode.remove(minimumIndex);
			}
		}
		System.out.println(" ");
	}
	public static void getReversedGraph(ArrayList<Node> nodeList){
    //creates a new list of nodes that has the same values as the original one
    ArrayList<Node> reversedGraph = new ArrayList();
    for (Node node: nodeList){
        Node newNode = new Node(node.getValue());
        reversedGraph.add(newNode);
    }

    //running through the list of adjacency list backwards
    for (int aux=(nodeList.size()-1); aux>=0; aux--){
        Node currentNode = nodeList.get(aux);
        //running through the adjacency list
        for(int aux2= 0; aux2<currentNode.getNeighbors().size(); aux2++){
            //here we get a Node from the reversedGraph and place it as
            //a neighbor in the adjacency list of the reverseGraph
            for ( Map.Entry<Integer, Node> entry : currentNode.getNeighbors().entrySet()) {
                Node newNode = new Node(0);
                newNode = reversedGraph.get(aux2);
                Integer newWeight = entry.getKey();
                reversedGraph.get(aux2).addNeighbor(newNode, newWeight);

            }
        }
    }
    //print the reverse graph using a search
    System.out.println("Busca em profundidade no grafo reverso:");
    reversedGraph.get(0).buscaProfundidadeByGraph(reversedGraph);
  }
	
}
