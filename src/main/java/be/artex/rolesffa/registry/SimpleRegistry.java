package be.artex.rolesffa.registry;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Simple <b>thread safe</b> implementation of a registry.
 *
 * @param <T> type of values contained in the registry.
 *
 * Based upon AtlasWorld Cardinal Registries system.
 */
public class SimpleRegistry<T> implements Registry<T> {
    protected final String key;

    protected final BiMap<String, T> entries;
    protected final AtomicBoolean finalized;
    protected final ReentrantReadWriteLock lock;

    /**
     * Constructs an instance of the {@code SimpleRegistry} with a specified key.
     *
     * @param key the key associated with this registry; must not be null.
     *
     * @throws NullPointerException if {@code key} is null.
     */
    public SimpleRegistry(String key) {
        this(key, HashBiMap.create());
    }

    /**
     * Constructs an instance of the {@code SimpleRegistry} with a specified key, map of entries.
     *
     * @param key the key associated with this registry; must not be null.
     * @param map the {@link BiMap} containing the registry entries; must not be null.
     *
     * @throws NullPointerException if {@code key} or {@code map} is null.
     */
    protected SimpleRegistry(String key, BiMap<String, T> map) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(map);

        this.key = key;

        this.entries = map;
        this.finalized = new AtomicBoolean(false);
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public String registryKey() {
        return this.key;
    }

    @Override
    public RegistryObject<T> register(String key, T value) {
        if (this.finalized.get())
            throw new IllegalStateException("Cannot register entries when registry is finalized!");

        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);

        this.lock.writeLock().lock();
        try {
            Preconditions.checkArgument(!this.entries.containsKey(key), "An entry is already registered with this key: %s", key);
            Preconditions.checkArgument(!this.entries.containsValue(value), "This value has already been registered.");

            this.entries.put(key, value);
        } finally {
            this.lock.writeLock().unlock();
        }

        return new RegistryObject<>(key, value);
    }

    @Override
    public void freezeRegistry() {
        if (this.finalized.get())
            throw new IllegalStateException("Registry has already been finalized.");

        this.finalized.set(true);
    }

    @Override
    public final boolean finalized() {
        return this.finalized.get();
    }

    @Override
    public boolean containsKey(String key) {
        return this.executeRead(() -> this.entries.containsKey(key));
    }

    @Override
    public boolean containsValue(T value) {
        return this.executeRead(() -> this.entries.containsValue(value));
    }

    @Override
    public boolean isEmpty() {
        return this.executeRead(this.entries::isEmpty);
    }

    @Override
    public Optional<T> retrieveValue(String key) {
        return this.executeRead(() -> Optional.ofNullable(this.entries.get(key)));
    }

    @Override
    public Optional<String> retrieveKey(T value) {
        return this.executeRead(() -> Optional.ofNullable(this.entries.inverse().get(value)));
    }

    @Override
    public Set<T> values() {
        return this.executeRead(this.entries::values);
    }

    @Override
    public Set<String> keys() {
        return this.executeRead(this.entries::keySet);
    }

    @Override
    public Set<Map.Entry<String, T>> entries() {
        return this.executeRead(this.entries::entrySet);
    }

    @Override
    public void clear() {
        if (this.finalized.get())
            throw new IllegalStateException("Cannot clear entries when registry is finalized!");

        this.lock.writeLock().lock();
        try {
            this.entries.clear();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    protected final <E> E executeRead(Supplier<E> supplier) {
        this.lock.readLock().lock();
        try {
            return supplier.get();
        } finally {
            this.lock.readLock().unlock();
        }
    }
}
