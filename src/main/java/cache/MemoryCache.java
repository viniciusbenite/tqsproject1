package cache;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MemoryCache<Object> {

    private final Map<String, Object> objects;
    private final Map<String, Long> expire;
    private final long defaultExpire;
    private final ExecutorService threads;

    public MemoryCache() {
        this(100);
    }

    public MemoryCache(final long defaultExpire) {
        this.objects = Collections.synchronizedMap(new HashMap<String, Object>());
        this.expire = Collections.synchronizedMap(new HashMap<String, Long>());
        this.defaultExpire = defaultExpire;
        this.threads = Executors.newFixedThreadPool(256);
        Executors.newScheduledThreadPool(2).scheduleWithFixedDelay(this.removeExpired(), this.defaultExpire / 2, this.defaultExpire, TimeUnit.SECONDS);
    }

    private Runnable removeExpired() {
        return new Runnable() {
            public void run() {
                for (final String name : expire.keySet()) {
                    if (System.currentTimeMillis() > expire.get(name)) {
                        threads.execute(createRemoveRunnable(name));
                    }
                }
            }
        };
    }

    private Runnable createRemoveRunnable(final String name) {
        return new Runnable() {
            public void run() {
                objects.remove(name);
                expire.remove(name);
            }
        };
    }

    public long getExpire() {
        return this.defaultExpire;
    }

    public void put(final String name, final Object obj) {
        this.put(name, obj, this.defaultExpire);
    }

    public void put(final String name, final Object obj, final long expireTime) {
        this.objects.put(name, obj);
        this.expire.put(name, System.currentTimeMillis() + expireTime * 1000);
    }

    public Object get(final String name) {
        final Long expireTime = this.expire.get(name);
        if (expireTime == null) return null;
            if (System.currentTimeMillis() > expireTime) {
                this.threads.execute(this.createRemoveRunnable(name));
                return null;
            }
        return Optional.ofNullable(this.objects.get(name)).orElse(null);
    }

    public Set<String> getAll() {
        return this.objects.keySet();
    }

    public void delete(final String name, final Object object) {
        this.objects.remove(name, object);
    }

    @SuppressWarnings("unchecked")
    public <R extends Object> R get(final String name, final Class<R> type) {
        return (R) this.get(name);
    }
}
