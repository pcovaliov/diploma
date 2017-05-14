package md.usm.tm.service;

import md.usm.tm.dao.LikeDaoImpl;
import md.usm.tm.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mbezaliuc on 11/24/2016.
 */

@Service
@Transactional
public class LikeServiceImpl {

    @Autowired
    private LikeDaoImpl likeDao;

    public Like addLike(Like like) {
        return likeDao.addLike(like);
    }

    public boolean thisTweetIsLikedByUser(int tweet_id, int user_id) {
        return likeDao.thisTweetIsLikedByUser(tweet_id, user_id);
    }
}
