import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
/**
 *  args[0] time in minutes example 2
 *  args[1] limit of avgCPULoad example 1.2
 *  args[2] limit of free memory in mb example 500
 *
* */

public class Checker {
    public static void main(String[] args) throws InterruptedException {
        final OperatingSystemMXBean operatingSystem = ManagementFactory.getOperatingSystemMXBean();
        double avgCpuLoad = 0;
        double maxAvgCPULoad=0;
        long freeMemory = 0;
        long minFreeMem=20000;
        int oneMinute = 0;
        while (oneMinute < Integer.parseInt(args[0])) {
            Thread.sleep(60000);
            avgCpuLoad = operatingSystem.getSystemLoadAverage();
            freeMemory = ((com.sun.management.OperatingSystemMXBean) ManagementFactory
                    .getOperatingSystemMXBean()).getFreePhysicalMemorySize()/1024/1024;
            if (avgCpuLoad > Double.parseDouble(args[1])) {
                System.out.println("cpu current="+avgCpuLoad+" cpu limit="+args[1]);
                return;
            }
            if (freeMemory < Integer.parseInt(args[2])){
                System.out.println("free memory current="+freeMemory+" free memory limit="+args[2]);
                return;
            }
            if(avgCpuLoad>maxAvgCPULoad){
                maxAvgCPULoad=avgCpuLoad;
            }
            if (freeMemory<minFreeMem){
                minFreeMem=freeMemory;
            }
            oneMinute++;
        }
        System.out.println("ok"+ "\nmaxCPU="+maxAvgCPULoad+"\nminFreeMem="+minFreeMem);
    }
}

