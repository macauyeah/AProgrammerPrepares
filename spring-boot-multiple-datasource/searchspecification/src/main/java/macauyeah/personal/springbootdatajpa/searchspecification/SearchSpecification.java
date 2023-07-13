package macauyeah.personal.springbootdatajpa.searchspecification;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification {
    private static Logger LOG = LoggerFactory.getLogger(SearchSpecification.class);

    public static <E, S> Specification<E> deepSearchAllFields(Class<E> rootEntityType, S searchRequest) {
        return (root, query, cb) -> {
            return deepSearch(cb, root, null, searchRequest);
        };
    }

    private static Predicate equalSearch(
            CriteriaBuilder cb,
            Path<?> path, Object fieldValue) {
        if (fieldValue.getClass().equals(String.class)) {
            return cb.like(path.as(String.class), "%" + fieldValue + "%");
        } else {
            return cb.equal(path.as(fieldValue.getClass()), fieldValue);
        }
    }

    private static Predicate foreignKeyInSearch(
            CriteriaBuilder cb,
            Path<?> path,
            ForeignKeyInFilter request) {
        return path.get("id").as(BigInteger.class).in(request.getIn());
    }

    private static <S> List<Field> getAllFields(Class<S> searchRequestType) {
        Field[] fieldArray = searchRequestType.getDeclaredFields();
        List<Field> fields = new ArrayList<>();
        if (fieldArray != null && fieldArray.length > 0) {
            fields.addAll(Arrays.asList(fieldArray));
        }
        if (!searchRequestType.equals(Object.class)) {
            fields.addAll(getAllFields(searchRequestType.getSuperclass()));
        }
        return fields;
    }

    private static Predicate deepSearch(CriteriaBuilder cb, Root<?> root, Join<?, ?> joinObj,
            Object searchRequest) {
        Predicate predicate = cb.and();
        List<Field> fields = getAllFields(searchRequest.getClass());
        Path<?> path;
        if (joinObj != null) {
            path = joinObj;
        } else {
            path = root;
        }
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object fieldValue = field.get(searchRequest);
                if (fieldValue == null) {
                    continue;
                }
                Class<?> fieldType = field.getType();
                if (fieldValue instanceof JoinSearchRequest) { // only case to extend the path with join
                    Join<?, ?> joinResult;
                    if (joinObj != null) {
                        joinResult = joinObj.join(field.getName());
                    } else {
                        joinResult = root.join(field.getName());
                    }
                    predicate = cb.and(predicate,
                            deepSearch(cb, null, joinResult, fieldValue));
                } else if (isSupportedEqualType(fieldType)) {
                    predicate = cb.and(predicate,
                            equalSearch(cb, path.get(field.getName()), fieldValue));
                } else if (fieldValue instanceof ForeignKeyInFilter) {
                    predicate = cb.and(predicate,
                            foreignKeyInSearch(cb, path.get(field.getName()),
                                    (ForeignKeyInFilter) fieldValue));
                } else if (fieldValue instanceof OperatorFilter) {
                    predicate = cb.and(predicate,
                            operatorSearch(cb, path.get(field.getName()), (OperatorFilter<?>) fieldValue));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                LOG.warn(e.getMessage());
                e.printStackTrace();
            }
        }
        return predicate;
    }

    private static boolean isSupportedEqualType(Class<?> fieldType) {
        return fieldType.equals(String.class) ||
                fieldType.equals(Date.class) ||
                fieldType.equals(Boolean.class) ||
                fieldType.equals(Integer.class);
    }

    private static <X extends Comparable<? super X>> Predicate operatorSearch(
            CriteriaBuilder cb,
            Path<?> path, OperatorFilter<X> fieldValue) {
        if (fieldValue.getIsNull() != null && fieldValue.getIsNull().booleanValue() == true) {
            return cb.isNull(path);
        } else if (fieldValue.getBetween() != null
                && fieldValue.getBetween().getLowerBound() != null
                && fieldValue.getBetween().getUpperBound() != null) {
            @SuppressWarnings("unchecked")
            Class<X> dataType = (Class<X>) fieldValue.getBetween().getLowerBound().getClass();
            X lowerBound = fieldValue.getBetween().getLowerBound();
            X upperBound = fieldValue.getBetween().getUpperBound();
            return cb.between(path.as(dataType), lowerBound, upperBound);
        } else if (fieldValue.getEqualTo() != null) {
            @SuppressWarnings("unchecked")
            Class<X> dataType = (Class<X>) fieldValue.getEqualTo().getClass();
            return cb.equal(path.as(dataType), fieldValue.getEqualTo());
        } else if (fieldValue.getLike() != null) {
            return cb.like(path.as(String.class), "%" + fieldValue.getLike() + "%");
        } else if (fieldValue.getGreaterThanOrEqualTo() != null) {
            @SuppressWarnings("unchecked")
            Class<X> dataType = (Class<X>) fieldValue.getGreaterThanOrEqualTo().getClass();
            return cb.greaterThanOrEqualTo(path.as(dataType),
                    fieldValue.getGreaterThanOrEqualTo());
        } else if (fieldValue.getLessThanOrEqualTo() != null) {
            @SuppressWarnings("unchecked")
            Class<X> dataType = (Class<X>) fieldValue.getLessThanOrEqualTo().getClass();
            return cb.lessThanOrEqualTo(path.as(dataType),
                    fieldValue.getLessThanOrEqualTo());
        } else if (fieldValue.getIn() != null
                && fieldValue.getIn().size() > 0) {
            @SuppressWarnings("unchecked")
            Class<X> dataType = (Class<X>) fieldValue.getIn().get(0).getClass();
            return path.as(dataType).in(fieldValue.getIn());
        } else {
            throw new RuntimeException("no valid operator in:" + fieldValue.toString());
        }
    }
}
