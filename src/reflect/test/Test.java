
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
		
		
		//ͨ������ʵ����ȡ��ӦClass����Object.getClass()--���ڻ��������޷�ʹ�����ַ���
		Person person = new Person("Bob");
		Class class1 = person.getClass();
		
		//ͨ��������ͻ�ȡClass����,��������ͬ������ʹ�����ַ���
		Class class2 = int.class;
		Class strClass = String.class;
		
		//ͨ�����ȫ�޶�����ȡClass���� ���������޷�ʹ�ô˷���
		Class c = Class.forName("java.lang.String");		
		Class cDoubleArray = Class.forName("[D");    //��������Ƚ�����,�൱��double[].class
		Class cStringArray = Class.forName("[[Ljava.lang.String;");   //�൱��String[][].class
		
		
		//�������ͺ�void ���͵İ�װ�����ʹ��TYPE�ֶλ�ȡ
		
		Class c1 = Double.TYPE;   //�ȼ��� double.class.
		Class c2 = Void.TYPE;
		
		//����һЩ���䷽�����Ի�ȡClass���󣬵�ǰ�������Ѿ���ȡ��һ��Class����
		c1.getSuperclass();//��ø�����ĸ���Class
		
		
		/*************
		 * һ������¶�ʹ�����������֣�
		 * 
		 * *********************/
		
		Class c11 = Test.class; //��˵���κ�һ���඼��һ�������ľ�̬��Ա����class�����ַ�ʽ��ͨ����ȡ��ľ�̬��Ա����class�õ���()
		Class c12 = new Test().getClass();// test��Test���һ���������ַ�ʽ��ͨ��һ����Ķ����getClass()������õ� (���ڻ��������޷�ʹ�����ַ���)
		Class c13 = Class.forName("reflect.test.Test"); //���ַ�����Class�����forName������ͨ��һ�����ȫ���޶�����ã����������޷�ʹ�ô˷�����

	}
	
	private static void getRelfectConstructor() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clz = Student.class;
		Constructor constructor = clz.getConstructor(String.class);
		constructor.setAccessible(true);//ȡ��Java���Լ�飬����private����
		Object object = constructor.newInstance("Bob");
		System.out.println(" obj :  " + object.toString());
	}
	
	
	/*****
	 * 
// ��ȡ Class ������ָ���������Ͳ����ĺ���������һΪ������������ 2 Ϊ���������б�
public Method getDeclaredMethod (String name, Class...<?> parameterTypes)

// ��ȡ�� Class �����е����к���( �������Ӹ���̳еĺ��� )
public Method[] getDeclaredMethods ()

// ��ȡָ���� Class �����е�**����**����������һΪ������������ 2 Ϊ���������б�
public Method getMethod (String name, Class...<?> parameterTypes)

// ��ȡ�� Class �����е�����**����**���� ( �����Ӹ���ͽӿ��༯�������ĺ��� )
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
                System.out.println("learn �����Ĳ������� : " + class1.getName());
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
        // ��ȡ���з���
        Method[] methods = student.getClass().getMethods();
        for (Method method : methods) {
            System.out.println("method name : " + method.getName());
        }

        try {
            // ͨ�� getMethod ֻ�ܻ�ȡ���з����������ȡ˽�з�������׳��쳣������ͻ����쳣
            Method learnMethod = student.getClass().getMethod("learn", String.class);
            // �Ƿ��� private �����������Ƿ��� private Ҳ����ʹ�����ַ�ʽ�ж�
            System.out.println(learnMethod.getName() + " is private " + Modifier.isPrivate(learnMethod.getModifiers()));
            // ���� learn ����
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
     * // ��ȡ Class ������ָ�������������ԣ�����һΪ������
public Method getDeclaredField (String name)

// ��ȡ�� Class �����е���������( �������Ӹ���̳е����� )
public Method[] getDeclaredFields ()

// ��ȡָ���� Class �����е�**����**���ԣ�����һΪ������
public Method getField (String name)

// ��ȡ�� Class �����е�����**����**���� ( �����Ӹ���ͽӿ��༯�������Ĺ������� )
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
        // ��ȡ��ǰ��͸�������й�������
        Field[] publicFields = student.getClass().getDeclaredFields();
        for (Field field : publicFields) {
            System.out.println("declared field name : " + field.getName());
        }

        try {
            // ��ȡ��ǰ��͸����ĳ����������
            Field gradeField = student.getClass().getDeclaredField("mGrade");
            Field ageField = student.getClass().getDeclaredField("mAge");
            ageField.setAccessible(true);
            // ��ȡ����ֵ
            System.out.println(" my grade is : " + gradeField.getInt(student));
            System.out.println(" my age is : " + ageField.getInt(student));
            // ��������ֵ
            gradeField.set(student, 10);
            System.out.println(" my grade is : " + gradeField.getInt(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void showFields() {
        Student student = new Student("mr.simple");
        // ��ȡ��ǰ��͸�������й�������
        Field[] publicFields = student.getClass().getFields();
        for (Field field : publicFields) {
            System.out.println("field name : " + field.getName());
        }

        try {
            // ��ȡ��ǰ��͸����ĳ����������,mAgeΪprivate������ͻ����쳣
            Field ageField = student.getClass().getField("mAge");
            ageField.setAccessible(true);
            System.out.println(" age is : " + ageField.getInt(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
    

    
    //��ȡ Class ������ʵ�ֵĽӿڡ�
    private static void showSuperClass() {
        Student student = new Student("mr.simple");
        Class<?> superClass = student.getClass().getSuperclass();
        while (superClass != null) {
            System.out.println("Student's super class is : " + superClass.getName());
            // �ٻ�ȡ�������һ�㸸�ֱ࣬������ Object �࣬Object �ĸ���Ϊ null
            superClass = superClass.getSuperclass();
        }
    }
    
    
    //��ȡ Class ������ʵ�ֵĽӿڡ�
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
