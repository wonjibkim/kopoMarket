package kopo.poly.dto;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
    private int startPage;
    private int endPage;
    private boolean prev, next;

    private int total;
    //현재 페이지 번호, 한 페이지에 표출할 데이터 개수





    public boolean isPrev() {
        return prev;
    }
    public boolean isNext() {
        return next;
    }

}
