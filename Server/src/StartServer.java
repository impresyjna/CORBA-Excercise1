/**
 * Created by impresyjna on 24.11.2016.
 */
import CalculatorApp.*;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;

public class StartServer {

    public static void main(String args[]) {
        try{
            // create and initialize the ORB //// get reference to rootpoa &amp; activate the POAManager
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // create servant and register it with the ORB
            CalculatorObj calculatorObj = new CalculatorObj();
            calculatorObj.setORB(orb);

            // get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(calculatorObj);
            Calculator href = CalculatorHelper.narrow(ref);

            org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent path[] = ncRef.to_name( "ABC" );
            ncRef.rebind(path, href);

            System.out.println("Calculator Server ready and waiting ...");

            // wait for invocations from clients
            for (;;){
                orb.run();
            }
        }

        catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("HelloServer Exiting ...");

    }
}
