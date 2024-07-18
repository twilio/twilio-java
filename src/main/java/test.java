import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.monitor.v1.Alert;

public class test {
    public static void main(String[] args) {
        Twilio.init("AC8d360ef83f8fe480d2793bd631d8bd23f", "ad27baa92de98ce21a873d606fd36c");
        ResourceSet<Alert> alerts = Alert.reader().limit(5).read();

        System.out.println("Before first loop:");
        for(Alert record : alerts) {
            System.out.println("     First loop - " + record.getSid());
        }

        System.out.println("Before second loop:");
        for(Alert record : alerts) {
            System.out.println("     Second loop - " + record.getSid());
        }
        System.out.println("After second loop");
    }
}