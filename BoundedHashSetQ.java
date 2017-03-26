package Week9;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundedHashSetQ<T> {	
	private final Set<T> set;
    private final Semaphore semaphore;
	
	public BoundedHashSetQ (int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		semaphore = new Semaphore(bound);
	}
	
	public boolean add(T o) throws InterruptedException {
        semaphore.acquire();
        boolean added = false;
        try {
            added = set.add(o);
            return added;
        }catch (Exception e){
            System.out.println("Error when adding");
        }
        if (!added){
            semaphore.release();
        }
		return added;
	}
	
	public boolean remove (Object o) {
        boolean removed = false;
        try {
            removed = set.remove(o);
            return removed;
        }catch (Exception e){
            System.out.println("Error when removing");
        }
        if (removed){
            semaphore.release();
        }
        return removed;
    }
}