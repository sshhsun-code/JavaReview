package annotation;

import java.lang.reflect.Method;

/*
 * 运行时 Annotation 解析  
 * */
public class AnnotationParseHelper {

	public AnnotationParseHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public static void parseAnnotationInfo(Class<?>  classZ ) {
		Method[] methods = classZ.getMethods();
		
		for (Method method : methods) {
			MethodInfo info = method.getAnnotation(MethodInfo.class);
			
			if (info != null) {
				System.out.println("method name = " + method.getName());
				System.out.println("info.author() = " + info.author());
				System.out.println("info.version() = " + info.version());
				System.out.println("info.date()  = " + info.date());				
			}
		}
	}

}
