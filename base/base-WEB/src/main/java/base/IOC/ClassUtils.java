package base.IOC;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

import lombok.NonNull;
import lombok.extern.java.Log;

@Log
public final class ClassUtils {
	
	public static Set<Class<?>> getClassSet() throws IOException, ClassNotFoundException {
		return getClassSet(ClassUtils.class.getName().substring(0, ClassUtils.class.getName().indexOf(".")));
	}

	public static Set<Class<?>> getClassSet(final String packageName) throws IOException, ClassNotFoundException {

		Set<Class<?>> classSet = new HashSet<>();

		Enumeration<URL> URLs = getClassLoader().getResources(packageName.replace(".", File.separator));

		while (URLs.hasMoreElements()) {
			URL url = URLs.nextElement();
			if (Objects.nonNull(url)) {
				switch (url.getProtocol()) {
					case "file":
						String packagePath = escapeSpace(url.getPath());
						addClass(classSet, packagePath, packageName);
						break;
					case "jar":
						JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
						if (Objects.nonNull(jarURLConnection)) {
							JarFile jarFile = jarURLConnection.getJarFile();
							if (Objects.nonNull(jarFile)) {
								Enumeration<JarEntry> jarEntries = jarFile.entries();
								while (jarEntries.hasMoreElements()) {
									JarEntry jarEntry = jarEntries.nextElement();
									String jarEntryName = jarEntry.getName();
									if (jarEntryName.endsWith(".class")) {
										String className = jarEntryName.substring(0, jarEntryName.lastIndexOf("."))
										                .replaceAll("/", ".");
										doAddClass(classSet, className);
									}
								}
							}
						}
						break;
				}
			}
		}

		return classSet;
	}

	private static void addClass(final Set<Class<?>> classSet, @NonNull final String packagePath,
	                @NonNull final String packageName) {

		File[] files = new File(packagePath).listFiles(new FileFilter() {
			@Override
			public boolean accept(final File file) {
				if (file.isFile()) {
					if (file.getName().endsWith(".class")) {
						return true;
					}
				}
				if (file.isDirectory()) {
					return true;
				}
				return false;
			}
		});

		Stream.of(files).forEach(file -> {
			String fileName = file.getName();
			if (file.isFile()) {
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				className = packageName.concat(".").concat(className);
				try {
					doAddClass(classSet, className);
				} catch (ClassNotFoundException e) {
					log.severe(e.getMessage());
					e.printStackTrace();
				}
			} else if (file.isDirectory()) {
				String subPackagePath = packagePath + "/" + fileName;
				String subPackageName = packageName + "." + fileName;
				addClass(classSet, subPackagePath, subPackageName);
			}
		});
	}
	
	private static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	private static Class<?> loadClass(final String className, final boolean initialize) throws ClassNotFoundException {
		return Class.forName(className, initialize, getClassLoader());
	}

	private static void doAddClass(final Set<Class<?>> classSet, final String className) throws ClassNotFoundException {
		Class<?> clazz = loadClass(className, false);
		classSet.add(clazz);
	}

	private static String escapeSpace(final String str) {
		return str.replace("%20", " ");
	}
}
