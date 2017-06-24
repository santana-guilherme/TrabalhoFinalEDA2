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
	private boolean searchOnStack(Node end, Stack<Node> stack){
		for(Node node: stack ){
			if(node == end){
				return true;
			}
		}
		return false;
	}
	public int distanceBetweenNodes(Node origin, Node end){
		//realizo uma busca em largura e a cada largura incrementar um ao contador
		//caso eu retorne para o primeriro nó a contagem é zerada
		int count  = 0;
		Stack<Node> stack = new Stack<Node>();
		stack.push(origin);
		origin.markVisited();
		while(!stack.isEmpty()){
			Node node = stack.pop();
			if(searchOnStack(end,stack)){break;}
			count++;
			for(Edge nodes: node.getNeighbors()){
				Node neighbor = nodes.getTarget();
				if(neighbor.hasBeenVisited() == false){
					neighbor.markVisited();
					stack.push(neighbor);
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
		Node source = new Node(1);
		Node end = new Node(2);
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


	public void prim(){
		if(nodeList.isEmpty()){return;}
		ArrayList<Integer> cost = new ArrayList<Integer>();
		ArrayList<Node> originNode = new ArrayList<Node>();
		ArrayList<Node> endNode = new ArrayList<Node>();

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
				System.out.printf("%s ->%s ",minimumCostOriginNode.getName(),minimumCostEndNode.getName());
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
		System.out.println(" ");
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
