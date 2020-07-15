package com.mason.game.db.base;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by mwu on 2020/6/30
 */
public class EntityWrapperKey {

    Serializable pk;

    Class<?> clazz;

    public EntityWrapperKey(Serializable pk, Class<?> clazz) {
        this.pk = pk;
        this.clazz = clazz;
    }

    public EntityWrapperKey(EntityWrapper<?> value) {
        this.pk = value.getPrimaryKey();
        this.clazz = value.getClass();
    }

    @Override
    public String toString() {
        return "EntityWrapperKey{pk=" + pk + ", clazz=" + clazz + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityWrapperKey that = (EntityWrapperKey) o;
        return Objects.equals(pk, that.pk) &&
                Objects.equals(clazz, that.clazz);
    }

    @Override
    public int hashCode() {
        int result = pk.hashCode();
        result = 31 * result + clazz.hashCode();
        return result;
    }
}
