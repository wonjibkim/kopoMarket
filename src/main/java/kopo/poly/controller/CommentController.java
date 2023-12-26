package kopo.poly.controller;


import kopo.poly.dto.CommentDTO;
import kopo.poly.dto.PictureDTO;
import kopo.poly.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@Slf4j
public class CommentController {

	@Resource(name = "CommentService")
	private final ICommentService commentService;

	public CommentController(ICommentService commentService) {
		this.commentService = commentService;
	}

	// 모댓글 작성
	@RequestMapping(value = "/picture_write_reply")
	public PictureDTO write_reply(@RequestParam String bno, @RequestParam String content, HttpSession session) {

		log.info("댓글 등록 실행");
		CommentDTO cdto = new CommentDTO();


		cdto.setBno(bno);

		// 댓글 내용 세팅
		cdto.setContent(content);

		// 댓글작성자 nick을 writer로 세팅
		cdto.setWriter((String) session.getAttribute("email"));

		// +1된 댓글 갯수를 담아오기 위함
		PictureDTO pto = commentService.pictureWriteReply(cdto);
		log.info("댓글 등록 종료");

		return pto;
	}

	// 답글 작성
	@RequestMapping(value = "/picture_write_rereply")
	public PictureDTO write_rereply(@RequestParam String no, @RequestParam String bno, @RequestParam String content,
								   HttpSession session) {

		CommentDTO cdto = new CommentDTO();

		// 게시물 번호 세팅
		cdto.setBno(bno);

		// grp, grps, grpl 은 ReplyTO에 int로 정의되어 있기 때문에 String인 no를 int로 변환해서 넣어준다.
		// 모댓글 번호 no를 grp으로 세팅한다.
		cdto.setGrp(Integer.parseInt(no));

		// 답글은 깊이가 1이되어야 하므로 grpl을 1로 세팅한다.
		cdto.setGrpl(1);

		// 답글 내용 세팅
		cdto.setContent(content);

		// 답글작성자 nick을 writer로 세팅
		cdto.setWriter((String) session.getAttribute("nick"));

		// +1된 댓글 갯수를 담아오기 위함
		PictureDTO pto = commentService.pictureWriteReReply(cdto);

		return pto;
	}

	// 댓글 리스트
	@RequestMapping(value = "/picture_replyList")
	public ArrayList<CommentDTO> reply_list(@RequestParam String no, HttpSession session) {

		log.info("댓글 리스트 조회 시작");
		CommentDTO cdto = new CommentDTO();

		// 가져올 댓글 리스트의 게시물번호를 세팅
		cdto.setBno(no);

		ArrayList<CommentDTO> replyList = new ArrayList();

		replyList = commentService.replyList(cdto);
		log.info("댓글 리스트 조회 끝");
		return replyList;
	}

	// 모댓글 삭제
	@RequestMapping(value = "/picture_delete_reply")
	public PictureDTO picture_delete_reply(@RequestParam String no, @RequestParam String bno ) {

		CommentDTO cdto = new CommentDTO();

		// 모댓글 번호 세팅
		cdto.setNo(no);

		// 게시물 번호 세팅
		cdto.setBno(bno);

		// 갱신된 댓글 갯수를 담아오기 위함
		PictureDTO pto = commentService.pictureDeleteReply(cdto);

		return pto;
	}

	// 답글 삭제
	@RequestMapping(value = "/picture_delete_rereply")
	public PictureDTO delete_rereply(@RequestParam String no, @RequestParam String bno, @RequestParam int grp) {

		CommentDTO cdto = new CommentDTO();

		// 답글 번호 세팅 - 답글 삭제하기 위해서 필요함
		cdto.setNo(no);

		// 게시물 번호 세팅 - p_board 의 reply+1하기 위해 필요함
		cdto.setBno(bno);

		// grp 세팅(모댓글이 뭔지) - 모댓글은 삭제를 해도 답글이 있으면 남아있게 되는데 답글이 모두 삭제되었을 때 모댓글도 삭제하기 위해
		// 필요함
		cdto.setGrp(grp);

		// 갱신된 댓글 갯수를 담아오기 위함
		PictureDTO pto = commentService.pictureDeleteReReply(cdto);

		return pto;
	}

	// 모댓글 수정
	@RequestMapping(value = "/picture_modify_reply")
	public void modify_reply(@RequestParam String no, @RequestParam String content){
		CommentDTO cdto = new CommentDTO();

		cdto.setNo(no);

		cdto.setContent(content);

		commentService.pictureModifyReply(cdto);
	}
}
