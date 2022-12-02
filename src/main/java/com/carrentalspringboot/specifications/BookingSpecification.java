package com.carrentalspringboot.specifications;

import com.carrentalspringboot.model.Booking;
import com.carrentalspringboot.model.User;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Builder
public class BookingSpecification implements Specification<Booking> {

    private final User user;

    @Override
    public Predicate toPredicate(Root<Booking> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate myPredicate = criteriaBuilder.equal(root.get("user").get("id"), user.getId());

        return myPredicate;

    }


}

