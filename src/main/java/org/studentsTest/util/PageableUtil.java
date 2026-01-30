package org.studentsTest.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {
    public static Pageable buildPageable(Integer page, String sortColumn, Sort.Direction sortDirection) {
        Sort.Order order = new Sort.Order(sortDirection, sortColumn);
        int p = page != null ? page : 0;
        return PageRequest.of(p, 10, Sort.by(order));
    }
}
