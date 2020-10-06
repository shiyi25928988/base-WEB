package core.http.result;

import core.http.MimeType;

/**
 * @author SHIYI
 *
 */
public interface ReturnType<T> {

	MimeType getMimeType();
	T getData();
}
