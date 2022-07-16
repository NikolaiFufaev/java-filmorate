package ru.yandex.practicum.filmorate.storage.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.mapper.UserMapper;
import ru.yandex.practicum.filmorate.storage.user.dao.FriendStorageDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FriendDbStorage implements FriendStorageDao {
    private final JdbcTemplate jdbcTemplate;

    public static final String SQL_QUERY_UPDATE_STATUS = "UPDATE USER_FRIENDS SET friends_status = ? " +
            "WHERE user_id = ? AND friend_id = ?";
    public static final String SQL_QUERY_ADD_FRIEND = "INSERT INTO USER_FRIENDS (user_id , friend_id) VALUES (?, ?)";

    public static final String SQL_QUERY_GET_STATUSES = "SELECT friends_status FROM USER_FRIENDS " +
            "WHERE user_id = ? AND friend_id = ? OR user_id = ? AND friend_id = ?";
    public static final String SQL_QUERY_DELETE_FRIEND = "DELETE FROM USER_FRIENDS WHERE user_id = ? AND friend_id = ?";
    public static final String SQL_QUERY_FIND_FRIENDS = "SELECT * FROM USERS AS u " +
            "WHERE EXISTS (SELECT * FROM USER_FRIENDS WHERE user_id = ? AND friend_id = u.USER_ID)";
    public static final String SQL_QUERY_FIND_MUTUAL_FRIENDS = "SELECT * FROM USERS " +
            "WHERE EXISTS " +
            "(SELECT * FROM user_friends WHERE USER_FRIENDS.user_id = ? " +
            "AND USER_FRIENDS.friend_id = USERS.USER_ID) " +
            "AND EXISTS" +
            "(SELECT * FROM USER_FRIENDS WHERE user_friends.user_id = ? " +
            "AND USER_FRIENDS.friend_id = USERS.USER_ID)";

    @Override
    public void addFriend(Long id, Long friendId) {
        jdbcTemplate.update(SQL_QUERY_ADD_FRIEND, id, friendId);
        if (checkStatus(id, friendId)) {
            jdbcTemplate.update(SQL_QUERY_UPDATE_STATUS, true, id, friendId);
            jdbcTemplate.update(SQL_QUERY_UPDATE_STATUS, true, friendId, id);
        }
    }

    private boolean checkStatus(Long userId, Long friendId) {
        List<Boolean> statuses = jdbcTemplate.query(SQL_QUERY_GET_STATUSES,
               (rs, rowNum) -> rs.getBoolean("friends_status")
                , userId, friendId, friendId, userId);
        return statuses.size() != 2;
    }


    @Override
    public void deleteFriend(Long id, Long friendId) {
        jdbcTemplate.update(SQL_QUERY_DELETE_FRIEND, id, friendId);
        jdbcTemplate.update(SQL_QUERY_UPDATE_STATUS, false, friendId, id);
    }

    @Override
    public List<User> findFriends(Long id) {
        return jdbcTemplate.query(SQL_QUERY_FIND_FRIENDS, new UserMapper(), id);
    }

    @Override
    public List<User> findMutualFriends(Long id, Long otherId) {
        return jdbcTemplate.query(SQL_QUERY_FIND_MUTUAL_FRIENDS, new UserMapper(), id, otherId);
    }
}