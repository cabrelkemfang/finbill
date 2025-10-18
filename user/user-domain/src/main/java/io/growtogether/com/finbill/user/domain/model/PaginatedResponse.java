package io.growtogether.com.finbill.user.domain.model;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record PaginatedResponse<T>(List<T> content,
                                   long totalElements,
                                   int totalPages,
                                   int currentPage,
                                   int pageSize) implements Serializable {
}
