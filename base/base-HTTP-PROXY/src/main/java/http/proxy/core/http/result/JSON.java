package http.proxy.core.http.result;

import http.proxy.core.http.utils.MimeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shiyi
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSON<T> implements ReturnType<T>{
	private T obj; 
	private final MimeType mimeType = MimeType.APPLICATION_JSON;
	@Override
	public T getData() {
		return obj;
	}
}
