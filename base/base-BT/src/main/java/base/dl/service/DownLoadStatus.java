package base.dl.service;

import lombok.Data;

@Data
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
