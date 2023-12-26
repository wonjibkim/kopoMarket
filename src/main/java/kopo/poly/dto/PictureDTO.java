package kopo.poly.dto;

import lombok.Data;


@Data
public class PictureDTO {
    private String no;
    private String bno;	//	댓글이 속한 게시글 번호 (받아와야 하는 값)
    private int grp;	//	댓글 그룹 번호 (모댓글과 거기에 속한 대댓글은 같은 grp를 가짐)
    private int grps;	//	그룹 내 댓글 순서 (오래된글 ~ 최신글  오름차순)
    private int grpl;	//	그룹내 댓글 깊이(댓글인지 대댓글인지)
    private String writer;
    private String content;
    private String wdate;
    private int reply;
}
