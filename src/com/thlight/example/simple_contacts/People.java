package com.thlight.example.simple_contacts;

public class People 
{
	private String id;
	private String name;
	private String tel;
	private String cell;
	private String email;
	private boolean hasPhone;
	public People(){};
	
	public People(String id,String name,String tel,String cell,String email, boolean hasPhone)
	{
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.cell = cell;
		this.email = email;
		this.hasPhone = hasPhone;
	}
	
	public void setHasPhone(boolean temp)
	{
		this.hasPhone = temp;
	}
	public void setID(String id)
	{
		this.id = id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	public void setCell(String cell)
	{
		this.cell = cell;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getID()
	{
		return this.id;
	}
	public String getName()
	{
		return this.name;
	}
	public String getTel()
	{
		return this.tel;
	}
	public String getCell()
	{
		return this.cell;
	}
	public String getEmail()
	{
		return this.email;
	}
	public boolean getHasPhone()
	{
		return this.hasPhone;
	}
	

}
