package com.example.user_service.data.userDTO;

import java.util.List;

public class UserPaginatedDTO<T> {
    private Boolean status = true;
    private List<T> data;
    private int page;
    private int limit;
    private long totalRecords;
    private int totalPages;

    public UserPaginatedDTO(List<T> data, int page, int limit, long totalRecords, int totalPages) {
        this.data = data;
        this.page = page;
        this.limit = limit;
        this.totalRecords = totalRecords;
        this.totalPages = totalPages;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
