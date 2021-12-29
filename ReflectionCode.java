
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReflectionExercises {
	public static Package getPackage(Object obj) {
		Object o = obj;
		Class<?> c = o.getClass();
		return c.getPackage();
	}

	public static List<Class<?>> getSuperClasses(Object obj) {
		List<Class<?>> lists = new ArrayList<>();
		Class curr = obj.getClass();
		lists.add(curr);
		Class superClass = curr.getSuperclass();
		while (superClass != null) {
			curr = superClass;
			lists.add(curr);
			superClass = curr.getSuperclass();
		}

		return lists;
	}

	public static List<Class<?>> getInterfaces(Object obj) {

		List<Class<?>> clss = List.of(obj.getClass().getInterfaces());
		return clss;
	}

	public static Boolean isInterface(Class<?> clazz) {
		return clazz.isInterface();
	}

	public static Boolean isAbstract(Class<?> clazz) {
		return Modifier.isAbstract(clazz.getModifiers());

	}

	public static Boolean isInnerClass(Class<?> clazz) {
		if (clazz.getEnclosingClass() != null) {
			return true;
		}
		return false;
	}


	public static String classToString(Class<?> clazz) {
		int modifiers = clazz.getModifiers();
		String p = "";
		String ab = "";
		if (Modifier.isProtected(modifiers)) {
			p = "private ";
		} else if (Modifier.isPrivate(modifiers)) {
			p = "public ";
		}

		String stat = "";
		if (Modifier.isStatic(clazz.getModifiers())) {
			stat = "static ";
		}

		if (Modifier.isAbstract(modifiers)) {
			ab = "abstract class ";
		}
		if (Modifier.isFinal(modifiers)) {
			ab = "final class";
		}
		String type = "";

		if (clazz.isEnum()) {
			type = "enumerated ";
		}
		if (clazz.isMemberClass()) {
			type = "class ";
		}
		if (Modifier.isProtected(modifiers)) {
			type = "protected";
		}
		String name = clazz.getCanonicalName();
		System.out.println(name);
		return p + stat + ab + type + name;

	}

	public static <T> List<String> getEnumConstants(Class<T> clazz) {
		if (clazz.getEnumConstants() == null) {
			return new ArrayList<String>();
		}
		List<T> lists = List.of(clazz.getEnumConstants());

		ArrayList<String> strings = new ArrayList<>();
		for (int i = 0; i < lists.size(); ++i) {
			strings.add(lists.get(i).toString());
		}
		Collections.sort(strings);
		return strings;

	}

	public static List<Method> getPublicObjectMethods(Class<?> clazz) {
		Method[] allMethods = clazz.getDeclaredMethods();
		List<Method> c = new ArrayList<>();
		for (Method method : allMethods) {
			if (Modifier.isPublic(method.getModifiers())) {
				c.add(method);
			}
		}
		return c;

	}


	public static List<String> methodParamsToString(Class<?> clazz, String name) {
		List<Method> classes = List.of(clazz.getClass().getMethods());
		List<String> o = new ArrayList<>();
		List<String> p = new ArrayList<>();
		for (int i = 0; i < classes.size(); ++i) {
			o.add(classes.get(i).getName().toString());
		}

		for (String c : o) {
			if (c.equals(name)) {
				p.add(c);
			}

		}

		return o;
	}


	public static Object invokeMethod(Class<?> clazz, Object obj, String name, Class<?>[] paramTypes, Object[] args) {
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(name, paramTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return method.invoke(obj, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			
			e.printStackTrace();
		}
		return method;

	}


	public static List<String> getPrivateFields(Class<?> clazz) {
		List<Method> c = List.of(clazz.getMethods());
		List<String> d = List.of(Modifier.toString(clazz.getModifiers()));
		List<String> m = new ArrayList<>();
		for (int i = 0; i < c.size(); ++i) {
			if (c.get(i).toString().contains("public")) {
				System.out.println(c.get(i));
			}
			else{
				m.add(d.get(i));
			}
		}
		return m;

	}


