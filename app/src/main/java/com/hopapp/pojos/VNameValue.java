package com.hopapp.pojos;


public class VNameValue implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	protected String name;
	protected String value;
	
	/**
	 * @param name the key
	 * @param value the value
	 */
	public VNameValue (String name,String value) {
		
		this.name=name;
		this.value=value;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getName( ) {
		return this.name;
	}

	public void setValue( String value ) {
		this.value = value;
	}

	public String getValue( ) {
		return this.value;
	}

	@Override
	public String toString( ) {
		String str = "VNameValue={";
		str += super.toString();
		str += "name={" + name + "} ";
		str += "value={" + value + "} ";
		str += "}";
		return str;
	}
}
