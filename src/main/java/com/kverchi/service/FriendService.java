package com.kverchi.service;

import java.util.List;

import com.kverchi.domain.Friend;
import com.kverchi.domain.User;

public interface FriendService {
      public void addFriend(int userId, int friendId);
      public List<User> getUserFriends(int userId, int status);
      public void removeFriend(int userId, int friendId, String referer);
      public void acceptFriend(int userId, int friendId);
}
