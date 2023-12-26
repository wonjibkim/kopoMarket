package kopo.poly.persistance.mapper;

import ch.qos.logback.classic.pattern.ClassNameOnlyAbbreviator;
import kopo.poly.dto.CommentDTO;
import kopo.poly.dto.PictureDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ICommentMapper {
    int picture_reply_write(CommentDTO to);

    int picture_reply_check(CommentDTO to);

    void picture_reply_delete_after_rereply_delete(CommentDTO to);

    int picture_rereply_write(CommentDTO to);

    void picture_reply_up(PictureDTO to);

    void picture_reply_modify(CommentDTO to);

    ArrayList<CommentDTO> picutre_replyList(CommentDTO to);

    PictureDTO picture_reply_count(PictureDTO to);

    int picture_count_rereply(CommentDTO to);

    int picture_count_rereply_fromrereply(CommentDTO to);

    int picture_reply_delete(CommentDTO to);

    int picture_reply_not_delete(CommentDTO to);

    void picture_reply_down(PictureDTO to);

    int p_reply_max_no();







}
