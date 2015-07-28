package DeMeo;
public class Nodo {
	private String id;
    private String type;
    private String style;
    private String vertex_label;
    private String x;
    private String y;
    private String p;
    
   
    
    public Nodo() {}

    public Nodo(String id, String type, String vertex_label, String style, String x, String y,String p) 
    {
    	this.id = id;
    	this.type = type;
    	this.vertex_label = vertex_label;
    	this.style = style;
    	this.x = x;
    	this.y = y;
    	this.p = p;
    	
    }

    public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setVertexLabel(String vertex_label) {
		this.vertex_label = vertex_label;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setX(String x) {
		this.x = x;
	}
		public void setY(String y) {
		this.y = y;
	}
	
	public void setP(String p) {
		this.p = p;
	}
		
	
	
	
	public String getId()
	{
		return this.id;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public String getVertexLabel()
	{
		return this.vertex_label;
	}
	
	public String getStyle()
	{
		return this.style;
	}
	
	public String getX()
	{
		return this.x;
	}
	
	public String getY()
	{
		return this.y;
    }
    
    public String getP()
	{
		return this.p;
    }


	@Override
    public String toString() {
            return "<" + id + ", " + type + ", " + vertex_label + ", " + style + ", " + x + ", "+ y +">";
    }
}
