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
	    
	    
	    ArrayList<Object> plottedNodes = new ArrayList<Object>();

	    //plotting the nodes
	    int x = 0, y =0;
	    
	    for(int aux=0; aux<newGraph.getNodeList().size(); aux++){
	    	String currentNodeName = new String();
	    	currentNodeName = newGraph.getNodeList().get(aux).getName();
	    	
		    if (aux > 0 && newGraph.areNodesBrothers(newGraph.getNodeList().get(aux-1), newGraph.getNodeList().get(aux))){
		    	x = x + 150;
		    		
		    }else{
		    	x = 0;
		    	y = y + 90;
		    }

	    	Object plottedNode = graph.insertVertex(defaultParent, null,currentNodeName, x, y, 130, 30);
	    	
	    	plottedNodes.add(plottedNode);

	    }

	    
	    //plotting edges
	    for(int aux=0; aux<plottedNodes.size(); aux++){
	    	Node source = newGraph.getNodeList().get(aux);
	    	for(int aux2=0; aux2<source.getNeighbors().size(); aux2++){
	    		String relation = source.getNeighbors().get(aux2).getRelation();
	    		int targetId = source.getNeighbors().get(aux2).getTarget().getNodeId();
	    		graph.insertEdge(defaultParent, null, relation, plottedNodes.get(aux), plottedNodes.get(targetId));
	    		
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 700);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
