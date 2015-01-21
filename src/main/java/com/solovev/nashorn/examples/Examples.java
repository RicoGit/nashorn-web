package com.solovev.nashorn.examples;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.Assert;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Constantine Solovev
 * Created: 19.01.15  9:24
 */


public class Examples {

    /* Start */

    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");



    @Test
    public void eval()
            throws ScriptException {







        /* 1 */

        String result1 = (String) engine.eval(
                "(function() { " +
                "   return ['hello', ' world'].join('') " +
                "})()"
        );

        Assert.assertEquals("hello world", result1);








        /* 2 */

        engine.eval(
                "function hi(name) { " +
                "   return ['hello', ' ', name].join('') " +
                "}"
        );

        String result2 = (String) engine.eval("hi('world')");

        Assert.assertEquals("hello world", result2);





    }


               /* Invoking Javascript Functions from Java */






    @Test
    public void invokeFunction()
            throws
            FileNotFoundException,
            ScriptException,
            NoSuchMethodException {


        engine.eval( new FileReader("src/main/resources/scripts/examples/invokeFunction.js") );

        Invocable invocable = (Invocable) engine;

        String resulta = (String) invocable.invokeFunction("a");
        Assert.assertEquals("function a", resulta);


        String resultb = (String) invocable.invokeFunction("b", "Parker");
        Assert.assertEquals("function b params = Parker", resultb);

    }








    @Test   // ScriptObjectMirror
    public void invokeFunction2() throws ScriptException, NoSuchMethodException {

        engine.eval("load('src/main/resources/scripts/examples/invokeFunction.js')" );

        Invocable invocable = (Invocable) engine;

        List<String> list = new ArrayList<>();
        list.add("user");
        list.add("admin");

        ScriptObjectMirror resultc =
                (ScriptObjectMirror) invocable.invokeFunction("c", list);


        Assert.assertTrue(resultc.containsKey("name"));
        Assert.assertEquals("granted authority", resultc.get("name"));

        ArrayList<String> roles =  (ArrayList) resultc.get("roles");
        Assert.assertEquals("user", roles.get(0));
//        Assert.assertTrue(list == roles);

        // call function in result object
        Assert.assertEquals("someVar someParam", resultc.callMember("someFunc", "someParam"));


    }










    @Test              //todo as interface
    public void invokeFunction3() throws ScriptException, NoSuchMethodException {

        engine.eval(" load('src/main/resources/scripts/examples/invokeFunction.js')" );
        Invocable invocable = (Invocable) engine;

        List<String> list = new ArrayList<>();
        list.add("user");
        list.add("admin");

        ScriptObjectMirror resultc =
                (ScriptObjectMirror) invocable.invokeFunction("c", list);


        Assert.assertTrue(resultc.containsKey("name"));
        Assert.assertEquals("granted authority", resultc.get("name"));
        Assert.assertEquals("user", ((ArrayList) resultc.get("roles")).get(0));

    }










    /*   Invoking Java Methods from Javascript */


    @Test
    public void javaMethods1() throws ScriptException, NoSuchMethodException {

        engine.eval(" load('src/main/resources/scripts/examples/invokeJava.js')" );
        Invocable invocable = (Invocable) engine;

        Assert.assertEquals("hello world", invocable.invokeFunction("staticMethod"));

    }










    @Test
    public void javaMethods2() throws ScriptException, NoSuchMethodException {

        engine.eval(" load('src/main/resources/scripts/examples/invokeJava.js')" );
        Invocable invocable = (Invocable) engine;

        Assert.assertEquals("два", ((ArrayList<String>)invocable.invokeFunction("useJavaClasses")).get(1));

    }









    @Test
    public void javaTypes() throws ScriptException, NoSuchMethodException {

        engine.eval(" load('src/main/resources/scripts/examples/invokeJava.js')" );
        Invocable invocable = (Invocable) engine;

        invocable.invokeFunction("javaTypes");

    }







  @Test
    public void streams() throws ScriptException, NoSuchMethodException {

        engine.eval(" load('src/main/resources/scripts/examples/invokeJava.js')" );
        Invocable invocable = (Invocable) engine;

      ArrayList result = ((ArrayList<String>)invocable.invokeFunction("streams"));

      Assert.assertEquals("aaa1", result.get(0));
      Assert.assertEquals("aaa2", result.get(1));


    }


    @Test
    public void multiThreading() throws ScriptException, NoSuchMethodException, InterruptedException {

        engine.eval(" load('src/main/resources/scripts/examples/invokeJava.js')" );
        Invocable invocable = (Invocable) engine;

        invocable.invokeFunction("multiThreading");

    }

//    @Test
//    public void extendsJavaClasses() throws ScriptException, NoSuchMethodException, InterruptedException {
//
//        engine.eval(" load('src/main/resources/scripts/examples/invokeJava.js')" );
//        Invocable invocable = (Invocable) engine;
//
//        invocable.invokeFunction("extendsJavaClasses");
//
//    }
}




