package DeMeo;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.JOptionPane;
//import javax.swing.ListSelectionModel;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;



public class Property 
{
	public static JFrame frameTabN;
	public static JTable table;
    public static Vector<String> items;
    
	public static void main(String args[])
	{
	Property a=new Property ();
	items=new Vector<String>();
	items.add("ciao");
	items.add("ciao2");
	items.add("ciao3");
	
    	a.viewTable(items);
	}
  public void viewTable(Vector<String> values)
  {
    frameTabN = new JFrame();
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameTabN.setResizable(false);
    frameTabN.setSize(100,200);
    frameTabN.setTitle("Property");
    frameTabN.toFront();
    //frame.

    Vector<String> row1 = new Vector<String>();
    row1.addElement("Label");
    row1.addElement("");
    
    
    Vector<String> row2 = new Vector<String>();
    row2.addElement("Pos X");
    row2.addElement("");
    
    Vector<String> row3 = new Vector<String>();
    row3.addElement("Pos Y");
    row3.addElement("");
    
    Vector<String> row4 = new Vector<String>();
    row4.addElement("Probability");
    row4.addElement("");
   
    
    Vector<Vector> rowData = new Vector<Vector>();
    rowData.addElement(row1);
    rowData.addElement(row2);
    rowData.addElement(row3);
    rowData.addElement(row4);
   
    Vector<String> columnNames = new Vector<String>();
     
    columnNames.addElement("Item");
    columnNames.addElement("Value");
  
    
    DefaultTableModel model = new DefaultTableModel(rowData, columnNames) 
		{
			public boolean isCellEditable(int row, int col) {
				if (col==0) 
				{
				return false;}
				else
				{ return true;}
			};
		
			
		};
		table = new JTable(model);
		try
		{
		
				for(int j=0;j<values.size();j++)
            	{
            	  //System.out.println( table.getValueAt(j, 0)+" : "+table.getValueAt(j, 1)); 
            	  
            	  table.setValueAt(values.get(j), j, 1);   
                }
        }
        catch 
        (Exception exxx) 
        {
        }
        
        
   

    table.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() 
    {
    	public void editingCanceled(ChangeEvent e) {
                       // System.out.println("editingCanceled");
                    }
      public void editingStopped(ChangeEvent e) 
      {
       
     
        //if ((table.getSelectedRow()==3) && (table.getSelectedColumn()==1))
        {
        	
             
         String probab=table.getValueAt(3, 1).toString();
         
         if ((probab!=null) && (probab.equals("")==false))
         {
         	
         	try
         	{
              double valore=Double.valueOf(probab);
             if ((valore<0) || (valore>1))
             { 
               
               
               JOptionPane.showMessageDialog
            (null,"Insert a Number between 0 and 1","Messaggio",JOptionPane.WARNING_MESSAGE);
               
               
             }
             
            }
            catch (Exception ex)
            {
            	JOptionPane.showMessageDialog
            (null,"Insert a Number between 0 and 1","Messaggio",JOptionPane.WARNING_MESSAGE); 
            
            }
         } 
        }
        
      }

    });

        
        
    

    //table.setValueAt("aa", 0, 0);   
   // System.out.println( table.getValueAt(0, 1)); 
   
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - frameTabN.getWidth();
        x=x-100;
        int y = 100;
        frameTabN.setLocation(x, y);
   
   
   
    JScrollPane scrollPane = new JScrollPane(table);
    frameTabN.add(scrollPane, BorderLayout.CENTER);
    frameTabN.setSize(200, 300);
    frameTabN.setVisible(true);
    
    frameTabN.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	/*
            	for(int j=0;j<2;j++)
            	{
            	  System.out.println( table.getValueAt(j, 0)+" : "+table.getValueAt(j, 1)); 
                }
                
                */
   
                items=closeTable();
                //System.exit(0);
            }
        });

  }
  public Vector<String> closeTable()
  {
  	 items=new Vector<String>();
  	
  	frameTabN.setVisible(false);
  	
  	for(int j=0;j<4;j++)
    {
    	String s=new String();
     	s=table.getValueAt(j, 1).toString();
         //System.out.println(s);
  		items.add(s);
    }
  	return items;
  }
}