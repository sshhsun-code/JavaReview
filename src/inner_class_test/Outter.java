package inner_class_test;

public class Outter {
	private class Inner {

        private final static int x=1;   // OK

        /* compile errors for below declaration
        * "The field x cannot be declared static in a non-static inner type, 
        * unless initialized with a constant expression" */
//        final static Inner a = new Inner();     // Error  
//
//        static Inner a1=new Inner();     // Error  
//
//        static int y;     // Error  
    }
}
