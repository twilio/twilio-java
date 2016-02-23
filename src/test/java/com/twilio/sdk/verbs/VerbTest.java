//package com.twilio.sdk.verbs;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.fail;
//
//public class VerbTest {
//
//    @Test
//    public void testAppend() throws TwiMLException {
//        Verb verb = new Verb(Verb.V_BODY, "Body");
//        verb.allowedVerbs = new ArrayList<>(1);
//        verb.allowedVerbs.add(Verb.V_BODY);
//
//        Verb newVerb = new Verb(Verb.V_BODY, "Body 2");
//        Verb appended = verb.append(newVerb);
//        assertNotNull(appended);
//        assertEquals(newVerb, verb.getChildren()
//                                  .get(0));
//    }
//
//    @Test(expected = TwiMLException.class)
//    public void testAppendNoAllowedVerbs() throws TwiMLException {
//        Verb verb = new Verb(Verb.V_BODY, "Body");
//
//        Verb newVerb = new Verb(Verb.V_BODY, "Body 2");
//        verb.append(newVerb);
//
//        fail();
//    }
//
//    @Test(expected = TwiMLException.class)
//    public void testAppendWrongVerbs() throws TwiMLException {
//        Verb verb = new Verb(Verb.V_BODY, "Body");
//        verb.allowedVerbs = new ArrayList<>(0);
//
//        Verb newVerb = new Verb(Verb.V_BODY, "Body 2");
//        verb.append(newVerb);
//
//        fail();
//    }
//
//    @Test
//    public void testAsURL() throws TwiMLException {
//        Verb verb = new Verb(Verb.V_BODY, "Body<>");
//        verb.allowedVerbs = new ArrayList<>(1);
//        verb.allowedVerbs.add(Verb.V_BODY);
//        verb.set("Key", "value");
//
//        Verb newVerb = new Verb(Verb.V_BODY, "Body 2");
//        verb.append(newVerb);
//
//        String url = verb.asURL();
//        assertNotNull(url);
//        assertEquals("%3CBody+Key%3D%22value%22%3EBody%3C%3E%3CBody%3EBody+2%3C%2FBody%3E%3C%2FBody%3E", url);
//    }
//
//    @Test
//    public void testToEscapedXML() throws TwiMLException {
//        Verb verb = new Verb(Verb.V_BODY, "Body<>");
//        verb.allowedVerbs = new ArrayList<>(1);
//        verb.allowedVerbs.add(Verb.V_BODY);
//        verb.set("Key", "value");
//
//        Verb newVerb = new Verb(Verb.V_BODY, "Body 2");
//        verb.append(newVerb);
//
//        String result = verb.toEscapedXML();
//        assertNotNull(result);
//        assertEquals("<Body Key=\"value\">Body&lt;&gt;<Body>Body 2</Body></Body>", result);
//    }
//
//    @Test
//    public void testToXML() throws TwiMLException {
//        Verb verb = new Verb(Verb.V_BODY, "Body<>");
//        verb.allowedVerbs = new ArrayList<>(1);
//        verb.allowedVerbs.add(Verb.V_BODY);
//        verb.set("Key", "value");
//
//        Verb newVerb = new Verb(Verb.V_BODY, "Body 2");
//        verb.append(newVerb);
//
//        String result = verb.toXML();
//        assertNotNull(result);
//        assertEquals("<Body Key=\"value\">Body<><Body>Body 2</Body></Body>", result);
//    }
//
//}
