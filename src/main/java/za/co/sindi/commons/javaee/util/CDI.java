/**
 * 
 */
package za.co.sindi.commons.javaee.util;

import javax.enterprise.inject.spi.BeanManager;

/**
 * @author Buhake Sindi
 * @since 03 September 2014
 *
 */
public class CDI {

//	private static final String CDI_JAVAEE_CLASS_NAME = "javax.enterprise.inject.spi.CDI";
	
	private CDI() {
		throw new AssertionError("Private constructor.", null);
	}
	
	/**
	 * Finds the {@link BeanManager} first by using {@link JNDI} <code>getBeanManager()</code> method. If none was found, use the {@link javax.enterprise.inject.spi.CDI} class.
	 *  
	 * @return {@link BeanManager} reference. For brevity, always check for <code>null</code>.
	 */
	public static BeanManager getBeanManager() {
		BeanManager beanManager = JNDI.findBeanManager();
		if (beanManager == null) {
			beanManager = javax.enterprise.inject.spi.CDI.current().getBeanManager();
		}
		
		return beanManager;
	}
}
