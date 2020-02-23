public class Twitter {
    
    private static class User {
        
        private List<User> following;
        private List<Tweet> myTweets;
        
        public User() {
            following = new ArrayList<>();
            myTweets = new ArrayList<>();
        }
    }

    private static class Tweet {
        
        private static int COUNTER = 0; // design for the whole class
        
        private int id;
        private int counter;

        public Tweet(int id) {
            this.id = id;
            this.counter = COUNTER++;
        }
    }

    private Map<Integer, User> database; // <userId, user>
    
    private User getOrCreateUser(int userId) {
        User user = database.get(userId);
        if (user == null) {
            user = new User();
            database.put(userId, user);
        }
        return user;
    }

    /** Initialize your data structure here. */
    public Twitter() {
        database = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        User user = getOrCreateUser(userId);
        Tweet tweet = new Tweet(tweetId);
        user.myTweets.add(tweet);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Tweet> feeds = new ArrayList<>();
        User user = getOrCreateUser(userId);
        feeds.addAll(user.myTweets); // from user herself
        for (User followee : user.following) // from all her followees
            feeds.addAll(followee.myTweets);
        
        // Sort
        // larger counter comes in front
        // feeds.sort(new Comparator<Tweet>() {
        //     @Override
        //     public int compare(Tweet o1, Tweet o2) {
        //         return o2.counter - o1.counter;
        //     }
        // });
        
        // List<Integer> res = new ArrayList<>();
        // for (Tweet tweet : feeds)
        //     res.add(tweet.id);
        // if (res.size() > 10) return res.subList(0, 10);

        // PriorityQueue: 
        // store at most 10 items, with 
        PriorityQueue<Tweet> pq = new PriorityQueue<>(new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                return o1.counter - o2.counter;
            }
        });

        for (Tweet tweet : feeds) {
            pq.add(tweet);
            if (pq.size() > 10) pq.poll();
        }

        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) res.add(0, pq.poll().id);
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        User follower = getOrCreateUser(followerId);
        User followee = getOrCreateUser(followeeId);
        if (!follower.following.contains(followee)) // in case of duplicate
            follower.following.add(followee);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        User follower = database.get(followerId);
        if (follower == null) return;
        
        User followee = database.get(followeeId);
        if (followee == null) return;
        
        follower.following.remove(followee); // make sure no duplicates when pushing in
    }
    
}