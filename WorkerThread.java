package Week9;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkerThread extends Thread {    	      
    private Map<String, Integer> map = null;

    public WorkerThread(Map<String, Integer> map) {
          this.map = map;     
    }

    public void run() {                
          for (int i=0; i<500000; i++) {
                 // Return 2 random integers
                 Integer newInteger1 = (int) Math.ceil(Math.random()*10000);
                 Integer newInteger2 = (int) Math.ceil(Math.random()*10000);                                           
                 // 1. Attempt to retrieve a random Integer element
                 map.get(String.valueOf(newInteger1));                       
                 // 2. Attempt to insert a random Integer element
                 map.put(String.valueOf(newInteger2), newInteger2);                
          }
    }
}
class CrunchifyConcurrentHashMapVsSynchronizedMap {
    public static Map<String, Integer> crunchifySynchronizedMapObject = null;
    public static Map<String, Integer> crunchifyConcurrentHashMapObject = null;

    public static void main(String[] args) throws InterruptedException {
        // Test with synchronizedMap Object
        long currentime1 = System.currentTimeMillis();
        crunchifySynchronizedMapObject = Collections.synchronizedMap(new HashMap<String, Integer>());
        new WorkerThread(crunchifySynchronizedMapObject);
        long timepast1 = System.currentTimeMillis()-currentime1;

        // Test with ConcurrentHashMap Object
        long currentime2 = System.currentTimeMillis();
        crunchifyConcurrentHashMapObject = new ConcurrentHashMap<String, Integer>();
        new WorkerThread(crunchifyConcurrentHashMapObject);
        long timepast2 = System.currentTimeMillis()-currentime2;

        System.out.println("SyncMap: " + timepast1);
        System.out.println("ConcurrentMap: " + timepast2);

    }
}