package mq.base.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestObj {

	private int     field_1;
	private double  field_2;
	private long    field_3;
	private float   field_4;
	private String  field_5;

	public boolean equals(TestObj t) {
		if (this.getField_1() != t.getField_1())
			return false;
		if (this.getField_2() != t.getField_2())
			return false;
		if (this.getField_3() != t.getField_3())
			return false;
		if (this.getField_4() != t.getField_4())
			return false;
		if (!this.getField_5().equals(t.getField_5()))
			return false;
		return true;
	}

}
