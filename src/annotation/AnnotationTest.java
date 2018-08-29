package annotation;

@Entity
public class AnnotationTest {

	public AnnotationTest() {
		this.getClass().getMethods();
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		String name = getAppName();
		System.out.println("name =  " + name);			
		
		AnnotationParseHelper.parseAnnotationInfo(AnnotationTest.class);
	}
	
	@MethodInfo (
			author = "sunqi",
			date = "2018/08/29",
			version = 1
			
			)
    public static String getAppName() {
        return "trinea";
    }
	
	private static void parseAnnotationInfo() {
		
		
	}

}
