package kopo.poly.service;

import kopo.poly.dto.CommentDTO;
import kopo.poly.dto.PictureDTO;

import java.util.ArrayList;
import java.util.List;

public interface ICommentService {
    PictureDTO pictureWriteReply(CommentDTO to);

    PictureDTO pictureWriteReReply(CommentDTO to);

    ArrayList<CommentDTO> replyList(CommentDTO to);

    PictureDTO pictureDeleteReply(CommentDTO to);

    PictureDTO pictureDeleteReReply(CommentDTO to);

    PictureDTO profile_pictureWriteReply(CommentDTO to);

    void pictureModifyReply(CommentDTO to);
}
