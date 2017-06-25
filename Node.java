import java.util.*;
import com.mxgraph.view.mxGraph;

public class Node{

	private int id;
	private String name;
	private boolean visited;
	private boolean plotted;
	private int generation; //the generation he belongs on the family tree
	private ArrayList <Edge> neighbors;

	public Node(int value, int generation){
		setNodeId(value);
		setGeneration(generation);
		this.visited = false;
		this.plotted = false;
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

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}
	
	public boolean isPlotted() {
		return plotted;
	}

	public void setPlotted(boolean plotted) {
		this.plotted = plotted;
	}

	public boolean hasBeenVisited(){
		return this.visited;
	}

	public void setNotVisited(){
		this.visited = false;
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
	
	public boolean isNeighbor(Node son){
		
		for (int i = 0; i<this.neighbors.size(); i++){
			if ((son.equals(this.neighbors.get(i).getTarget()))){
				return true;
			}
		}
		return false;
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


	*/
	
	public String bFS(){
		String bfsResult = "BFS:\n"	;
		bfsResult += String.format("%s, ",this.getName());
		Stack<Node> stack = new Stack<Node>();
		stack.push(this);
		this.markVisited();
		
		bfsResult += "\n";
		while(!stack.empty()){
			
			bfsResult += "\n";
			Node stackNode = stack.pop();
			for(int aux=0; aux<stackNode.getNeighbors().size(); aux++){
				
				Node neighborNode = stackNode.getNeighbors().get(aux).getTarget();
				if(neighborNode.hasBeenVisited() == false){
					stack.push(neighborNode);
					neighborNode.markVisited();
					bfsResult += String.format("%s, ",neighborNode.getName());
				}
			}
		}
		return bfsResult;
	}
	
	public String buscaProfundidade(){
		String dfsResult = "";
		dfsResult += String.format("%s, ",this.getName());
		this.markVisited();
		for(int i =0; i<this.getNeighbors().size(); i++){
			Node neighborNode = this.getNeighbors().get(i).getTarget();
			if(neighborNode.hasBeenVisited() == false ){
				dfsResult += neighborNode.buscaProfundidade();
			}
		}
		return dfsResult;
	}
	
	public void plotNodesAndItsEdgesByBFS(mxGraph graph, Object defaultParent, int x, int y, ArrayList<Object> plottedNodes){
		Object plottedNode = graph.insertVertex(defaultParent, null,this.getName(), x, y, 130, 30);
		this.setPlotted(true);
		plottedNodes.add(plottedNode);
		y = y + 70;
		
		for (int i =0; i<this.getNeighbors().size(); i++){
			if (this.getNeighbors().get(i).getTarget().plotted == false){
				this.getNeighbors().get(i).getTarget().plotNodesAndItsEdgesByBFS(graph, defaultParent, x, y, plottedNodes);
				x = x + 200;
			}
			
		}

	}
}
