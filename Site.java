package db.assignment6;

import java.lang.String;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.*;


@Entity
@NamedQuery(name="GetAllSites", query="SELECT site FROM Site site")
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Site
{	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private double latitude;
	
	@XmlAttribute
	private double longitude;
	
	@OneToMany(mappedBy = "site", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
	@XmlElement(name="tower")
	private List<Tower> towers;
	

	public Site() 
	{
		super();
	}   
	
	public Site(String name, double latitude, double longitude) 
	{
		this.name = name;
		this.latitude = latitude;
		this.longitude =longitude;
	}
	
	public Site(String name, double latitude, double longitude, List<Tower> towers)
	{
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.towers = towers;
	}
	
	public int GetId() 
	{
		return this.id;
	}

	public void SetId(int id)
	{
		this.id = id;
	}   
	
	public String GetName() 
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}   
	
	public double GetLatitude() 
	{
		return this.latitude;
	}

	public void SetLatitude(double latitude) 
	{
		this.latitude = latitude;
	}   
	
	public double GetLongitude()
	{
		return this.longitude;
	}

	public void SetLongitude(double longitude) 
	{
		this.longitude = longitude;
	}
	
	public List<Tower> GetTowers()
	{
		return this.towers;
	}

	public void SetTowers(List<Tower> towers)
	{
		this.towers = towers;
	}
	
}

