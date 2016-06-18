/**
 * 
 */
package za.co.sindi.commons.javaee.jaxb.adapters;

import java.math.BigDecimal;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Bienfait Sindi
 * @since 25 January 2015
 *
 */
public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public BigDecimal unmarshal(String value) throws Exception {
		// TODO Auto-generated method stub
		return new BigDecimal(value);
	}

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public String marshal(BigDecimal value) throws Exception {
		// TODO Auto-generated method stub
		return (value == null ? null : value.toString());
	}
}
