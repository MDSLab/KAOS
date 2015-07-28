package DeMeo;
public class Cella extends Nodo {

    private String p;
    private String p1;
    private String p2;
    
   
    
    public Cella() {}

    public Cella(String p, String p1, String p2) 
    {
    	this.p = p;
       	this.p1 = p1;
    	this.p2 = p2;
    }

    public void setP(String p) {
		this.p = p;
	}
	 public void setP1(String p1) {
		this.p1 = p1;
	}
 	public void setP2(String p2) {
		this.p2 = p2;
	}
	
	
	
	public String getP()
	{
		return this.p;
	}
	public String getP1()
	{
		return this.p1;
	}
	public String getP2()
	{
		return this.p2;
	}
	
	
	@Override
    public String toString() {
            return "<" + p + ", " + p1 + ", " + p2 +">";
    }
}
