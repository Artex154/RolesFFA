package be.artex.rolesffa.registry;

import com.google.common.base.Preconditions;

/**
 * Holds the instance of a registered object.
 *
 * @param <T> type of values hold in the registry.
 *
 * Based upon AtlasWorld Cardinal Registries system.
 */
public final class RegistryObject<T> {
    private final String key;
    private final T value;

    public RegistryObject(String key, T value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);

        this.key = key;
        this.value = value;
    }

    /**
     * Retrieve the key of the object.
     *
     * @return key of the object.
     */
    public String key() {
        return this.key;
    }

    /**
     * Retrieve the value of the object.
     *
     * @return value of the object.
     */
    public T value() {
        return this.value;
    }
}