package DeMeo;
public class Cluster 
{
	private String id; 
    private String label;
    private String matrix;
    private String prob;
    
    public Cluster() {}

    public Cluster(String id, String label, String matrix) 
    {
    	this.id = id;
    	this.label = label;
    	this.matrix = matrix;   
    	this.prob = prob;	
    }

    public void setId(String id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setMatrix(String matrix) {
		this.matrix = matrix;
	}

    public void setProb(String prob) {
		this.prob = prob;
	}
	
	
	public String getId()
	{
		return this.id;
	}
	
	public String getLabel()
	{
		return this.label;
	}
	

    public String  getMatrix()
	{
		return this.matrix;
    }
    
    public String  getProb()
	{
		return this.prob;
    }


	@Override
    public String toString() {
            return "<" + id + ", " + label +", " + prob + ", " + matrix +">";
    }
}
