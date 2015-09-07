/**
 	* JGrapht Adapter Editor for:
 	* Modeling altruism and selfishness in welfare dynamics: The role of nonlinear interactions
 	* Kinetic Theory for Active Particles (KTAP)
 	* @author Ing. Giulio De Meo 
**/
//import DeMeo.*;
import DeMeo.Cluster;
import DeMeo.WriteXMLFile;
import DeMeo.ReadXMLFile;
import DeMeo.ChartBar;
import DeMeo.GraphCurve;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.*;
import java.util.List;
//import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;
//import org.jgraph.graph.DefaultEdge;//non crea edge ma crea solo una linea senza connessione
import org.jgrapht.graph.ListenableDirectedGraph;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxGraphView;
import com.mxgraph.view.mxGraph;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.io.graphml.mxGraphMlConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.FileWriter; 
import java.io.IOException; 
import javax.xml.transform.TransformerConfigurationException; 
import com.mxgraph.swing.view.mxCellEditor;
import com.mxgraph.swing.view.mxICellEditor;
import com.mxgraph.layout.hierarchical.stage.mxCoordinateAssignment;
import org.jgraph.graph.GraphLayoutCache;
import org.xml.sax.SAXException; 
import org.jgraph.graph.GraphConstants;
import com.mxgraph.model.mxGraphModel;
import java.awt.image.BufferedImage;
import com.mxgraph.util.mxCellRenderer;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;
import com.mxgraph.util.mxUndoManager;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxUndoableEdit;
//import org.jgraph.graph.*;
import org.jgrapht.ext.*;
import org.jgrapht.ext.GraphMLExporter; 
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.graph.SimpleDirectedWeightedGraph; 
import org.jgrapht.ext.VertexNameProvider;
import org.jgrapht.ext.IntegerNameProvider;
import org.jgrapht.ext.EdgeNameProvider;
import com.mxgraph.layout.hierarchical.model.mxGraphAbstractHierarchyCell;
import com.mxgraph.layout.hierarchical.model.mxGraphHierarchyEdge;
import javax.swing.JTable;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.event.*;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxRectangle;
import javax.swing.border.TitledBorder;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;


@SuppressWarnings("unchecked")

public class Demo 
{
//	public Vector<Nodo> n=new Vector<Nodo>();//vector dei nodi  inseriti nel grafico
//	public Vector<Edge> a=new Vector<Edge>();//vector degli archi inseriti nel grafico
    static  JButton nodo_but;
    static  JButton rect_but;
    static  JButton triangle_but;
    public static JButton freccia_but;    
    static JButton curve_but;
    static JButton piu_but;
    static JButton meno_but;
    static JButton go_but;
    public static JFrame frame;
    static JButton canc_but;
    public static int i=0;
    public String saveFileAs;
    public mxGraphView view;
    public mxUndoManager undoMgr = new mxUndoManager();
    public String edgeStyle;
    private  JGraphModelAdapter<String,DefaultEdge> jgAdapter;
    public boolean tabOpen;
    public static JFrame frameTabN;
	public static JTable table;
	public static Vector<String> items;
	public int nPos; //posizione del vertex selezionato all'interno del vector n
	public int aPos; //posizione dell' edge  selezionato all'interno del vector a
	public String oldLabel;
	public boolean labelChanged;
	public static final String fillcolor = "#D3D3D3";//colore turchese di background cella con probabilità modificata !=0
	public static double sizeChangedCell;//dimensione cella proporzionale alla probabilità inserita
	public static JPanel panelVar; //pannello di destra contenente la tabella VARIABILI GLOBALI mu, eta0, beta0
	public static JPanel panelProp; //pannello di destra contenente le tabelle proprietà	
	JCheckBoxMenuItem resizeMi;
	JCheckBoxMenuItem tabProbabMi,tabVarMi,tabPropMi,colorMi,edgeLabelMi;	
	public String mode;//cooperation/competition, consent/dissent o User Defined		
	public static int screenX,screenY; //risoluzione schermo		
	public static int mu; // mu=7
	public static double eta0; //eta0= 0.6
	public static double beta0; //beta0= 0.6
	public static double beta; //beta0= 0.4
	public static double tmax; //tempo massimo integrazione Tmax= 200
	public static int nt; //numero di time steps Nt = 100000
	public static double deltat; //rapporto tra Tmax ed Nt
	
	public static double B[][][];
	public static double eta[][];
	public static double enne[];
	public static double emme[];
	public static double df[];
	
	
	public static boolean sessionOpen; // se true indica che la sessione è aperta, hId e iId assegnati
	public boolean tabVarOpen; 
	public static JTable tableData;
	public static JScrollPane scrollPaneVar;
   public  static JScrollPane scrollPaneProp,scrollPaneCol,scrollPaneProbab,scrollPaneData;
   public static JSplitPane splitPanel,mainPanel;
   public static int nCluster;
   public static double  coop; //valore di probabilità comune a tutti i clusters in modalità cooperation
   public static int dist; // di quanto si sposta sul raggio una cella selezionata
   public static int r,x0,y0,iId,hId;
   public static int diametro; //dimensione del diametro del cluster
   public static boolean cell1On,cell2On;

   public static Vector<MyEdge> edges = new Vector<MyEdge>();
   public  static Vector<Double> f = new Vector<Double>();
   public static Vector<Cluster> clust=new Vector<Cluster>(); //vector contenente i clusters da inviare al file XML
   
    //non permette di creare piu edge tra nodi. 
    public ListenableDirectedWeightedGraph<String, MyEdge> graph = new ListenableDirectedWeightedGraph<String, MyEdge>(MyEdge.class);
    
    /* permette di creare piu edge tra nodi. */
    //public ListenableGraph<String, DefaultEdge> graph = new ListenableDirectedMultigraph<String, DefaultEdge>(DefaultEdge.class);
    
    public mxGraphComponent component;
    public int keyCode;
    public Map<String, Object> stil;
   public static String colorValue; //colore selezionato in JColorChooser relativo allo sfondo dei clusters
   public JGraphTXAdapter<String, MyEdge> g ;
   
   public static final int nMax=100;
   
   public static Color color0,color1,color2;
  
  public static String hCol,kCol,iCol;
     
   public static int rowCol;
   
    /** Calculate the N function
   	* @param nC number of clusters of the model
    * @return  double  Value of the N function
    **/ 
   public double enneF(int nC)
   {
   	
   	 int n=Math.round((nC-1)/2); 
   	 
     double sum1=0;
     double sum2=0;
     
     for (int i=0;i<n;i++)
     {
     	sum2=sum2+f.get(i);   
     }
     
     for (int i=n+1;i<nC;i++)
     {
     	sum1=sum1+f.get(i);   
     }
     double summ=sum1-sum2;
     
     return summ;
   }
   
    /** Calculate the M function
   	* @param nC number of clusters of the model
    * @return  double  Value of the M function
    **/  
	 public double emmeF(int nC)
   {
   	int n=Math.round((nC-1)/2); 
   	double sum=0;
    int num=-n;
     for (int i=0;i<nC;i++)
     {
     	sum=sum+num*f.get(i);
     //	System.out.println("F () "+f.get(i));
     	num=num+1;
         //System.out.println("emmeF"+sum);
     }
    
     return sum;
   }
   
   /** Set all elements of  Eta Matrix to value
   	*  @param nC number of clusters
   	* @param value Value to set all elements of Eta Matrix
    **/  
   public void setEta(int nC,double value)
   {
        for(int h=0; h<nC; h++) 
		for(int k=0; k<nC; k++) 
		{
		  eta[h][k] = value; 
		}  
   }
   
   /** Calculate the B Matrix complete
   	* @param tempo index of timestep
    **/  
   public void calculateB(int tempo)
   {
   	
   	  int n=Math.round((nCluster-1)/2); 
   	
   	
   			//resetB(nCluster);
		for(int i=0; i<nCluster; i++) 
		for(int h=0; h<nCluster; h++) 
		for(int k=0; k<nCluster; k++) 
		{
			B[h][k][i] = 0; 
	
		}
		
		
		
		for(int i=0; i<nCluster; i++) //1° indice di matrice = 
		{
		for(int h=0; h<nCluster; h++) //2° indice di riga
		{
		for(int k=0; k<nCluster; k++) //3° indice colonna
		{
			if  ( (h==k) && (h==i) )
			{ B[i][h][k]= 1; } 
			/*
			if ( (Math.abs(h-k)>=mu) && (h>n+1) && (k>n+1) && (h==i) )			
			{
		      B[h][k][i] = 1;
			}
			
			if ( (Math.abs(h-k)>=mu) && (h<n+1) && (k<n+1) && (h==i) )			
			{
		      B[h][k][i] = 1;
			}
			*/
			//scelta tra competizione e cooperazione
			
			if (Math.abs(k-h)<mu) //competizione
			{
			     if ( (h==0) && (h!=k) && (i==h) )
			     {
			        B[i][h][k] = 1;
			     }
			     
			     if ( (h==nCluster-1) && (h!=k) && (i==h) )
			     {
			        B[i][h][k] = 1;
			     }
			     
			     if ( (h!=0) && (h!=k) && (h<k) && (i==h-1) )
			     {
			        B[i][h][k] = beta0;
			     }
			     
			     if ( (h!=0) && (h!=k) && (h<k) && (i==h) )
			     {
			        B[i][h][k] = 1 - beta0;
			     }
			     //triangolare superiore
			     if ( (h!=nCluster-1) && (h>k) && (h!=k) && (i==h) )
			     {
			        B[i][h][k] =1 - ( beta0 + beta * enneF(nCluster) );
			       // B[i][h][k]= 1 - ( beta0 );
			     }
			     if ( (h!=nCluster-1) && (h>k) && (h!=k) && (i==h+1) )
			     {
			        B[i][h][k] =( beta0 + beta * enneF(nCluster) );
			       //B[i][h][k] = ( beta0 );
			     }
			 
			 
			}
			else
			//coperazione
			{
				//System.out.println(i+" "+h+" "+k);
			     if ( (h<k) && (i==h+1) )
			     {
			        B[i][h][k] =( beta0 + beta * enneF(nCluster)  );
			        //B[i][h][k] =( beta0 );//+ beta * enneF(nCluster)  );
			        
			     }
			     
			     if (  (h<k) &&  (i==h) )
			     {
			        B[i][h][k] = 1 - ( beta0 + beta * enneF(nCluster)  );
			        //B[i][h][k] = 1 - ( beta0 );//+ beta * enneF(nCluster)  );
			     }
			     //triangolare inferiore
			     if (  (h>k)  && (i==h) )
			     {
			        B[i][h][k] = 1 - beta0; 
			     
			     }
			     if ((h>k)  && (i==h-1))
			     {
			        B[i][h][k] = beta0; 
			        //System.out.println("entraaaaaaaaaaaaaaaa");
			     }
		    }
		 }
		
		
		}
	
    	}
	
	
		
		//fine calcolo matrici B
   
   		double sommab=0;
	  for(int i=0; i<nCluster; i++) 
	  {
	  	sommab=0;
	  	for(int h=0; h<nCluster; h++) 
	    //for(int k=0; k<nCluster; k++) 
	    {
	    	sommab=sommab+B[i][i][h];
	     // System.out.println("B "+"( "+h+" "+h+" "+i+" = "+B[h][h][i]);
	    }   
	  
	  }
	  //System.out.println("SOMMA B: "+sommab);
   
   }//end calculateB
    
     /** Calculate the B Matrix
   	* @param tempo index of timestep
    **/  
   public void calculateB_(int tempo)
   {
   	
   	  int n=Math.round((nCluster-1)/2); 
   	
   	
   			//resetB(nCluster);
		for(int i=0; i<nCluster; i++) 
		for(int h=0; h<nCluster; h++) 
		for(int k=0; k<nCluster; k++) 
		{
			B[h][k][i] = 0; 
	
		}
		
		
		
		for(int i=0; i<nCluster; i++) //1° indice di matrice = 
		{
		for(int h=0; h<nCluster; h++) //2° indice di riga
		{
		for(int k=0; k<nCluster; k++) //3° indice colonna
		{
			if  ( (h==k) && (h==i) )
			{ B[i][h][k]= 1; } 
			/*
			if ( (Math.abs(h-k)>=mu) && (h>n+1) && (k>n+1) && (h==i) )			
			{
		      B[h][k][i] = 1;
			}
			
			if ( (Math.abs(h-k)>=mu) && (h<n+1) && (k<n+1) && (h==i) )			
			{
		      B[h][k][i] = 1;
			}
			*/
			//scelta tra competizione e cooperazione
			
			if (Math.abs(k-h)<mu) //competizione
			{
			     if ( (h==0) && (h!=k) && (i==h) )
			     {
			        B[i][h][k] = 1;
			     }
			     
			     if ( (h==nCluster-1) && (h!=k) && (i==h) )
			     {
			        B[i][h][k] = 1;
			     }
			     
			     if ( (h!=0) && (h!=k) && (h<k) && (i==h-1) )
			     {
			        B[i][h][k] = beta0;
			     }
			     
			     if ( (h!=0) && (h!=k) && (h<k) && (i==h) )
			     {
			        B[i][h][k] = 1 - beta0;
			     }
			     
			     if ( (h!=nCluster-1) && (h>k) && (h!=k) && (i==h) )
			     {
			        //B[h][i][k] =1 - ( beta0 + beta * enneF(nCluster) );
			        B[i][h][k]= 1 - ( beta0 );
			     }
			     if ( (h!=nCluster-1) && (h>k) && (h!=k) && (i==h+1) )
			     {
			       // B[h][i][k] =( beta0 + beta * enneF(nCluster) );
			       B[i][h][k] = ( beta0 );
			     }
			 
			 
			}
			else
			//coperazione
			{
				//System.out.println(i+" "+h+" "+k);
			     if ( (h<k) && (i==h+1) )
			     {
			        //B[h][i][k] =( beta0 + beta * enneF(nCluster)  );
			        B[i][h][k] =( beta0 );//+ beta * enneF(nCluster)  );
			        
			     }
			     
			     if (  (h<k) &&  (i==h) )
			     {
			        //B[h][i][k] = 1 - ( beta0 + beta * enneF(nCluster)  );
			        B[i][h][k] = 1 - ( beta0 );//+ beta * enneF(nCluster)  );
			     }
			     
			     if (  (h>k)  && (i==h) )
			     {
			        B[i][h][k] = 1 - beta0; 
			     
			     }
			     if ((h>k)  && (i==h-1))
			     {
			        B[i][h][k] = beta0; 
			        //System.out.println("entraaaaaaaaaaaaaaaa");
			     }
		    }
		 }
		
		
		}
	
    	}
	
	
		
		//fine calcolo matrici B
   
   		double sommab=0;
	  for(int i=0; i<nCluster; i++) 
	  {
	  	sommab=0;
	  	for(int h=0; h<nCluster; h++) 
	    //for(int k=0; k<nCluster; k++) 
	    {
	    	sommab=sommab+B[i][i][h];
	     // System.out.println("B "+"( "+h+" "+h+" "+i+" = "+B[h][h][i]);
	    }   
	  
	  }
	  //System.out.println("SOMMA B: "+sommab);
   
   }//end calculateB
     
    
      
    /** Calculate the solution of the Cluster Model in Cooperation/Competition Mode
    **/  
   public void solutionCC()
   {
   	
   	 double somma=0;
   	 
   	 /*
   	  double a=0.0476;
      for (int i=0;i<5;i++)
      {
        f.set(i,a);
      }
      double b=0.1667;
      for (int i=5;i<9;i++)
      {
        f.set(i,b);
      }
   	  f.set(9,a);
   	  double somma2=0;
   	  for (int i=0;i<10;i++)
   	  {
   	    somma2=somma2+f.get(i);
   	  }
   	  somma2=1-somma2;
   	  
   	  f.set(10,somma2);
   	 
   	*/
   
   	
   	
   	
   	
   	  double[] ff=new double [nCluster];
   	  
   	  
   	  for (int i=0;i<nCluster;i++)
   	  {
   	    ff[i]=(double) f.get(i);
   	    //System.out.println(ff[i]);
   	  }
   	  
   	  
   	  enne=new double [nt];
      emme=new double [nt];
      int n=Math.round((nCluster-1)/2);
      System.out.println("calcola soluzione");
      
       resetEta(nCluster);
        
        
        setEta(nCluster,eta0);
        df=new double[nCluster];
      
      for (int tempo=1;tempo<nt;tempo++)
      {
      	
      	enne[tempo]=enneF(nCluster);
      
      	emme[tempo]=emmeF(nCluster);
    	
      
		
        calculateB(tempo);
		
		  
           for (int i=0;i<nCluster;i++)
           {
              df[i]=0;   
              //System.out.println(i+"  "+f.get(i));
           }
           
          
        	for(int i=0; i<nCluster;i++) 	
        	{
				for(int k=0; k<nCluster; k++)
				{ 
					for(int h=0; h<nCluster; h++) 
		            {
		           
		             df[i]=df[i]+(eta[h][k]*B[i][h][k]*f.get(h)*f.get(k));
		            // System.out.println(f.get(h)+"  B (h.i.k) **  "+B[h][i][k]);
		            }
		            df[i]=df[i]-(eta[i][k]*f.get(i)*f.get(k));
		           
	            } 
	          //  System.out.println("df "+df[i]);
            }
            
            
         // double deltaa=0.000002;
           for (int i=0;i<nCluster;i++)
           {
           	 
           
             f.set(i,f.get(i)+deltat*df[i]);
           // System.out.println(f.get(i)+" f  ** df "+df[i]);
           }
            
           
              	
      
      }//fine ciclo timesteps tempo da 1 a Nt 
      
      System.out.println("Soluzione");
      
      
           for (int i=0;i<nCluster;i++)
           {
            System.out.println("df "+df[i]);
             ff[i]=f.get(i);
             somma=somma+f.get(i);
           }
           
              
              // System.out.println("ENNE e EMME");
               
               for (int i=0;i<tmax;i++)
               {
               	 
                  //System.out.println("enne: "+enne[i] +" *** "+emme[i]);
               }
                System.out.println("\n\nSomma Finale: "+somma+"\n\n");
                
                for(int i=0; i<nCluster; i++) 
		{ 
			for(int h=0; h<nCluster; h++) 
			{ 
				for(int k=0; k<nCluster; k++) 
				{
					
					
					System.out.print(B[i][h][k] + " "); 
				}
					System.out.println(); 
			} 
			System.out.println(); 
		}
           
           String text="Graphic";
           String[] s= new String [nCluster];
      ChartBar cb=new ChartBar(ff, s, text);
      cb.chart(nCluster,ff);
   }
   
   
   
    /** Calculate the solution of the Cluster Model in User Defined Mode
    **/  
   public void solution()
   {
   	
   	 double somma=0;
   	 
   	 /*
   	  double a=0.0476;
      for (int i=0;i<5;i++)
      {
        f.set(i,a);
      }
      double b=0.1667;
      for (int i=5;i<9;i++)
      {
        f.set(i,b);
      }
   	  f.set(9,a);
   	  double somma2=0;
   	  for (int i=0;i<10;i++)
   	  {
   	    somma2=somma2+f.get(i);
   	  }
   	  somma2=1-somma2;
   	  
   	  f.add(10,somma2);
   	 
   	*/
   	
   	
   	
   	
   	
   	  double[] ff=new double [nCluster];
   	  
   	  
   	  for (int i=0;i<nCluster;i++)
   	  {
   	    ff[i]=(double) f.get(i);
   	    //System.out.println(ff[i]);
   	  }
   	  
   	  
   	  enne=new double [nt];
      emme=new double [nt];
      int n=Math.round((nCluster-1)/2);
      System.out.println("calcola soluzione");
      
       resetEta(nCluster);
        
        
        setEta(nCluster,eta0);
        df=new double[nCluster];
      
      for (int tempo=1;tempo<nt;tempo++)
      {
      	
      	enne[tempo]=enneF(nCluster);
      
      	emme[tempo]=emmeF(nCluster);
    	
       
		/*
		if ( (Math.abs(h-k)>=mu) && (h>n+1) && (k>n+1) )	
		
		{
		   resetEta(nCluster);
		}
		
		if ( (Math.abs(h-k)>=mu) && (h<n+1) && (k<n+1) )	
		
		{
		   resetEta(nCluster);
		}
		*/
		
        //calculateB(tempo);
		
		  
           for (int i=0;i<nCluster;i++)
           {
              df[i]=0;   
              //System.out.println(i+"  "+f.get(i));
           }
           
          
        	for(int i=0; i<nCluster;i++) 	
        	{
				for(int k=0; k<nCluster; k++)
				{ 
					for(int h=0; h<nCluster; h++) 
		            {
		           
		             df[i]=df[i]+(eta[h][k]*B[i][h][k]*f.get(h)*f.get(k));
		            // System.out.println(f.get(h)+"  B (h.i.k) **  "+B[h][i][k]);
		            }
		            df[i]=df[i]-(eta[i][k]*f.get(i)*f.get(k));
		           
	            } 
	          //  System.out.println("df "+df[i]);
            }
            
            
         // double deltaa=0.000002;
           for (int i=0;i<nCluster;i++)
           {
           	 
           
             f.set(i,f.get(i)+deltat*df[i]);
           // System.out.println(f.get(i)+" f  ** df "+df[i]);
           }
            
           
              	
      
      }//fine ciclo timesteps tempo da 1 a Nt 
      
      System.out.println("Soluzione");
      
      
           for (int i=0;i<nCluster;i++)
           {
            System.out.println("df "+df[i]);
             ff[i]=f.get(i);
             somma=somma+f.get(i);
           }
           
              
              // System.out.println("ENNE e EMME");
               
               for (int i=0;i<tmax;i++)
               {
               	 
                  //System.out.println("enne: "+enne[i] +" *** "+emme[i]);
               }
                System.out.println("\n\nSomma Finale: "+somma+"\n\n");
                
                for(int i=0; i<nCluster; i++) 
		{ 
			for(int h=0; h<nCluster; h++) 
			{ 
				for(int k=0; k<nCluster; k++) 
				{
					
					
					System.out.print(B[i][h][k] + " "); 
				}
					System.out.println(); 
			} 
			System.out.println(); 
		}
           
           String text="Graphic";
           String[] s= new String [nCluster];
      ChartBar cb=new ChartBar(ff, s, text);
      cb.chart(nCluster,ff);
   }
   
    /** Convert RGB Color in Hexadecimal Color
   	* @param col Color to convert 
    * col is Color type in the form:  java.awt.Color[r=255,g=255,b=0]   
    * @return  String  hexadecimal code of the color in the form: #FFFF00  
    **/  
   public String convertColor(Color col)
   {
   	String rgb = Integer.toHexString(col.getRGB());
   	rgb=rgb.substring(2, rgb.length());
   	rgb="#"+rgb;
    return rgb;
   }
   
   
   /**Change colors of 
   	*@param col Color 
    *@param row Index row in Color Table 
    * 0 = Color of all unchecked Clusters (k clusters)
    * 1 = Color of checked Candidate cluster (h)
    * 2 = Color of checked Test cluster (i) 
    **/
   public void changeColors(Color col,int row)
   {
   	String color=col.toString();
   	
   	String rgb = convertColor(col);
   	
   	System.out.println(row+"  Colore cambiato: "+color+" rgb "+rgb);
    
    
    if (row==1)
    {
   
      hCol=rgb;
      
    }
    else
    if (row==2)
    {
    	
       iCol=rgb;
       
    }
    else
    if (row==0)
    {
     kCol=rgb;
     drawClusters(nCluster);    
	 g.getView().validate();
    }
    
   
   }
   
   /**
 * This editor pops up a color dialog to edit a cell value
 */
class ColorTableCellEditor extends AbstractCellEditor implements TableCellEditor
{
	
	private static final long serialVersionUID = 1L;
 

   public ColorTableCellEditor()
   {
      panel = new JPanel();
      // prepare color dialog
      
      

      colorChooser = new JColorChooser();
      colorDialog = JColorChooser.createDialog(null, "Clusters Color", false, colorChooser,
            new ActionListener() // OK button listener
               {
                  public void actionPerformed(ActionEvent event)
                  {
                     stopCellEditing();
                  }
               }, new ActionListener() // Cancel button listener
               {
                  public void actionPerformed(ActionEvent event)
                  {
                     cancelCellEditing();
                  }
               });
      colorDialog.addWindowListener(new WindowAdapter()
         {
            public void windowClosing(WindowEvent event)
            {
               cancelCellEditing();
            }
         });
   }

   public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
         int row, int column)
   {
      // this is where we get the current Color value. We store it in the dialog in case the user
      // starts editing
      colorChooser.setColor((Color) value);
       return panel;
   }

   public boolean shouldSelectCell(EventObject anEvent)
   {
      // start editing
      colorDialog.setVisible(true);

      // tell caller it is ok to select this cell
      return true;
   }

   public void cancelCellEditing()
   {
      // editing is canceled--hide dialog
      colorDialog.setVisible(false);
      super.cancelCellEditing();
   }

   public boolean stopCellEditing()
   {
      // editing is complete--hide dialog
      colorDialog.setVisible(false);
      super.stopCellEditing();

      // tell caller is is ok to use color value
      return true;
   }

   public Object getCellEditorValue()
   {
   	  //System.out.println(rowCol+"----Z "+colorChooser.getColor());
   	
   
   	 
      if (rowCol==0) {color0=colorChooser.getColor();changeColors(color0,rowCol);}
      else
      if (rowCol==1) {color1=colorChooser.getColor();changeColors(color1,rowCol);}
      else
       if (rowCol==2) {color2=colorChooser.getColor();changeColors(color2,rowCol);}
   	
   	
      return colorChooser.getColor();
   }

   public  JColorChooser colorChooser;
  public  JDialog colorDialog;
   public  JPanel panel;
} //end class ColorTableCellEditor
   
   
   
class MyTableModel extends AbstractTableModel
{
	 private static final long serialVersionUID = 1L;
	 
   public String getColumnName(int c)
   {
      return columnNames[c];
   }

   public Class<?> getColumnClass(int c)
   {
      return cells[0][c].getClass();
   }

   public int getColumnCount()
   {
      return cells[0].length;
   }

   public int getRowCount()
   {
      return cells.length;
   }

   public Object getValueAt(int r, int c)
   {
      return cells[r][c];
   }

   public void setValueAt(Object obj, int r, int c)
   {
      cells[r][c] = obj;
   }

   public boolean isCellEditable(int r, int c)
   {
      return c == COLOR_COLUMN;
      
      //return c == CLUSTER_COLUMN ||  c == COLOR_COLUMN;
   }

   public static final int CLUSTER_COLUMN = 0;

   public static final int COLOR_COLUMN = 1;

   private Object[][] cells = {
         { "Clusters",  color0 },
         { "Candidate h", color1},
         { "Test i",  color2},        
          };

   private String[] columnNames = { "Cluster", "Colors"};
} //end class MyTableModel
   
   
    public static class MyEdge extends DefaultWeightedEdge 
    {
    	 private static final long serialVersionUID = 1L;
    	
        @Override
        public String toString() {
            return String.valueOf(getWeight());
        }
        
        
    }
    
  /**
    * Change Vertex Label of graph
    * @param vec Vector of Cluster
    **/
    public void updateLabel(Vector<Cluster> vec)
    {
       g.getModel().beginUpdate();
       int j=0;
            for (Object vertex : g.getChildCells(g.getDefaultParent(), true, true)) 
                      {
                      	
                      	mxCell cc=(mxCell) vertex;
                      	if (cc.isVertex()==true) 
                      	{
                      	 
                      	  g.getView().getState(cc).setLabel(vec.get(j).getLabel());
                      	  cc.setValue(vec.get(j).getLabel());
                      	 
                      	  j=j+1;
                      	}
                      }
                      g.getView().validate();
                      g.repaint();
                     
                      
       g.getModel().endUpdate();
    
    }
    
    /**
    * Check if there are edges drawed in the Graph
    * @return boolean true if there are edges drawed in the Graph
    **/
     public boolean edgePainted()
     {
       boolean trovato=false;
      for (Object edge : g.getChildCells(g.getDefaultParent(), true, true)) 
      {
                      	
                      	mxCell cc=(mxCell) edge;
                      	if (cc.isEdge()==true)
                      	 
                      	{ 
                      	//System.out.println("archi presenti");
                      	trovato=true;
                        }
     }
     
     return trovato;
   }
   
   /**
       * Check if all probability values of i-cluster column are equals to coop value
       
      * return boolean true if si cooperation mode - false if is competition mode
   **/
   public boolean isCooperation()
   {
   	 boolean cooper=true;
     String val1=String.valueOf(B[hId][0][iId]);
   	 for (int k=1;k<nCluster;k++)
    {
      
	  String  val2=String.valueOf(B[k][hId][iId]);  	
	  if ((val1.equals(val2)==true)  && (val1.equals(String.valueOf(coop))))
	  {
	  
	  }
	  else
	  {
	    cooper=false;
	  }
	  		 		
     }
     return cooper;
   }
   
     
    public void removeAllEdges()
    {
    
    							for (int k=0;k<nCluster;k++)
							   {
							   	if ((B[k][hId][iId]>=0) && (hId!=k))
							   	{
							       graph.removeEdge(String.valueOf(hId),String.valueOf(k));
							    
							    
							    }
							   }
    }
    
  
  
  /**
 	* Parse a String [ 1 2 3;4 5 6; 7 8 9;] in a 3D Matrix 
 	* @param m String related to 3D Matrix to be parsed
 	* @param n number of rows and columns of  3D Matrix M(h,k,i)
    **/      
  public static void unParsing(String m,int n)
  {
  		double C[][][] = new double[n][n][n]; 
	
  	Vector<String> vect=new Vector<String>();
          Vector<String> vect2=new Vector<String>();
         
          if (m.length()>2)
          {
                int k=0;
 	    	   // System.out.println("parola ricevuta: ");System.out.println(m);
 	    	    
 	    	    m=m.substring(1,m.length()-1);//toglie le parentesi []
 	    	    
 		
 		    	String [] splitRow = m.split(";");
				for(String s:splitRow)
				{
	    			//System.out.println(s);
	    	    	k=k+1;
	    			vect.add(s);
	    		
	    		}
	    		
	    		for (int i=0;i<vect.size();i++)
	    		{
	    			k=0;
	    		   String [] splitCol = vect.get(i).split(" ");
					for(String s:splitCol)
					{
	    			//	System.out.println(s);
	    				
	    				
	    			
	    	    		k=k+1;
	    				vect2.add(s);
	    		
	    		    }
	    		 }
	    		
	    			int z=0;
	    	try 
			  {
	    		for(int i=0; i<n; i++) 
				for(int j=0; j<n; j++) 
				for( int h=0; h<n; h++) 
				{
					
						if (z<vect2.size())
						{
						    
							C[i][j][h] = Double.parseDouble(vect2.get(z)); 
	
					   		z=z+1;
				   	    }
			   }
					   
					   B=C; 	 
		            
               }
               catch (Exception  ex)
		            {
		            	System.out.println("Eccezione Parsing: "+ex); 
		            
		            }
               
              // System.out.println("Matrix ricostruita");
               
        /*       	
		for(int i=0; i<n; i++) 
		{ 
			for(int j=0; j<n; j++) 
			{ 
				for(int h=0; h<n;h++) 
				{
					
					
					System.out.print(C[i][j][h] + " "); 
				}
					System.out.println(); 
			} 
			System.out.println(); 
		} 
		*/
               
               
          }
          
  }
    
    
/**
 	* Parse 3D Matrix in a String [a b c;d e f;g h i;] with: Row separator = ";" - and Column separator = " "
 	* @param m 3D Matrix to be parsed
 	* @param h row index of the 3D Matrix M(h,k,i)
 	* @param r row index of the 3D Matrix M(h,k,i)
 	* @param c row index of the 3D Matrix M(h,k,i)
 	* @return String parsing of 2D matrix related to h index 
    **/    

public String parseMatrix(double m[][][], int h,int r,int c)
{
		
    		String matrix="[";
			for(int j=0; j<r; j++) 
			{ 
				for(int k=0; k<c; k++) 
				{
					
					
					//System.out.print(m[h][j][k] + " "); 
					matrix=matrix+String.valueOf(m[h][j][k]);
					if(k!=c-1)
					{
						matrix=matrix+" ";
					}
				
				}
					//System.out.println("\n"); 
				matrix=matrix+";";
			} 
			matrix=matrix+"]";
		//	System.out.println(matrix); 
    
	     return matrix;
	
	

}
 
public void userDefined() 
{
   mode="User Defined";
  if (nCluster>0)
  {
   
  // resetB(nCluster);
   
 
   
   MyEdge edgess=new MyEdge();
							
							if ((hId!=-1) && (iId!=-1))
							{
								if (edgePainted()==true)
								 { removeAllEdges();}
								  
								   for (int i=0;i<nCluster;i++)
								   {
								     B[hId][hId][i]=1;
								   }
								   
								   
								   //tabPropMi.setSelected(false);
		             			 
             			    		tabOpen=false;
             			    		scrollPaneProp.setVisible(false);		 
             			  			splitPanel.validate();
								   
								   
								   	Vector<String> infoH=new Vector<String>();
		             			 	
		             			 	for (int k=0;k<nCluster;k++)
		             			 	{
		             			 		infoH.add(String.valueOf(B[k][hId][iId]));
		             			 		
		             			 	}
		             			 	
		             			 	//tabPropMi.setSelected(true);
		             			 
             			    		tabOpen=true;
             			    	//			 
             			  		//	splitPanel.validate();
								   
								   viewTableH(infoH);
								scrollPaneProp.setVisible(true);
									splitPanel.validate();
								
							   for (int k=0;k<nCluster;k++)
							   {
							   	if ((B[k][hId][iId]>0) && (hId!=k))
							   	{
							   		g.getModel().beginUpdate();
			                        edgess=graph.addEdge(String.valueOf(hId),String.valueOf(k));			                
        					  		graph.setEdgeWeight(edgess,B[k][hId][iId]);
 			            			g.getModel().endUpdate();
							    
							    }
							   }
							}
   
   
   
   
   
  
  
  }

}

/* Set the cooperation/competition interaction mode between the clusters
*/

public void cooperation()
{
  MyEdge edgess;

  mode="Cooperation/Competition";
  
    double y=1;
  	double z=hId+1;
  
 System.out.println("cooop: "+coop);
 
 if (Math.abs(iId-hId)<mu) //COMPETITION MODE - probabilità esterne ad h e i
 {
  if (nCluster>0)
  {
  	
  	coop= y/z;
    coop=round(coop,4);
  }
  else
  {coop=1;} //(1/nCluster);//probabilità di ogni singolo cluster
 	
 	if (hId<iId) //nulli a destra di h
    {
		  if (nCluster>0)
		  {
		  	coop= y/z ;
		    coop=round(coop,4);
		  }
		  else
		  {coop=1;} //(1/nCluster);//probabilità di ogni singolo cluster
		  
	   for (int i=0;i<=hId;i++)
	   {
	     B[i][hId][iId]=coop;
	   	table.setValueAt(String.valueOf(coop),i,1);
	   
	   }
	   for (int i=hId+1;i<nCluster;i++)
	   {
	     B[i][hId][iId]=0;
	   	table.setValueAt("0.0",i,1);
	   
	   }
    }
    else //nulli a sinistra
    if (hId>iId)
    {
    	if (nCluster>0)
		  {
		  	double a=1;
		  	double kk=nCluster-hId;
		  	coop= ( a / kk ) ;
		    coop=round(coop,4);
		  }
		  else
		  {coop=1;} //(1/nCluster);//probabilità di ogni singolo cluster
    	
    	for (int i=0;i<hId;i++)
	   {
	   	B[i][hId][iId]=0;
	   	table.setValueAt("0.0",i,1);
	     
	   }
	   for (int i=hId;i<nCluster;i++)
	   {
	     
	   	B[i][hId][iId]=coop;
	   	table.setValueAt(String.valueOf(coop),i,1);
	   
	   }
    
    }
     /*
  for (int i=0;i<nCluster;i++)
   {
   	if (i<=iId)
   	{
   	B[hId][i][iId]=coop;
   	table.setValueAt(String.valueOf(coop),i,1);
    }
    else
    {
      B[hId][i][iId]=0;
   	   table.setValueAt("0.0",i,1);
    }
   }
   */
  
  String s1=String.valueOf(hId);
  
  scrollPaneProp.setVisible(true);
  
  if (edgePainted()==true)
		{ removeAllEdges();}
  
 // removeAllEdges();
 
   for (int i=0;i<nCluster;i++)
   {
   	
    
    	String s2=String.valueOf(i);
        if(i!=hId)
        {
   								g.getModel().beginUpdate();
				               
				                  edgess=graph.addEdge(s1, s2);
				                 
				                    if (hId<iId)  //nulli a destra
					                {  
						                if (i<hId)
						                {
			        					  graph.setEdgeWeight(edgess,coop);
			        			        }
		        			            else	        			        
						                {
			        					  graph.setEdgeWeight(edgess,0);
			        			        }
		        				    }
		        				    else //nulli a sinistra
		        				    if (hId>iId) 
		        				    {
		        				    	if (i<hId)
						                {
			        					  graph.setEdgeWeight(edgess,0);
			        			        }
		        			            else	        			        
						                {
			        					  graph.setEdgeWeight(edgess,coop);
			        			        }
		        				    
		        				    
		        				    }
	        					  
	        					  freccia_but.setSelected(true);
	        					  
	        				   g.getModel().endUpdate();
	     }
   
   }
 }
 else
 if (Math.abs(iId-hId)>mu) //COOPERATION MODE - probabilità interne ad h e i
 {
	  
 	
 	if (hId<iId) //nulli interni 
    {
      if (nCluster>0)
	  {
	  	coop= ( 100 / (nCluster-Math.abs(iId-hId)) ) * 0.01 ;
	    //coop=round(coop,3);
	  }
	  else
	  {coop=1;} //(1/nCluster);//probabilità di ogni singolo cluster
	   for (int i=0;i<nCluster;i++)
	   {
	   	if ((i<=hId) || (i>iId))
	   	{
	     B[i][hId][iId]=coop;
	   	table.setValueAt(String.valueOf(coop),i,1);
	    }
	     else
	   {
	     B[i][hId][iId]=0;
	   	table.setValueAt("0.0",i,1);
	   }
	   
	   }
    }
    else //nulli esterni
    if (hId>iId) 
    {
      if (nCluster>0)
	  {
	  	coop= ( 100 / (Math.abs(hId-iId)+1 )) * 0.01 ;
	    //coop=round(coop,3);
	  }
	  else
	  {coop=1;} //(1/nCluster);//probabilità di ogni singolo cluster
    	
       for (int i=0;i<nCluster;i++)
	   {
	   	if ((i<iId) || (i>hId))
	   	{
	       B[i][hId][iId]=0;
	   	table.setValueAt("0.0",i,1);
	    }
	     else
	     {
	   
	   	   B[i][hId][iId]=coop;
	   	   table.setValueAt(String.valueOf(coop),i,1);
	     }
	   	
	   }
    
    }

  
  String s1=String.valueOf(hId);
  
  scrollPaneProp.setVisible(true);
  
  if (edgePainted()==true)
		{ removeAllEdges();}
  
 // removeAllEdges();
 
   for (int i=0;i<nCluster;i++)
   {
   	
    
    	String s2=String.valueOf(i);
        if(i!=hId)
        {
   								g.getModel().beginUpdate();
				               
				                  edgess=graph.addEdge(s1, s2);
				                 
				                    if (hId<iId)  //nulli interni 
					                {  
						                if ((i<hId) || (i>iId))
						                {
			        					  graph.setEdgeWeight(edgess,coop);
			        			        }
		        			            else	        			        
						                {
			        					  graph.setEdgeWeight(edgess,0);
			        			        }
		        				    }
		        				    else //nulli esterni
		        				    if (hId>iId) 
		        				    {
		        				    	if ((i<iId) || (i>hId))
						                {
			        					  graph.setEdgeWeight(edgess,0);
			        			        }
		        			            else	        			        
						                {
			        					  graph.setEdgeWeight(edgess,coop);
			        			        }
		        				    
		        				    
		        				    }
	        					  
	        					  freccia_but.setSelected(true);
	        					  
	        				   g.getModel().endUpdate();
	     }
   
   }
 }
 
   
   
}//end cooperation



/* Set the cooperation/competition interaction mode between the clusters
*/

public void cooperation_()
{
  MyEdge edgess;

  mode="Cooperation/Competition";
  
    double y=1;
  	double z=hId+1;
  
 System.out.println("cooop: "+coop);
 
 if (Math.abs(iId-hId)<mu) //COMPETITION MODE - probabilità esterne ad h e i
 {
  if (nCluster>0)
  {
  	
  	coop= y/z;
    coop=round(coop,4);
  }
  else
  {coop=1;} //(1/nCluster);//probabilità di ogni singolo cluster
 	
 	if (hId<iId) //nulli a destra di h
    {
		  if (nCluster>0)
		  {
		  	coop= y/z ;
		    coop=round(coop,4);
		  }
		  else
		  {coop=1;} //(1/nCluster);//probabilità di ogni singolo cluster
		  
	   for (int i=0;i<=hId;i++)
	   {
	     B[i][hId][iId]=coop;
	   	table.setValueAt(String.valueOf(coop),i,1);
	   
	   }
	   for (int i=hId+1;i<nCluster;i++)
	   {
	     B[i][hId][iId]=0;
	   	table.setValueAt("0.0",i,1);
	   
	   }
    }
    else //nulli a sinistra
    if (hId>iId)
    {
    	if (nCluster>0)
		  {
		  	double a=1;
		  	double kk=nCluster-hId;
		  	coop= ( a / kk ) ;
		    coop=round(coop,4);
		  }
		  else
		  {coop=1;} //(1/nCluster);//probabilità di ogni singolo cluster
    	
    	for (int i=0;i<hId;i++)
	   {
	   	B[i][hId][iId]=0;
	   	table.setValueAt("0.0",i,1);
	     
	   }
	   for (int i=hId;i<nCluster;i++)
	   {
	     
	   	B[i][hId][iId]=coop;
	   	table.setValueAt(String.valueOf(coop),i,1);
	   
	   }
    
    }
     /*
  for (int i=0;i<nCluster;i++)
   {
   	if (i<=iId)
   	{
   	B[hId][i][iId]=coop;
   	table.setValueAt(String.valueOf(coop),i,1);
    }
    else
    {
      B[hId][i][iId]=0;
   	   table.setValueAt("0.0",i,1);
    }
   }
   */
  
  String s1=String.valueOf(hId);
  
  scrollPaneProp.setVisible(true);
  
  if (edgePainted()==true)
		{ removeAllEdges();}
  
 // removeAllEdges();
 
   for (int i=0;i<nCluster;i++)
   {
   	
    
    	String s2=String.valueOf(i);
        if(i!=hId)
        {
   								g.getModel().beginUpdate();
				               
				                  edgess=graph.addEdge(s1, s2);
				                 
				                    if (hId<iId)  //nulli a destra
					                {  
						                if (i<hId)
						                {
			        					  graph.setEdgeWeight(edgess,coop);
			        			        }
		        			            else	        			        
						                {
			        					  graph.setEdgeWeight(edgess,0);
			        			        }
		        				    }
		        				    else //nulli a sinistra
		        				    if (hId>iId) 
		        				    {
		        				    	if (i<hId)
						                {
			        					  graph.setEdgeWeight(edgess,0);
			        			        }
		        			            else	        			        
						                {
			        					  graph.setEdgeWeight(edgess,coop);
			        			        }
		        				    
		        				    
		        				    }
	        					  
	        					  freccia_but.setSelected(true);
	        					  
	        				   g.getModel().endUpdate();
	     }
   
   }
 }
 else
 if (Math.abs(iId-hId)>mu) //COOPERATION MODE - probabilità interne ad h e i
 {
	  
 	
 	if (hId<iId) //nulli interni 
    {
      if (nCluster>0)
	  {
	  	coop= ( 100 / (nCluster-Math.abs(iId-hId)) ) * 0.01 ;
	    //coop=round(coop,3);
	  }
	  else
	  {coop=1;} //(1/nCluster);//probabilità di ogni singolo cluster
	   for (int i=0;i<nCluster;i++)
	   {
	   	if ((i<=hId) || (i>iId))
	   	{
	     B[i][hId][iId]=coop;
	   	table.setValueAt(String.valueOf(coop),i,1);
	    }
	     else
	   {
	     B[i][hId][iId]=0;
	   	table.setValueAt("0.0",i,1);
	   }
	   
	   }
    }
    else //nulli esterni
    if (hId>iId) 
    {
      if (nCluster>0)
	  {
	  	coop= ( 100 / (Math.abs(hId-iId)+1 )) * 0.01 ;
	    //coop=round(coop,3);
	  }
	  else
	  {coop=1;} //(1/nCluster);//probabilità di ogni singolo cluster
    	
       for (int i=0;i<nCluster;i++)
	   {
	   	if ((i<iId) || (i>hId))
	   	{
	       B[i][hId][iId]=0;
	   	table.setValueAt("0.0",i,1);
	    }
	     else
	     {
	   
	   	   B[i][hId][iId]=coop;
	   	   table.setValueAt(String.valueOf(coop),i,1);
	     }
	   	
	   }
    
    }

  
  String s1=String.valueOf(hId);
  
  scrollPaneProp.setVisible(true);
  
  if (edgePainted()==true)
		{ removeAllEdges();}
  
 // removeAllEdges();
 
   for (int i=0;i<nCluster;i++)
   {
   	
    
    	String s2=String.valueOf(i);
        if(i!=hId)
        {
   								g.getModel().beginUpdate();
				               
				                  edgess=graph.addEdge(s1, s2);
				                 
				                    if (hId<iId)  //nulli interni 
					                {  
						                if ((i<hId) || (i>iId))
						                {
			        					  graph.setEdgeWeight(edgess,coop);
			        			        }
		        			            else	        			        
						                {
			        					  graph.setEdgeWeight(edgess,0);
			        			        }
		        				    }
		        				    else //nulli esterni
		        				    if (hId>iId) 
		        				    {
		        				    	if ((i<iId) || (i>hId))
						                {
			        					  graph.setEdgeWeight(edgess,0);
			        			        }
		        			            else	        			        
						                {
			        					  graph.setEdgeWeight(edgess,coop);
			        			        }
		        				    
		        				    
		        				    }
	        					  
	        					  freccia_but.setSelected(true);
	        					  
	        				   g.getModel().endUpdate();
	     }
   
   }
 }
 
   
   
}//end cooperation

/*
public void consent()
{
  MyEdge edgess;
  

  mode="Consent";
  
  if (nCluster>0)
  {
  	coop= ( 100 / (iId+1) ) * 0.01 ;
    //coop=round(coop,3);
  }
  else
  {coop=1;} //(1/nCluster);//probabilità di ogni singolo cluster
  
 System.out.println("cooop: "+coop);
  
  for (int i=0;i<nCluster;i++)
   {
   	if (i>iId)
   	{
   	B[hId][i][iId]=coop;
   	table.setValueAt(String.valueOf(coop),i,1);
    }
    else
    {
      B[hId][i][iId]=0;
   	   table.setValueAt("0.0",i,1);
    }
   }
  
  String s1=String.valueOf(hId);
  
  scrollPaneProp.setVisible(true);
  
  if (edgePainted()==true)
		{ removeAllEdges();}
  
 // removeAllEdges();
 
   for (int i=0;i<nCluster;i++)
   {
   	
    
    	String s2=String.valueOf(i);
        if(i!=hId)
        {
   								g.getModel().beginUpdate();
				               
				                  edgess=graph.addEdge(s1, s2);
				                if (i>iId)
				                {
	        					  graph.setEdgeWeight(edgess,coop);
	        			        }
	        			        else
	        			        
				                {
	        					  graph.setEdgeWeight(edgess,0);
	        			        }
	        					
	        					  
	        					  freccia_but.setSelected(true);
	        					  
	        				   g.getModel().endUpdate();
	     }
   
   }
   
   
}
*/
 public static void main(String[] args) //throws InterruptedException
 {
    Demo vai=new Demo();
    vai.go();
    
 }
 
  
 /**
 	* Create a Vector named "f"  which values  are  probability equals to 1/n
 	* @param n number of clusters
 	**/
 public void setF (int n)
  {
  	f = new Vector<Double>(n);
  	double a=1;
  	
  	double prob = a/n;
  	//prob=round(prob,6);
  	for (int i=0;i<n;i++)
  	{
  	  f.add(prob);
  	}
  	
  }
 
 /**
 	* Check sum of probability clusters
 	* @param v Vector of strings to check the sum number to rounding
 	* @return true if the sum of all the elements of v vector is equal to 1; false otherwise.
 	**/
 public boolean check (Vector<Double> v)
  {
  	boolean ok=true;
  	double somma=0;
  	for (int i=0;i<v.size();i++)
  	{
  	  somma=somma+(double) v.get(i);
  	}
  	somma=round(somma,8);
  	if (somma!=1)
  	{
  		ok=false;
  	}
  	 		System.out.println("Somma f: "+somma);
     return ok;
  }
 
  /**
 	* Return a rounded double
 	* @param d number to rounding
 	* @param p number of digits to rounding
 	* @return Double number rounded to 2 digits
 	**/
 public double round (double d, int p)
  { 		
     return Math.rint(d*Math.pow(10,p))/Math.pow(10,p); 	
  }
 
 /**
 	* Reset 3D Matrix B	
    */
 public void resetB(int n)
  {
       	
       	B = new double [n][n][n]; 
		
		for(int h=0; h<n; h++) 
		for(int k=0; k<n; k++) 
		for(int i=0; i<n; i++) 
		{
			//if ((h==k) && (k==i) &&(h==i))
			{
			//B[h][k][i] = 1; 
			}
		//	else
			{
			B[h][k][i] = 0; 
	  	    }
	
		}
		
		for(int h=0; h<n; h++) 
       	for (int i=0;i<n;i++)
		             			   {
		             			      B[h][h][i]=1;
		             			   }
		
  }
  
   /**
 	* Reset 2D Matrix eta (Encounter Rate Matrix) 	
    */
 public void resetEta(int n)
  {
       	
       	eta = new double [n][n]; 
		
		for(int h=0; h<n; h++) 
		for(int k=0; k<n; k++) 
		{
		  eta[h][k] =0; 
	
		}		
		
  }
  
  
   /**
 	* Reset Vector of cluster probabilities
 	* @param int Max number of clusters
    */
 public void resetF(int n)
  {
       	
       	f = new Vector<Double>(); 
       	double num=0;
		
		for(int h=0; h<n; h++) 
		{
		
		f.add(num);
		}
		
		if(scrollPaneProbab!=null)
            		  	{ scrollPaneProbab.setVisible(false);}
         tabProbabMi.setSelected(false);
        

  }
 
  /**
 	* Reset clusters i,h 	
    */
 public void reset()
  {
 		cell1On=false;
       	cell2On=false;
       	iId=-1;
       	hId=-1;
       	sessionOpen=false;
       	
  }
  
  
  
  public void resizeCluster(int r,double valore)
  {
    	sessionOpen=true;
      //se la probabilità inserita nella tabella è diversa da 0 cambia colore e dimensioni della cella
           if ((valore>=0) && (valore<=1) && (nCluster<10))
            {
            
            
           		
           	
           	    String labelTab=table.getValueAt(r, 1).toString();
             			    
             			   
        					
        					
        					
			//Object vert=g.getSelectionCell();      					
			Object[] cells = g.getChildVertices(g.getDefaultParent());
			mxStylesheet style = g.getStylesheet();
			Map<String, Object> vstyle = new HashMap<String, Object>(style.getDefaultVertexStyle());
            /*
            if (okNum>0)
			{
				vstyle.put(mxConstants.STYLE_FILLCOLOR, fillcolor);//#AEBAF8}
			}
			else
			if (okNum==0)
			{
				vstyle.put(mxConstants.STYLE_FILLCOLOR, "white");
			}
			*/
				vstyle.put(mxConstants.STYLE_FILLCOLOR, "white");
			vstyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
			vstyle.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
			vstyle.put(mxConstants.STYLE_PERIMETER,mxConstants.PERIMETER_ELLIPSE);

			for (Object o : cells) 
			{
			   mxCell c = (mxCell) o;
			   //if ((c==vert)) 
			   if ((c.getId().equals(String.valueOf(r))==true) )
			   {
			      g.getView().getState(c).setStyle(vstyle);
			         
			    g.getModel().beginUpdate();
			    try 
			    {
			      
			      double prob_in = (100/nCluster)*(0.01);
			       sizeChangedCell=diametro- ( 100*(prob_in - valore) ); //varia le dimensioni della cella in funzione della probabilità inserita
			        mxGeometry geo = (mxGeometry) c.getGeometry().clone();
			        mxRectangle bounds = g.getView().getState(c).getLabelBounds();
			        geo.setHeight( sizeChangedCell); //10 is for padding
			        geo.setWidth( sizeChangedCell); //10 is for padding
			        g.cellsResized(new Object[] { c }, new mxRectangle[] { geo });
			   		System.out.println(valore+"  NUOVA DIIMENSIONE   "+sizeChangedCell);
			        } 
			        finally 
			        {
			        	g.getModel().endUpdate();
			        }
			       
			   }
			}
			        
        	g.getView().validate();
          }
  
  }
  
    /**
 	* Paint the background of  cluster with specific ID
 	* @param color Background color 
 	* @param id Id of specific cluster 
    */
  public void fillCell(String color, int id)
  {
  						Object vert=g.getSelectionCell();      					
						Object[] cells = g.getChildVertices(g.getDefaultParent());
						mxStylesheet style = g.getStylesheet();
						Map<String, Object> vstyle = new HashMap<String, Object>(style.getDefaultVertexStyle());

						vstyle.put(mxConstants.STYLE_FILLCOLOR, color);//#AEBAF8
						vstyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
						vstyle.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
						vstyle.put(mxConstants.STYLE_PERIMETER,mxConstants.PERIMETER_ELLIPSE);
						for (Object o : cells) 
						{
						   mxCell c = (mxCell) o;
						   if ((c.getId().equals(String.valueOf(id))==true) )
						   {
						      g.getView().getState(c).setStyle(vstyle);
						       
						   }
						}
						        
			        	g.getView().validate();		
  	
  }
  
  
  /**
 	* Paint the background of selected cluster
 	* @param color Background color 
    */
  public void fillCluster(String color)
  {
  						Object vert=g.getSelectionCell();   
  						mxCell cc=(mxCell) vert;
  						String l= g.getView().getState(cc).getLabel();  
  						System.out.println(cc.getValue().toString()+"Vekkia label -Z> "+l);					
						Object[] cells = g.getChildVertices(g.getDefaultParent());
						mxStylesheet style = g.getStylesheet();
						Map<String, Object> vstyle = new HashMap<String, Object>(style.getDefaultVertexStyle());

						vstyle.put(mxConstants.STYLE_FILLCOLOR, color);//#AEBAF8
						vstyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
						vstyle.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
						vstyle.put(mxConstants.STYLE_PERIMETER,mxConstants.PERIMETER_ELLIPSE);
						for (Object o : cells) 
						{
						   mxCell c = (mxCell) o;
						   if ((c==vert)) 
						   {
						   	
						      g.getView().getState(c).setStyle(vstyle);
						     g.getView().getState(c).setLabel(l);
						          
						   }
						}
						        
			        	g.getView().validate();		
  	
  }
 
 public void mov(Object[] cells, double dx, double dy)
  {
  	
  	
	 g.moveCells(cells,dx,dy,false);
	 
  }
 
   /**
 	* Draw the clusters in a semicircle
 	* @param numCluster Number of cluster
    */
 public void drawClusters_(int numCluster)
 {

 	double alfaStep; //angolo tra un cluster e l' altro
 //	Vector<Double> angles = new Vector<Double>();
 	double grad,rad;
 	double x=0;
 	double y=0;
 	int r=500;//raggio semicirconferenza;
 	int x0=50;//ascissa primo cluster basso a sinistra
 	int y0=50; //ordinata cluster centrale
 	String labell;
 		Object parent = g.getDefaultParent();
 		
 		if ( (numCluster & 1) == 0 ) { System.out.println("pari"); } else { System.out.println("dispari");}
 		
 	if (numCluster>0)
 	{
 		System.out.println("\nrimuovi tutte le celleeeee\n");
 		g.removeCells(g.getChildVertices(g.getDefaultParent()));// rimuove tutte le celle
 		
 		if (numCluster==1)
 		{
 		 	x=x0+r;
 		 	y=y0+r/2;		
 		 g.insertVertex(parent,null,"0",x,y,40,40,"shape=ellipse;perimeter=ellipsePerimeter;fillColor="+kCol);
 		}
 		else
 		if ( (numCluster & 1) == 0 )//numero pari
 	    {}
 		
 		else //numero dispari
 		{
 			//cluster di sinistra
 			x=x0;y=y0+r;
 			g.insertVertex(parent,null,"0",x,y,40,40,"shape=ellipse;perimeter=ellipsePerimeter;fillColor="+kCol);
 			int central=(nCluster-1)/2;
 			
 			//cluster centrale
 			labell=String.valueOf(central);
 			x=x0+r;y=y0+r/2;
 			g.insertVertex(parent,null,labell,x,y,40,40,"shape=ellipse;perimeter=ellipsePerimeter;fillColor="+kCol);
 			
 			//cluster di destra
 			
 			g.insertVertex(parent,null,"0",x,y,40,40,"shape=ellipse;perimeter=ellipsePerimeter;fillColor="+kCol);
 			
 			alfaStep=180/(numCluster-1);
 			grad=180;rad=0;
 		
 		
 			for (int k=0;k<numCluster;k++)
 			{
 		   		//angles.add(grad);
 		   	   labell=String.valueOf(k);
 		   	   
 		   	   rad=(grad*Math.PI)/180;
 		   		
 		   		x= r*(Math.cos(rad))+r+x0;
 		   		y=-r*(Math.sin(rad))+r+y0;
 		   		
 		   	   g.insertVertex(parent,null,labell,x,y,40,40,"shape=ellipse;perimeter=ellipsePerimeter;fillColor="+kCol);
 		   			
 		   		
 		   			System.out.println(k+" "+x+" "+y+"  gradi  "+grad);
 		   					grad=grad-alfaStep;
 			}
 		
 	    }
 	    
 	    
 	    
 	    	
 	}
	
 }
 
 
   /**
 	* Draw the clusters in a semicircle
 	* @param numCluster Number of cluster
    */
 public void drawClusters(int numCluster)
 {
 	double alfaStep; //angolo tra un cluster e l' altro
 //	Vector<Double> angles = new Vector<Double>();
 	double grad,rad;
 	double x=0;
 	double y=0;
 	 	int id=nCluster-1;
 	r=(screenX-368)/2;//raggio semicirconferenza 500;
 	x0=50;//ascissa primo cluster basso a sinistra 50
 	y0=50; //ordinata cluster centrale 50
 	String labell;
 		Object parent = g.getDefaultParent();
 	if (numCluster>0)
 	{
 		diametro=40;
 		g.removeCells(g.getChildVertices(g.getDefaultParent()));// rimuove tutte le celle
 		
 		if (numCluster==1)
 		{
 		 	x=x0+r;
 		 	y=y0+r/2;
 		 	String idC=new String();
 		 	idC="0";
 		 			
 		 //g.insertVertex(parent,idC,"0",x,y,diametro,diametro,"shape=ellipse;perimeter=ellipsePerimeter;fillColor=white"); //
 		  g.insertVertex(parent,idC,"0",x,y,diametro,diametro,"shape=ellipse;perimeter=ellipsePerimeter;fillColor="+kCol);
 		}
 		else
 		{
 			
 			if ((nCluster>30) && (nCluster<=45))
 			{
 			  diametro=30;
 			}
 			else
 			if ((nCluster>45) && (nCluster<=55))
 			{
 			  diametro=25;
 			}
 			else
 			if (nCluster>55)
 			{
 			 diametro=20;
 			}
 			alfaStep=180/(numCluster-1);
 			
 			grad=180;rad=0;
 		
 		
 			for (int k=0;k<numCluster;k++)
 			{
 		   		//angles.add(grad);
 		   	   labell=String.valueOf(k);
 		   	   
 		   	   rad=(grad*Math.PI)/180;
 		   		
 		   		x= r*(Math.cos(rad))+r+x0;
 		   		y=-r*(Math.sin(rad))+r+y0;
 		   		id=id-1;
 		   		
 		   	   g.insertVertex(parent,String.valueOf(id),labell,x,y,diametro,diametro,"shape=ellipse;perimeter=ellipsePerimeter;fillColor="+kCol);
 		   			
 		   		
 		   			//System.out.println(k+" "+x+" "+y+"  gradi  "+grad);
 		   			grad=grad-alfaStep;
 			}
 		
 	    }
 	    	
 	
 	int j=0;         //setta tutti gli id dei vertex inseriti
 					for (Object vertex : g.getChildCells(g.getDefaultParent(), true, true)) 
                      {
                      	
                      	mxCell cc=(mxCell) vertex;
                      	if (cc.isVertex()==true) 
                      	{
                      	  cc.setId(String.valueOf(j));
                      	  j=j+1;
                      	}
                      }
	}
	else
	{
	 g.removeCells(g.getChildVertices(g.getDefaultParent()));// rimuove tutte le celle
	}
 }
 
    /**
 	* Draw the clusters in a semicircle
 	* @param numCluster Number of clusters
 	* @param vec Vector containing the properties of the clusters
    */
 public void drawOpenClusters(int numCluster, Vector<Cluster> vec)
 {
 	double alfaStep; //angolo tra un cluster e l' altro
 
 	double grad,rad;
 	double x=0;
 	double y=0;
 	 	int id=nCluster-1;
 	r=500;//raggio semicirconferenza;
 	x0=50;//ascissa primo cluster basso a sinistra
 	y0=50; //ordinata cluster centrale
 	String labell;
 		Object parent = g.getDefaultParent();
 	if (numCluster>0)
 	{
 		diametro=40;
 		g.removeCells(g.getChildVertices(g.getDefaultParent()));// rimuove tutte le celle
 		
 		if (numCluster==1)
 		{
 		 	x=x0+r;
 		 	y=y0+r/2;
 		 	String idC=new String();
 		 	idC="0";
 		  String lab=vec.get(0).getLabel();
 		 g.insertVertex(parent,idC,lab,x,y,diametro,diametro,"shape=ellipse;perimeter=ellipsePerimeter;fillColor="+kCol);
 		
 		}
 		else
 		{
 			
 			if ((nCluster>30) && (nCluster<=45))
 			{
 			  diametro=30;
 			}
 			else
 			if ((nCluster>45) && (nCluster<=55))
 			{
 			  diametro=25;
 			}
 			else
 			if (nCluster>55)
 			{
 			 diametro=20;
 			}
 			alfaStep=180/(numCluster-1);
 			
 			grad=180;rad=0;
 		
 		
 			for (int k=0;k<numCluster;k++)
 			{
 		   		//angles.add(grad);
 		   	   //labell=String.valueOf(k);
 		   	   
 		   	   rad=(grad*Math.PI)/180;
 		   		
 		   		x= r*(Math.cos(rad))+r+x0;
 		   		y=-r*(Math.sin(rad))+r+y0;
 		   		id=id-1;
 		   		
 		   		labell=vec.get(k).getLabel();
 		   		String idd=vec.get(k).getId();
 		   	   g.insertVertex(parent,idd,labell,x,y,diametro,diametro,"shape=ellipse;perimeter=ellipsePerimeter;fillColor="+kCol);
 		   			
 		   		
 		   			//System.out.println(k+" "+x+" "+y+"  gradi  "+grad);
 		   			grad=grad-alfaStep;
 			}
 		
 	    }
 	    	
 	}
 	int j=0;         //setta tutti gli id dei vertex inseriti
 					for (Object vertex : g.getChildCells(g.getDefaultParent(), true, true)) 
                      {
                      	
                      	mxCell cc=(mxCell) vertex;
                      	if (cc.isVertex()==true) 
                      	{
                      	  cc.setId(String.valueOf(j));
                      	  j=j+1;
                      	}
                      }
	
 }
 
  /**
 	* Set Extra Label (3 labels) on Edge  
 	* @param Startpoint Edge label 
 	* @param Central Edge label 
 	* @param Endpoint Edge label 
    */
 public void setLabelEdges(String labA,String labB, String labC)
 {
 	Object[] labels = {labC,labB,labA};
	Point2D[] labelPositions = {new Point2D.Double
	(GraphConstants.PERMILLE*7/8, -20), new Point2D.Double
	(GraphConstants.PERMILLE/8, -20)};
	GraphConstants.setExtraLabelPositions(stil,labelPositions);
	GraphConstants.setExtraLabels(stil, labels);
    
 	
 }
 
 /*

 public int aPosSearch(String lab)
 {
 	int index=-1;
    
 	for (int k=0;k<a.size();k++)
 	{
 		if (a.get(k).getEdgeLabel().equals(lab)==true)
 		{
 			index=k;
 			
 		}
 	
 	}
 	return index;
 	
 }
 

 public int nPosSearch(String lab)
 {
 	int index=-1;
    
 	for (int k=0;k<n.size();k++)
 	{
 		if (n.get(k).getVertexLabel().equals(lab)==true)
 		{
 			index=k;
 			
 		}
 	
 	}
 	return index;
 	
 }
 
 */
 
 /**
 	* Close the Property Table of the selected Vertex 
 	* @return String Vector containing the columns items
    */
 
 public Vector<String> closeTable()
  {
  	 items=new Vector<String>();
  	 
  	
  	//frameTabN.setVisible(false);
  scrollPaneProp.setVisible(false);
  	
  	for(int j=0;j<3;j++)
    {
    	String s=new String();
     	s=table.getValueAt(j, 1).toString();
         //System.out.println(s);
  		items.add(s);
    }
  	return items;
  }
  
    /**
 	* Open the table relative to Data Variables (mu, Mode, N)
   */ 
  public void viewTabData (Vector<String> values)
  {
  	List<TableCellEditor> editors = new ArrayList<TableCellEditor>(1);
    
    String[] items1 = { "Coop / Comp","User Defined"};
        
        JComboBox comboBox1 = new JComboBox(items1);
        DefaultCellEditor dce1 = new DefaultCellEditor( comboBox1 );
        editors.add( dce1 );
    
    Vector<String> row1 = new Vector<String>();
    row1.addElement(" Mu ");
    row1.addElement("");
 	 
 	Vector<String> row2 = new Vector<String>();
    row2.addElement(" N ");
    row2.addElement("");
    
    Vector<String> row3 = new Vector<String>();
    row3.addElement(" Mode ");
    row3.addElement("");
   
    Vector<Vector<String>> rowData = new Vector<Vector<String>>();   
    rowData.addElement(row1);
    rowData.addElement(row2);
    rowData.addElement(row3);
   
    Vector<String> columnNames = new Vector<String>();
     
    columnNames.addElement("Variable");
    columnNames.addElement("Value");
  
    
    DefaultTableModel model = new DefaultTableModel(rowData, columnNames) ;
    tableData = new JTable(model)
	{
		 private static final long serialVersionUID = 1L;
		 
			//  Determine editor to be used by row
            public TableCellEditor getCellEditor(int row, int column)
            {
              	
                int modelColumn = convertColumnIndexToModel( column );

                if (modelColumn == 1 && row ==2)
                    return editors.get(0);

                else
                    return super.getCellEditor(row, column);
            }//
			
			public boolean isCellEditable(int row, int col) 
			{
				if (col==0)
				{
				return false;}
				else
				
				{ return true;}
			};
			
   };
   
   	comboBox1.addActionListener(new ActionListener() 
   	{

			public void actionPerformed(ActionEvent e) 
			{
				final long serialVersionUID = 1L;
				
				JComboBox val = (JComboBox) e.getSource();
				String selected = (String) val.getSelectedItem();
				 
				if (selected.equals("User Defined"))
				{
					resetB(nMax);
					 System.out.println("user defined");
					if ((hId!=-1) && (iId!=-1) && (nCluster>0))
					{
					 
					  if ((tableData.getValueAt(2, 1).toString()).equals("User Defined")==false)
					  {
					  	g.getModel().beginUpdate();
					  	
					  	if (edgePainted()==true)
								 { removeAllEdges();}
					     // removeAllEdges();
					    g.getModel().endUpdate();
					    
					    userDefined();
					 }
			 	    }
				}
				else
				if (selected.equals("Coop / Comp"))
				{
				  
				//	if ((hId!=-1) && (iId!=-1))
				    if ((hId==-1) && (iId==-1) && (nCluster>0))
					{
							System.out.println("cooperazione/competizione *");
							if ((tableData.getValueAt(2, 1).toString()).equals("Coop / Comp")==false)
							{
							  
					 
					             if ((hId!=-1) && (iId!=-1))
					             {
					 					  	g.getModel().beginUpdate();
					 					  	if (edgePainted()==true)
								            { removeAllEdges();}
							 					  	
							                 g.getModel().endUpdate();
							     }
							      
							 }
					 }
		    	
				    
				    /*
				    else
				    if ((hId==-1) && (iId==-1) && (nCluster>0))
				    {
				      mode="dolfin";
				      System.out.println(mode);
				    }
				    */
				    
				}//end cooperation/competition
				
				/*
				else
				if (selected.equals("Cons / Diss"))
				{
				
					if ((hId!=-1) && (iId!=-1))
					{
							System.out.println("consent/dissent");
							if ((tableData.getValueAt(2, 1).toString()).equals("Cons / Diss")==false)
							{
							
					 
					 
					 					  	g.getModel().beginUpdate();
					 					  	if (edgePainted()==true)
								 { removeAllEdges();}
							 					  	
							    //  removeAllEdges();
							    g.getModel().endUpdate();
							    consent();
							 }
				    }
				}
				*/
			}
		});//end combobox1
		
		
		
		//panelVar=new JPanel(new BorderLayout());
	//	panelVar.setBorder(new TitledBorder("Properties"));
	//	panelVar.setSize(150,65);
	//	panelVar.setLayout(new BorderLayout());
		
	
       // model.setColumnIdentifiers(columnNames);
   

	///	tableVar = new JTable(model);
		
  //tableVar.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox1));
		
        scrollPaneData = new JScrollPane(tableData);
       
        Dimension tablePreferred = scrollPaneData.getPreferredSize();
                scrollPaneData.setPreferredSize(
                    new Dimension(tablePreferred.width/8, tablePreferred.height/5) );
    
        

        
      
       //tableVar.setLayout(null);
    	//tableVar.setBackground(Color.lightGray);
    	//tableVar.setRowMargin(5);
      
		
		tableData.setMinimumSize(new Dimension(100,60));
	  //tableVar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableData.setPreferredSize(new Dimension(150,60));
        
	//	panelVar.add(tableVar);
		
		 // scrollPane.setViewportView(tableVar);//nasconde l header
		  
	//	 panelVar.add(scrollPaneVar, BorderLayout.CENTER); 
		 
	//	frame.getContentPane().add(panelVar);
	
	
		
		try
		{
		
				for(int j=0;j<values.size();j++)
            	{
            	  //System.out.println( table.getValueAt(j, 0)+" : "+table.getValueAt(j, 1)); 
            	  
            	  tableData.setValueAt(values.get(j), j, 1);   
                }
                
        }
        catch 
        (Exception exxx) 
        {
        }
 
    tableData.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() 
    {
    	public void editingCanceled(ChangeEvent e) 
    	{
                       // System.out.println("editingCanceled");
                                
        }
      	public void editingStopped(ChangeEvent e) 
      	{
           	String cellMu=tableData.getValueAt(0, 1).toString();        	
          	String cellN=tableData.getValueAt(1, 1).toString();
            String cellMode=tableData.getValueAt(2, 1).toString();
           
               
               
    	 r=tableData.getSelectedRow(); 
    
  
         
            try
            {
              if ((cellMu!=null) && (cellMu.equals("")==false))
              {
              	double valore=Double.parseDouble(cellMu);
             
	             if (valore<0)
	             { 
	               
	               
	               JOptionPane.showMessageDialog
	            (null,"Insert a Positive Number","Warning",JOptionPane.WARNING_MESSAGE);
	               
	                tableData.setValueAt(mu,r,1);
	               tableData.setEditingRow(r);
	             }
	             else
	             {
	             	mu=(int) valore;
	             	
	             }
              
              }
             
            }
            catch (NumberFormatException  ex)
            {
            	JOptionPane.showMessageDialog
            (null,"Insert a Positive Number","Warning",JOptionPane.WARNING_MESSAGE); 
             tableData.setValueAt(mu,r,1);
	               tableData.setEditingRow(r);
            }

            try
            {
              if ((cellN!=null) && (cellN.equals("")==false))
              {
              	double valore=Double.parseDouble(cellN);
             
	             if (valore<0)
	             { 
	               
	               
	               JOptionPane.showMessageDialog
	            (null,"Insert a Positive Number","Warning",JOptionPane.WARNING_MESSAGE);
	               
	                tableData.setValueAt(nCluster,r,1);
	               tableData.setEditingRow(r);
	             }
	             else
	             {
	             	nCluster=(int) valore;
	             	drawClusters(nCluster);
	             	setF(nCluster); 
	             }
              
              }
             
            }
            catch (NumberFormatException  ex)
            {
            	JOptionPane.showMessageDialog
            (null,"Insert a Positive Number","Warning",JOptionPane.WARNING_MESSAGE); 
             tableData.setValueAt(nCluster,r,1);
	               tableData.setEditingRow(r);
            }
      
            
            
            
           		
      	}

    });
    

   System.out.println("\n");
   panelVar.add(scrollPaneData);
   
  }//end class ViewTabData
  
  
  /**
 	* Open the table relative to Global Variables (eta0, beta0, beta, Tmax, Nt)
   */ 
  public void viewTabVar (Vector<String> values)
  {
  	
    Vector<String> row1 = new Vector<String>();
    row1.addElement(" Eta0 ");
    row1.addElement("");
    
    Vector<String> row2 = new Vector<String>();
    row2.addElement(" Beta0 ");
    row2.addElement("");
    
    Vector<String> row3 = new Vector<String>();
    row3.addElement(" Beta ");
    row3.addElement("");
  
    Vector<String> row4 = new Vector<String>();
    row4.addElement(" Tmax ");
    row4.addElement("");
    
    Vector<String> row5 = new Vector<String>();
    row5.addElement(" Nt ");
    row5.addElement("");
     
    Vector<Vector<String>> rowData = new Vector<Vector<String>>();
     
    rowData.addElement(row1);
    rowData.addElement(row2);
    rowData.addElement(row3);
    rowData.addElement(row4);
   	rowData.addElement(row5);
  
   
    Vector<String> columnNames = new Vector<String>();
     
    columnNames.addElement("Variable");
    columnNames.addElement("Value");
  
    
    DefaultTableModel model = new DefaultTableModel(rowData, columnNames) ;
    JTable tableVar = new JTable(model)
	{
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int col) 
			{
				if (col==0)
				{
				return false;}
				else
				
				{ return true;}
			};
			
   };
   

		

		
        scrollPaneVar = new JScrollPane(tableVar);
       
        Dimension tablePreferred = scrollPaneVar.getPreferredSize();
                scrollPaneVar.setPreferredSize(
                    new Dimension(tablePreferred.width/8, tablePreferred.height/6) );
    
        

        
      
       //tableVar.setLayout(null);
    	//tableVar.setBackground(Color.lightGray);
    	//tableVar.setRowMargin(5);
      
		
		tableVar.setMinimumSize(new Dimension(100,80));
	  //tableVar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableVar.setPreferredSize(new Dimension(150,80));
        
	//	panelVar.add(tableVar);
		
		 // scrollPane.setViewportView(tableVar);//nasconde l header
		  
	//	 panelVar.add(scrollPaneVar, BorderLayout.CENTER); 
		 
	//	frame.getContentPane().add(panelVar);
	
	
		
		try
		{
		
				for(int j=0;j<values.size();j++)
            	{
            	  //System.out.println( table.getValueAt(j, 0)+" : "+table.getValueAt(j, 1)); 
            	  
            	  tableVar.setValueAt(values.get(j), j, 1);   
                }
                
        }
        catch 
        (Exception exxx) 
        {
        }
 
    tableVar.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() 
    {
    	public void editingCanceled(ChangeEvent e) 
    	{
                       // System.out.println("editingCanceled");
                                
        }
      	public void editingStopped(ChangeEvent e) 
      	{
           
           	String cellEta0=tableVar.getValueAt(0, 1).toString();
           	String cellBeta0=tableVar.getValueAt(1, 1).toString();
          	String cellBeta=tableVar.getValueAt(2, 1).toString();
          
            String cellTmax=tableVar.getValueAt(3, 1).toString();
            String cellNt=tableVar.getValueAt(4, 1).toString();
               
               
    	 r=tableVar.getSelectedRow(); 
    
       	try
         	{
             	
         	  if ((cellEta0!=null) && (cellEta0.equals("")==false))
         	  {	
         	     double valore=Double.parseDouble(cellEta0);     
	             if ((valore<0) || (valore>1))
	             { 
	               
	               
	               JOptionPane.showMessageDialog
	            (null,"Insert a Number between 0 and 1","Warning",JOptionPane.WARNING_MESSAGE);
	               
	               
	               tableVar.setValueAt(eta0,r,1);
	               tableVar.setEditingRow(r);
	               
	             }
	             else
	             {
	             	eta0=valore; 
	             	
	             }
	            
              }
            }   
              
            catch (NumberFormatException  ex)
            {
            	JOptionPane.showMessageDialog (null,"Insert a Number between 0 and 1","Warning",JOptionPane.WARNING_MESSAGE);             
	               tableVar.setValueAt(eta0,r,1);
	               tableVar.setEditingRow(r);
            }
         
           
            try
         	{
         	  if ((cellBeta0!=null) && (cellBeta0.equals("")==false))
         	  {	
                double valore=Double.parseDouble(cellBeta0);
             
	             if ((valore<0) || (valore>1))
	             { 
	               
	               
	               JOptionPane.showMessageDialog
	            (null,"Insert a Number between 0 and 1","Warning",JOptionPane.WARNING_MESSAGE);
	                tableVar.setValueAt(beta0,r,1);
	               tableVar.setEditingRow(r);
	             }
	             else
	             {
	             	beta0=valore;
	             }
              } 
            }  
              
            catch (NumberFormatException  ex)
            {
            	JOptionPane.showMessageDialog
            (null,"Insert a Number between 0 and 1","Warning",JOptionPane.WARNING_MESSAGE); 
            
             tableVar.setValueAt(beta0,r,1);
	               tableVar.setEditingRow(r);
            }
          try
         	{
         	  if ((cellBeta!=null) && (cellBeta.equals("")==false))
         	  {	
                double valore=Double.parseDouble(cellBeta);
             
	             if ((valore<0) || (valore>1))
	             { 
	               
	               
	               JOptionPane.showMessageDialog
	            (null,"Insert a Number between 0 and 1","Warning",JOptionPane.WARNING_MESSAGE);
	                tableVar.setValueAt(beta,r,1);
	               tableVar.setEditingRow(r);
	               
	             }
	             else
	             {
	             	beta=valore;
	             }
              } 
            }  
              
            catch (NumberFormatException  ex)
            {
            	JOptionPane.showMessageDialog
            (null,"Insert a Number between 0 and 1","Warning",JOptionPane.WARNING_MESSAGE); 
             tableVar.setValueAt(beta,r,1);
	               tableVar.setEditingRow(r);
            }
   
            try
            {
            	 r=tableVar.getSelectedRow(); 
              if ((cellTmax!=null) && (cellTmax.equals("")==false))
              {
              	double valore=Double.parseDouble(cellTmax);
             
	             if (valore<=0)
	             { 
	               
	               
	               JOptionPane.showMessageDialog
	            (null,"Insert a Positive Number","Warning",JOptionPane.WARNING_MESSAGE);
	                tableVar.setValueAt(Math.round(tmax),r,1);
	               tableVar.setEditingRow(r);
	               
	             }
	             else
	             {
	             	tmax=(int) valore;
	             	deltat=tmax/nt;
	             }
              
              }
             
            }
            catch (NumberFormatException  ex)
            {
            	JOptionPane.showMessageDialog
            (null,"Insert a Positive Number","Warning",JOptionPane.WARNING_MESSAGE); 
             tableVar.setValueAt(Math.round(tmax),r,1);
	               tableVar.setEditingRow(r);
            }
            
            try
            {
            	r=tableVar.getSelectedRow(); 
              if ((cellNt!=null) && (cellNt.equals("")==false))
              {
              	double valore=Double.parseDouble(cellNt);
             
	             if (valore<=0)
	             { 
	               
	               
	               JOptionPane.showMessageDialog
	            (null,"Insert a Positive Number","Warning",JOptionPane.WARNING_MESSAGE);
	          
	                tableVar.setValueAt(Math.round(nt),r,1);
	               tableVar.setEditingRow(r);
	               
	             }
	             else
	             {
	             	nt=(int) valore;
	             	deltat=tmax/nt;
	             }
              
              }
             
            }
            catch (NumberFormatException  ex)
            {
            	JOptionPane.showMessageDialog
            (null,"Insert a Positive Number","Warning",JOptionPane.WARNING_MESSAGE); 
             tableVar.setValueAt(Math.round(nt),r,1);
	               tableVar.setEditingRow(r);	                
            }
            
            
            
           		
      	}

    });

 
   panelProp.add(scrollPaneVar);
   
  }//end class ViewTabVar
  
  
   
  
 
 
  /**
 	* Open the Cluster Probability Table of the drawed clusters
 	* @param String Vector containing the probability of every cluster of the model.
 	* The sum of all the probabilities must be equal to 1 
   */ 
  public void viewTableF(Vector<Double> values)
  {  
    
   Vector<Vector<String>> rowData = new Vector<Vector<String>>();
   for (int i=0;i<values.size();i++)
    {
  	    Vector<String> row = new Vector<String>();
    	row.addElement(String.valueOf(i));
    	row.addElement("");
    	rowData.addElement(row);
    	
	}
  
    Vector<String> columnNames = new Vector<String>();
     
    columnNames.addElement("Cluster");
    columnNames.addElement("Probability");
  
    
    DefaultTableModel model = new DefaultTableModel(rowData, columnNames) 
		{
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int col) {
				if (col==0)   
				{
				return false;}
				else
				{ return true;}
			};
		
			
		};
		table = new JTable(model);
		int u=0;
		
		Vector<Cluster> vec1=new Vector<Cluster>(); //vector con le labels dei clusters esistenti
		  for (Object vertex : g.getChildCells(g.getDefaultParent(), true, true)) 
                      {
                      	
                      	mxCell cc=(mxCell) vertex;
                      	if (cc.isVertex()==true) 
                      	{
                      	Cluster cl=new Cluster();
                      	 cl.setLabel( g.getView().getState(cc).getLabel()) ;
                      	  vec1.add(cl);
                      	 
                      	 u=u+1;
                      	}
                      }
		
		try
		{
		
				for(int j=0;j<values.size();j++)
            	{
            	  //System.out.println( table.getValueAt(j, 0)+" : "+table.getValueAt(j, 1)); 
            	  
            	  //carica la colonna del vector f  nella seconda colonna della tabella e le labels dei clusters nella prima
            	  table.setValueAt(round(values.get(j),4), j, 1);  
            	  table.setValueAt(vec1.get(j).getLabel(), j, 0); 
           	   System.out.println("valore vector f "+values.get(j));
                }
        }
        catch 
        (Exception exxx) 
        {
        	
        	System.out.println(values.size()+" eccezione viewtableF ->"+exxx);
        }
        
        
        	scrollPaneProbab=new JScrollPane(table);
		
		 Dimension tablePreferred2 = scrollPaneProbab.getPreferredSize();
                scrollPaneProbab.setPreferredSize(
                    new Dimension(tablePreferred2.width/8, tablePreferred2.height/4) );
    
		
		table.setMinimumSize(new Dimension(100,90));
	  //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredSize(new Dimension(150,500));
	   
		scrollPaneProbab.setVisible(true);
 
    table.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() 
    {
    	
    	public void editingCanceled(ChangeEvent e) 
    	{
    		
                       // System.out.println("editingCanceled");
                       
                       table.setValueAt("0.0",r,1);
                       
                       	sessionOpen=true;
                       
        }
      	public void editingStopped(ChangeEvent e) 
      	{
      		r=table.getSelectedRow();
      		Double precVal=f.get(r);
      	
      		System.out.println("riga selezionata ->>>>> "+r+" Valore prec: "+precVal);
      		double okNum,valore;
      
		           String probab=table.getValueAt(r, 1).toString();
		           System.out.println("Probab "+probab);
		          okNum=0;
		          valore=0;
		          
		      if ((probab!=null) && (probab.equals("")==false))
		      {
		         
		          	
		         try
		         {
		         	 do
		         	 {	
		              valore=Double.parseDouble(probab);
		              System.out.println(valore);
		             
		             if ((valore<0) || (valore>1))
		             { 
		               
		               
		               JOptionPane.showMessageDialog
		            (null,"Insert a Number between 0 and 1","Warning",JOptionPane.WARNING_MESSAGE);
		               
		               table.setEditingRow(r);
		               table.setValueAt(precVal,r,1);
		               okNum=1;
		               
		             }
		             else
		             if ((valore>0) && (valore!=precVal))
		             {
		             	boolean ok=true;//check();
		             	
		             //	if (ok==true)
		             	{ 
		             	      sessionOpen=true;
		             	   	  okNum=1;
		             	   	  f.setElementAt(valore,r);
		             	   	  
		             	   	  if (resizeMi.isSelected()==true)
		             	   	  {
		             	   	//  System.out.println("espandi forma del cluster");
		             	   	  resizeCluster(r,valore);
		             	   	  
		             	   	  }
		             	
		                }
		                /*
		                else
		                {
		                JOptionPane.showMessageDialog
			           			 (null,"Error: The total probability is not equal than 1","Warning",JOptionPane.WARNING_MESSAGE);
			           			 table.setEditingRow(r);
		              		 	table.setValueAt(precVal,r,1);
		               			okNum=1;
			           			
		                }
		                */ 
		               }
		             else
		             if ((valore==0) &&  (valore!=precVal))
		             {
		               	okNum=1;
		             	sessionOpen=true;
		      	        table.setValueAt("0.0",r,1);
		      	        double num=0;
		      	         f.setElementAt(num,r);
		      	          if (resizeMi.isSelected()==true)
		      	          {
		      	            resizeCluster(r,valore);
		      	           }
		      	  
		              }
		               else
		               if ((valore==precVal)) 
		               { okNum=1; 
		               
		                   if (resizeMi.isSelected()==true)
		             	   	  {
		             	   	      //System.out.println("espandi forma del cluster");
		             	   	      
		             	   	      resizeCluster(r,valore);
		             	   	  
		             	   	  }
		                }
		               
		               
		            }
		            while (okNum==0);
		           }
		            catch (NumberFormatException  ex)
		            {
			            	JOptionPane.showMessageDialog
			            (null,"Insert a Number between 0 and 1","Warning",JOptionPane.WARNING_MESSAGE); 
			            
			           
			            table.setValueAt(precVal,r,1);
			             table.setEditingRow(r);
			            
		            }
		     
		            
		            
		                  
		      	}
		      	else
		      	if (probab.equals("")==true) //cella cancellata
		      	{
		      		
		      	   table.setValueAt("0.0",r,1);
		      	   double num=0;
		      	   f.setElementAt(num,r);
		      	  
		                 
		      	}
      	/*
      	if (check(f)==false)
      	{
      	  JOptionPane.showMessageDialog
			           			 (null,"Error: The total probability is not equal than 1","Warning",JOptionPane.WARNING_MESSAGE);
			
      	}
        */
      	
      }

    });
  
    	panelProp.add(scrollPaneProbab);

  }//end class ViewTableF


  /**
 	* Open the  Table of the selected h Cluster 
 	* @param String Vector containing the Table columns items to be visualized 
   */ 
  public void viewTableH(Vector<String> values)
  {  
    
   Vector<Vector<String>> rowData = new Vector<Vector<String>>();
   for (int i=0;i<values.size();i++)
    {
  	    Vector<String> row = new Vector<String>();
    	row.addElement(String.valueOf(i));
    	row.addElement("");
    	rowData.addElement(row);
    	
	}
  
    Vector<String> columnNames = new Vector<String>();
     
    columnNames.addElement("Item");
    columnNames.addElement("Value");
    
    
            	   	if ((scrollPaneCol.isVisible()==true) && (scrollPaneCol!=null))
            	   	{
            	   	  scrollPaneCol.setVisible(false);
            	   	  
            	   	}
            	   	if ((scrollPaneProbab.isVisible()==true) && (scrollPaneProbab!=null))
            	   	{
            	   	  scrollPaneProbab.setVisible(false);
            	   	  
            	   	}
            	   	if ((scrollPaneVar.isVisible()==true) && (scrollPaneVar!=null))
            	   	{
            	   	  scrollPaneVar.setVisible(false);
            	   	 
            	   	}
            	   	
            	   	colorMi.setSelected(false);tabProbabMi.setSelected(false); tabPropMi.setSelected(false);
  
    
    DefaultTableModel model = new DefaultTableModel(rowData, columnNames) 
		{
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int col) {
				if ((col==0) || (row==hId) || mode.equals("Cooperation"))
				{
				return false;}
				else
				{ return true;}
			};
		
			
		};
		table = new JTable(model);
		int u=0;
		
		Vector<Cluster> vec1=new Vector<Cluster>();
		  for (Object vertex : g.getChildCells(g.getDefaultParent(), true, true)) 
                      {
                      	
                      	mxCell cc=(mxCell) vertex;
                      	if (cc.isVertex()==true) 
                      	{
                      	Cluster cl=new Cluster();
                      	 cl.setLabel( g.getView().getState(cc).getLabel()) ;
                      	  vec1.add(cl);
                      	 
                      	 u=u+1;
                      	}
                      }
		
		try
		{
		
				for(int j=0;j<values.size();j++)
            	{
            	  //System.out.println( table.getValueAt(j, 0)+" : "+table.getValueAt(j, 1)); 
            	  
            	  //carica la colonna della matrice B nella colonna della tabella
            	  table.setValueAt(values.get(j), j, 1);  
            	  table.setValueAt(vec1.get(j).getLabel(), j, 0); 
           	   
                }
        }
        catch 
        (Exception exxx) 
        {
        	System.out.println("eccez tableH"+exxx);
        }
        
        
        	scrollPaneProp=new JScrollPane(table);
		
		 Dimension tablePreferred2 = scrollPaneProp.getPreferredSize();
                scrollPaneProp.setPreferredSize(
                    new Dimension(tablePreferred2.width/8, tablePreferred2.height/4) );
    
		
		table.setMinimumSize(new Dimension(100,90));
	  //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredSize(new Dimension(150,500));
	   
		scrollPaneProp.setVisible(true);
 
    table.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() 
    {
    	
    	public void editingCanceled(ChangeEvent e) 
    	{
    		
                       // System.out.println("editingCanceled");
                       
                       table.setValueAt("0.0",r,1);
                       
        }
      	public void editingStopped(ChangeEvent e) 
      	{
      		r=table.getSelectedRow();
      		Double precVal=B[r][hId][iId];  //B[hId][r][iId];
      	
      		System.out.println("riga selezionata ->>>>> "+r+" Valore prec: "+precVal);
      		double okNum,valore;
      
		           String probab=table.getValueAt(r, 1).toString();
		           System.out.println("Probab "+probab);
		          okNum=0;
		          valore=0;
		          
		      if ((probab!=null) && (probab.equals("")==false))
		      {
		         
		          	
		         try
		         {
		         	 do
		         	 {	
		              valore=Double.parseDouble(probab);
		              System.out.println(valore);
		             
		             if ((valore<0) || (valore>1))
		             { 
		               
		               
		               JOptionPane.showMessageDialog
		            (null,"Insert a Number between 0 and 1","Warning",JOptionPane.WARNING_MESSAGE);
		               
		               table.setEditingRow(r);
		               table.setValueAt(precVal,r,1);
		               okNum=1;
		               
		             }
		             else
		             if ((valore>0) && (valore!=precVal))
		             {
		             	double hVal=Double.parseDouble(table.getValueAt(hId, 1).toString());
		             	double rVal=Double.parseDouble(table.getValueAt(r, 1).toString());
		             	
		             	System.out.println("hVal: "+hVal+" rVal: "+rVal+ "precVal: "+precVal);
		             	
		             	if (rVal>precVal)
		             	{ 
		             	
		             	   
		             		if (precVal>0)
		             		{
		             		   g.getModel().beginUpdate();
		             		 
		             		 graph.removeEdge(String.valueOf(hId),String.valueOf(r) );
		             		 
		             		  g.getModel().endUpdate();
		             	    } 
		             		 
		             		
			             	if (hVal>=(rVal-precVal))
				            {
				             		hVal=hVal-(rVal-precVal);
				             		hVal=round(hVal,4);
				             		B[hId][hId][iId]=hVal;
				             		table.setValueAt(String.valueOf(hVal),hId,1);
				             	
				             	B[r][hId][iId]=valore;
				             	String s1=String.valueOf(hId);
				             	String s2=String.valueOf(table.getSelectedRow());
				             	
				               g.getModel().beginUpdate();
				               
				                  MyEdge edgess=graph.addEdge(s1, s2);
				                
	        					  graph.setEdgeWeight(edgess,valore);
	        					
	        					  
	        					  freccia_but.setSelected(true);
	        					  
	        				   g.getModel().endUpdate();
        					  okNum=1;
			               }
			               else
			               {
			               	if (hVal>0)
			               	{
			                  JOptionPane.showMessageDialog
			           			 (null,"Error: Insert a Number less than or equal to "+hVal,"Warning",JOptionPane.WARNING_MESSAGE);
			                }
			                else
			                {
			                  JOptionPane.showMessageDialog
			           			 (null,"Error: The total probability is greater than 1","Warning",JOptionPane.WARNING_MESSAGE);
			                }
			            
			            	table.setValueAt(String.valueOf(precVal),r,1);
			            	table.setEditingRow(r);
			            	okNum=1;
			               }
			               
			               
		               
		               }
		               //se hVal<precVal ->   inserisco un numero minore
		               else
		               if (rVal<precVal)
		               {
		               	System.out.println("hval: "+hVal+" precVal "+precVal+" rval: "+rVal);
		                 hVal=hVal+precVal-rVal;
		                 hVal=round(hVal,4);
		                 B[hId][hId][iId]=hVal;
			             		table.setValueAt(String.valueOf(hVal),hId,1);
			             	okNum=1;
			             	B[r][hId][iId]=rVal;
			             	if (edgePainted()==true)
								 { removeAllEdges();}
			             //	removeAllEdges();
			             	
			             	for (int k=0;k<nCluster;k++)
							   {
							   	if ((B[k][hId][iId]>0) && (hId!=k))
							   	{
							   		g.getModel().beginUpdate();
							   		
			                         MyEdge edgess=graph.addEdge(String.valueOf(hId),String.valueOf(k));
			                
        					  graph.setEdgeWeight(edgess,B[k][hId][iId]);
 			             
			             g.getModel().endUpdate();
							    // graph.addEdge(String.valueOf(hId),String.valueOf(k));
							     
							    }
							   }
		               }
		               
		             }
		             else
		             if ((valore==0) &&  (valore!=precVal))
		               {
		               	okNum=1;
		               	double hVal=Double.parseDouble(table.getValueAt(hId, 1).toString());
		      	   table.setValueAt("0.0",r,1);
		      	   
		      	   hVal=hVal+precVal;
		      	   	hVal=round(hVal,4);
		           table.setValueAt(String.valueOf(hVal),hId,1);
		               	
		               	B[r][hId][iId]=0;
		      	   B[hId][hId][iId]=hVal;
		               	
		               	String s1=String.valueOf(hId);
		             	String s2=String.valueOf(table.getSelectedRow());
		                 graph.removeEdge(s1,s2);
		               }
		               else
		               if ((valore==precVal)) { okNum=1;}
		            }
		            while (okNum==0);
		           }
		            catch (NumberFormatException  ex)
		            {
			            	JOptionPane.showMessageDialog
			            (null,"Insert a Number between 0 and 1","Warning",JOptionPane.WARNING_MESSAGE); 
			            
			           
			            table.setValueAt(precVal,r,1);
			             table.setEditingRow(r);
			            
		            }
		     
		            
		            
		                  
		      	}
		      	else
		      	if (probab.equals("")==true) //cella cancellata
		      	{
		      		double hVal=Double.parseDouble(table.getValueAt(hId, 1).toString());
		      	   table.setValueAt("0.0",r,1);
		      	   hVal=hVal+precVal;
		      	  	hVal=round(hVal,4);
		           table.setValueAt(String.valueOf(hVal),hId,1);
		      	   String s1=String.valueOf(hId);
		            String s2=String.valueOf(table.getSelectedRow());
		            
		            B[r][hId][iId]=0;
		      	   B[hId][hId][iId]=hVal;
		            
		             g.getModel().beginUpdate();
		                 graph.removeEdge(s1,s2);
		                 
		                  g.getModel().endUpdate();
		                 
		      	}
      	
        
      	
      }

    });
  
    	panelProp.add(scrollPaneProp);

  }//end class ViewTableH
  
  
  /**
 	* Open the  Table to change colors of the drawed clusters 
   */ 
  public void viewTabColor()
  {  
    
   
  
    Vector<String> columnNames = new Vector<String>();
     
    columnNames.addElement("Clusters");
    columnNames.addElement("Colors");
    
    Vector<String> row1 = new Vector<String>();
    row1.addElement(" Clusters ");
    row1.addElement("");
    
    
    Vector<String> row2 = new Vector<String>();
    row2.addElement(" Candidate h ");
    row2.addElement("");
    
    Vector<String> row3 = new Vector<String>();
    row3.addElement(" Test i ");
    row3.addElement("");
    
    
    
    
   
    
    Vector<Vector<String>> rowData = new Vector<Vector<String>>();
     
    rowData.addElement(row1);
    rowData.addElement(row2);
    rowData.addElement(row3);
    
    
    TableModel model = new MyTableModel();
      JTable table = new JTable(model);
      
      table.setDefaultRenderer(Color.class, new ColorTableCellRenderer());
      table.setDefaultEditor(Color.class, new ColorTableCellEditor());

   
        
        	scrollPaneCol=new JScrollPane(table);
		
		 Dimension tablePreferred2 = scrollPaneCol.getPreferredSize();
                scrollPaneCol.setPreferredSize(
                    new Dimension(tablePreferred2.width/8, tablePreferred2.height/4) );
    
		
		table.setMinimumSize(new Dimension(100,90));
	  //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredSize(new Dimension(150,100));
	   
		scrollPaneCol.setVisible(true);
 
    table.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() 
    {
    	private static final long serialVersionUID = 1L;
    	
    	public void editingCanceled(ChangeEvent e) 
    	{
    	              
        }
      	public void editingStopped(ChangeEvent e) 
      	{
      		
      	}
      	
      	
      	
      	
      	
    });
   table.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) 
            {
            
                rowCol=table.getSelectedRow(); 

           

               
                 //System.out.println("Color2: " + getTableCellBackground(table, 2, 1));
            }
            
        });

  
    	panelProp.add(scrollPaneCol);

  }//end class ViewTabColor
 

  public Color getTableCellBackground(JTable table, int row, int col) 
  {
  	
  	
  	
        TableCellRenderer renderer = table.getCellRenderer(row, col);
        Component component = table.prepareRenderer(renderer, row, col);    
        return component.getBackground();
    }
 
  private class MenuItemAction extends AbstractAction 
  {
  	private static final long serialVersionUID = 1L;
        
        public MenuItemAction(String text, ImageIcon icon, 
                Integer mnemonic) {
            super(text);
            
            putValue(SMALL_ICON, icon);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            System.out.println(e.getActionCommand());
        }
  }
    
    
    
  /**
 	* Open the Popup Menu on the selected cell 
 	* @param pt Position of the selected cell 
 	* @param cell Selected cell to view popup menu 
 	* @return JPopupMenu Menu editor of the selected cell
  */
	public JPopupMenu createPopupMenu(final Point pt, final Object cell) 
	{
		final long serialVersionUID = 1L;
		
		JPopupMenu menu = new JPopupMenu();
		final ImageIcon iconEdit=new ImageIcon ("icons/edit.png");
		final ImageIcon iconRemove=new ImageIcon ("icons/remove.png");
		final ImageIcon iconSelectAll=new ImageIcon ("icons/selectAll.png");
		final ImageIcon iconSelf=new ImageIcon ("icons/self.png");
		
		//GraphLayoutCache cache=graph.getGraphLayoutCache();
		if (cell != null) 
		{
			// Edit
			menu.add(new AbstractAction("Edit",iconEdit) 
			{
				private static final long serialVersionUID = 1L;
 
				public void actionPerformed(ActionEvent e) 
				{
					
				try
					{	
					   Object  cella=g.getSelectionCell();		
               			if  (cella!=null)		
                		{
                  
                 		 mxCell cell=(mxCell) cella;
               
                		System.out.println("cella da EDITARE:"+ cell);
                 		if	(cell.isVertex()==true)   
                 		{ 
                    		   System.out.println("vertice ");
                 	 	  String lab = g.convertValueToString(cella);
                 	  	 System.out.println("cella da EDITARE:"+ lab);
                 	  	 oldLabel=lab;
                 	   
                 	
                		 }
					
				        }
					     component.startEditing();	
					  
					     labelChanged=true;
					     
					     				
					     
				         //updateVector();  
				     }
  				
				catch (Exception exedit){System.out.println("Err Edit popup: "+exedit);}	
			 
				}
				
			});
			/*
			menu.addSeparator();
			//remove
			menu.add(new AbstractAction("Remove",iconRemove) {
				public void actionPerformed(ActionEvent e) 
				{
				
					try
					{
					   Object[] cells = g.getSelectionCells();
						g.getModel().remove(cells);
							
					    g.removeCells(cells);
		    		}
		   			 catch (Exception npee){System.out.println(npee);}				
				}
			       });
		//}
		*/
		menu.addSeparator();
		Object cells = g.getSelectionCell();
		 mxCell cel=(mxCell) cells;
	
		// Select All
		menu.add(new AbstractAction("Select All",iconSelectAll) 
		{
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent ev) 
			{
				//insert(pt);
			 g.selectAll();  
			}
		});
		try
		{
	 if (cel.isVertex()==true)
	 {
		  menu.addSeparator();
		
		// self edge
		/*
		menu.add(new AbstractAction("Self Edge",iconSelf) 
		{
			public void actionPerformed(ActionEvent ev) 
			{
				//insert(pt);
				
				mxCell cells = (mxCell) g.getSelectionCell();

				String labell=cells.getValue().toString();
			//graph.addEdge(labell,labell);
			Object parent = g.getDefaultParent();	
		//	g.insertEdge(parent,null,labell,cells,cells,"dashed=0;fontColor=#FF0000;style=GraphConstants.EDGESTYLE_SPLINE");
			g.insertEdge(parent,null,labell,cells,cells,"edgeStyle=elbowEdgeStyle;elbow=horizontal;"
									+ "exitX=1;exitY=0;exitPerimeter=1;entryX=1;entryY=1;entryPerimeter=1;GraphConstants.STYLE_SPLINE");

			}
		});
		*/
	  }
		
	   }
	   catch  (Exception selfex) {System.out.println("exception tasto self");}
         
	  }
		return menu;
	}
	
	/*
	//riceve la vecchia label e la sostituisce con la nuova nel vector degli archi
	public void changeEdgeLabel(String old, String news)
	{
		Edge nod=new Edge();
	  for (int j=0;j<a.size();j++)
	  {
	  
	  		if (a.get(j).getSource().equals(old))
	  		{
	  			nod=a.get(j);
	  			nod.setSource(news);
	  			a.setElementAt(nod,j);
	  		}
	  		if (a.get(j).getTarget().equals(old))
	  		{
	  			nod=a.get(j);
	  			nod.setTarget(news);
	  			a.setElementAt(nod,j);
	  		}
	  
	  }
	
	}
	
	

            //aggiorna i vector n ed a
            public  void updateVector() 
            {
            	
            	Vector<String> vero=new Vector<String>();
                 Vector<Nodo> vec=new Vector<Nodo>();//vector temporaneo dei dei nodi  inseriti nel grafico   
                 Vector<String> newLabel=new Vector<String>();
                Vector newX=new Vector(); //vettore nuova posizione x cella spostata
                Vector newY=new Vector(); //vettore nuova posizione y cella spostata
                   
                
                   String archi=graph.edgeSet().toString(); 

                  // a=new Vector<Edge>();
                   a= parseEdge(archi);//riempie il vector a con  tutti gli edge del grafico
                 
                 
                System.out.println("\n\n");
              
                vero=parseVertex(graph.vertexSet().toString());//lista vertex id reali del grafico 
                
                //rimuove dal vector di nodi n i vertex cancellati
                for (int j=0;j<n.size();j++)
                {
                	boolean trovato=false;
                	for (int k=0;k<vero.size();k++)
                	{
                	     if (n.get(j).getId().equals(vero.get(k))==true) 
                	    { trovato=true; }
                	}
                	if (trovato==false)
                	{
                	n.remove(j);
                	}
                }
          
                
                    
                      String lab=new String();
                       System.out.println("Vector sistema:\n");
                       int id=0; 
                      for (Object vertex : g.getChildCells(g.getDefaultParent(), true, true)) 
                      {
                      	mxCell cc=(mxCell) vertex;
                      	if (cc.isVertex()==true) 
                      	{
                      	   int  num=(int) g.getCellGeometry(vertex).getX();
                      	   newX.add(num);
                      	   num=(int) g.getCellGeometry(vertex).getY();
                      	   newY.add(num);
                      	   
                      		
                      		lab= ((mxCell) vertex).getValue().toString();
                      	
                      		newLabel.add(lab);
                            // System.out.println("ID "+lab+"  Value:"+((mxCell) vertex).getValue()+" Style:"+((mxCell) vertex).getStyle());
                         	id=id+1;
                        }
                      }
                      System.out.println("\nvettore newLabel\n");
                       for (int j=0;j<newLabel.size();j++)
                       {
                       
                         System.out.println(newLabel.get(j));
                       }
                       
                       for (int j=0;j<n.size();j++)
                       {
                         
                           if (n.get(j).getVertexLabel().equals(newLabel.get(j))==false) 
                	       { 
                	           changeEdgeLabel(n.get(j).getVertexLabel(),newLabel.get(j));
                	        }
                        
                         //System.out.println(newLabel.get(j));
                       }
                       
       					   
       					//System.out.println(graph);
                    
                    System.out.println("Vector aggiornato:");
                    Nodo nuovo_nodo=new Nodo();
                    for(int j=0;j<n.size();j++)
                    {
                      nuovo_nodo=n.get(j);
                      nuovo_nodo.setVertexLabel(newLabel.get(j));
                      nuovo_nodo.setX(newX.get(j).toString());
                      nuovo_nodo.setY(newY.get(j).toString());
                        
                      n.setElementAt(nuovo_nodo,j);
                      
                      System.out.println("label: "+n.get(j).getVertexLabel());
                    }
       		    
                      System.out.println("VertexSet:  "+graph.vertexSet());
       					System.out.println("EdgeSet:   "+graph.edgeSet());
       					
       			
            }
            
            */
    
    public void go()  
    {  
    	
  
    
    
      screenX=(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
      screenY=(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
     //  System.out.println("schermo:"+screenX+" "+screenY);
       saveFileAs="";
       labelChanged=false;
       //inizializzazione variabili globali 
       mode="";
       mu=5;
       eta0=0.6;
       beta0=0.6;
       beta=0.4;
       tmax=200;
       nt=100000;
       deltat=tmax/nt;
       nCluster=0;
       dist=200;
       diametro=40;
       cell1On=false;
       cell2On=false;
       iId=-1;
       hId=-1;
       resetB(nMax);
       
       // resetF(nMax);
       
       clust=new Vector<Cluster>();
       
       enne=new double [nt];
      emme=new double [nt];
       
       color0=Color.WHITE;
       color1=Color.LIGHT_GRAY;
       color2=Color.YELLOW;
       
       hCol=fillcolor;
       iCol="yellow";
       kCol="white";
       
       sessionOpen=false;
       
       panelVar = new JPanel(new BorderLayout(2,1));
       panelVar.setBorder(new TitledBorder("Variables"));
       
       panelProp = new JPanel(new BorderLayout(2,1));
       panelProp.setBorder(new TitledBorder("Properties"));
       
       scrollPaneProp=new JScrollPane();
       scrollPaneCol=new JScrollPane();
       scrollPaneData=new JScrollPane();
       scrollPaneVar=new JScrollPane();
          scrollPaneProbab=new JScrollPane();
      
       
      //  ListenableGraph<String, DefaultEdge> graph = new ListenableDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        
       /* 	String s1 = "A";
        	String s2 = "B";
         	graph.addVertex(s1);
        	graph.addVertex(s2);
            graph.addEdge(s1, s2);
        */    
            

       

       // System.out.println("VertexSet:  "+graph.vertexSet());
      // System.out.println("EdgeSet:   "+graph.edgeSet());


       // JGraphTXAdapter<String, DefaultEdge> 
        g = new JGraphTXAdapter<String, MyEdge>(
                graph, "noLabel=0",
                "shape=ellipse;perimeter=ellipsePerimeter");
                
                
          
           /*
            //crea un visualization usando JGraph via adapter g
            jgAdapter=new JGraphModelAdapter<String,DefaultEdge>(graph);
            JGraph jgraph=new JGraph(jgAdapter);    
            //adjustDisplaySettings(jgraph); 
//            getContentPane().add(jgraph);
            jgraph.setJumpToDefaultPort(true);
            
            */
            
            /*
            GraphModel model = new DefaultGraphModel();
        JGraph jgraph = new JGraph(model);
        // Control-drag should clone selection
        jgraph.setCloneable(true);

        // Enable edit without final RETURN keystroke
        jgraph.setInvokesStopCellEditing(true);

        // When over a cell, jump to its default port (we only have one, anyway)
        jgraph.setJumpToDefaultPort(true);
        */

    

        g.setCellsResizable(false);//seleziona cella per ridimensionare
        g.setCellsEditable(true);
        g.setCellsMovable(false);
        g.setAutoSizeCells(false);
        g.setCellsDeletable(false);
        g.setCellsBendable(false);
        g.setCellsDisconnectable(false);     
        g.setCellsDeletable(true);
        g.setCellsCloneable(true);
        g.setDropEnabled(true);
        g.setSplitEnabled(true);
        g.setAllowDanglingEdges(false);
        g.setAllowLoops(false);
       
        
      //graph.waypointsEnabled(true);
        
       
     
      //	Map<String, Object> stil = new HashMap<String, Object>();
      	
      	stil = g.getStylesheet().getDefaultEdgeStyle();
		stil.put(mxConstants.STYLE_ROUNDED, true);
	//	stil.put(mxConstants.STYLE_NOLABEL, true);//NASCONDE LA LABEL SULL EDGE
		
		stil.put(mxConstants.STYLE_EDGE, GraphConstants.STYLE_SPLINE); // <-- This is what you want
	   // stil.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ORTHOGONAL);
	 //stil.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ELBOW);
	  //stil.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_LOOP);
	   // stil.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ENTITY_RELATION);
	  
	   	//stil.put(mxConstants.STYLE_EDGE, mxConstants.STYLE_NOEDGESTYLE);
		stil.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
		
		//stil.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OVAL);
		stil.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_OVAL);
		
		stil.put(mxConstants.STYLE_STROKECOLOR, "#6482B9");
		stil.put(mxConstants.STYLE_FONTCOLOR, "#446299");
		//stil.put(mxConstants.STYLE_ENDFILL, 1);//PALLINO FINALE BIANCO 
		stil.put(mxConstants.STYLE_STARTFILL, 1);//PALLINO INIZIALE BIANCO 
		
		stil.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_LEFT);
		stil.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_LEFT);
		stil.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT);
		
		mxConstants.LABEL_INSET=5; //distanza della label dal suo edge
		
		//GraphConstants.setLabelAlongEdge(stil, true);
		
		
	
	
	   
		
		
		//GraphConstants.setLineStyle(stil, GraphConstants.STYLE_SPLINE);
		mxStylesheet foo = new mxStylesheet();
		foo.setDefaultEdgeStyle(stil);
		g.setStylesheet(foo);
		
	//	GraphConstants.setLabelAlongEdge(stil, true);
		
		
		 mxEventSource.mxIEventListener listener = new mxEventSource.mxIEventListener() {
			@Override
			public void invoke(Object sender, mxEventObject evt) {
				undoMgr.undoableEditHappened((mxUndoableEdit) evt.getProperty("edit"));
			}
		};        
             
  //Quando um evento UNDO acontecer o mesmo serÃ¡ memorizado
		g.getModel().addListener(mxEvent.UNDO, listener);
		g.getView().addListener(mxEvent.UNDO, listener);
	
      
       /*
       
        for (Object vertex : g
                .getChildCells(g.getDefaultParent(), true, false)) {
            g.updateCellSize(vertex, true);
        }*/
   
        for (Object vertex : g
                .getChildCells(g.getDefaultParent(), true, false)) {
           System.out.println("getValue:  "+((mxCell) vertex).getValue()+"   getStyle:   "+((mxCell) vertex).getStyle());
           
        }
        
        /*
        System.out.println("test");

       for (Object vertex : g
               .getChildCells(g.getDefaultParent(), false, true)) {
            System.out.println("edges");
          System.out.println(((mxCell) vertex).getSource() + " target "
                   + ((mxCell) vertex).getTarget());
       }*/

        mxHierarchicalLayout layout = new mxHierarchicalLayout(g);
        layout.execute(g.getDefaultParent());
        /*
        mxIGraphLayout layout = new mxCircleLayout(g);
        layout.execute(g.getDefaultParent());
        */
       // mxGraphComponent component = new mxGraphComponent(g);
       component = new mxGraphComponent(g);
       //component.zoom(1.5); //effettua lo zoom del grafico
        frame = new JFrame("JGraphX Editor");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /*
        GraphModel model = new DefaultGraphModel();
        JGraph graphh = new JGraph(model);
        // Control-drag should clone selection
        graphh.setCloneable(true);
        frame.getContentPane().add(new JScrollPane(graphh));
        */
       
         component.setConnectable(false); //disabilita la creazione di edges manualmente
  
      Vector<String> infoVar=new Vector<String>();
 
 	  
     
      infoVar.add(String.valueOf(eta0));	//eta0 = 0.6;
      infoVar.add(String.valueOf(beta0));	//beta0 = 0.6;
      infoVar.add(String.valueOf(beta));	//beta0 = 0.4;
  	  infoVar.add(String.valueOf(Math.round(tmax)));	
  	  infoVar.add(String.valueOf(Math.round(nt)));	

                 Vector<String> infoData=new Vector<String>();
                 infoData.add(String.valueOf(mu));		//mu = 9; 
                 infoData.add(String.valueOf(nCluster)); //numero di clusters
     			 infoData.add("");		//mode = competition;
                 
                  viewTabData(infoData); 
                 
     	//viewTabVar(infoVar); 
     	
     	 tabVarOpen=true;
        
        
        
      

    
        
        //crea il pannello delle proprietà posto a destra
        splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,panelVar, panelProp);
       
       splitPanel.setDividerSize(3);
        
       // panelVar.add(splitPanel,BorderLayout.CENTER);
       
        //crea il pannello principale con a sinistra il grafico ed a  destra le tabelle
        mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, component,splitPanel);
        
        
       
       // panelVar.setVisible(false); nasconde il pannello con le tabelle proprietà
	
        mainPanel.setContinuousLayout(true);
        mainPanel.setOneTouchExpandable(true);
       
         mainPanel.setResizeWeight(0.9);
         mainPanel.setDividerSize(10);
            
        // mainPanel.setDividerLocation(0.1);
        // panelVar.setPreferredSize(new Dimension(150, 100));
         
         
         //aggiunge al frame generale il pannello principale
		frame.getContentPane().add(mainPanel);
		
       // frame.getContentPane().add(component);
        //frame.setSize(1360,700);
        frame.setSize(800,400);
        frame.setVisible(true);
       //frame.setLocationRelativeTo(null);
       frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        
        Demo cc = new Demo();
        
        JMenuBar menubar = new JMenuBar();
        
        final ImageIcon iconNew = new ImageIcon("icons/exit1.png");
        final ImageIcon iconOpen = new ImageIcon("icons/open1.png");
        final ImageIcon iconSave = new ImageIcon("icons/save1.png");
        final ImageIcon iconPng = new ImageIcon("icons/image.png");
        final ImageIcon iconExit = new ImageIcon();
        final ImageIcon iconPrint = new ImageIcon("icons/print1.png");

		//Menu File
       final  JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        //Menu Edit
        //JMenu editMenu = new JMenu("Edit");
        //editMenu.setMnemonic(KeyEvent.VK_F);
        
         //Menu View
         JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic(KeyEvent.VK_F);

	 tabVarMi = new JCheckBoxMenuItem (new MenuItemAction("Data Var", null, KeyEvent.VK_C));
		
	 tabProbabMi = new JCheckBoxMenuItem (new MenuItemAction("Cluster Probability", null, KeyEvent.VK_C));
                
        tabPropMi = new JCheckBoxMenuItem (new MenuItemAction("Global Var", null, KeyEvent.VK_C));

 	 edgeLabelMi = new JCheckBoxMenuItem (new MenuItemAction("Show Labels", null, KeyEvent.VK_C));
      
        colorMi = new JCheckBoxMenuItem (new MenuItemAction("Change Colors", null, KeyEvent.VK_C));
        
        resizeMi = new JCheckBoxMenuItem (new MenuItemAction("Cluster Resizing", null, KeyEvent.VK_C));


       

        //JMenuItem copyMi = new JMenuItem(new MenuItemAction("Copy", null, KeyEvent.VK_C));

        //JMenuItem pasteMi = new JMenuItem(new MenuItemAction("Paste", null,KeyEvent.VK_P));

       

       final  JMenuItem openMi = new JMenuItem(new MenuItemAction("Open", iconOpen, 
                KeyEvent.VK_O));

       final  JMenuItem saveMi = new JMenuItem(new MenuItemAction("Save", iconSave, 
                KeyEvent.VK_S));
       final  JMenuItem saveAsMi = new JMenuItem(new MenuItemAction("Save As...", iconSave, 
                KeyEvent.VK_S));
                
         final JMenuItem exportPngMi = new JMenuItem(new MenuItemAction("Export PNG", iconPng, 
                KeyEvent.VK_S));
      final   JMenuItem printMi = new JMenuItem(new MenuItemAction("Print", iconPrint, 
                KeyEvent.VK_S));
                
     final    JMenuItem newMi = new JMenuItem(new MenuItemAction("Close", iconNew, 
                KeyEvent.VK_N));

        JMenuItem exitMi = new JMenuItem("Exit", iconExit);
        exitMi.setMnemonic(KeyEvent.VK_E);
        exitMi.setToolTipText("Esci");
        exitMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
            ActionEvent.CTRL_MASK));
            
             newMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	try
            	{
                g.removeCells(g.getChildVertices(g.getDefaultParent()));
                i=0;
                saveFileAs="NewGraph"; frame.setTitle(saveFileAs);
                nCluster=0;
                cell1On=false;
       			cell2On=false;
       			iId=-1;
       			hId=-1;
       			resetB(nMax);
       			tableData.setValueAt(nCluster, 1, 1); 
       				scrollPaneProp.setVisible(false);		 
             			  			splitPanel.validate();
       			 //f=new Vector<String>;
       			 
               
            }
            catch (Exception neww) {};
                
                //frame.setVisible(false); go();
            }
        });
        
        
        openMi.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                saveFileAs=apri();//scegli il nome del file da aprire
                
                if (saveFileAs.length()!=0) 
                {
                	resetB(nCluster);
                	g.removeCells(g.getChildVertices(g.getDefaultParent()));
                	i=0;
                	ReadXMLFile leggi=new ReadXMLFile();
                	
                	clust=new Vector<Cluster>();
                	clust=leggi.readXmlNodi(saveFileAs);//carica il vector dei nodi
                	
                	
                
                	System.out.println("lunghezze vector clust: "+clust.size());
                	
                		Cluster nod; 	
                		Object parent = g.getDefaultParent();
                		String parola="[";
                		String parola2="";
                		for (int i=0;i<clust.size();i++)
                		{
                			parola2=clust.get(i).getMatrix();
                			parola2=parola2.substring(1,parola2.length()-1);
                			parola=parola+parola2;
                		}
                		parola=parola+"]";
                		
                	//	System.out.println("matrix completa caricata da xml ");
                		//	System.out.println(parola);
                			nCluster=clust.size();
                			//setF(nCluster);
                			tabProbabMi.setVisible(true);
                	f = new Vector<Double>(nCluster);
					for (int j=0;j<nCluster;j++)
					{
  						double p = Double.parseDouble(clust.get(j).getProb());
  							//p=round(p,6);
  	 				   f.add(p);					  
					}
                			
                			 unParsing(parola,nCluster);
                	tableData.setValueAt(nCluster, 1, 1);   
               
					reset();
					drawClusters(nCluster);
					updateLabel(clust);
				//	drawOpenClusters(nCluster,clust);    
					g.getView().validate();
                /*			
                 try
                 {
                 	for (int j=0;j<n.size();j++)
                	{
                	
                	
                	  
                	  System.out.println("VETTORE"+n.size());
                	  System.out.println(n.get(j).getVertexLabel()+"  "+n.get(j).getStyle());
                	  double p=Double.parseDouble(n.get(j).getP());
                	  double dimen=60;
                	  String styl=new String();
                	  if (p>0)
                	  {
                	   styl="shape=ellipse;perimeter=ellipsePerimeter;"+fillcolor;
                	  	dimen=60+p*100;
                	  
                	  }
                	  else
                	  {
                	   styl=n.get(j).getStyle();
                	   
                	  }
                	                 	  
                	g.insertVertex(parent,null,n.get(j).getVertexLabel(),Double.valueOf(n.get(j).getX()),Double.valueOf(n.get(j).getY()),dimen,dimen,styl);
                	i=i+1;
                	}
                	 Object  sourc,targ;
              	    for (int j=0;j<a.size();j++)
                	{
                		
                	  //g.insertEdge(parent,null,a.get(j).getEdgeLabel(),a.get(j).getSource(),a.get(j).getTarget());
                		  
                	graph.addEdge(a.get(j).getSource(),a.get(j).getTarget());
                	
                	sourc= a.get(j).getSource().toString();
                	targ = a.get(j).getTarget().toString();
                	mxGraphModel mx=new mxGraphModel();
                	
                System.out.println(sourc+"  -> "+targ);	
              	g.insertEdge(parent,null,a.get(j).getEdgeLabel().toString(),mx.getCell(sourc.toString()),mx.getCell(targ.toString()),"edgeStyle=elbowEdgeStyle;elbow=horizontal;"
									+ "exitX=1;exitY=0;exitPerimeter=1;entryX=1;entryY=1;entryPerimeter=1;");
                
                	}
	
                }
                catch (Exception npe){System.out.println("eccezione ");}
                
                */
                	
                }
                
                
            }
        });
        
        

        exitMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
               
                System.exit(0);
            }
        });
        
         exportPngMi.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
            	String imgName=saveAs();
            	
            	imgName=imgName+".png";
            	System.out.println(imgName);
          try
            {
          BufferedImage image = mxCellRenderer.createBufferedImage(g, null, 1, Color.WHITE, true, null);
          ImageIO.write(image, "PNG", new File(imgName));
            }
            catch(Exception imgg) {System.out.println("export image error");}
            	
            }
         });
        
        
        saveMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                //save("prova",graph);
                // updateVector();
               
                     updateClusters();
                
                WriteXMLFile wf=new WriteXMLFile();
                if (saveFileAs.length()==0) 
                {
                	saveFileAs=saveAs();
                }
                else
                {
                	saveFileAs=saveFileAs.substring(0,saveFileAs.length()-4);
                }
                try
                {
                wf.writeXML(clust,saveFileAs);
                }
                	catch (Exception ss){}
                //save_xml("prova2",graph);
            }
        });
        
        saveAsMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
               //updateVector();
               
                     updateClusters();
               
               
               String oldName=saveFileAs;
               saveFileAs = saveAs();
               //String archi=graph.edgeSet().toString();
               if (saveFileAs.equals(oldName)==true)
               {
                  saveFileAs=saveFileAs.substring(0,saveFileAs.length()-4);
               }
                
                
                
                WriteXMLFile wf=new WriteXMLFile();
             try{   
            
                wf.writeXML(clust,saveFileAs);
                //save_xml("prova2",graph);
              }
               	catch (Exception ss){}
               
            }
        });
        
        printMi.addActionListener(new ActionListener() {
        	
        	
        	
        	       	
            @Override
            public void actionPerformed(ActionEvent event) 
            {   
             
             System.out.println(rowCol+"  Colori: "+kCol+"  "+hCol+" "+iCol);
            
     			updateClusters();
                    System.out.println("GRAPH giulio:\n"+graph);
                    
                    
                    
                    
                    System.out.println("Vector Clust:");
                    for(int j=0;j<clust.size();j++)
                    {
                      System.out.println("id: "+clust.get(j).getId()+" Label: "+clust.get(j).getLabel()+" 2DMatrix: "+clust.get(j).getMatrix());
                    }
                    
                
                 String archi=graph.edgeSet().toString();
               
                System.out.println("graph.edgeSet().toString() **********: \n"+archi);
                
                    
                System.out.println("**********\n\n");
                    
                    
                      int id=0; int ed=0;
                      for (Object vertex : g.getChildCells(g.getDefaultParent(), true, true)) 
                      {
                      	mxCell cc=(mxCell) vertex;
                      	if (cc.isVertex()==true) 
                      	{
                      		id=id+1;
                             
                               
                               int newX=(int) g.getCellGeometry(vertex).getX();
                        	int newY=(int) g.getCellGeometry(vertex).getY();
 							  
 							System.out.println("ID "+id+"  Value:"+((mxCell) vertex).getValue()
                               +" Style:"+((mxCell) vertex).getStyle()+" "+newX+"  "+newY);   
                        }
                        else
                        	if (cc.isEdge()==true)
                        	{
                        		ed=ed+1;
                        		System.out.println("Edge ID "+ed+"  Value:"+((mxCell) vertex).getValue()
                               +" Style:"+((mxCell) vertex).getStyle());
                                System.out.println("geometry  "+cc.getGeometry());
                        	}
                        	                  	
                       
                      }
                       System.out.println("VertexSet:  "+graph.vertexSet());
       					System.out.println("EdgeSet:   "+graph.edgeSet());
                      
            }
        }); //end printMi
        
        tabVarMi.setSelected(true);
         tabProbabMi.setSelected(false);tabProbabMi.setVisible(false);
        tabPropMi.setSelected(false);
        edgeLabelMi.setSelected(true);
        colorMi.setSelected(false);
        resizeMi.setSelected(false);
  
        
        
        
        
       tabVarMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
            	if(tabVarMi.isSelected())
            	{
            	 scrollPaneData.setVisible(true);
            	}
            	else
            	{
            	scrollPaneData.setVisible(false);
            	}
            }
		 }); 
		 
		 tabProbabMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
            		if(tabProbabMi.isSelected()==false ) //è già selezionato, premo e lo deseleziono
            		{
            			//System.out.println("chiudere panelllo:");
            			if(scrollPaneProbab!=null)
            		  	{ scrollPaneProbab.setVisible(false);}
            		}
            		else
            	   if(tabProbabMi.isSelected()==true ) // non è selezionato, premo e lo seleziono per aprire la tabella
            	   {
		            	if (nCluster!=0)
		            	{
				            if ((scrollPaneCol.isVisible()==true) && (scrollPaneCol!=null))
		            	   	{
		            	   	  scrollPaneCol.setVisible(false);
		            	   	  colorMi.setSelected(false);
		            	   	}
		            	   	if ((scrollPaneVar.isVisible()==true) && (scrollPaneVar!=null))
		            	   	{
		            	   	  scrollPaneVar.setVisible(false);
		            	   	  tabPropMi.setSelected(false);
 	  
            	   	        }
		            		
		            		
		            			viewTableF(f);
		            		
		            	 		scrollPaneProbab.setVisible(true);
		            	 	  	
		            	 	  	splitPanel.validate();
		            	 	
		            		
		            	
		               }
                  }
            }
		 }); 
		 /*
		 tabPropMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
            	if ((nCluster!=0) && (scrollPaneProp!=null))
            	{
            		if(tabPropMi.isSelected())
            		{
            	 		scrollPaneProp.setVisible(true);
            	 	
            		}
            		else
	            	{
	            	scrollPaneProp.setVisible(false);
	            	}
               }
            }
		 }); 
		 */
		 
		 tabPropMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
            		if(tabPropMi.isSelected()==false ) //è già selezionato, premo e lo deseleziono
            		{
            			//System.out.println("chiudere panelllo:");
            			if(scrollPaneVar!=null)
            		  	{ scrollPaneVar.setVisible(false);}
            		}
            		else
            	   if(tabPropMi.isSelected()==true ) // non è selezionato, premo e lo seleziono per aprire la tabella
            	   {
            	   	
            	   	
            	   	if ((scrollPaneCol.isVisible()==true) && (scrollPaneCol!=null))
            	   	{
            	   	  scrollPaneCol.setVisible(false);
            	   	  colorMi.setSelected(false);
            	   	}
            	   	if ((scrollPaneProbab.isVisible()==true) && (scrollPaneProbab!=null))
            	   	{
            	   	  scrollPaneProbab.setVisible(false);
            	   	  tabProbabMi.setSelected(false);
            	   	}
            	   	if ((scrollPaneProp.isVisible()==true) && (scrollPaneProp!=null))
            	   	{
            	   	  scrollPaneProp.setVisible(false);
            	   	  //tabProbabMi.setSelected(false);
            	   	}
		              
		              
		            		
		            			viewTabVar(infoVar);
		            	 		scrollPaneVar.setVisible(true);
		            	 	  	
		            	 	  	splitPanel.validate();		               
                  }
            }
		 }); 
		 
		 
		 
		 edgeLabelMi.addActionListener(new ActionListener() 
		 {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
            	if (nCluster!=0)
            	{
            		if (edgePainted()==true)
								 { removeAllEdges();}
            		 //removeAllEdges();
            	   
	            	
	            		if(edgeLabelMi.isSelected())
	            		{
	            	 		//stil.put(mxConstants.STYLE_NOLABEL, "0");
	            	 		g.setLabelsVisible(true);
	            		}
	            		else
		            	{
		            		//stil.put(mxConstants.STYLE_NOLABEL, "1");
		            		g.setLabelsVisible(false);
		            	}
		            	
				  }	
							MyEdge edgess=new MyEdge();
							
							if ((hId!=-1) && (iId!=-1))
							{
							   for (int k=0;k<nCluster;k++)
							   {
							   	if ((B[k][hId][iId]>=0) && (hId!=k))
							   	{
							   		g.getModel().beginUpdate();
			                        edgess=graph.addEdge(String.valueOf(hId),String.valueOf(k));			                
        					  		graph.setEdgeWeight(edgess,B[k][hId][iId]);
 			            			g.getModel().endUpdate();
							    // graph.addEdge(String.valueOf(hId),String.valueOf(k));
							    }
							   }
							}
							
	               
	                	
	            		
            
            }
		 }); //end edgeLabelMi
		 
		 
		 colorMi.addActionListener(new ActionListener() 
		 {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
            	
            		// if (edgePainted()==true) { removeAllEdges();}
            		 //removeAllEdges();
            	   
	            	
	            		if(colorMi.isSelected()==false)
	            		{
	            			
	            		scrollPaneCol.setVisible(false);
	            	 	
	            		}
	            		else
	            		{
	            			
	            			if ((scrollPaneVar.isVisible()==true) && (scrollPaneVar!=null))
		            	   	{
		            	   	  scrollPaneVar.setVisible(false);
		            	   	  tabPropMi.setSelected(false);
		            	   	  
		            	   	}
		            	   		if ((scrollPaneProbab.isVisible()==true) && (scrollPaneProbab!=null))
		            	   	{
		            	   	  scrollPaneProbab.setVisible(false);
		            	   	  tabProbabMi.setSelected(false);
		            	   	}
		              
	            			
	            			
	            			viewTabColor();
             				splitPanel.validate();
             				
             				
             			//	System.out.println("editor value: "+color0+" "+color1+" "+color2);
             		
	            		   
	            		}
	            	
		            	
				  	
							
							
							   		
							  
							
							
	               
	                	
	            		
            
            }
		 }); //end colorMi
		 
        
        fileMenu.add(openMi);
        fileMenu.add(saveMi);
        fileMenu.add(saveAsMi);
        fileMenu.add(exportPngMi);
        fileMenu.add(printMi);
        fileMenu.add(newMi);
        fileMenu.addSeparator();
        fileMenu.add(exitMi);
       
       // editMenu.add(copyMi);
       // editMenu.add(pasteMi);
        
        viewMenu.add(tabVarMi);
         viewMenu.add(tabProbabMi);
        viewMenu.add(tabPropMi);
        viewMenu.add(edgeLabelMi);
         viewMenu.add(colorMi);
         viewMenu.add(resizeMi);

        menubar.add(fileMenu);
        
       // menubar.add(editMenu);
        
        menubar.add(viewMenu);

        frame.setJMenuBar(menubar);        
        
        
        
    //	cc.setBackground(Color.red);
       JToolBar toolbar1 = new JToolBar();
       toolbar1.setFloatable(true);//toolbar spostabile
       
       
       
       
       	final ImageIcon sel = new ImageIcon("icons/sel.png");
    	
    	final ImageIcon nodo = new ImageIcon("icons/nodo.png");
    	
    	final ImageIcon rect = new ImageIcon("icons/rect.png");
      
    	final ImageIcon triangle = new ImageIcon("icons/triangle.png");
      
        final ImageIcon freccia= new ImageIcon("icons/freccia.png");
        
         final ImageIcon curva = new ImageIcon("icons/curva.png");
        
        
        final ImageIcon canc = new ImageIcon("icons/canc.png");
        
        final ImageIcon select = new ImageIcon("icons/select.png");
        
        final ImageIcon zoomp = new ImageIcon("icons/zoomp.png");
        final ImageIcon zoomm = new ImageIcon("icons/zoomm.png");
        
         final ImageIcon undo = new ImageIcon("icons/undo.png");
       
        final ImageIcon redo= new ImageIcon("icons/redo.png");
        
         final ImageIcon piu= new ImageIcon("icons/piu.png");
         final ImageIcon meno= new ImageIcon("icons/meno.png");
        
         final ImageIcon go= new ImageIcon("icons/go.png");
         
         final ImageIcon bar= new ImageIcon("icons/bar.png");
         
         final ImageIcon curve= new ImageIcon("icons/curve.png");
        
        
 		JButton sel_but = new JButton(sel);      
    	JButton nodo_but = new JButton(nodo);
    	JButton rect_but = new JButton(rect);
    	JButton triangle_but = new JButton(triangle);
        freccia_but = new JButton(freccia);
        JButton curva_but = new JButton(curva);
        JButton canc_but = new JButton(canc);
        JButton select_but = new JButton(select);
        JButton zoomp_but = new JButton(zoomp);
        JButton zoomm_but = new JButton(zoomm);        
        JButton undo_but = new JButton(undo);
        JButton redo_but = new JButton(redo);
        
        JButton piu_but = new JButton(piu);
        JButton meno_but = new JButton(meno);
        JButton go_but = new JButton(go);
        JButton bar_but = new JButton(bar);
         JButton curve_but = new JButton(curve);
        
        toolbar1.add(sel_but);        
        toolbar1.add(nodo_but);
        //toolbar1.add(rect_but);
        //toolbar1.add(triangle_but);
        //
        //toolbar1.add(curva_but);
       // toolbar1.add(canc_but);
        //toolbar1.add(select_but);
        toolbar1.add(zoomp_but);
        toolbar1.add(zoomm_but);
        toolbar1.add(undo_but);
        toolbar1.add(redo_but);
        toolbar1.add(piu_but);
        toolbar1.add(meno_but);
        toolbar1.add(freccia_but);
         toolbar1.add(go_but);
         toolbar1.add(bar_but);
           toolbar1.add(curve_but);
        
        sel_but.addActionListener(new ActionListener() 
        {      
            public void actionPerformed(ActionEvent event) 
            {
            	view = g.getView();
          	    view.setScale(1);
              // nodo_but.setBorder(BorderFactory.createLineBorder(Color.BLUE,6,true));// crea bordo al bottone di spessore 6	
               sel_but.setSelected(true);
               nodo_but.setSelected(false);
               rect_but.setSelected(false);
               triangle_but.setSelected(false);
               freccia_but.setSelected(false);
               canc_but.setSelected(false);
               sel_but.setBackground(Color.BLUE);
               nodo_but.setBackground(null);
               triangle_but.setBackground(null);
               rect_but.setBackground(null);
               freccia_but.setBackground(null);
               canc_but.setBackground(null);      
               curva_but.setSelected(false);
               curva_but.setBackground(null);       
            }

        });
              
        nodo_but.addActionListener(new ActionListener() 
        {      
            public void actionPerformed(ActionEvent event) 
            {
            	view = g.getView();
          	    view.setScale(1);
              // nodo_but.setBorder(BorderFactory.createLineBorder(Color.BLUE,6,true));// crea bordo al bottone di spessore 6	
               sel_but.setSelected(false);
               nodo_but.setSelected(true);
               rect_but.setSelected(false);
               triangle_but.setSelected(false);
               freccia_but.setSelected(false);
               canc_but.setSelected(false);
                sel_but.setBackground(null);
               nodo_but.setBackground(Color.BLUE);
               triangle_but.setBackground(null);
               rect_but.setBackground(null);
               freccia_but.setBackground(null);
               canc_but.setBackground(null);      
               curva_but.setSelected(false);
               curva_but.setBackground(null);       
            }

        });
        /*
        rect_but.addActionListener(new ActionListener() 
        {     
            public void actionPerformed(ActionEvent event) 
            {
            	view = g.getView();
          	    view.setScale(1);
          	    
          	    sel_but.setSelected(false);
               nodo_but.setSelected(false);
               rect_but.setSelected(true);
               triangle_but.setSelected(false);
               freccia_but.setSelected(false);
               canc_but.setSelected(false);
               nodo_but.setBackground(null);
               rect_but.setBackground(Color.BLUE);
               triangle_but.setBackground(null);
               freccia_but.setBackground(null);
               canc_but.setBackground(null);  
               curva_but.setSelected(false);
               curva_but.setBackground(null);
               sel_but.setBackground(null);
               
               
            }

        });
        
        triangle_but.addActionListener(new ActionListener() 
        {
        	 public void actionPerformed(ActionEvent event) 
            {
            	view = g.getView();
          	    view.setScale(1);
          	    sel_but.setSelected(false);
        	   triangle_but.setSelected(true);
           	   nodo_but.setSelected(false);
               rect_but.setSelected(false);
               
               freccia_but.setSelected(false);
               canc_but.setSelected(false);
               nodo_but.setBackground(null);
               triangle_but.setBackground(Color.BLUE);
               rect_but.setBackground(null);
               freccia_but.setBackground(null);
               canc_but.setBackground(null);  
               curva_but.setSelected(false);
               curva_but.setBackground(null);
               sel_but.setBackground(null);
             }

        });
        */
        freccia_but.addActionListener(new ActionListener() 
        {
        
        	public void actionPerformed(ActionEvent event) 
            {
             try
              {   
                  
                 updateClusters();
      
                  	
            	if (freccia_but.isSelected()==false)
            	{
	            	sel_but.setSelected(false);
	        	   nodo_but.setSelected(false);
	               rect_but.setSelected(false);
	               triangle_but.setSelected(false);
	               freccia_but.setSelected(true);
	               canc_but.setSelected(false);
	               nodo_but.setBackground(null);
	               triangle_but.setBackground(null);
	               rect_but.setBackground(null);
	               freccia_but.setBackground(Color.BLUE);
	               canc_but.setBackground(null);  
	               curva_but.setSelected(false);
	               curva_but.setBackground(null);  
	               sel_but.setBackground(null);
	               
	               /*
	                     	stil.put(mxConstants.STYLE_EDGE, GraphConstants.STYLE_SPLINE);
							foo.setDefaultEdgeStyle(stil);
							g.setStylesheet(foo);
							//g.refresh();
							//frame.repaint();
					
							g.getView().validate();
							*/
							MyEdge edgess=new MyEdge();
							
							if ((hId!=-1) && (iId!=-1))
							{
							   for (int k=0;k<nCluster;k++)
							   {
							   	if ((B[k][hId][iId]>0) && (hId!=k))
							   	{
							   		// System.out.println(hId+" "+String.valueOf(hId)+" ->> "+String.valueOf(k)+" Peso: ");
							   		g.getModel().beginUpdate();
							   		
			                       edgess=graph.addEdge(String.valueOf(hId),String.valueOf(k));	
			                       
			                       
			                       		                
        					  	   graph.setEdgeWeight(edgess,B[k][hId][iId]);
        					  	      					  	
        					  	
 			            			g.getModel().endUpdate();
					
							    }
							   }
							}
				}
				else
				{
						
					freccia_but.setSelected(false);
					freccia_but.setBackground(null);
					if ((hId!=-1) && (iId!=-1))
							{
								/*
							   for (int k=0;k<nCluster;k++)
							   {
							   	if ((B[hId][k][iId]>0) && (hId!=k))
							   	{
							       graph.removeEdge(String.valueOf(hId),String.valueOf(k));
							    
							    
							    }
							   }
							   */
							   if (edgePainted()==true)
								 { 
								    removeAllEdges();
								 }
							  
							}
				
				}
				
			}
			catch(IllegalArgumentException eccep)
				{
				  System.out.println("eccezione tasto freccia "+eccep);
				}
        
        }

        });
        
        
        curva_but.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent event) 
            {
            	sel_but.setSelected(false);
        	   nodo_but.setSelected(false);
               rect_but.setSelected(false);
               triangle_but.setSelected(false);
               freccia_but.setSelected(true);
               canc_but.setSelected(false);
               nodo_but.setBackground(null);
               triangle_but.setBackground(null);
               rect_but.setBackground(null);
               freccia_but.setBackground(null);
               canc_but.setBackground(null);  
                curva_but.setSelected(true);
               curva_but.setBackground(Color.BLUE); 
               sel_but.setBackground(null); 
               
                     	   	stil.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_SEGMENT);
                       // mxStylesheet foo = new mxStylesheet();
						foo.setDefaultEdgeStyle(stil);
						g.setStylesheet(foo);
						//g.refresh();
					//	frame.repaint();
					g.getView().validate();

        }

        });
        
          canc_but.addActionListener(new ActionListener() 
          {
          	 public void actionPerformed(ActionEvent event) 
             {
               try
               {
                Object[] cells = g.getSelectionCells();
                Object  cella=g.getSelectionCell();		
                if ((cells != null) && (cella!=null))		
                {
                  
                  mxCell cell=(mxCell) cella;
               
                //System.out.println("cella da rimuovere:"+ cell);
                 if	(cell.isVertex()==true)   
                 { 
                       System.out.println("vertice ");
                 	   String lab = g.convertValueToString(cella);
                 	   System.out.println("cella da rimuovere:"+ lab);
                 	   //int pos=cerca_vertice(lab);
                 	  // n.remove(pos);
                 	
                 }
                 
                 
							
						g.getModel().remove(cells);
							//Object cell = g.getFirstCellForLocation(x, y); 
					    g.removeCells(cells);
					    
					   // updateVector();
				}			
               }
               catch (Exception z) { System.out.println(z);}  
             }

        });
        
        select_but.addActionListener(new ActionListener() 
          {
          	 public void actionPerformed(ActionEvent event) 
             {
          	   g.selectAll();  
          	   
             }

        });
        
        zoomp_but.addActionListener(new ActionListener() 
          {
          	 public void actionPerformed(ActionEvent event) 
             {
             view = g.getView();
          	 view.setScale(1.5 * view.getScale());  
          	   
             }

        });

	
        zoomm_but.addActionListener(new ActionListener() 
          {
          	 public void actionPerformed(ActionEvent event) 
             {
          	  view = g.getView();
          	 
          	  view.setScale(view.getScale() / 1.5);
          	  
             }

        });
        
       undo_but.addActionListener(new ActionListener() 
          {
          	 @Override
			public void actionPerformed(ActionEvent ae) {
				if (undoMgr.canUndo()) {
					undoMgr.undo();
					
					//g.getModel().getGraphLayoutCache().reload();
				}
			}

        });
    
    redo_but.addActionListener(new ActionListener() 
          {
          	 @Override
			public void actionPerformed(ActionEvent ae) {
				if (undoMgr.canRedo()) {
					undoMgr.redo();
				}
			}

        });
        
        
       piu_but.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent event) 
            {
            	if (sessionOpen==true)
            	{
            		            	
	            	int jopt= JOptionPane.showConfirmDialog
		            (null,"Do you want Save the Project before close it?","Warning",JOptionPane.YES_NO_OPTION);
		           
		            if (jopt==0)
		            {
		             saveAs();
		             
		            }
		            
		          resetF(nCluster);
		          

	            
            	}
            	
            	if(scrollPaneProbab!=null)
            		  	{ scrollPaneProbab.setVisible(false);}
                tabProbabMi.setSelected(false);
            	
            	sel_but.setSelected(false);
        	   nodo_but.setSelected(false);
               rect_but.setSelected(false);
               triangle_but.setSelected(false);
              piu_but.setSelected(true);
              meno_but.setSelected(false);
               canc_but.setSelected(false);
               nodo_but.setBackground(null);
               triangle_but.setBackground(null);
               rect_but.setBackground(null);
               freccia_but.setBackground(null);
               canc_but.setBackground(null);  
                curva_but.setSelected(false);
               curva_but.setBackground(null); 
               sel_but.setBackground(null); 
              // piu_but.setBackground(Color.BLUE); 
               
               nCluster=nCluster+1;
               tableData.setValueAt(nCluster, 1, 1);   
               
					reset();
				
					
					tabProbabMi.setVisible(true);
					
					setF(nCluster); 
					
					drawClusters(nCluster);    
					g.getView().validate();

        }

        });
        
        meno_but.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent event) 
            {
            	
            	if (sessionOpen==true)
            	{
            		            	
	            	int jopt= JOptionPane.showConfirmDialog
		            (null,"Do you want Save the Project before close it?","Warning",JOptionPane.YES_NO_OPTION);
		            
		            if (jopt==0)
		            {
		             saveAs();
		             
		            }
		            
		            resetF(nCluster);
	            
            	}
            	
            	if(scrollPaneProbab!=null)
            		  	{ scrollPaneProbab.setVisible(false);}
                tabProbabMi.setSelected(false);
            	
            	sel_but.setSelected(false);
        	   nodo_but.setSelected(false);
               rect_but.setSelected(false);
               triangle_but.setSelected(false);
              meno_but.setSelected(true);
              piu_but.setSelected(false);
               canc_but.setSelected(false);
               nodo_but.setBackground(null);
               triangle_but.setBackground(null);
               rect_but.setBackground(null);
               freccia_but.setBackground(null);
               canc_but.setBackground(null);  
                curva_but.setSelected(false);
               curva_but.setBackground(null); 
               sel_but.setBackground(null); 
               //meno_but.setBackground(Color.BLUE); 
               if (nCluster>0)
               {
	               nCluster=nCluster-1;
	               
	               if (nCluster>0) 
	               {
	                 tabProbabMi.setVisible(true);
	                 
	                 setF(nCluster); 
	               }
	               tableData.setValueAt(nCluster, 1, 1);  
	               
	               
	               reset();
	               
	              
	               
	                 drawClusters(nCluster);    	   	
						
						//	frame.repaint();
						g.getView().validate();
			    }

        }

        });
        
        //premuto per iniziare il calcolo della soluzione
        go_but.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent event) 
            {
            	//if (sessionOpen==true)
            	{  
            	      if (check(f)==true)
            		     {  
            		       if ((scrollPaneProbab.isVisible()==true) && (scrollPaneProbab!=null))
		            	   	{
		            	   	  scrollPaneProbab.setVisible(false);
		            	   	  panelProp.validate();
		            	   	  
		            	   	}
		            	   	
		            	    if ((tableData.getValueAt(2, 1).toString()).equals("Coop / Comp")==true)
		            	   	{
		            	   	  solutionCC();
		            	   	}
		            	   	else
		            	   	if ((tableData.getValueAt(2, 1).toString()).equals("User Defined")==true)
		            	   	{
            		           solution();
            		        }
            		        
            		       viewTableF(f);
            		       
            		       
            		       if(scrollPaneProbab!=null) 
            		         {
            		           scrollPaneProbab.setVisible(true);
            		         } 
            		        tabProbabMi.setVisible(true);
            		        tabProbabMi.setSelected(true);
            		         panelProp.validate();
            		          
            		     }
            		     else
            		     {
            		     if(scrollPaneProbab!=null) { scrollPaneProbab.setVisible(true);}
                        tabProbabMi.setSelected(true);
            		    JOptionPane.showMessageDialog 
            		    (null,"The Sum of cluster probabilities is not equal than 1","Warning",JOptionPane.WARNING_MESSAGE);
            		     
            		     }
            		/*
            		double somma=0;
            		for (int i=0;i<nCluster;i++)
  					{
  	 				 somma=somma+f.get(i);
  					}
				  	if (somma!=1)
				  	{
				  		
			
            		  	if(scrollPaneProbab!=null) { scrollPaneProbab.setVisible(true);}
                        tabProbabMi.setSelected(true);
            		    JOptionPane.showMessageDialog (null,"Error: the sum of cluster probabilities is different from 1","Warning",JOptionPane.WARNING_MESSAGE);
            		  }
            		  else          	
	            	{
		               
		               solution();
		            }
                   */
	            
            	}
            	
            	
            	
            	sel_but.setSelected(false);
        	   nodo_but.setSelected(false);
               rect_but.setSelected(false);
               triangle_but.setSelected(false);
               go_but.setSelected(true);
               piu_but.setSelected(false);
              meno_but.setSelected(false);
               canc_but.setSelected(false);
               nodo_but.setBackground(null);
               triangle_but.setBackground(null);
               rect_but.setBackground(null);
               freccia_but.setBackground(null);
               canc_but.setBackground(null);  
                curva_but.setSelected(false);
               curva_but.setBackground(null); 
               sel_but.setBackground(null); 
              // go_but.setBackground(Color.BLUE); 
               
               

        }

        });
        
            //premuto per mostrare il grafico a barre della soluzione o vector delle probabilità f dei cluster
        bar_but.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent event) 
            {
            	if ((f.size()>0) && (nCluster>0))
            	{  
            	   double[] ff=new double [nCluster];
		           for (int i=0;i<nCluster;i++)
		           {
		            
		             ff[i]=f.get(i);
		             
		           }
            	
            	    String text="Graphic";
           			String[] s= new String [nCluster];
      				ChartBar cb=new ChartBar(ff, s, text);
      				cb.chart(nCluster,ff);
	            
            	}
            	
            	
            	
            	sel_but.setSelected(false);
        	   nodo_but.setSelected(false);
               rect_but.setSelected(false);
               triangle_but.setSelected(false);
               go_but.setSelected(true);
               piu_but.setSelected(false);
              meno_but.setSelected(false);
               canc_but.setSelected(false);
               nodo_but.setBackground(null);
               triangle_but.setBackground(null);
               rect_but.setBackground(null);
               freccia_but.setBackground(null);
               canc_but.setBackground(null);  
                curva_but.setSelected(false);
               curva_but.setBackground(null); 
               sel_but.setBackground(null); 
              // go_but.setBackground(Color.BLUE); 
               
               

        }

        });
        
                    //premuto per mostrare il grafico curvilineo  delle funzioni Enne
       curve_but.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent event) 
            {
            	int enneLeng=enne.length;
            	
            	if ((enneLeng>0) && (nCluster>0))
            	{  
            	   Vector <Double> enneV=new Vector<Double>();
            	  int i=0; int delta=(int)(nt/tmax);
                do
               {
               	  
               	
                   enneV.add(enne[i]);
                  i=i+delta;
               }
               while(i<nt);
            	   
            	   
		          
            	
            	    String text="Enne Graphic";
           			
      				GraphCurve gc=new GraphCurve(enneV);
      				gc.drawCurve(enneV,text);
	            
            	}
            	
            	
            	
            	sel_but.setSelected(false);
        	   nodo_but.setSelected(false);
               rect_but.setSelected(false);
               triangle_but.setSelected(false);
               go_but.setSelected(true);
               piu_but.setSelected(false);
              meno_but.setSelected(false);
               canc_but.setSelected(false);
               nodo_but.setBackground(null);
               triangle_but.setBackground(null);
               rect_but.setBackground(null);
               freccia_but.setBackground(null);
               canc_but.setBackground(null);  
                curva_but.setSelected(false);
               curva_but.setBackground(null); 
               sel_but.setBackground(null); 
              // go_but.setBackground(Color.BLUE); 
               
               

        }

        });
    	
    
    	frame.add(BorderLayout.NORTH,toolbar1);
    	/* stabilisce dimensioni */
     	//p.setSize(600,100);          
           
        MouseMethods vai3=new MouseMethods(frame);
     //   KeyEvents ke=new KeyEvents(frame);
        
        

        //Thread.sleep(5000);
        	Object parent = g.getDefaultParent();
//g.getModel().beginUpdate();
   
   //g.insertVertex(parent,null,"Test",100,100,30,30,"shape=ellipse;perimeter=ellipsePerimeter");
  
    
  
              
         component.getGraphControl().addMouseListener(new MouseAdapter() 
         {
                
                public void mousePressed(MouseEvent e) 
                { 
                
					
				
                
            // Se viene premuto il TASTO DESTRO del mouse e non è selezionato il tasto freccia Select che apre la tab proprietà
					if ((SwingUtilities.isRightMouseButton(e) && (sel_but.isSelected()==false))) 
					{
						
						System.out.println("tasto destro");
						// Find Cell in Model Coordinates
						Object cell =  component.getCellAt(e.getX(), e.getY(),true);
						
						//Object[] cells = g.getSelectionCells();
						if (cell!=null) 
						{
								 mxCell cel=(mxCell) cell;
							if ((cel.isEdge()==true))
							{
             					mxPoint point=new mxPoint(e.getX(), e.getY()); 
             				Vector<mxPoint> points=new Vector<mxPoint>();
             					points.add(point);
             			
			
             				//	System.out.println(point.toString());
             				
							  
							}
						
							//g.getModel().remove(cells);
						
						   JPopupMenu menu = createPopupMenu(e.getPoint(), cell);// Create PopupMenu for the Cell
						
			     		   menu.show(frame, e.getX(), e.getY());// Display PopupMenu
			     	      
					
				    	}
				    	
 

					}
                
                    else
                  if (SwingUtilities.isLeftMouseButton(e)) 
				  {
					 Point point = e.getPoint();
					 
				   if (hId!=0)
				   {
				     //fillCluster(fillcolor);
				   }
				   if (iId!=0)
				   {
				  //fillClluster(yellow);
				   }

  				//	 System.out.println("mousePressed at " + point);
  			
					if ((e.getClickCount() == 1) && (sel_but.isSelected()==true ))
                	{
                		
                        //System.out.println("preme"+e.getX()+"  "+e.getY());
                        
                        
                        
                        Object cell =  component.getCellAt(e.getX(), e.getY(),true);
						
						Object[] cells = g.getSelectionCells();
					
						mxCell cel=(mxCell) cell;
					     if (cel!=null)
								 
					     {	
					        try
					        {
					        	if (tabOpen==true)
					        	{
					        	   Vector<String> prop=closeTable();
             			        	tabOpen=false;
					        	}
             				  System.out.println("Selection cell: "+cel.getId()+" "+g.getCellGeometry(cel));
             				
             			    
             			    
             			  
             			   if (cel.isVertex()==true) 
             		       {
             		       	Vector<String> infoVertex=new Vector<String>() ;
             			    //vector di stringhe contenente le info da passare alla tabella Proprietà del Vertex
             			    
             			    String x=String.valueOf(g.getCellGeometry(cel).getX());
             			    String y=String.valueOf(g.getCellGeometry(cel).getY());
             			    int id=Integer.parseInt(cel.getId());
             			    id=id-1;
             			    String labelTab=cel.getValue().toString();
             			
             			  
             			  mxCell clust = (mxCell) g.getSelectionCell();
             			  
             			  double  alfaStep=0;
             			  double rad=0; 
             			  double dx=0;
             			  double dy=0;
             			  double x1=  r*(Math.cos(rad))+r+x0;
 		   				  double y1= -r*(Math.sin(rad))+r+y0;
             			  
             			  if (nCluster>1)
             			   {
             			  	  
	             			 	  alfaStep=180/(nCluster-1);
	 		   	  		    
		 		   	   			  int idd=Integer.parseInt(clust.getId());
		 		   	   			
		 		   	  			 rad=(alfaStep*Math.PI)/180;
		 		   	  			 rad=Math.PI-(rad*idd);
	             			     dx=-(dist*Math.cos(rad));
		             			 
		             			 dy=(dist*Math.sin(rad));
		             			  
		             			 System.out.println("rad: "+rad+" DX: "+dx+" DY: "+dy+"  ID->> "+clust.getId()+" "+cel.getValue().toString());
		             			 //h ed i nulle, assegno h
		 						 if ((cell1On==false) && (cell2On==false))
		 						 {
		 						 
		 						   if(scrollPaneProbab!=null)
            		  					{ scrollPaneProbab.setVisible(false);}
            		  					
            		  					tabProbabMi.setVisible(false);
		 						 
		             			   mov(cells,dx,dy); //sposta il cluster 1 (assegno h candidate)
		             			  
		             			   cell1On=true;
		             			   hId=idd;
		             			   System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		             			   fillCluster(hCol);//fillcolor
		             			   /*
		             			   for (int i=0;i<nCluster;i++)
		             			   {
		             			      B[hId][hId][i]=1;
		             			   }
		             			   */
		             			   
		             			   
										    /*    //stampa         			   
										for(int i=0; i<nCluster; i++) 
										{ 
											for(int j=0; j<nCluster; j++) 
											{ 
												for(int k=0; k<nCluster; k++) 
												{
													
													
													System.out.print(B[i][j][k] + " "); 
												}
													System.out.println(); 
											} 
											System.out.println(); 
										} 
		             			   */

		             			 }
		             			 else
		             			 //h assegnato, i non assegnato
		             			 if ((cell1On==true) && (cell2On==false))
		             			 {
		             			 if (idd==hId) //se clicco su h, deve tornare a posto e annullare h
		             			 {
		             			 		tabProbabMi.setVisible(true);
		             			 		tabProbabMi.setSelected(false);
		             			 	
		             			 	colorMi.setVisible(true);
		             			 	colorMi.setSelected(false);
		             			 	
		             			    if (scrollPaneProp.isVisible()==true)
		             			       {	scrollPaneProp.setVisible(false); }
		             			 	
		             			 	dist=4*dist;
		             			   mov(cells,-dx,-dy);
		             			   dist=dist/4;
		             			   hId=-1;
		             			   cell1On=false;
		             			   fillCluster(kCol); //white
		             			 }
		             			 else
		             			 //assegno i
		             			 {
		             			 	iId=idd;
		             			 	cell2On=true;
		             			 	/*
		             			 	//passo la colonna del cluster i selezionato e controllo i valori: se sono tutti uguali è cooperaz 
		             			 	if (isCooperation()==true)
		             			 	{
		             			 	mode="Cooperation";
		             			 	}
		             			 	else
		             			 	{
		             			 	mode="User Defined"; //competition o User defined
		             			    }
		             			    */
		             			 	//tableData.setValueAt(mode, 2, 1);
		             			 	
		             			 	mov(cells,-dx/4,-dy/4);
		             			 	fillCluster(iCol);//yellow
		             			 	
		             			 	Vector<String> infoH=new Vector<String>();
		             			 	
		             			 	for (int k=0;k<nCluster;k++)
		             			 	{
		             			 		infoH.add(String.valueOf(B[k][hId][iId]));
		             			 		
		             			 	}
		             			 	
		             			 	tabPropMi.setSelected(true);
		             			   	colorMi.setVisible(false);
		             			   /* if (scrollPaneProp.isVisible()==true)
		             			       {	scrollPaneProp.setVisible(false); }
		             			       */
		             			 	viewTableH(infoH); //chiama la tabella relativa alle proprietà del Vertex
             			    		tabOpen=true;
             			    		scrollPaneProp.setVisible(true);		 
             			  			splitPanel.validate();
             			  			
             			  			sessionOpen=true;
		             			 	if ((tableData.getValueAt(2, 1).toString()).equals("Coop / Comp")==true)
		             			 	{
		             			 	  cooperation();
		             			 	}
		             			 	else
		             			 	if ((tableData.getValueAt(2, 1).toString()).equals("User Defined")==true)
		             			 	{
		             			 	  userDefined();
		             			 	}
		             			 
		             			 }
		 					     }
		             			 else
		             			 //entrambi i cluster h,i assegnati
		             			 if ((cell1On==true) && (cell2On==true))
		 						 {
		 						 	
		 						 	//se ho cliccato su i, devo disattivarlo e portarlo a posto
		 						 	if(idd==iId)
		             			    {
		             			    	
		             			       cell2On=false; 
		             			       mov(cells,dx/4,dy/4); 
		             			       
		             			       fillCluster(kCol);//white
		             			       for (int i=0;i<nCluster;i++)
		             			       {
		             			       graph.removeEdge(String.valueOf(hId),String.valueOf(i));
		             			       }
		             			      iId=-1;
		             			      
		             			      if (scrollPaneCol.isVisible()==true)
		             			    	{
		             			    	scrollPaneCol.setVisible(false);
		             			    	}
		             			      
		             			      if (scrollPaneProp.isVisible()==true)
		             			       {	scrollPaneProp.setVisible(false); }
		             			       
		             			    }
		             			    
		             			   
		             			 }
		             			 	
	             			 		
	             			}
          			     }
             			  
             			    
             			    
             			    
             			    
             			  }
             			  catch
             			  (Exception exx){System.out.println("eccezione tasto sel "+exx);}
             				
						  }
						  else
						  if (tabOpen==true)
						  {
						  	try
						  	{
						  	
             			    	Vector<String> prop=closeTable();
             			    tabPropMi.setSelected(false);
             			    	
             			    	tabOpen=false;
             			   // prop=tp.closeTable();
             			     for(int j=0;j<prop.size();j++)
            				 {
            	               System.out.println(prop.get(j)); 
                             }
             			   
						  	System.out.println("chiudi tabellaaaaaaaaa");
						    }
						    catch
						    (Exception ercx){System.out.println("ERROREEEEE "+ercx);};
						 }
                       
                    }
                    else
                    
                   	if ((e.getClickCount() == 1) && (nodo_but.isSelected()==true ))
                	{
                		
                      
                    }
                    
                    
                   } 
                    
                    
                   
        } //end mousePressed
                	
                public void mouseClicked(MouseEvent e) 
                { 
                
                if (hId!=-1)
                {
                  	fillCell(hCol,hId); //fillcolor
                }
                
                if (iId!=-1)
                {
                  	fillCell(iCol,iId);//yellow
                }
                
                 
                   System.out.println("OLD_LABEL:  "+oldLabel);
                     
                      if (labelChanged ==true)
                      
                      {
                      	labelChanged =false;
                      	
                      	 System.out.println("Editato >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                      	   	 
                      	   	 int id=-1;int ed=-1;
                      	/*  	 
                      	 for (int j=0;j<n.size();j++)
                      	 {
                      	 	//ricerco la posizione nel vector n del nodo con la oldlabel che devo modificare
                      	   if (n.get(j).getVertexLabel().equals(oldLabel))
                      	   {
                      	   	 id=j; //posizione
                      	   	 System.out.println("###########  "+id);
                      	   }
                      	 }
                      	 */
                      	 
                      	 
                      	 
                      	 
                    
                      	// Nodo n2=new Nodo();
                     for (Object vertex : g.getChildCells(g.getDefaultParent(), true, true)) 
                      {
                      	
                      	mxCell cc=(mxCell) vertex;
                      	if (cc.isVertex()==true) 
                      	{ 
                      	   String newLabel= ((mxCell) vertex).getValue().toString();
                      		boolean trovato=false;
                      		/*
                      	    for (int j=0;j<n.size();j++)
                      	    {
                      	    	//ricerco la posizione nel vector n del nodo con label modificata
                      	      if (n.get(j).getVertexLabel().equals(newLabel))
                      	      {
                      	   	    trovato=true;
                      	       }
                      	    }
                      	    
                      	 
                      	    if (trovato==false)
                     		{
                      			n2=n.get(id);
                      			n2.setVertexLabel(newLabel);
                      			if (n2.getP().equals("0")==false)
                      			{
                      			  n2.setStyle("shape=ellipse;perimeter=ellipsePerimeter;fillColor="+fillcolor);
                      			}
                      		  	n.set(id,n2);
                      	  	    
                      		}
                      		*/
                      		//id=id+1;
                             
                               
                           //    int newX=(int) g.getCellGeometry(vertex).getX();
                        //	int newY=(int) g.getCellGeometry(vertex).getY();
                       
 							  
 						
                        }
                        else
                        	if (cc.isEdge()==true)
                        	{
                        		ed=ed+1;
                        		System.out.println("Edge ID "+ed+"  Value:"+((mxCell) vertex).getValue()
                               +" Style:"+((mxCell) vertex).getStyle());
                              
                        	}
                        	                  	
                       
                      }
                      
                    /*
                      double valNum=Double.valueOf(n.get(id).getP());
                         
                      if (valNum>0)
                      {
                      	 fillCluster(fillcolor);					
					   }
                      
                      */
                      }
                    
                    
                      System.out.println("tasto released");
						// Find Cell in Model Coordinates
						Object cell =  component.getCellAt(e.getX(), e.getY(),true);
						
						Object[] cells = g.getSelectionCells();
					
								 mxCell cel=(mxCell) cell;
					  if (cel!=null)
								 
					  {	
					     System.out.println("ID "+cel.getId());
             					mxPoint point=new mxPoint(e.getX(), e.getY()); 
             				Vector<mxPoint> points=new Vector<mxPoint>();
             					points.add(point);
             			
			
             					System.out.println("Nuova pos: "+point.toString());
             				  System.out.println(g.getCellGeometry(cel));
             				
							  
						}
						
							//g.getView().validate();
			     	      
					
				    	
               
                
                }//end mouseClicked
                
      }); 
      
      
 
       //String o="O";
       //g.insertVertex(parent,null,o,200,20,60,60,"shape=ellipse;perimeter=ellipsePerimeter");
//       graph.addEdge(o,s1);
        
   // g.getModel().endUpdate();    
        System.out.println(graph.vertexSet());
       System.out.println(graph.edgeSet());
        
        
        /* Thread.sleep(2000);
         
        graph.addEdge(s7, "Test");
        for (Object vertex : g.getChildCells(g.getDefaultParent(), true, false)) {
        	g.updateCellSize(vertex, true);
        }
        layout.execute(g.getDefaultParent());
        Thread.sleep(2000);
       
        g.removeCells(new Object[]{g.getVertexToCell(s3)});
        Thread.sleep(2000);
        
        g.removeCells(new Object[]{g.getEdgeToCell(graph.getEdge(s1, s2))});
        Thread.sleep(2000);
        
        
        g.getModel().beginUpdate();
        try{
        graph.removeVertex(s5);
        } finally{
        	g.getModel().endUpdate();
        }
        Thread.sleep(2000);
        
        graph.removeEdge(graph.getEdge(s1, s4));
        Thread.sleep(2000);
         
        layout.execute(g.getDefaultParent());
        System.out.println("Ende");
        
      
         */
         
        
          
         
         
        }//end go
        
        
	        
	       /**
	 	* Insert fields of all clusters in the vector clust 
	 	* @param id memory id of the cluster
	 	
	 	* @param label label of the cluster.
	 	* @param prob  cluster probability.
	 
	 	* @param matrix 2D matrix  field of the cluster
	    */
        
        public void add_Cluster(String id,String label, String prob, String matrix)//inserisce il nodo nel vector clust
        
       {
          Cluster nodo=new Cluster();
          nodo.setId(id);
          
          nodo.setLabel(label);
          nodo.setProb(prob);
          nodo.setMatrix(matrix);
         
          clust.add(nodo);
       
       }
       
       	       /**
	 	* Insert fields of all clusters in the vector clust 
	 	* @param id memory id of the cluster
	 	
	 	* @param label label of the cluster.
	 
	 	* @param matrix 2D matrix  field of the cluster
	    */
        
        public void updateClusters()        
        {
       	     Vector <String> labels= new Vector<String>();
       	     clust=new Vector<Cluster>();
       	             
       	              for (Object vertex : g.getChildCells(g.getDefaultParent(), true, true)) 
                      {
                      	mxCell cc=(mxCell) vertex;
                      	
                      	if (cc.isVertex()==true) 
                      	{
                      	   String s=((mxCell) vertex).getValue().toString();
                             labels.add(s);
                               
 							  
 							//System.out.println("ID "+((mxCell) vertex).getId()+"  Value:"+((mxCell) vertex).getValue());   
                        }

                      }
                      //System.out.println("lunghezza labels: "+labels.size());
       	
          for (int i=0;i<nCluster;i++)
          {
          	
          	//set campo matrix
          
          	String mat=new String();
          	mat=parseMatrix(B,i,nCluster,nCluster);
          	String id=String.valueOf(i);
          	
            add_Cluster(id,labels.get(i),String.valueOf(f.get(i)),mat);
          }
        }
        
        //sceglie e ritorna il nome del file xml da aprire
        public String apri()
	    {
	    	  String filename=new String("");
	          FileDialog fd = new FileDialog(frame, "Open ", FileDialog.LOAD);
	          fd.setFile("*.xml"); 
              fd.setVisible(true); 
              if ((fd.getFile()!=null)&&(fd.getDirectory()!=null))  
	          { 
	          	frame.setTitle(fd.getFile());
	            
		     	
	            filename = fd.getDirectory()+fd.getFile(); 
	            System.out.println(filename);
	            
	           // File f = new File(filename);
		    	try
		    	{
					//open_xml(filename,graph);
	   			}catch(Exception ex){	}
			  }
			  return filename;
	    }
        
         public String saveAs()
	    {
	    	  String filename=new String("");
	    	  String newnome=new String("");
	    	  String este=new String();
	    	  /*
		    if (filename!=null) //file esistente ed  aperto
		     {este =filename.substring(filename.length()-4);}*/
		      
		   		try
                {   // finestra di dialogo per salvare il file con dove si desidera
                	FileDialog fd = new FileDialog(frame,"Save As...",FileDialog.SAVE); 
          			fd.setFile(""); 
          			fd.setVisible(true); 
          	
				
					
					if ((fd.getFile()!=null) && (fd.getDirectory()!=null) && (fd.getFile()!=null))
          			{ 
          				newnome=fd.getDirectory()+fd.getFile();
          			 
          			 	
          			}
          			
						
          		}
	   			catch(Exception ex){	}
	   			return newnome;
	     }
	    
        /*
        public static void save_xml(String nomef,ListenableGraph<String, DefaultEdge> grafico)
        {
            
            
           
            
                GraphMLExporter exp = new GraphMLExporter(
                	 new VertexNameProvider()
               {
    				public String getVertexName(Object object)
    				{
    					
      						if (object == null)       return "none";
      						return object.toString().replaceAll("\"","\'");
      						
      							
    				}
  				},
                new VertexNameProvider()
               {
    				public String getVertexName(Object object)
    				{
    					
      						if (object == null)       return "none";
      						
      						return object.toString().replaceAll("\"","\'");
      							
    				}
  				},
  				new EdgeNameProvider<Object>()
				{	
    				public String getEdgeName(    Object object)
    				{
      					if (object == null)       return "none";
     			 		return object.toString().replaceAll("\"","\'");
    				}
  				},
				new EdgeNameProvider<Object>()
				{	
    				public String getEdgeName(    Object object)
    				{
      					if (object == null)       return "none";
     			 		return object.toString().replaceAll("\"","\'");
    				}
  				}
			                                   );
             
                FileWriter fw; 
                try 
                { 
                        fw = new FileWriter(nomef+".xml"); 
                       exp.export(fw,grafico); 
                       System.out.println("Salvataggio xml effettuato con successo!");
                } 
                catch 
                (NullPointerException | IOException | TransformerConfigurationException | SAXException  e)  {e.printStackTrace();} 
                
             
        }
        
        

        public static void save(String nomef,ListenableGraph<String, DefaultEdge> grafico)
        {
            System.out.println("Salvataggio effettuato con successo!");
           
               DOTExporter dot=new DOTExporter(new IntegerNameProvider(),new VertexNameProvider()
               {
    				public String getVertexName(Object object)
    				{
      						if (object == null)       return "none";
      							return object.toString().replaceAll("\"","\'");
    				}
  				}
,				new EdgeNameProvider<Object>()
				{	
    				public String getEdgeName(    Object object)
    				{
      					if (object == null)       return "none";
     			 		return object.toString().replaceAll("\"","\'");
    				}
  				}
			                                   );
                FileWriter fw; 
                try 
                { 
                        fw = new FileWriter(nomef+".dot"); 
                        dot.export(fw,grafico);    
                } 
                catch 
                (IOException e) {e.printStackTrace();}  
                
                 
  
        }
        */
          //riceve la stringa del vertexSet  e ritorna il vector di tipo String con tutti i vertex del grafico
        public Vector<String> parseVertex(String vertici)
        {
          Vector<String> vect=new Vector<String>();
          
          vertici=vertici.trim();
          if (vertici.length()>2)
          {
          int j=0;
 	    	vertici=vertici.substring(1,vertici.length()-1);//toglie le parentesi []
		
 			String [] splits = vertici.split("\\, ");
			for(String s:splits)
			{
    			System.out.println(s);
    			j=j+1;
    			vect.add(s);
    		
    		}
          }
          
          return vect;
        }
    /*    
        //riceve la stringa del edgeSet  e ritorna il vector di tipo Edge con tutti gli archi
    public  Vector<Edge> parseEdge(String archi)
 	{
 		Vector<String> vect=new Vector<String>();
 		Vector<String> ret=new Vector<String>();
 		Vector<Edge> arc=new Vector<Edge>();
 		Edge arco=new Edge();
 		
 	//	String archi=new String();
 	//	archi="[(1:3),(3:2),(A:F),(A:1)]";
 		
 		archi=archi.trim();
 	
 		System.out.println(archi);
 		if (archi.length()==2)
 		{
 	//	System.out.println("vuota");
 		
 		}
 		else
 		{
 	     int i=0;
 	    archi=archi.substring(1,archi.length()-1);//toglie le parentesi []
		//System.out.println(archi);
 		String [] splits = archi.split("\\, ");
		for(String s:splits)
		{
    		//	System.out.println(s);
    			i=i+1;
    			vect.add(s);
    		
    	}
    	int lun=vect.size();
    //	System.out.println(lun);
    	for (i=0;i<lun;i++)
    	{
    	  String temp=vect.get(i);
    	  temp=temp.substring(1,temp.length()-1);//toglie le parentesi () 
    	  vect.setElementAt(temp,i);
    	 // System.out.println(temp);
    	  String [] splits3 = temp.split("\\ : ");
	    	for(String s:splits3)
		    {
    		   //System.out.println(s);
    		   ret.add(s);
 		
 		
 		    }
    	  
    	  
    	  }
   
	}
	
	for (int j=0;j<ret.size();j++)
	{
		System.out.println("vettore "+ret.get(j));
	}
	int id_count=1; 
	for (int i=0;i<ret.size();i++)
	{
	  //System.out.println(ret.get(i));
	  arco=new Edge();
	  arco.setId(String.valueOf(id_count));
	  arco.setType("edge");
	  String lab=new String();
	  lab="("+ret.get(i)+":"+ret.get(i+1)+")";
	  arco.setEdgeLabel("("+ret.get(i)+":"+ret.get(i+1)+")");
	  arco.setStyle("orthogonal");
	  arco.setSource(ret.get(i));
	  arco.setTarget(ret.get(i+1));
	  arc.add(arco);
	  i=i+1;id_count=id_count+1;
	  if (a.size()==0)
	  {
	  	arco.setPab("0");arco.setRate("0");arco.setPba("0");
	  }
	  else
	  {
	  	
	  	for (int k=0;k<a.size();k++)
	  	{
	  	 if (a.get(k).getEdgeLabel().equals(lab)==true)
	  	 {
	  	   arco.setPab(a.get(k).getPab());arco.setRate(a.get(k).getRate());arco.setPba(a.get(k).getPba());
	  	 }
	  	}
	  	
	  	if (arco.getPab()==null)
	  	{arco.setPab("0");}
	  	if (arco.getRate()==null)
	  	{arco.setRate("0");}
	  	if (arco.getPba()==null)
	  	{arco.setPba("0");}
	  
	  }
	}	
	
     return arc;
  }
  
  */
      
    /**
     * a listenable directed multigraph that allows loops and parallel edges.
     */
    private static class ListenableDirectedMultigraph<V, E>
        extends DefaultListenableGraph<V, E>
        implements DirectedGraph<V, E>
    {
        private static final long serialVersionUID = 1L;

        ListenableDirectedMultigraph(Class<E> edgeClass)
        {
            super(new DirectedMultigraph<V, E>(edgeClass));
        }
    }  
        
        
}//end public class demo

class MouseMethods implements MouseListener {

	public MouseMethods(JFrame frame) {
		//JFrame frame = new JFrame("MouseMethods");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(500,100);
		frame.addMouseListener(this);
		
		frame.setVisible(true);
		
	}

	public void mouseClicked(MouseEvent e) {
	//	System.out.println(e.getX());
		
	}
	
	public void mouseDragged(MouseEvent e) {
		System.out.println("dragges");
		
	}
	
		

	public void mouseEntered(MouseEvent e) 
	{
		//System.out.println("The mouse entered the frame.");
		
	}

	public void mouseExited(MouseEvent e) {
	//	System.out.println("The mouse exited the frame.");
		
	}

	public void mousePressed(MouseEvent e) {
		//System.out.println("The left mouse button was pressed."); 
		
	}

	public void mouseReleased(MouseEvent e) {
	//	System.out.println("The left mouse button was released.");
		
	}
	
	
	
}




/**
 * This renderer renders a color value as a panel with the given color.
 */
class ColorTableCellRenderer extends JPanel implements TableCellRenderer
{
	 private static final long serialVersionUID = 1L;
	
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
         boolean hasFocus, int row, int column)
   {
      setBackground((Color) value);
      if (hasFocus) setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
      else setBorder(null);
      return this;
   }
}

