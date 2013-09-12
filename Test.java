import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.list.MediaList;
import com.twilio.sdk.verbs.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @[* @since 9/12/13 10:43 AM] author kelvin: */
public class FuncTest {

    public static void main(String[] args) throws TwilioRestException {

        final TwilioRestClient trc = new TwilioRestClient("AC648d937704b94309822578b85ff1227f", "50719b44799bc584c77207152365be37");

        List<NameValuePair> list = new ArrayList<NameValuePair>();

        list.add(new BasicNameValuePair("From", "+14156399102"));
        list.add(new BasicNameValuePair("To", "+14156399102"));
        list.add(new BasicNameValuePair("MediaUrl", "https://static0.twilio.com/packages/foundation/img/twilio-logo-50x50.png"));
        list.add(new BasicNameValuePair("MediaUrl", "https://static0.twilio.com/packages/foundation/img/twilio-logo-50x50.png"));

        final MediaList media = trc.getAccount().getMessage("MMc2bac7dd21f04d6da1f8b0d89c43d730").getMedia();

        System.out.println(media);
    }
}
