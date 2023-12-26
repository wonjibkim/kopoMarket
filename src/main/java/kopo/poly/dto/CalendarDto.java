package kopo.poly.dto;

import lombok.Data;

@Data
public class CalendarDto {
    private int calendarSeq;
    private String groupId;
    private String title;
    private String writer; // 사용자 아이디
    private String content;
    private String start;
    private String end;
    private boolean allDay;
    private String textColor;
    private String backgroundColor;
    private String borderColor;

    private String userId; // 병원 번호

}
