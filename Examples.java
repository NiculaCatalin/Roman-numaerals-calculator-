
/**
 * Class for testing some basic overloading and overriding examples.
 * 
 */
public class Examples {


    public static void main(String[] args) {
        System.out.println("\nInitialization:\n");
        A a = new A("hi");
        System.out.println();
        A a2 = new B("hi");
        System.out.println();
        B b = new C("hi");
        System.out.println();
        B b2 = new B("hi");
        System.out.println();
        C c = new C(""); 
        System.out.println();

        System.out.println("\nOverrding:\n");
        a.f(1);
        a2.f(2);
        b.f(3);
        b2.f(4);
        a.f(3); 
        b.f(1);
        // you can add your own examples too :)
        
        System.out.println("\nOverloading:\n");
        new Examples().test(a);
        new Examples().test(a2);
        new Examples().test(b);
        new Examples().test(b2);
        new Examples().test(c);
    }
    
    public void test(A a) {
        System.out.println("Testing A");
        a.f(10);
    }
    
    public void test(B b) {
        System.out.println("Testing B");
        b.f(10);
    }
    
    public void test(C c) {
        System.out.println("Testing C");
        c.f(10);
    }
}

class A {

    A(){}

    A(String s) {
        System.out.println("Constructor A, s = " + s + " this = " + this);
    }

    public void f(int x) {
        System.out.println("In A, x = " + x + " this = " + this);
    }
}

class B extends A {

    B(){}
    
    B(String s) {
        super(s);
        System.out.println("Constructor B, s = " + s + " this = " + this);
    }


    public void f(int x) {
        System.out.println("In B, x = " + x + " this = " + this);
    } 
    
    public void f(int x, int y) {
        System.out.println("In B, x = " + x + " y = " + y + " this = " + this);
    }

    public void g(String s) {
        System.out.println("In B, s = " + s + " this = " + this);
    }

    public int g(int s) {
        System.out.println("In B, s = " + s + " this = " + this);
        return 0;
    }
}

class C extends B {
   
    C (String s) {
        System.out.println("Constructor C, s = " + s + " this = " + this);
    }

    public void f() {
        super.f(1);
    } 
}

