import java.util.ArrayList;

import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class JGraphXFrame extends JFrame{
	
	public JGraphXFrame(Graph newGraph){
		super("JGraphXFrame");
	    mxGraph graph = new mxGraph();
	    Object defaultParent = graph.getDefaultParent();
	    graph.getModel().beginUpdate();
	    
	    /*
	     * THIS WORK:
	    Object v1 = graph.insertVertex(defaultParent, null, "Hello", 20, 20, 80, 30);
	    Object v2 = graph.insertVertex(defaultParent, null, "World", 240, 150, 80, 30);
	    graph.insertEdge(defaultParent, null, "Edge", v1, v2);
	    
	    */
	    
	    ArrayList<Object> plottedNodes = new ArrayList<Object>();
	    
	    //plotting the nodes
	    int x = 0, y = 0;
	    for(int aux=0; aux<newGraph.getNodeList().size(); aux++){
	    	String currentNodeName = new String();
	    	currentNodeName = newGraph.getNodeList().get(aux).getName();
	    	Object plottedNode = graph.insertVertex(defaultParent, null,currentNodeName, 20*x, 20*y, 130, 30);
	    	
	    	y = y +3;
	    	if (aux%5 == 4){
	    		x = x+10;
	    		y = 0;
	    	}
	    	
	    	plottedNodes.add(plottedNode);
	    }
	    
	    //plotting edges
	    for(int aux=0; aux<newGraph.getNodeList().size(); aux++){
	    	Node currentNode = newGraph.getNodeList().get(aux);
	    	for(int aux2=0; aux2<currentNode.getNeighbors().size(); aux2++){
	    		//finding the name of the neighbor and searching for it in the list of plotted nodes
	    		String neighborName = currentNode.getNeighbors().get(aux2).getName();
	    		int neighborIndex;
	    		neighborIndex = newGraph.findNodebyName(neighborName).getNodeId();
	    		//inserting the edge
	    		graph.insertEdge(defaultParent, null, "Test", plottedNodes.get(aux), plottedNodes.get(neighborIndex));
	    		//TODO: replace "Test" by the name of relation
	    	}
	    }
	    
	    graph.getModel().endUpdate();
	    mxGraphComponent graphComponent = new mxGraphComponent(graph);
	    getContentPane().add(graphComponent);

	    //attributes to edit the graph during execution
	    graph.setCellsEditable(false);
	    graph.setCellsMovable(true);  
	    graph.setCellsResizable(false);
	    graph.setCellsSelectable(true); 
	    graph.setEnabled(true); 
	     
	    graphComponent.setConnectable(false); // Inhibit edge creation in the graph.
	}

	public static void createFrame(Graph newGraph) {
		JGraphXFrame frame = new JGraphXFrame(newGraph);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
