package org.blog.vo;
import java.util.List;

public class PageVo<T> {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer pageCount;
    private Integer dateCount;
    private List<T> list;

    @Override
    public String toString() {
        return "PageVo{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", dateCount=" + dateCount +
                ", list=" + list +
                '}';
    }

    public PageVo() {
    }

    public PageVo(Integer pageIndex, Integer pageSize, Integer pageCount, Integer dateCount, List<T> list) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.dateCount = dateCount;
        this.list = list;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getDateCount() {
        return dateCount;
    }

    public void setDateCount(Integer dateCount) {
        this.dateCount = dateCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
