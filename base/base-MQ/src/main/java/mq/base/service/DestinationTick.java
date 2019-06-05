package mq.base.service;

import java.util.Objects;

public class DestinationTick {

	private String destinationName;
	private Boolean isMultiCast;
	private int hash;
	
	public DestinationTick(String destinationName, Boolean isMultiCast) {
		this.destinationName = destinationName;
		this.isMultiCast = isMultiCast;
		hash = 31;
		
		if(!destinationName.isEmpty()) {
			hash += destinationName.hashCode();
		}
		hash *= 31;
		if(Objects.nonNull(isMultiCast)) {
			hash += isMultiCast.hashCode();
		}
	}
	
	public String getDestinationName() {
		return destinationName;
	}

	public Boolean getIsMultiCast() {
		return isMultiCast;
	}

	@Override
	public int hashCode() {
		return this.hash;
	}
	
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof DestinationTick) {
            return equals((DestinationTick) that);
        }
        return false;
    }

    public boolean equals(DestinationTick that) {
        return isEqual(this.destinationName, that.destinationName) && isEqual(this.isMultiCast, that.isMultiCast);
    }
	
	
    public static boolean isEqual(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        return o1 != null && o2 != null && o1.equals(o2);
    }
}
