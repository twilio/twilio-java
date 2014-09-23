package com.twilio.examples;

import com.twilio.sdk.ivr.Back;
import com.twilio.sdk.ivr.Dial;
import com.twilio.sdk.ivr.Menu;
import com.twilio.sdk.ivr.Say;

import java.util.HashMap;
import java.util.Map;

public class IVR {

    public static void main(String[] args) {
        Menu menu = new Menu("main", "http://www.example.com/handleCall");
        Menu legalMenu = new Menu("legal");
        Menu processMenu = new Menu("process");

        menu.register("1", "to Call Sue Johnson", new Dial("+14155551234"));
        menu.register("2", "to Call Bill Watersford", new Dial("+14155557890"));
        menu.register("3", "for the legal department", legalMenu);

        legalMenu.setPreRoll("In compliance with Fairness in Legal Proceedings Act (FLePA) this call will be recorded");
        legalMenu.register("1", "to get information about a claim", new Dial("+14155550987"));
        legalMenu.register("2", "to submit a new claim", new Dial("+14155557638"));
        legalMenu.register("3", "for required process information", processMenu);
        legalMenu.register("*", "to go back to the main menu", new Back());

        processMenu.register("1", "to hear the Claim Process", new Say("There is a claim process, it's terrible, you will hate going through it"));
        processMenu.register("2", "to hear the Standard Disclaimer", new Say("We are not legally responsible if you get eaten by a bear"));
        processMenu.register("3", "to go back to legal menu", new Back());

        Map<String, String> context = new HashMap<String, String>();
        String result = menu.execute(context);
        System.out.println("With no Context");
        System.out.println(result);
        System.out.println("-------------------------------------------------");


        context = new HashMap<String, String>();
        context.put("Digits", "1");
        result = menu.execute(context);
        System.out.println("If the user presses 1");
        System.out.println(result);
        System.out.println("-------------------------------------------------");

        context = new HashMap<String, String>();
        context.put("Digits", "2");
        result = menu.execute(context);
        System.out.println("If the user presses 2");
        System.out.println(result);
        System.out.println("-------------------------------------------------");

        context = new HashMap<String, String>();
        context.put("Digits", "3");
        result = menu.execute(context);
        System.out.println("If the user presses 3");
        System.out.println(result);
        System.out.println("-------------------------------------------------");

        context = new HashMap<String, String>();
        context.put("Digits", "4");
        result = menu.execute(context);
        System.out.println("If the user presses 4 (invalid key)");
        System.out.println(result);
        System.out.println("-------------------------------------------------");

        context = new HashMap<String, String>();
        context.put(Menu.CONTEXT_PREFIX + "main", "3");
        context.put("Digits", "1");
        result = menu.execute(context);
        System.out.println("If the user presses 3 and then 1");
        System.out.println(result);
        System.out.println("-------------------------------------------------");

        context = new HashMap<String, String>();
        context.put(Menu.CONTEXT_PREFIX + "main", "3");
        context.put("Digits", "*");
        result = menu.execute(context);
        System.out.println("If the user presses 3 and then *");
        System.out.println(result);
        System.out.println("-------------------------------------------------");

        context = new HashMap<String, String>();
        context.put("UnrelatedKey", "UnrelatedValue");
        context.put(Menu.CONTEXT_PREFIX + "main", "3");
        context.put("Digits", "3");
        result = menu.execute(context);
        System.out.println("If the user presses 3 and then 3");
        System.out.println(result);
        System.out.println("-------------------------------------------------");

        context = new HashMap<String, String>();
        context.put(Menu.CONTEXT_PREFIX + "main", "3");
        context.put(Menu.CONTEXT_PREFIX + "main.legal", "3");
        context.put("Digits", "1");
        result = menu.execute(context);
        System.out.println("If the user presses 3 and then 3 and then 1");
        System.out.println(result);
        System.out.println("-------------------------------------------------");

        context = new HashMap<String, String>();
        context.put(Menu.CONTEXT_PREFIX + "main", "3");
        context.put(Menu.CONTEXT_PREFIX + "main.legal", "3");
        context.put("Digits", "3");
        result = menu.execute(context);
        System.out.println("If the user presses 3 and then 3 and then 3");
        System.out.println(result);
        System.out.println("-------------------------------------------------");
    }
}
