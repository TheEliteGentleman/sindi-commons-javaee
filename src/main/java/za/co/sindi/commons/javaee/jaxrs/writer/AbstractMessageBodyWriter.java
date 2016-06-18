/**
 * 
 */
package za.co.sindi.commons.javaee.jaxrs.writer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 * @author Bienfait Sindi
 * @since 08 August 2015
 *
 */
public abstract class AbstractMessageBodyWriter<T> implements MessageBodyWriter<T> {

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.MessageBodyWriter#getSize(java.lang.Object, java.lang.Class, java.lang.reflect.Type, java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType)
	 */
	@Override
	public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return -1;
	}
}
