/**
 * Created with IntelliJ IDEA.
 * User: Constantine Solovev
 * Date: 21.01.15
 * Time: 10:44
 */

//'use strict';


    function staticMethod() {

        var String = Java.type('java.lang.String');

        return String.format('hello %s', 'world');

    }















    function useJavaClasses() {

        var ArrayList = Java.type('java.util.ArrayList');
        var list = new ArrayList();

        list.add(1);
        list.add('два');

        return list;

    }









    function myMethod(param) {
        return 'myMethod return this ' + param;
    }














//        MyJavaClass.fun2(123);
////        // class java.lang.Integer
////
//        MyJavaClass.fun2(49.99);
////        // class java.lang.Double
////
//        MyJavaClass.fun2(true);
////        // class java.lang.Boolean
////
//        MyJavaClass.fun2("hi there");
////        // class java.lang.String
////
//        MyJavaClass.fun2(new Number(23));
////        // class jdk.nashorn.internal.objects.NativeNumber
////
//        MyJavaClass.fun2(new Date());
////        // class jdk.nashorn.internal.objects.NativeDate
////
//        MyJavaClass.fun2(new RegExp());
////        // class jdk.nashorn.internal.objects.NativeRegExp
////
//        MyJavaClass.fun2({foo: 'bar'});
////        // class jdk.nashorn.internal.scripts.JO4






        /* Java types in javaScript */
        function javaTypes() {


           var intArray = Java.type("int[]");
           var array = new intArray(5);
           array[0] = 1;

           var javaIntArray = Java.to([3, 5, 7, 11], "int[]");


           var arrayList1 = Java.type('java.util.ArrayList');
           // or
           var arrayList2 = new java.util.ArrayList();

            importClass(java.util.HashSet);
            var set = new HashSet();

            importPackage(java.util);
            var list = new ArrayList();


            // better

            var CollectionsAndFiles = new JavaImporter(
                java.util,
                java.io,
                java.nio);

            with (CollectionsAndFiles) {   // не работает в strict mode
                var files = new LinkedHashSet();
                files.add(new File("Plop"));
                files.add(new File("Foo"));
                files.add(new File("w00t.js"));
            }

            // impl java classes

            var iterator1 = new java.util.Iterator({
                i: 0,
                hasNext: function() {
                    return this.i < 10;
                },
                next: function() {
                    return this.i++;
                }
            });

            // or

            var Iterator = Java.type('java.util.Iterator');
            var iterator2 = Java.extend(Iterator, {
                i: 0,
                hasNext: function() {
                    return this.i < 10;
                },
                next: function() {
                    return this.i++;
                }
            });

           arrayList2.add(__FILE__);
           arrayList2.add(__LINE__);
           arrayList2.add(__DIR__);

           var jsArray = Java.from(arrayList2);  //js native array

           return jsArray;

       }















        /* Java 8 stream API */

        function streams() {

            var list2 = new java.util.ArrayList();

            list2.add("ddd2");
            list2.add("aaa2");
            list2.add("bbb1");
            list2.add("aaa1");
            list2.add("bbb3");
            list2.add("ccc");
            list2.add("bbb2");
            list2.add("ddd1");


            return list2
                .parallelStream()
                .filter(function(el) {
                    return el.startsWith("aaa");
                })
                .sorted()
                .collect(Java.type('java.util.stream.Collectors').toList());

        }











    function multiThreading() {

        var Runnable = Java.type('java.lang.Runnable');
        var Thread = Java.type('java.lang.Thread');

        var RunnableImpl = Java.extend(Runnable, {
            run: function() {
                while(true) {
                    Thread.sleep(1000);
                    print('RunnableImpl ******************');
                }
            }
        });

        var thread1 = new Thread(new RunnableImpl());

        var thread2 = new Thread(function() {
            while (true) {
                Thread.sleep(3000);
                print('***********  Thread 2');
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }



