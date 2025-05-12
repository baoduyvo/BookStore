package org.voduybao.bookstorebackend.tools.response.panigation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultPaginations<T> {
    private int total;
    private List<T> data;
    private Integer page;
    private Integer pageSize;

    public ResultPaginations(int total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public ResultPaginations(int total, List<T> data, Integer page, Integer pageSize) {
        this.total = total;
        this.data = data;
        this.page = page;
        this.pageSize = pageSize;
    }
}
