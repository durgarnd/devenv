package com.dds.intrumentation;

import java.lang.instrument.Instrumentation;

public class JavaAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Executing premain.........");
        inst.addTransformer(new ExecutionDurationTransformer());
    }
}
