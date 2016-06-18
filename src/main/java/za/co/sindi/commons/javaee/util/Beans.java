/**
 * 
 */
package za.co.sindi.commons.javaee.util;

import java.lang.reflect.Type;
import java.util.Iterator;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

/**
 * @author Buhake Sindi
 * @since 04 September 2014
 *
 */
public class Beans {

	private Beans() {
		throw new AssertionError("Private constructor.", null);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Bean<T> resolve(BeanManager beanManager, Class<T> clazz) {
		return (Bean<T>) beanManager.resolve(beanManager.getBeans(clazz));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getReference(BeanManager beanManager, Class<T> clazz) {
		if (beanManager == null) {
			throw new IllegalArgumentException("No " + BeanManager.class.getName() + " reference was provided.");
		}
		
		Iterator<Bean<?>> iter = beanManager.getBeans(clazz).iterator();
        if (!iter.hasNext()) {
            throw new IllegalStateException("CDI BeanManager cannot find an instance of requested type " + clazz.getName());
        }
		Bean<T> bean = (Bean<T>) iter.next();
        CreationalContext<T> ctx = beanManager.createCreationalContext(bean);
        T reference = (T) beanManager.getReference(bean, clazz, ctx);
        return reference;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getReference(BeanManager beanManager, String elName) {
		if (beanManager == null) {
			throw new IllegalArgumentException("No " + BeanManager.class.getName() + " reference was provided.");
		}
		
		Iterator<Bean<?>> iter = beanManager.getBeans(elName).iterator();
        if (!iter.hasNext()) {
            throw new IllegalStateException("CDI BeanManager cannot find an instance of requested EL name " + elName);
        }
		Bean<T> bean = (Bean<T>) iter.next();
        CreationalContext<T> ctx = beanManager.createCreationalContext(bean);
        Type type = (Type) bean.getTypes().iterator().next();
        return (T) beanManager.getReference(bean, type, ctx);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(BeanManager beanManager, Class<T> clazz, boolean create) {
		if (beanManager == null) {
			throw new IllegalArgumentException("No " + BeanManager.class.getName() + " reference was provided.");
		}
		
		Iterator<Bean<?>> iter = beanManager.getBeans(clazz).iterator();
        if (!iter.hasNext()) {
            throw new IllegalStateException("CDI BeanManager cannot find an instance of requested type " + clazz.getName());
        }
		Bean<T> bean = (Bean<T>) iter.next();
		Context context = beanManager.getContext(bean.getScope());
		return create ? context.get(bean, beanManager.createCreationalContext(bean)) : context.get(bean);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(BeanManager beanManager, String elName, boolean create) {
		if (beanManager == null) {
			throw new IllegalArgumentException("No " + BeanManager.class.getName() + " reference was provided.");
		}
		
		Iterator<Bean<?>> iter = beanManager.getBeans(elName).iterator();
        if (!iter.hasNext()) {
            throw new IllegalStateException("CDI BeanManager cannot find an instance of requested EL name " + elName);
        }
		Bean<T> bean = (Bean<T>) iter.next();
		Context context = beanManager.getContext(bean.getScope());
		return create ? context.get(bean, beanManager.createCreationalContext(bean)) : context.get(bean);
	}
}
