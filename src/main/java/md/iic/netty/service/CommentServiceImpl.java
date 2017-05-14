package md.iic.netty.service;

import md.iic.netty.dao.CommentDaoImpl;
import md.iic.netty.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by acusnir on 11/4/2016.
 */
@Service
@Transactional
public class CommentServiceImpl {

    @Autowired
    private CommentDaoImpl commentDao;

    public Comment addComment(Comment comment){
       return commentDao.addComment(comment);
    }
}
