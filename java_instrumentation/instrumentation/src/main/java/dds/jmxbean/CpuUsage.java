package dds.jmxbean;

public class CpuUsage extends Thread{

    @Override
    public void run() {
        System.out.println("Infinite Process run started");
                   try {
                Thread.sleep(100);
                JobThreadlocal.getTh();
                JobThreadlocal.setEnd(true);
                JobThreadlocal.removeEndTh();
            } catch (InterruptedException e) {
                e.printStackTrace();

        }
    }

    public static void main(String ...as) throws Exception{
    while(true) {

        CpuUsage t = new CpuUsage();
        t.start();
        Thread.sleep(1000);
    }
/*
        while (true) {

            OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

            System.out.println("Process CPU Usage:" + osBean.getProcessCpuLoad());
            System.out.println("System CPU Usage:" + osBean.getSystemCpuLoad());
            Thread.sleep(2000);
                   }*/
    }
}

class JobThreadlocal {

    private static ThreadLocal thcheckEnd = new ThreadLocal() {
        @Override
        protected synchronized Object initialValue()
        {
            System.out.println("Thread Local Object initialized "+ thcheckEnd.hashCode() +" :"+Thread.currentThread());
            return Boolean.valueOf(false);
        }

    };

    protected static Boolean getTh() {
        return (Boolean) thcheckEnd.get();
    }

    public static boolean isEnd() {

        return getTh().booleanValue();
    }

    public static void removeEndTh()

    {
        System.out.println("Thread Local Object remove "+ thcheckEnd);
        thcheckEnd.remove();
    }

    public static void setEnd(boolean isend)
    {
        System.out.println("Thread Local Object setend "+ thcheckEnd);
        setTh(isend);
    }

    protected static void setTh(boolean isend) {
        thcheckEnd.set(Boolean.valueOf(isend));
    }

    private JobThreadlocal() {
        super();
    }

}

