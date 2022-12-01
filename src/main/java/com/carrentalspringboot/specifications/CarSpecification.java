package com.carrentalspringboot.specifications;

import com.carrentalspringboot.model.Booking;
import com.carrentalspringboot.model.Car;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
public class CarSpecification implements Specification<Car> {

    private final LocalDate startDate;
    private final LocalDate endDate;

    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Subquery<Booking> bookingSubquery = query.subquery(Booking.class);
        Root<Booking> bookingRoot = bookingSubquery.from(Booking.class);

        bookingSubquery
                .select(bookingRoot.get("car").get("id"))
                .where(getBookedByIntervalPredicate(bookingRoot, criteriaBuilder));

        return criteriaBuilder.not(criteriaBuilder.in(root.get("id")).value(bookingSubquery));
    }

    private Predicate getBookedByIntervalPredicate(Root<Booking> bookingRoot, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();

        predicateList.add(criteriaBuilder.lessThanOrEqualTo(bookingRoot.get("startDate"), endDate));
        predicateList.add(criteriaBuilder.greaterThanOrEqualTo(bookingRoot.get("endDate"), startDate));

        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }

}
