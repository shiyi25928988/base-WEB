package lego.rest.result;

import lego.rest.utils.MimeType;

/**
 * @author SHIYI
 *
 */
public interface ReturnType<T> {

	MimeType getMimeType();
	String getSuffix();
	T getData();
}
