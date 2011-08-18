/**
 * 
 */
package com.twilio.stashboard.sdk;

import junit.framework.Assert;

import org.junit.Test;

import com.twilio.stashboard.sdk.TwilioServiceStatusReadRestClient;
import com.twilio.stashboard.sdk.resource.instance.Service;
import com.twilio.stashboard.sdk.resource.instance.Services;
import com.twilio.stashboard.sdk.resource.instance.Status;
import com.twilio.stashboard.sdk.resource.instance.Statuses;


/**
 * Test Class for Testing Twilio Services Status Client
 * Created Aug 15, 2011 9:09:14 PM
 * @author sdakara
 */
public class TwilioServicesTest{
  
  @Test
  public void testTwilioServices(){
		TwilioServiceStatusReadRestClient client = new TwilioServiceStatusReadRestClient();

    Services services = client.getServices();
    Assert.assertNotNull(services);
    for (Service service : services) {
      System.out.println("Service Id: "+service.getId());
      System.out.println("Service Name: "+service.getName());
      System.out.println("Service Description: "+service.getDescription());
      System.out.println("************    Service Current Status Level: "+service.getCurrentEvent().getStatus().getLevel()+"   ***********");
      System.out.println("===================================================================================");
    }
    Statuses statuses = new Statuses(client);
    Assert.assertNotNull(statuses);
    for (Status status : statuses) {
      System.out.println("Status Id: "+status.getId());
      System.out.println("Status Name: "+status.getName());
      System.out.println("Status Description: "+status.getDescription());
      System.out.println("===================================================================================");
    }
  }
}
