package com.bonc.dxbrgrmp.pojo.qo;

/**
 * Created by liuxiaoyang on 16/12/9.
 */
public class PageQo {

    // 默认分页查询
    protected boolean paged = true;
    protected Integer pageSize = 20;
    protected Integer pageNum = 1;
    protected Integer offset = 0;

    public boolean isPaged() {
        return paged;
    }

    public void setPaged(boolean paged) {
        this.paged = paged;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum <= 1){
            this.pageNum = 1;
        }else{
            this.pageNum = pageNum;
        }
    }

    public Integer getOffset() {
        offset = (pageNum - 1) * pageSize;
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "PageQo{" +
                "paged=" + paged +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", offset=" + offset +
                '}';
    }
}
