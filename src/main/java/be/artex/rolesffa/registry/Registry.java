package be.artex.rolesffa.registry;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * The Registry holds all references to registered entries.
 * <p>
 * When initialized, the registry is not finalized,
 * and you are still able to register entries to it.
 * <br>
 * In multithreaded environments,
 * finalizing the registry allows multiple threads to read concurrently without added latency of writing to the registry.
 *
 * @param <T> type of values contained in the registry.
 *
 * Based upon AtlasWorld Cardinal Registries system.
 */
public interface Registry<T> {

    /**
     * Unique key of the registry.
     *
     * @return unique key of the registry.
     */
    String registryKey();

    /**
     * Register a new value to this registry.
     *
     * @param key unique key of the value.
     * @param value the value.
     *
     * @throws IllegalArgumentException if a value was already registered with the specified key,
     *                                  or that the same instance of the value is already registered.
     * @throws IllegalStateException if the registry has been finalized.
     */
    RegistryObject<T> register(String key, T value);

    /**
     * Close the registry, this will make the registry immutable.
     * <br>
     * You won't be able to register entries after this call.
     *
     * @throws IllegalStateException if the registry has already been finalized, or that the registry doesn't allow freezing.
     */
    void freezeRegistry();

    /**
     * Checks whether this registry finalized. (aka immutable)
     *
     * @return true if this registry has been finalized, false otherwise.
     */
    boolean finalized();

    /**
     * Checks whether this registry contains the specified key.
     *
     * @param key key to check for.
     *
     * @return true if this registry contains the key, false otherwise.
     */
    boolean containsKey(String key);

    /**
     * Checks whether this registry contains the specified value.
     *
     * @param value value to check for.
     *
     * @return true if this registry contains the key, false otherwise.
     */
    boolean containsValue(T value);

    /**
     * Checks whether this registry is empty.
     *
     * @return true if this registry is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Retrieve the value from this registry.
     *
     * @param key key of the value.
     *
     * @return optional containing the value, empty optional if no value with the key is present.
     */
    Optional<T> retrieveValue(String key);

    /**
     * Retrieve the key from this registry.
     *
     * @param value value attached to the key to look for.
     *
     * @return optional containing the key, empty optional if no key could be found with the value.
     */
    Optional<String> retrieveKey(T value);

    /**
     * Retrieve all values in this registry.
     *
     * @return an <b>immutable</b> set of all values in this registry.
     */
    Set<T> values();

    /**
     * Retrieve all keys in this registry.
     *
     * @return an <b>immutable</b> set of all keys in this registry.
     */
    Set<String> keys();

    /**
     * Retrieve all entries of this registry.
     *
     * @return an <b>immutable</b> set of all entries in this registry.
     */
    Set<Map.Entry<String, T>> entries();

    /**
     * Clear the registry, removing all entries.
     *
     * @throws IllegalStateException if the registry has been finalized.
     */
    void clear();
}
