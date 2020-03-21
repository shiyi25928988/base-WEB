package lego.rest.result;

import lego.rest.utils.MimeType;
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
	private final String suffix = ".json";
	@Override
	public T getData() {
		return obj;
	}
}
