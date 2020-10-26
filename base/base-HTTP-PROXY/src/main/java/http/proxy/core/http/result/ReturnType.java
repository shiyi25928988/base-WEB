package http.proxy.core.http.result;

import http.proxy.core.http.utils.MimeType;

/**
 * @author SHIYI
 *
 */
public interface ReturnType<T> {

	MimeType getMimeType();
	T getData();
}
