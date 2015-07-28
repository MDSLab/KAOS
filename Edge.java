package DeMeo;
public class Edge {
	private String id;
    private String type;
    private String style;
    private String edge_label;
    private String source;
    private String target;
    private String rate;
    private String pab;
    private String pba;
    
   
    
    public Edge() {}

    public Edge(String id, String type, String edge_label, String style, String source, String target, String rate, String pab, String pba ) 
    {
    	this.id = id;
    	this.type = type;
    	this.edge_label = edge_label;
    	this.style = style;
    	this.source = source;
    	this.target = target;
    	this.rate = rate;
    	this.pab = pab;
    	this.pba = pba;
    }

    public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEdgeLabel(String edge_label) {
		this.edge_label = edge_label;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setSource(String source) {
		this.source = source;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public void setPab(String pab) {
		this.pab = pab;
	}
	public void setPba(String pba) {
		this.pba = pba;
	}
	
	
	public String getId()
	{
		return this.id;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public String getEdgeLabel()
	{
		return this.edge_label;
	}
	
	public String getStyle()
	{
		return this.style;
	}
	
	public String getSource()
	{
		return this.source;
	}
	
	public String getTarget()
	{
		return this.target;
    }
    public String getRate()
	{
		return this.rate;
	}
	public String getPab()
	{
		return this.pab;
	}
		public String getPba()
	{
		return this.pba;
	}

	@Override
    public String toString() 
    {
     return "<" + id + ", " + type + ", " + edge_label + ", " + style + ", " + source + ", "+ target + ", " + rate + ", "+ pab + ", " + pba +">";
    }
}
