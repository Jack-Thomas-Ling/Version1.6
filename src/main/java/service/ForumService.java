package service;

import model.*;
import net.sf.jsqlparser.statement.select.Top;

import java.util.List;

public interface ForumService {
    //发表主题,积分加10，板块主题帖子加1
    void addTopic(Topic topic);
    //板块主题帖子减一，扣50积分，删除帖子和其回复帖子
    void removeTopic(int TopicId);
    //用户积分加5，帖子回复数加1，更新最后回复时间
    void addPost(Post post);
    //设置精华帖子
    void setStarTopic(int TopicId);

    List<Topic> getNewerForum();

    List<Topic> getHotForum();

    Topic getSelectTopic(int TopicId);

    List<Post> getAllPostReplyByTopicId(int TopicId);

    void addViewed(int TopicId);

    Post getHostTopicInnerText(int topicId, int userId);

    void deletePost(int PostId);

    List<Board> getAllboard();

    int selectLastInsert();

    List<BoardRe> getBoardMap();
    //返回整合Data的board对象
    List<Data> getBoardData(int boardId);

    List<Topic> selectByuserId(int userId);


    //显示所有的帖子 不需要参数，返回所有的帖子及其信息；
    List<Topic> getAllTopics();


    //根据帖子id加精帖子
    void jiajing(int id);

    //根据贴子id取消加精
    void canceljiajing(int id);

    //根据帖子id置顶帖子
    void top(int id);

    //根据帖子id取消置顶帖子
    void canceltop(int id);
}
