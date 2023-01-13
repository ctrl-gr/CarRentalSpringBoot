package com.carrentalspringboot.specifications;

import com.carrentalspringboot.model.Booking;
import com.carrentalspringboot.model.Car;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
public class CanMoveCarSpecification implements Specification<Car> {

    private final int carId;

    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Subquery<Booking> bookingSubquery = query.subquery(Booking.class);
        Root<Booking> bookingRoot = bookingSubquery.from(Booking.class);

        bookingSubquery
                .select(bookingRoot.get("car").get("id"))
                .where(isInBookedCars(bookingRoot, criteriaBuilder));

        return criteriaBuilder.not(criteriaBuilder.in(root.get("id")).value(bookingSubquery));

    }

    private Predicate isInBookedCars(Root<Booking> bookingRoot, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        LocalDate today = LocalDate.now();

        predicateList.add(criteriaBuilder.equal(bookingRoot.get("car"), carId));
        predicateList.add(criteriaBuilder.greaterThanOrEqualTo(bookingRoot.get("end_date"), today));

        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }

}
