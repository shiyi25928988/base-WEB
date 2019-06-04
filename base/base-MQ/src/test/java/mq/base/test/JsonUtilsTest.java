package mq.base.test;

import java.io.IOException;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mq.base.utils.JsonUtils;

public class JsonUtilsTest {

	@Test
	public void test() {
		TestObj t = new TestObj();
		t.setField_1(new Random().nextInt());
		t.setField_2(new Random().nextDouble());
		t.setField_3(new Random().nextLong());
		t.setField_4(new Random().nextFloat());
		t.setField_5("test");
		
		try {
			Assertions.assertTrue(t.equals(JsonUtils.decodeMessage(JsonUtils.encodeMessage(t))));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
