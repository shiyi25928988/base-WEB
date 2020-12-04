package dl.service;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DownLoadStatus {

	int totalPieces;
	
	int completePieces;
	
	int incompletePieces;
	
	int remainingPieces;
	
	int skippedPieces;
	
	int notSkippedPieces;
	
	long downloaded;
	
	long uploaded;
}
