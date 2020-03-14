package lego.rest.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author shiyi
 *
 * @param <T>
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JSON<T> {
	private T obj; 
}
