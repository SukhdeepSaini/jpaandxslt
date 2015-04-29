package db.assignment6;

import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Tower
{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	
	@XmlAttribute
	private double height;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private int sides;

	@OneToMany(mappedBy="tower",cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	@XmlElement(name="equipment")
	private List<Equipment> equipments;

	@ManyToOne
	@JoinColumn(name="siteId")
	@XmlTransient
	private Site site;

	public Tower() 
	{
		super();
	}
	
	public Tower(double height, String name, int sides, List<Equipment> equipments, Site site) 
	{
		super();
		this.height = height;
		this.name = name;
		this.sides = sides;
		this.equipments = equipments;
		this.site = site;
	}

	public int GetId() 
	{
		return this.id;
	}

	public void SetId(int id)
	{
		this.id = id;
	}

	public double GetHeight() 
	{
		return this.height;
	}
	
	public void SetName(String name)
	{
		this.name = name;
	}

	public int GetSides() 
	{
		return this.sides;
	}

	public void SetSides(int sides) 
	{
		this.sides = sides;
	}


	public void SetHeight(double height)
	{
		this.height = height;
	}

	public String GetName()
	{
		return this.name;
	}

	
	public List<Equipment> GetEquipments() 
	{
		return this.equipments;
	}

	public void SetEquipments(List<Equipment> equipments)
	{
		this.equipments = equipments;
	}


	public Site GetSite()
	{
		return this.site;
	}

	public void SetSite(Site site)
	{
		this.site = site;
	}

}
