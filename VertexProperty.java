package DeMeo;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;
/**
 * Vertex  Property Frame
 * @author  Ing. Giulio De Meo
 */
public class VertexProperty extends Frame implements ActionListener,ItemListener
{
	//public VertexProperty barra ;

  /* GUI Objects. */
  private Label     labelLabel,labelProbability,labelShape;
  private Button    ok;
  public  JFrame frame;
  private TextField textLabel,textProbability;
  private Choice    shape;
  private static String COM;
  
    public static void main (String args[]) 
  {
  	VertexProperty guis=new VertexProperty();
  	guis.gui();
  }
  
  public boolean activ()
  {
 
   if (frame.isActive()==true) { return true; }
   else
   { return false;}
  	
  }
 
  public void gui () 
  {
  
        //Create and set up the window.
        frame = new JFrame("Property");
      //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
     /* Set Layout. */
    frame.setSize(new Dimension(200, 300));
    frame.setLayout(null);
    frame.setBackground(new Color(177, 226, 230));
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


    /* Set Choice. */
    shape = new Choice();
    shape.addItem("<shape>");
    shape.addItem("Circle");
    shape.addItem("Rectangle");
    shape.addItem("Triangle");
   
    shape.setBounds(new Rectangle(80, 50, 100, 24));
    shape.addItemListener(this);
 
    /* Set TextField. Vertex_Label*/
    textLabel = new TextField();
    textLabel.setBounds(new Rectangle(80, 80, 60, 24));
    textLabel.setText("");

	/* Set TextField. Area per scrivere il messaggio*/
   	textProbability = new TextField();
   	textProbability.setBounds(new Rectangle(80, 110, 50, 24));
   	textProbability.setText("");

    /* Set labelLabels. */
    labelShape = new Label();
    labelShape.setBounds(new Rectangle(15, 50, 80, 24));
    labelShape.setText("Shape");
    
    labelLabel = new Label();
    labelLabel.setBounds(new Rectangle(15, 80, 80, 24));
    labelLabel.setText("Label");   
   
    labelProbability = new Label();
    labelProbability.setBounds(new Rectangle(15, 110, 60, 24));
    labelProbability.setText("Probability");
    
     /* Set Button Esegui */
    ok = new Button();
    ok.setBounds(new Rectangle(80, 200, 72, 24));
    ok.setLabel("OK");
    ok.addActionListener(this);
    ok.setEnabled(false);

    /* Add the graphics objects to layout. */
   
    frame.add(textLabel, null);
    frame.add(textProbability, null);
    frame.add(ok, null);
    frame.add(labelProbability, null);
    frame.add(labelLabel, null);
    frame.add(textProbability, null);
    frame.add(shape, null);
    frame.add(labelShape,null);
    
    textLabel.addActionListener(this); 
    textProbability.addActionListener(this); 
 
  }

  /**
   * Capture the button events. Eventi dopo aver premuto il tasto OK
   */
  public void actionPerformed(ActionEvent e)
  {
     String label = textLabel.getText();
     String probability= textProbability.getText();

   
	 if (shape.getSelectedItem().equals("Circle"))
      {
      	System.out.println("cerchio");
      	   
      }	
      else
      if (shape.getSelectedItem().equals("Rectangle"))
      { 
      	
      	    	System.out.println("rettangolo");
      }	
      else
      if (shape.getSelectedItem().equals("Triangle"))
      {
      	System.out.println("triangolo");
      
      }
      
      if (probability.length()>0)
      {
        System.out.println(probability);
      };
      if (label.length()>0)
      {
        System.out.println(label);
      }
      
  } 
  
  
  //Capture the choice events 
  public void itemStateChanged(ItemEvent e) 
  {
        
        
      ok.setEnabled(true);
     
		
  }

	 
	  
} // end of class.