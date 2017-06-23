import java.util.*;
class Graph{
	
	private ArrayList<Node> nodeList;
	private ArrayList<Edge> edgeList;

	public Graph(){
		nodeList = new ArrayList<Node>();
		edgeList = new ArrayList<Edge>();
	}

	public ArrayList<Edge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(ArrayList<Edge> edgeList) {
		this.edgeList = edgeList;
	}

	public void setNodeList(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}

	public void addNode(Node node){
		this.nodeList.add(node);
	}
	
	public ArrayList<Node> getNodeList(){
		return nodeList;
	}
	
	public void addEdge(Edge newEdge){
		this.edgeList.add(newEdge);
	}
	
	public Node findNodebyId(int id){
		for(int aux=0; aux<nodeList.size(); aux++){
			if (nodeList.get(aux).getNodeId() == id)
				return nodeList.get(aux);
		}
		
		return null;
	}
	
	public Node findNodebyName(String name){
		for(int aux=0; aux<nodeList.size(); aux++){
			if (nodeList.get(aux).getName().equalsIgnoreCase(name))
				return nodeList.get(aux);
		}
		
		return null;
	}
	
	public boolean areNodesBrothers(Node a, Node b){
		Node parent1 = new Node(0);
		Node parent2 = new Node(0);
		for (int aux=0; aux<this.getEdgeList().size(); aux++){
			if(this.getEdgeList().get(aux).getTarget().equals(a)){
				parent1 = this.getEdgeList().get(aux).getSource();
				break;
			}
		}
		for (int aux=0; aux<this.getEdgeList().size(); aux++){
			if(this.getEdgeList().get(aux).getTarget().equals(b)){
				parent2 = this.getEdgeList().get(aux).getSource();
				break;
			}
		}
		if (parent1.equals(parent2)){
			return true;
		}
		return false;
	}
	
	
	//TODO rebuild these methods to the new graph structure

	/*
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


	public void prim(){
		if(nodeList.isEmpty()){return;}
		ArrayList<Integer> cost = new ArrayList<Integer>();
		ArrayList<Node> originNode = new ArrayList<Node>();
		ArrayList<Node> endNode = new ArrayList<Node>();

		Node firstNode = nodeList.get(0);
		firstNode.markVisited();
		for(Map.Entry<Integer, Node> node: firstNode.getNeighbors().entrySet()){
			originNode.add(firstNode);
			cost.add(node.getKey());
			endNode.add(node.getValue());
		}

		while(!cost.isEmpty()){
			int minimumIndex = checkMinimum(cost);
			Node minimumCostEndNode = endNode.get(minimumIndex);

			if(!minimumCostEndNode.hasBeenVisited()){
				Node minimumCostOriginNode = originNode.get(minimumIndex);
				System.out.printf("%d ->%d ",minimumCostOriginNode.getNodeId(),minimumCostEndNode.getNodeId());
				minimumCostOriginNode.markVisited();
				minimumCostEndNode.markVisited();
				for(Map.Entry<Integer, Node> node: minimumCostEndNode.getNeighbors().entrySet()){
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
	public void getReversedGraph(){
    //creates a new list of nodes that has the same values as the original one
    ArrayList<Node> reversedGraph = new ArrayList<Node>();
    for (Node node: this.getNodeList() ){
        Node newNode = new Node(node.getNodeId());
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

	*/
}
