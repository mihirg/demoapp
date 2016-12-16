package in.gore.jmx.impl;

import in.gore.jmx.api.TestMXBean;

/**
 * Created by mihirgore on 15/12/16.
 */
public class TestMBeanImpl implements TestMXBean {

    private int cacheSize;

    public TestMBeanImpl() {
        cacheSize = 10;
    }
    @Override
    public int getCacheSize() {
        // return a random value
        return new Integer(String.valueOf(Math.random()*10));
    }

    @Override
    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }
}
