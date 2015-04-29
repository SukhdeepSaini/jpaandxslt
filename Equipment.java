package db.assignment6;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Equipment
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	
	@XmlAttribute
	private String brand;
	
	@XmlAttribute
	private String description;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private double price;

	@ManyToOne
	@JoinColumn(name = "towerId")
	@XmlTransient
	private Tower tower;

	public Equipment()
	{
		super();
	}

	public Equipment(String brand, String description, String name, double price, Tower tower) 
	{
		super();
		this.brand = brand;
		this.description = description;
		this.name = name;
		this.price = price;
		this.tower = tower;
	}

	public int GetId() 
	{
		return this.id;
	}

	public void SetId(int id) 
	{
		this.id = id;
	}

	public String GetBrand()
	{
		return this.brand;
	}

	public void SetBrand(String brand)
	{
		this.brand = brand;
	}

	public String GetDescription() 
	{
		return this.description;
	}

	public void SetDescription(String description)
	{
		this.description = description;
	}

	public String GetName() 
	{
		return this.name;
	}

	public void SetName(String name) 
	{
		this.name = name;
	}

	public double GetPrice() 
	{
		return this.price;
	}

	public void SetPrice(double price)
	{
		this.price = price;
	}

	public Tower GetTower() 
	{
		return this.tower;
	}

	public void SetTower(Tower tower)
	{
		this.tower = tower;
	}

}