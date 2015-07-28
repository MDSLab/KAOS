import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

//import teo.isgci.gui.JGraphXCanvas;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.view.mxGraphView;

public class ZoomDemo implements ActionListener {

	private mxGraphComponent component;
	private ListenableGraph<String, DefaultEdge> graph = new ListenableDirectedGraph<String, DefaultEdge>(
			DefaultEdge.class);
	private JGraphTXAdapter<String, DefaultEdge> g;
	private JFrame mainFrame = new JFrame("ZoomDemo");
	private JButton zoomToFit = new JButton("Zoom to fit");

	public ZoomDemo() {
		String s1 = "0,2 Colorable";
		String s2 = "PURE-2-DIR";
		String s3 = "cubical";
		String s4 = "modular";
		String s5 = "tree convex";
		String s6 = "star convex";
		String s7 = "triad convex";

		graph.addVertex(s1);
		graph.addVertex(s2);
		graph.addVertex(s3);
		graph.addVertex(s4);
		graph.addVertex(s5);
		graph.addVertex(s6);
		graph.addVertex(s7);

		graph.addEdge(s1, s2);
		graph.addEdge(s1, s3);
		graph.addEdge(s1, s4);
		graph.addEdge(s1, s5);
		graph.addEdge(s5, s6);
		graph.addEdge(s5, s7);

		g = new JGraphTXAdapter<String, DefaultEdge>(graph, "noLabel=1",
				"shape=circle;perimeter=circlePerimeter");
		g.setCellsResizable(true);
		g.setCellsEditable(true);
		g.setCellsMovable(true);
		g.setAutoSizeCells(true);
		g.setCellsDeletable(true);
		
		for (Object vertex : g.getChildCells(g.getDefaultParent(), true, false)) {
			g.updateCellSize(vertex, true);
		}
		
		mxHierarchicalLayout layout = new mxHierarchicalLayout(g);
		layout.execute(g.getDefaultParent());
		
		//component = new JGraphXCanvas(g);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JButton zoomToFit = new JButton("Zoom to fit");
		zoomToFit.addActionListener(this);
		mainFrame.setLayout(new FlowLayout());
		mainFrame.getContentPane().add(component);
		mainFrame.getContentPane().add(zoomToFit);
		mainFrame.setSize(800, 600);
		mainFrame.setVisible(true);

	}

	public static void main(String[] args) throws InterruptedException {
		ZoomDemo demo = new ZoomDemo();
		mxRubberband rubber = new mxRubberband(demo.component);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		double newScale = 1;
//
//		Dimension graphSize = component.getGraphControl().getSize();
//		Dimension viewPortSize = component.getViewport().getSize();
//
//		int gw = (int) graphSize.getWidth();
//		int gh = (int) graphSize.getHeight();
//
//		if (gw > 0 && gh > 0) {
//			int w = (int) viewPortSize.getWidth();
//			int h = (int) viewPortSize.getHeight();
//
//			newScale = Math.min((double) w / gw, (double) h / gh);
//		}
//
//		component.zoom(newScale);
		
		mxGraphView view = component.getGraph().getView();
		int compLen = component.getWidth();
		double viewLen = view.getGraphBounds().getWidth();
		view.setScale(compLen/viewLen * view.getScale());
	}
}