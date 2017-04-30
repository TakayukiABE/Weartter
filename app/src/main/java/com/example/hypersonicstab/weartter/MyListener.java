package com.example.hypersonicstab.weartter;

import android.util.Log;

import java.util.Map;

import twitter4j.AccountSettings;
import twitter4j.Category;
import twitter4j.DirectMessage;
import twitter4j.Friendship;
import twitter4j.IDs;
import twitter4j.Location;
import twitter4j.OEmbed;
import twitter4j.PagableResponseList;
import twitter4j.Place;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Relationship;
import twitter4j.ResponseList;
import twitter4j.SavedSearch;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.TwitterAPIConfiguration;
import twitter4j.TwitterException;
import twitter4j.TwitterListener;
import twitter4j.TwitterMethod;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.api.HelpResources;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuth2Token;
import twitter4j.auth.RequestToken;

/**
 * Created by hypersonicstab on 2017/04/30.
 */

public class MyListener implements TwitterListener {

    MainActivity mainActivity;

    public  MyListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void gotMentions(ResponseList<Status> responseList) {

    }

    @Override
    public void gotHomeTimeline(ResponseList<Status> responseList) {
        mainActivity.callback(responseList);
        Log.d("called CALLBACK", "");
    }

    @Override
    public void gotUserTimeline(ResponseList<Status> responseList) {

    }

    @Override
    public void gotRetweetsOfMe(ResponseList<Status> responseList) {

    }

    @Override
    public void gotRetweets(ResponseList<Status> responseList) {

    }

    @Override
    public void gotShowStatus(Status status) {

    }

    @Override
    public void destroyedStatus(Status status) {

    }

    @Override
    public void updatedStatus(Status status) {

    }

    @Override
    public void retweetedStatus(Status status) {

    }

    @Override
    public void gotOEmbed(OEmbed oEmbed) {

    }

    @Override
    public void lookedup(ResponseList<Status> responseList) {

    }

    @Override
    public void searched(QueryResult queryResult) {

    }

    @Override
    public void gotDirectMessages(ResponseList<DirectMessage> responseList) {

    }

    @Override
    public void gotSentDirectMessages(ResponseList<DirectMessage> responseList) {

    }

    @Override
    public void gotDirectMessage(DirectMessage directMessage) {

    }

    @Override
    public void destroyedDirectMessage(DirectMessage directMessage) {

    }

    @Override
    public void sentDirectMessage(DirectMessage directMessage) {

    }

    @Override
    public void gotFriendsIDs(IDs iDs) {

    }

    @Override
    public void gotFollowersIDs(IDs iDs) {

    }

    @Override
    public void lookedUpFriendships(ResponseList<Friendship> responseList) {

    }

    @Override
    public void gotIncomingFriendships(IDs iDs) {

    }

    @Override
    public void gotOutgoingFriendships(IDs iDs) {

    }

    @Override
    public void createdFriendship(User user) {

    }

    @Override
    public void destroyedFriendship(User user) {

    }

    @Override
    public void updatedFriendship(Relationship relationship) {

    }

    @Override
    public void gotShowFriendship(Relationship relationship) {

    }

    @Override
    public void gotFriendsList(PagableResponseList<User> pagableResponseList) {

    }

    @Override
    public void gotFollowersList(PagableResponseList<User> pagableResponseList) {

    }

    @Override
    public void gotAccountSettings(AccountSettings accountSettings) {

    }

    @Override
    public void verifiedCredentials(User user) {

    }

    @Override
    public void updatedAccountSettings(AccountSettings accountSettings) {

    }

    @Override
    public void updatedProfile(User user) {

    }

    @Override
    public void updatedProfileBackgroundImage(User user) {

    }

    @Override
    public void updatedProfileColors(User user) {

    }

    @Override
    public void updatedProfileImage(User user) {

    }

    @Override
    public void gotBlocksList(ResponseList<User> responseList) {

    }

    @Override
    public void gotBlockIDs(IDs iDs) {

    }

    @Override
    public void createdBlock(User user) {

    }

    @Override
    public void destroyedBlock(User user) {

    }

    @Override
    public void lookedupUsers(ResponseList<User> responseList) {

    }

    @Override
    public void gotUserDetail(User user) {

    }

    @Override
    public void searchedUser(ResponseList<User> responseList) {

    }

    @Override
    public void gotContributees(ResponseList<User> responseList) {

    }

    @Override
    public void gotContributors(ResponseList<User> responseList) {

    }

    @Override
    public void removedProfileBanner() {

    }

    @Override
    public void updatedProfileBanner() {

    }

    @Override
    public void gotMutesList(ResponseList<User> responseList) {

    }

    @Override
    public void gotMuteIDs(IDs iDs) {

    }

    @Override
    public void createdMute(User user) {

    }

    @Override
    public void destroyedMute(User user) {

    }

    @Override
    public void gotUserSuggestions(ResponseList<User> responseList) {

    }

    @Override
    public void gotSuggestedUserCategories(ResponseList<Category> responseList) {

    }

    @Override
    public void gotMemberSuggestions(ResponseList<User> responseList) {

    }

    @Override
    public void gotFavorites(ResponseList<Status> responseList) {

    }

    @Override
    public void createdFavorite(Status status) {

    }

    @Override
    public void destroyedFavorite(Status status) {

    }

    @Override
    public void gotUserLists(ResponseList<UserList> responseList) {

    }

    @Override
    public void gotUserListStatuses(ResponseList<Status> responseList) {

    }

    @Override
    public void destroyedUserListMember(UserList userList) {

    }

    @Override
    public void gotUserListMemberships(PagableResponseList<UserList> pagableResponseList) {

    }

    @Override
    public void gotUserListSubscribers(PagableResponseList<User> pagableResponseList) {

    }

    @Override
    public void subscribedUserList(UserList userList) {

    }

    @Override
    public void checkedUserListSubscription(User user) {

    }

    @Override
    public void unsubscribedUserList(UserList userList) {

    }

    @Override
    public void createdUserListMembers(UserList userList) {

    }

    @Override
    public void checkedUserListMembership(User user) {

    }

    @Override
    public void createdUserListMember(UserList userList) {

    }

    @Override
    public void destroyedUserList(UserList userList) {

    }

    @Override
    public void updatedUserList(UserList userList) {

    }

    @Override
    public void createdUserList(UserList userList) {

    }

    @Override
    public void gotShowUserList(UserList userList) {

    }

    @Override
    public void gotUserListSubscriptions(PagableResponseList<UserList> pagableResponseList) {

    }

    @Override
    public void gotUserListMembers(PagableResponseList<User> pagableResponseList) {

    }

    @Override
    public void gotSavedSearches(ResponseList<SavedSearch> responseList) {

    }

    @Override
    public void gotSavedSearch(SavedSearch savedSearch) {

    }

    @Override
    public void createdSavedSearch(SavedSearch savedSearch) {

    }

    @Override
    public void destroyedSavedSearch(SavedSearch savedSearch) {

    }

    @Override
    public void gotGeoDetails(Place place) {

    }

    @Override
    public void gotReverseGeoCode(ResponseList<Place> responseList) {

    }

    @Override
    public void searchedPlaces(ResponseList<Place> responseList) {

    }

    @Override
    public void gotSimilarPlaces(ResponseList<Place> responseList) {

    }

    @Override
    public void gotPlaceTrends(Trends trends) {

    }

    @Override
    public void gotAvailableTrends(ResponseList<Location> responseList) {

    }

    @Override
    public void gotClosestTrends(ResponseList<Location> responseList) {

    }

    @Override
    public void reportedSpam(User user) {

    }

    @Override
    public void gotOAuthRequestToken(RequestToken requestToken) {

    }

    @Override
    public void gotOAuthAccessToken(AccessToken accessToken) {

    }

    @Override
    public void gotOAuth2Token(OAuth2Token oAuth2Token) {

    }

    @Override
    public void gotAPIConfiguration(TwitterAPIConfiguration twitterAPIConfiguration) {

    }

    @Override
    public void gotLanguages(ResponseList<HelpResources.Language> responseList) {

    }

    @Override
    public void gotPrivacyPolicy(String s) {

    }

    @Override
    public void gotTermsOfService(String s) {

    }

    @Override
    public void gotRateLimitStatus(Map<String, RateLimitStatus> map) {

    }

    @Override
    public void onException(TwitterException e, TwitterMethod twitterMethod) {

    }
}
