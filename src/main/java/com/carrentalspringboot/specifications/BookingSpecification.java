package com.carrentalspringboot.specifications;

import com.carrentalspringboot.model.Booking;
import com.carrentalspringboot.model.User;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

@Builder
public class BookingSpecification implements Specification<Booking> {

    private final User user;

    @Override
    public Predicate toPredicate(Root<Booking> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate myPredicate = criteriaBuilder.equal(root.get("user").get("id"), user.getId());

        return myPredicate ;

    }


}

