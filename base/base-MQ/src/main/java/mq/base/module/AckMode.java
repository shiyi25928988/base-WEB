package mq.base.module;

public enum AckMode {
    AUTO_ACKNOWLEDGE(1),

    CLIENT_ACKNOWLEDGE(2),

    DUPS_OK_ACKNOWLEDGE(3),

    SESSION_TRANSACTED(0);
	
	public final int mode;
	
	private AckMode(int mode) {
		this.mode = mode;
	}
}
