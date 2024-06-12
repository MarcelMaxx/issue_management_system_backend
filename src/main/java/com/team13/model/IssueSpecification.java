package com.team13.model;

import org.springframework.data.jpa.domain.Specification;

public class IssueSpecification {

    public static Specification<Issue> hasPriority(String priority) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("priority"), priority);
    }

    public static Specification<Issue> hasStatus(String status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Issue> hasUserId(Long userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.equal(root.get("reporterId"), userId),
                criteriaBuilder.equal(root.get("assigneeId"), userId),
                criteriaBuilder.equal(root.get("fixerId"), userId),
                criteriaBuilder.equal(root.get("fixedId"), userId)
        );
    }
}
