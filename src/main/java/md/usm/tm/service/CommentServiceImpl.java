package md.usm.tm.service;

import md.usm.tm.dao.CommentDaoImpl;
import md.usm.tm.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pcovaliov on 11/4/2016.
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
