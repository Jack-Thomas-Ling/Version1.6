package service.impl;
import dao.*;
import model.*;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ForumService;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ForumServiceI implements ForumService {
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private showTopicDao showTopicDao;

    public void addTopic(Topic topic) {
        topicMapper.insertSelective(topic);
        Board board = boardMapper.selectByPrimaryKey(topic.getBoardId());
        board.setTopicNum(board.getTopicNum() + 1);
        boardMapper.updateByPrimaryKeySelective(board);
    }

    public void removeTopic(int TopicId) {
        Topic topic = topicMapper.selectByPrimaryKey(TopicId);
        //删除相关回复贴
        topicMapper.deleteByPrimaryKey(TopicId);
        List<Post> postList = postMapper.selectByTopicId(TopicId);
        for (Post p:
             postList) {
            postMapper.deleteByPrimaryKey(p.getPostId());
        }
        //论坛板块主题帖子数-1
        Board board = boardMapper.selectByPrimaryKey(topic.getBoardId());
        board.setTopicNum(board.getTopicNum() - 1);
        boardMapper.updateByPrimaryKeySelective(board);
        //扣50积分
        User user = userMapper.selectByPrimaryKey(topic.getUserId());
        user.setCredit(user.getCredit() - 50 >= 0 ? user.getCredit() - 50 : 0);
        userMapper.updateByPrimaryKey(user);
    }

    public void addPost(Post post) {
        //插入回复信息
        post.setCreateTime(new Timestamp(System.currentTimeMillis()));
        postMapper.insertSelective(post);
        //积分+5
        User user = userMapper.selectByPrimaryKey(post.getUserId());
        user.setCredit(user.getCredit() + 5);
        userMapper.updateByPrimaryKeySelective(user);
        //主题回复数+1，更新回复时间
        Topic topic = topicMapper.selectByPrimaryKey(post.getTopicId());
        topic.setTopicReplies(topic.getTopicReplies() + 1);
        topic.setLastPost(new Timestamp(System.currentTimeMillis()));
        topicMapper.updateByPrimaryKeySelective(topic);
    }

    public void setStarTopic(int TopicId) {
        Topic topic = topicMapper.selectByPrimaryKey(TopicId);
        topic.setDigest(Topic.IS_DISGEST);
        topicMapper.updateByPrimaryKeySelective(topic);
    }

    public List<Topic> getNewerForum() {
        return topicMapper.selectOrderBydatetime();
    }

    public List<Topic> getHotForum() {
        return topicMapper.selectOrderByReply();
    }

    public Topic getSelectTopic(int TopicId) {
        return topicMapper.selectByPrimaryKey(TopicId);
    }

    public List<Post> getAllPostReplyByTopicId(int TopicId) {
        List<Post> list = postMapper.selectByTopicId(TopicId);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPostType() == Post.TYPE_MAIN) {
                list.remove(i);
            }
        }
        for (Post p :
                list) {
            p.setPostTitle(userMapper.selectByPrimaryKey(p.getUserId()).getUserName());

        }
        return list;
    }

    public void addViewed(int TopicId) {
        Topic topic = topicMapper.selectByPrimaryKey(TopicId);
        topic.setTopicViews(topic.getTopicViews() + 1);
        topicMapper.updateByPrimaryKeySelective(topic);
    }

    public Post getHostTopicInnerText(int topicId, int userId) {
        return postMapper.getHostTopicInnerText(topicId, userId);
    }
    //删除回复贴,主题回复数-1，删除post
    public void deletePost(int PostId) {
        Post post = postMapper.selectByPrimaryKey(PostId);
        Topic topic = topicMapper.selectByPrimaryKey(post.getTopicId());
        topic.setTopicReplies(topic.getTopicReplies() - 1);
        topicMapper.updateByPrimaryKeySelective(topic);
        postMapper.deleteByPrimaryKey(PostId);
    }

    public List<Board> getAllboard() {
        return boardMapper.getAllBoard();
    }

    public int selectLastInsert() {
        return topicMapper.selectLastInsertId();
    }

    @Override
    public List<BoardRe> getBoardMap() {
        return boardMapper.getBoardMap();
    }

    @Override
    public List<Data> getBoardData(int boardId) {
        return boardMapper.getboardData(boardId);
    }

    @Override
    public List<Topic> selectByuserId(int userId) {
        return topicMapper.selectByuserId(userId);
    }


    //显示所有topics
    public List<Topic>  getAllTopics(){
        return showTopicDao.getAllTopics();
    }

    //根据帖子id加精帖子
    public void jiajing(int id) {
        topicMapper.jiajing(id);

    }


    //根据贴子id取消加精
    public void canceljiajing(int id) {
        topicMapper.canceljiajing(id);
    }


    //根据帖子id置顶帖子
    public void top(int id) {
        topicMapper.top(id);
    }

    //根据帖子id取消置顶帖子
    public void canceltop(int id) {
        topicMapper.canceltop(id);
    }
}
