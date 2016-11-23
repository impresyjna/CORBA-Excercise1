import CalculatorApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;

class CalculatorObj extends CalculatorPOA {
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    // implement add() method
    public int add(int a, int b) {
        int r=a+b;
        return r;
    }

    @Override
    public int multiply(int a, int b) {
        int r=a*b;
        return r;
    }

    @Override
    public int subtract(int a, int b) {
        int r = a-b;
        return r;
    }

    @Override
    public int divide(int a, int b) {
        int r = a/b;
        return r;
    }

    // implement shutdown() method
    public void shutdown() {
        orb.shutdown(false);
    }
}
