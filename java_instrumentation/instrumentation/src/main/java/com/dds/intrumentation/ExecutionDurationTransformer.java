package com.dds.intrumentation;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ExecutionDurationTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        byte[] byteCode = classfileBuffer;

        // since this transformer will be called when all the classes are
        // loaded by the classloader, we are restricting the instrumentation
        // using if block only for the Lion class
       // System.out.println("ClassNAme:"+className);
        if (className.equals("com/dds/sample/TestApp")) {
            System.out.println("Instrumenting......");
            try {
                ClassPool classPool = ClassPool.getDefault();
                CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(
                        classfileBuffer));
                CtMethod[] methods = ctClass.getDeclaredMethods();
                for (CtMethod method : methods) {
                    method.addLocalVariable("startTime", CtClass.longType);
                    method.insertBefore("startTime = System.nanoTime();");
                    method.insertAfter("System.out.println(\"Execution Duration for method[ "+method.getName()
                            + "(nano sec): \"+ (System.nanoTime() - startTime) );");
                }
                byteCode = ctClass.toBytecode();
                ctClass.detach();
                System.out.println("Instrumentation complete.");
            } catch (Throwable ex) {
                System.out.println("Exception: " + ex);
                ex.printStackTrace();
            }
        }
        return byteCode;
          }
}
