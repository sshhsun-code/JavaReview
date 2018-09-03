
package reflect.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}	

	private static void getRelfectClass() throws ClassNotFoundException {
		
		
		//通过对象实例获取对应Class对象Object.getClass()--对于基本类型无法使用这种方法
		Person person = new Person("Bob");
		Class class1 = person.getClass();
		
		//通过类的类型获取Class对象,基本类型同样可以使用这种方法
		Class class2 = int.class;
		Class strClass = String.class;
		
		//通过类的全限定名获取Class对象， 基本类型无法使用此方法
		Class c = Class.forName("java.lang.String");		
		Class cDoubleArray = Class.forName("[D");    //对于数组比较特殊,相当于double[].class
		Class cStringArray = Class.forName("[[Ljava.lang.String;");   //相当于String[][].class
		
		
		//基本类型和void 类型的包装类可以使用TYPE字段获取
		
		Class c1 = Double.TYPE;   //等价于 double.class.
		Class c2 = Void.TYPE;
		
		//还有一些反射方法可以获取Class对象，但前提是你已经获取了一个Class对象。
		c1.getSuperclass();//获得给定类的父类Class
		
		
		/*************
		 * 一般情况下都使用以下这三种：
		 * 
		 * *********************/
		
		Class c11 = Test.class; //这说明任何一个类都有一个隐含的静态成员变量class，这种方式是通过获取类的静态成员变量class得到的()
		Class c12 = new Test().getClass();// test是Test类的一个对象，这种方式是通过一个类的对象的getClass()方法获得的 (对于基本类型无法使用这种方法)
		Class c13 = Class.forName("reflect.test.Test"); //这种方法是Class类调用forName方法，通过一个类的全量限定名获得（基本类型无法使用此方法）

	}
	
	private static void getRelfectConstructor() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clz = Student.class;
		Constructor constructor = clz.getConstructor(String.class);
		constructor.setAccessible(true);//取消Java语言检查，调用private方法
		Object object = constructor.newInstance("Bob");
		System.out.println(" obj :  " + object.toString());
	}
	
	
	/*****
	 * 
// 获取 Class 对象中指定函数名和参数的函数，参数一为函数名，参数 2 为参数类型列表
public Method getDeclaredMethod (String name, Class...<?> parameterTypes)

// 获取该 Class 对象中的所有函数( 不包含从父类继承的函数 )
public Method[] getDeclaredMethods ()

// 获取指定的 Class 对象中的**公有**函数，参数一为函数名，参数 2 为参数类型列表
public Method getMethod (String name, Class...<?> parameterTypes)

// 获取该 Class 对象中的所有**公有**函数 ( 包含从父类和接口类集成下来的函数 )
public Method[] getMethods ()
	 * 
	 * 
	 * 
	 * *******/
	
	private static void showDeclaredMethods() {
		Student student = new Student("mr.simple");
		
		Method[] methods = student.getClass().getDeclaredMethods();
		for (Method method : methods) {
			System.out.println("declared method name : " + method.getName());
		}
		
		try {
			Method learnMethod = student.getClass().getDeclaredMethod("learn", String.class);
			learnMethod.setAccessible(true);
			Class<?>[] paramClasses = learnMethod.getParameterTypes() ;
            for (Class<?> class1 : paramClasses) {
                System.out.println("learn 方法的参数类型 : " + class1.getName());
            }
            
            System.out.println(learnMethod.getName() + " is private "
                    + Modifier.isPrivate(learnMethod.getModifiers()));
            
            learnMethod.invoke(student, "java --- >");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();;
		}				
	}
	
    private static void showMethods() {
        Student student = new Student("mr.simple");
        // 获取所有方法
        Method[] methods = student.getClass().getMethods();
        for (Method method : methods) {
            System.out.println("method name : " + method.getName());
        }

        try {
            // 通过 getMethod 只能获取公有方法，如果获取私有方法则会抛出异常，这里就会抛异常
            Method learnMethod = student.getClass().getMethod("learn", String.class);
            // 是否是 private 函数，属性是否是 private 也可以使用这种方式判断
            System.out.println(learnMethod.getName() + " is private " + Modifier.isPrivate(learnMethod.getModifiers()));
            // 调用 learn 函数
            learnMethod.invoke(student, "java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    /********
     * 
     * 
     * 
     * 
     * // 获取 Class 对象中指定属性名的属性，参数一为属性名
public Method getDeclaredField (String name)

// 获取该 Class 对象中的所有属性( 不包含从父类继承的属性 )
public Method[] getDeclaredFields ()

// 获取指定的 Class 对象中的**公有**属性，参数一为属性名
public Method getField (String name)

// 获取该 Class 对象中的所有**公有**属性 ( 包含从父类和接口类集成下来的公有属性 )
public Method[] getFields ()
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * **********/
    
    
    
    
    
    
    
    private static void showDeclaredFields() {
        Student student = new Student("mr.simple");
        // 获取当前类和父类的所有公有属性
        Field[] publicFields = student.getClass().getDeclaredFields();
        for (Field field : publicFields) {
            System.out.println("declared field name : " + field.getName());
        }

        try {
            // 获取当前类和父类的某个公有属性
            Field gradeField = student.getClass().getDeclaredField("mGrade");
            Field ageField = student.getClass().getDeclaredField("mAge");
            ageField.setAccessible(true);
            // 获取属性值
            System.out.println(" my grade is : " + gradeField.getInt(student));
            System.out.println(" my age is : " + ageField.getInt(student));
            // 设置属性值
            gradeField.set(student, 10);
            System.out.println(" my grade is : " + gradeField.getInt(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void showFields() {
        Student student = new Student("mr.simple");
        // 获取当前类和父类的所有公有属性
        Field[] publicFields = student.getClass().getFields();
        for (Field field : publicFields) {
            System.out.println("field name : " + field.getName());
        }

        try {
            // 获取当前类和父类的某个公有属性,mAge为private，这里就会抛异常
            Field ageField = student.getClass().getField("mAge");
            ageField.setAccessible(true);
            System.out.println(" age is : " + ageField.getInt(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
    

    
    //获取 Class 对象中实现的接口。
    private static void showSuperClass() {
        Student student = new Student("mr.simple");
        Class<?> superClass = student.getClass().getSuperclass();
        while (superClass != null) {
            System.out.println("Student's super class is : " + superClass.getName());
            // 再获取父类的上一层父类，直到最后的 Object 类，Object 的父类为 null
            superClass = superClass.getSuperclass();
        }
    }
    
    
    //获取 Class 对象中实现的接口。
    private static void showInterfaces() {
        Student student = new Student("mr.simple");
        Class<?>[] interfaceses = student.getClass().getInterfaces();
        for (Class<?> class1 : interfaceses) {
            System.out.println("Student's interface is : " + class1.getName());
        }
    }
    
    
    
	public static void main(String[] args) {
		try {
			getRelfectConstructor();
			getRelfectClass();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showDeclaredMethods();
		showMethods();
		showDeclaredFields();
		showFields();
		showSuperClass();
		showInterfaces();
	}
	
    
    
    
    
    
    
    
    

}
