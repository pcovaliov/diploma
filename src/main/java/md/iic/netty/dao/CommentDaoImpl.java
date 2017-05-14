package md.iic.netty.dao;

import md.iic.netty.model.Comment;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CommentDaoImpl {

    private static final Logger logger = RootLogger.getLogger(CommentDaoImpl.class);

    @Autowired
    SessionFactory sessionFactory;

    public Comment addComment(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(comment);
        logger.info("comment added!");
        return comment;
    }
}
