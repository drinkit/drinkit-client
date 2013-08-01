package ua.kiev.naiv.drinkit.cocktail.common;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 02.08.13
 * Time: 1:22
 */
public class CustomGenerator extends ObjectIdGenerator {
    @Override
    public Class<?> getScope() {
        throw new IllegalStateException("Not implemented yet"); //TODO Not implemented
    }

    @Override
    public boolean canUseFor(ObjectIdGenerator<?> gen) {
        throw new IllegalStateException("Not implemented yet"); //TODO Not implemented
    }

    @Override
    public ObjectIdGenerator forScope(Class<?> scope) {
        throw new IllegalStateException("Not implemented yet"); //TODO Not implemented
    }

    @Override
    public ObjectIdGenerator newForSerialization(Object context) {
        throw new IllegalStateException("Not implemented yet"); //TODO Not implemented
    }

    @Override
    public IdKey key(Object key) {
        throw new IllegalStateException("Not implemented yet"); //TODO Not implemented
    }

    @Override
    public Object generateId(Object forPojo) {
        throw new IllegalStateException("Not implemented yet"); //TODO Not implemented
    }
}
