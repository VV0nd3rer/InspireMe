package com.kverchi.service;

import java.util.List;

import com.kverchi.domain.User;
import com.kverchi.tools.Pair;

public interface FriendService {
      public void addFriend(int userId, int friendId);
      public List<User> getUserFriends(int userId, int status);
      public List<Pair<User, Integer>> getPeopleList(int userId, String fragment);
      public List<User> sortRequests(List<User> requestList, int userId, int sortParam);
      public void removeFriend(int userId, int friendId, String referer);
      public void acceptFriend(int userId, int friendId);
}
