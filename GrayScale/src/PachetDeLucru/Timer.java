package PachetDeLucru;

import java.util.concurrent.TimeUnit;

public class Timer  {
	 private long startTime;
	    private long stopTime;

	    public void start() {
	        startTime = System.nanoTime();
	    }

	    public void stop() {
	        stopTime = System.nanoTime();
	    }

	    public long getRuntime() {
	        return stopTime - startTime;
	    }

	    public long getRuntime(TimeUnit timeUnit) {
	        return timeUnit.convert(getRuntime(), TimeUnit.NANOSECONDS);
	    }

}
