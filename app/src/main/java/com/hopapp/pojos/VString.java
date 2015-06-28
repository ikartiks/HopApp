package com.hopapp.pojos;

public class VString  implements java.io.Serializable {


	protected String value;

	public VString (String value ) {
		this.value=value;
	}

	public void setValue( String value ) {
		this.value = value;
	}

	public String getValue( ) {
		return this.value;
	}

	@Override
	public String toString( ) {
		String str = "VString={";
		str += super.toString();
		str += "value={" + value + "} ";
		str += "}";
		return str;
	}
}
