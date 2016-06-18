/**
 * 
 */
package za.co.sindi.commons.javaee.util;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import za.co.sindi.common.utils.PreConditions;

/**
 * @author Bienfait Sindi
 * @since 03 September 2014
 *
 */
public final class JNDI {

//	private static final String[] BEAN_MANAGER_JNDI_NAMES = {"java:/comp/BeanManager", "java:/app/BeanManager", "java:/comp/env/BeanManager"};
	private static final String EJB_CONTEXT;

    static {
        try {
            EJB_CONTEXT = "java:global/" + new InitialContext().lookup("java:app/AppName") + "/";
        } catch (NamingException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
	
	private JNDI() {
		throw new AssertionError("Private constructor.", null);
	}
	
	public static BeanManager findBeanManager() {
		BeanManager beanManager = null;
		Context context = null;
		
		try {
			context = new InitialContext();
			beanManager = (BeanManager) context.lookup("java:/comp/BeanManager");
		} catch (NamingException e) {
			//TODO: Log?
		}
		
		if (beanManager == null) {
			try {
				//For WELDINT-19 (https://issues.jboss.org/browse/WELDINT-19)
				beanManager = (BeanManager) context.lookup("java:/app/BeanManager");
			} catch (NamingException e) {
				//TODO: Log?
			}
		}
		
		if (beanManager == null) {
			try {
				//For Tomcat and Jetty
				beanManager = (BeanManager) context.lookup("java:/comp/env/BeanManager");
			} catch (NamingException e) {
				//TODO: Log?
			}
		}
		
		if (context != null) {
			try {
				context.close();
			} catch (NamingException e) {
				// TODO Log?
			}
		}
		
		return beanManager;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T findEJB(Class<T> ejbClass) {
		PreConditions.checkArgument(ejbClass != null, "No EJB class specified.");
		PreConditions.checkArgument(!ejbClass.isInterface(), "EJB class must not be an interface.");
		
		Context context = null;
		String jndiName = EJB_CONTEXT + ejbClass.getSimpleName();

        try {
        	context = new InitialContext();
            // Do not use ejbClass.cast(). It will fail on local/remote interfaces.
            return (T) context.lookup(jndiName);
        } catch (NamingException e) {
            throw new IllegalArgumentException(
                String.format("Cannot find EJB class %s in JNDI %s", ejbClass, jndiName), e);
        } finally {
        	if (context != null) {
        		try {
					context.close();
				} catch (NamingException e) {
					// TODO Log?
				}
        	}
        }
    }
	
	@SuppressWarnings("unchecked")
	public static <T> T findEJB(Class<? extends T> ejbClass, Class<T> ejbInterfaceClass) {
		if (ejbInterfaceClass == null) {
			return findEJB(ejbClass);
		}
		
		PreConditions.checkArgument(ejbClass != null, "No EJB class specified.");
		PreConditions.checkArgument(!ejbClass.isInterface(), "EJB class must not be an interface.");
		
		Context context = null;
		String jndiName = EJB_CONTEXT + ejbClass.getSimpleName() + "!" + ejbInterfaceClass.getName();

        try {
        	context = new InitialContext();
            // Do not use ejbClass.cast(). It will fail on local/remote interfaces.
            return (T) context.lookup(jndiName);
        } catch (NamingException e) {
            throw new IllegalArgumentException(
                String.format("Cannot find EJB class %s in JNDI %s", ejbClass, jndiName), e);
        } finally {
        	if (context != null) {
        		try {
					context.close();
				} catch (NamingException e) {
					// TODO Log?
				}
        	}
        }
    }
}
