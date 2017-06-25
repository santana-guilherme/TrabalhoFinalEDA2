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
	
	public boolean areNodesInTheSameGeneration(Node a, Node b){
		if(a.getGeneration() == b.getGeneration()){
			return true;
		}
		return false;
	}	
	public int calculateDistance(ArrayList<Edge> history){
		Edge node = history.get(history.size() - 1);
		int distance = 0;
		while(node.getSource() != null){
			distance++;
			Node originNode = node.getSource();
			for(Edge temp: history){
				if(originNode == temp.getTarget()){
					node = temp;
					break;
				}
			}
		}
		return distance;
	}
	public int distanceBetweenNodes(Node origin, Node end){
		if(origin == null || end == null){return -1;}
		ArrayList<Edge> history = new ArrayList<Edge>();
		history.add(new Edge(null,origin,"irrelevant"));

		Stack<Node> stack = new Stack<Node>();
		stack.push(origin);
		origin.markVisited();
		while(!stack.isEmpty()){
			Node node = stack.pop();
			for(Edge nodes: node.getNeighbors()){
				Node neighbor = nodes.getTarget();
				if(!neighbor.hasBeenVisited()){
					neighbor.markVisited();
					stack.push(neighbor);
					history.add(new Edge(node,neighbor,"irrelevant"));
					if(history.get(history.size() -1).getTarget() == end)
						return calculateDistance(history)-1;
				}
			}
		}
		return 0;
	}

	//TODO rebuild these methods to the new graph structure

	
	private int checkMinimum(ArrayList<Edge> nodes){
		int minimumValue = 0;// index minimum value
		for(int i = 1; i < nodes.size(); i++){
			if( nodes.get(i).getWeight() < nodes.get(minimumValue).getWeight() ){
				minimumValue = i;
			}
		}
		return minimumValue;
	}
	

    // @Test
	public void checkMinimumTest(){
		ArrayList<Edge> numbers = new ArrayList<Edge>();
		Node source = new Node(1, 0);
		Node end = new Node(2, 0);
		numbers.add(new Edge(source,end,"teste"));
		numbers.add(new Edge(source,end,"teste"));
		numbers.add(new Edge(source,end,"teste"));
		numbers.add(new Edge(source,end,"teste"));
		numbers.add(new Edge(source,end,""));
		numbers.get(0).setWeight(1);
		numbers.get(1).setWeight(23);
		numbers.get(2).setWeight(-9);
		numbers.get(3).setWeight(0);
		int minimumIndex = checkMinimum(numbers);
		if(minimumIndex == 2){
			System.out.printf(" true");
		}else{
			 System.out.printf("false");
		}
	}
	// endTest


	public String prim(){
		if(nodeList.isEmpty()){return null;}
		String pathMinimumTree = "Prim:\n";
		ArrayList<Edge> nodes = new ArrayList<Edge>();

		Node firstNode = nodeList.get(0);
		firstNode.markVisited();

		for(Edge nodeEdges: firstNode.getNeighbors()){
			nodes.add(nodeEdges);
		}

		while(!nodes.isEmpty()){
			int minimumIndex = checkMinimum(nodes);
			Node minimumCostEndNode = nodes.get(minimumIndex).getTarget();

			if(!minimumCostEndNode.hasBeenVisited()){
				Node minimumCostOriginNode = nodes.get(minimumIndex).getSource();
				pathMinimumTree += String.format("%s ->%s\n",minimumCostOriginNode.getName(),minimumCostEndNode.getName());
				minimumCostOriginNode.markVisited();
				minimumCostEndNode.markVisited();
				for(Edge node: minimumCostEndNode.getNeighbors()){
					if(!node.getTarget().hasBeenVisited()){
						nodes.add(node);
					}
				}
			}else{
				nodes.remove(minimumIndex);
			}
		}
		pathMinimumTree += " ";
		return pathMinimumTree;
	}

	public void resetGraph(){ // temporary function
		for(Node node : nodeList){
			node.setNotVisited();
		}
	}
	/*
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
