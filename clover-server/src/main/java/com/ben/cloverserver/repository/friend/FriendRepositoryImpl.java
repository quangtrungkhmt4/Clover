package com.ben.cloverserver.repository.friend;

import com.ben.cloverserver.entity.SearchFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FriendRepositoryImpl implements FriendRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public FriendRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<SearchFriend> getAllFriendByUserId(String userId, int skip, int limit) {
        LookupOperation lookupOperation = Aggregation.lookup("user", "friend_id", "user_id", "info");
        MatchOperation matchOperation = Aggregation.match(new Criteria("user_id").in(userId));
        SkipOperation skipOperation = Aggregation.skip(skip);
        LimitOperation limitOperation = Aggregation.limit(limit);

        Aggregation aggregation
                = Aggregation.newAggregation(lookupOperation, matchOperation, limitOperation, skipOperation);

        AggregationResults<SearchFriend> results = mongoTemplate.aggregate(aggregation, "friend", SearchFriend.class);

        return results.getMappedResults();
    }

    @Override
    public List<String> getAllFriendIds(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(userId));
        query.fields().include("friend_id");
        List<SearchFriend> friends = mongoTemplate.find(query, SearchFriend.class, "friend");
        return friends.stream().map(SearchFriend::getFriendId).collect(Collectors.toList());
    }
}
