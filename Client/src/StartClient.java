
import CalculatorApp.*;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;
import java.util.*;

public class StartClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef =   orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            Calculator calculatorObj = (Calculator) CalculatorHelper.narrow(ncRef.resolve_str("ABC"));

            System.out.println("Welcome to the calculator system:");

            int r=calculatorObj.add(5,5);
            System.out.println("The result for addition is : "+r);
            System.out.println("-----------------------------------");

            r=calculatorObj.divide(20,5);
            System.out.println("The result for divide is : "+r);
            System.out.println("-----------------------------------");

            r=calculatorObj.subtract(1,5);
            System.out.println("The result for addition is : "+r);
            System.out.println("-----------------------------------");

            r=calculatorObj.multiply(5,5);
            System.out.println("The result for addition is : "+r);
            System.out.println("-----------------------------------");
        }
        catch (Exception e) {
            System.out.println("Hello Client exception: " + e);
            e.printStackTrace();
        }

    }

}
