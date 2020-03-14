package lego.ioc;

import java.util.ArrayList;
import java.util.List;

import lego.annotation.PackageScan;

public final class PackageScanConfig {

	private static List<String> list = new ArrayList<>();

	public static void scan(Class<?> clazz) {
		if (clazz.isAnnotationPresent(PackageScan.class)) {
			PackageScan packageScan = clazz.getAnnotation(PackageScan.class);
			list.add(packageScan.target());
		}
	}
	
	public static List<String> getPackageList() {
		return PackageScanConfig.list;
	}
}
