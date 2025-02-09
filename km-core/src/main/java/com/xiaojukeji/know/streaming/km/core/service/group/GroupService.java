package com.xiaojukeji.know.streaming.km.core.service.group;

import com.xiaojukeji.know.streaming.km.common.bean.dto.pagination.PaginationBaseDTO;
import com.xiaojukeji.know.streaming.km.common.bean.entity.group.Group;
import com.xiaojukeji.know.streaming.km.common.bean.entity.result.PaginationResult;
import com.xiaojukeji.know.streaming.km.common.bean.entity.result.Result;
import com.xiaojukeji.know.streaming.km.common.bean.po.group.GroupMemberPO;
import com.xiaojukeji.know.streaming.km.common.enums.group.GroupStateEnum;
import com.xiaojukeji.know.streaming.km.common.exception.AdminOperateException;
import com.xiaojukeji.know.streaming.km.common.exception.NotExistException;
import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.common.TopicPartition;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface GroupService {
    /**
     * 从Kafka中获取消费组名称列表
     */
    List<String> listGroupsFromKafka(Long clusterPhyId) throws NotExistException, AdminOperateException;

    /**
     * 从Kafka中获取消费组详细信息
     */
    Group getGroupFromKafka(Long clusterPhyId, String groupName) throws NotExistException, AdminOperateException;

    Map<TopicPartition, Long> getGroupOffsetFromKafka(Long clusterPhyId, String groupName) throws NotExistException, AdminOperateException;

    ConsumerGroupDescription getGroupDescriptionFromKafka(Long clusterPhyId, String groupName) throws NotExistException, AdminOperateException;

    Result<Void> resetGroupOffsets(Long clusterPhyId, String groupName, Map<TopicPartition, Long> offsetMap, String operator) throws NotExistException, AdminOperateException;

    /**
     * 批量更新DB
     */
    void batchReplaceGroupsAndMembers(Long clusterPhyId, List<Group> newGroupList, long updateTime);

    int deleteByUpdateTimeBeforeInDB(Long clusterPhyId, Date beforeTime);

    /**
     * DB-Group相关接口
     */
    GroupStateEnum getGroupStateFromDB(Long clusterPhyId, String groupName);

    Group getGroupFromDB(Long clusterPhyId, String groupName);

    List<Group> listClusterGroups(Long clusterPhyId);

    List<String> getGroupsFromDB(Long clusterPhyId);

    Integer calGroupCount(Long clusterPhyId);

    Integer calGroupStatCount(Long clusterPhyId, GroupStateEnum stateEnum);

    /**
     * DB-GroupTopic相关接口
     */
    List<GroupMemberPO> listGroupByTopic(Long clusterPhyId, String topicName);

    PaginationResult<GroupMemberPO> pagingGroupMembers(Long clusterPhyId,
                                                       String topicName,
                                                       String groupName,
                                                       String searchTopicKeyword,
                                                       String searchGroupKeyword,
                                                       PaginationBaseDTO dto);

    GroupMemberPO getGroupTopicFromDB(Long clusterPhyId, String groupName, String topicName);
}