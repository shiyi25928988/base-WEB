package lego.rest.result;

import lego.rest.utils.HttpStatusCode;
import lego.rest.utils.MimeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shiyi
 *
 * @param <T>
 */
public abstract class Result<T> implements ReturnType<T> {

	@Override
	public MimeType getMimeType() {
		return MimeType.APPLICATION_JSON;
	}

	@Override
	public String getSuffix() {
		return "";
	}

	/**
	 * @author shiyi
	 *
	 */
	public static class Builder {
		public static Result<ResultStatus> success(String message) {
			return new Result<ResultStatus>() {

				@Override
				public ResultStatus getData() {
					ResultStatus rs = new ResultStatus();
					rs.setHttpStatus(HttpStatusCode.SC_OK);
					rs.setSuccess(true);
					rs.setMessage(message);
					return rs;
				}
			};
		}
		
		public static Result<ResultStatus> success(int httpStatus, String message) {
			return new Result<ResultStatus>() {

				@Override
				public ResultStatus getData() {
					ResultStatus rs = new ResultStatus();
					rs.setHttpStatus(httpStatus);
					rs.setSuccess(true);
					rs.setMessage(message);
					return rs;
				}
			};
		}

		public static Result<ResultStatus> failed(String message) {
			return new Result<ResultStatus>() {

				@Override
				public ResultStatus getData() {
					ResultStatus rs = new ResultStatus();
					rs.setHttpStatus(HttpStatusCode.SC_BAD_REQUEST);
					rs.setSuccess(false);
					rs.setMessage(message);
					return rs;
				}
			};
		}
		
		public static Result<ResultStatus> failed(int httpStatus, String message) {
			return new Result<ResultStatus>() {

				@Override
				public ResultStatus getData() {
					ResultStatus rs = new ResultStatus();
					rs.setHttpStatus(httpStatus);
					rs.setSuccess(false);
					rs.setMessage(message);
					return rs;
				}
			};
		}
	}

	/**
	 * @author shiyi
	 *
	 */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	private static class ResultStatus {
		private Boolean success;
		private String message;
		private int httpStatus;
	}

}
