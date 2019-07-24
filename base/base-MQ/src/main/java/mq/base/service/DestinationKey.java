package mq.base.service;

import java.util.Objects;

import mq.base.module.DestinationType;

public class DestinationKey {

	private String destinationName;
	private DestinationType destinationType;
	private int hash;
	
	public DestinationKey(String destinationName, DestinationType destinationType) {
		this.destinationName = destinationName;
		this.destinationType = destinationType;
		
		hash = 31;
		
		if(!destinationName.isEmpty()) {
			hash += destinationName.hashCode();
		}
		hash *= 31;
		if(Objects.nonNull(destinationType)) {
			switch(destinationType) {
			case Queue:
				hash += 1;
				break;
			case Topic:
				hash += 2;
				break;
			}
		}
	}
	
	public String getDestinationName() {
		return destinationName;
	}

	public DestinationType getType() {
		return destinationType;
	}

	@Override
	public int hashCode() {
		return this.hash;
	}
	
    @Override
    public boolean equals(Object that) {
        if (this.hashCode() == that.hashCode()) {
            return true;
        }
        if (that instanceof DestinationKey) {
            return equals((DestinationKey) that);
        }
        return false;
    }

    public boolean equals(DestinationKey that) {
        return isEqual(this.destinationName, that.destinationName) && isEqual(this.getType(), that.getType());
    }
	
	
    public static boolean isEqual(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        return o1 != null && o2 != null && o1.equals(o2);
    }
}
