package edu.unicesumar.rest.infrastructure;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface CustomQuerydslBinderCustomizer<T extends EntityPath<?>> extends QuerydslBinderCustomizer<T> {

    @Override
    default void customize(QuerydslBindings querydslBindings, T entityPath) {
        querydslBindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value));
    }

}
