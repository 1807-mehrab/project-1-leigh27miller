package mb.dao;


import mb.model.MbUser;
import mb.model.Post;
import mb.model.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageBoardDAO {
    public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@myawsinstance.cceiqisd977e.us-east-2.rds.amazonaws.com:1521:ORCL";
    public static final String USER = "leigh_h_miller";
    public static final String PASSWORD = "4535Belvedere";

    //get a connection to the database to talk to it
    public Connection getConnection(){
        Connection connection = null;
        try{
            //this loads the driver
            Class.forName(DRIVER);
            //get actual connection to database where you can send commands
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(Exception e){
            e.printStackTrace();
        }

        return connection;
    }

    //use case 1: login(servlet done: login servlet)
    public MbUser login(String username, String password){
        Connection connection = null;
        PreparedStatement statement = null;
        MbUser user = null;
        try{
            connection = getConnection();
            String SQL = "SELECT * FROM MBUSER WHERE username = ? and password = ?";
            //create a JDBC statement to be sent to the database
            statement = connection.prepareStatement(SQL);
            statement.setString(1, username);
            statement.setString(2, password);
            //send command to database and run it
            ResultSet rs = statement.executeQuery();
            //process the result set one row at a time
            if(rs.next()){
                //get column values for each row
                String email = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                String admin = rs.getString(6);
                user = new MbUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAdmin(admin);

            }
        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return user;
    }


    //Use case 2: view profile(ViewProfile servlet done)

    public MbUser viewProfile(String username){
        Connection connection = null;
        PreparedStatement statement = null;
        MbUser user = new MbUser();
        try{
            connection = getConnection();
            String SQL = "SELECT * FROM MBUSER WHERE username = ?";
            //create a JDBC statement to be sent to the database
            statement = connection.prepareStatement(SQL);
            statement.setString(1, username);
            //send command to database and run it
            ResultSet rs = statement.executeQuery();
            //process the result set one row at a time
            if(rs.next()){
                String password = rs.getString(2);
                String email = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);

                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setFirstName(firstName);
                user.setLastName(lastName);
            }
        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return user;
    }


    //Use case 4: get all topics(GetTopicsServlet done)
    public List<Topic> getTopics(String genre){
        Connection connection = null;
        PreparedStatement statement = null;
        List<Topic> topics = new ArrayList<>();
        try{
            connection = getConnection();
            String SQL = "SELECT * FROM TOPIC WHERE GENRE = ?";
            //create a JDBC statement to be sent to the database
            statement = connection.prepareStatement(SQL);
            statement.setString(1, genre);
            //send command to database and run it
            ResultSet rs = statement.executeQuery();
            //process the result set one row at a time
            while(rs.next()){
                int topicId = rs.getInt(1);
                String topicName = rs.getString(2);

                Topic topic = new Topic();
                topic.setTopicId(topicId);
                topic.setTopicName(topicName);
                topic.setGenre(genre);

                topics.add(topic);
            }
        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return topics;
    }

    public List<Topic> getAllTopics(){
        Connection connection = null;
        PreparedStatement statement = null;
        List<Topic> topics = new ArrayList<>();
        try{
            connection = getConnection();
            String SQL = "SELECT * FROM TOPIC";
            //create a JDBC statement to be sent to the database
            statement = connection.prepareStatement(SQL);
            //send command to database and run it
            ResultSet rs = statement.executeQuery();
            //process the result set one row at a time
            while(rs.next()){
                int topicId = rs.getInt(1);
                String topicName = rs.getString(2);
                String genre = rs.getString(3);
                Topic topic = new Topic();
                topic.setTopicId(topicId);
                topic.setTopicName(topicName);
                topic.setGenre(genre);

                topics.add(topic);
            }
        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return topics;
    }

    //Use case 5: get all posts(TODO: Get post servlet)
    public List<Post> getAllPostsByTopicId(int topicId){
        Connection connection = null;
        PreparedStatement statement = null;
        List<Post> posts = new ArrayList<>();
        try{
            connection = getConnection();
            String SQL = "SELECT * FROM POST WHERE topic_topicid = ?";
            //create a JDBC statement to be sent to the database
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, topicId);
            //send command to database and run it
            ResultSet rs = statement.executeQuery();
            //process the result set one row at a time
            while(rs.next()){
                int postId = rs.getInt(1);
                String text = rs.getString(3);

                Post post = new Post();
                post.setPostId(postId);
                post.setText(text);
                post.setTopicId(topicId);

                posts.add(post);
            }
        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return posts;
    }


    //use case 6: post a topic and reply to a topic(save new post servlet done)
    public void saveNewPost(int topicId, String username, String text ) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            String SQL = "insert into POST (postid, user_username, text, topic_topicid) values " +
                    "(post_seq.nextval, ?, ?, ?)";
            statement = connection.prepareStatement(SQL);
            statement.setString(1, username);
            statement.setString(2, text);
            statement.setInt(3, topicId);

            statement.executeUpdate();

        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    //TODO: Use case 7: post an image along with servlet

    //Use case 8: flag a post(save flagged post servlet done)
    public void saveFlaggedPost(int postId, String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            String SQL = "insert into FLAGGEDPOST (post_postid, user_username) values " +
                    "(?, ?)";
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, postId);
            statement.setString(2, username);

            statement.executeUpdate();

        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    //use case 9 : view their replies(get dashboard post servlet done)

    public List<Post> getDashboardPosts(String username){
        Connection connection = null;
        PreparedStatement statement = null;
        List<Post> posts = new ArrayList<>();
        try{
            connection = getConnection();
            String SQL = "SELECT * FROM POST WHERE user_username = ?";
            //create a JDBC statement to be sent to the database
            statement = connection.prepareStatement(SQL);
            statement.setString(1, username);
            //send command to database and run it
            ResultSet rs = statement.executeQuery();
            //process the result set one row at a time
            while(rs.next()){
                int postId = rs.getInt(1);
                String text = rs.getString(3);
                int topicId = rs.getInt(5);

                Post post = new Post();
                post.setPostId(postId);
                post.setText(text);
                post.setTopicId(topicId);

                posts.add(post);
            }
        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return posts;
    }


    //Use case 10: update profile(servlet done)

    public void updateProfile(String username, String password, String email, String firstName, String lastName ) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            String SQL = "update  MBUSER SET password = ?, email = ?, firstName = ?, lastName = ? WHERE username = ?";
            statement = connection.prepareStatement(SQL);
            statement.setString(1, password);
            statement.setString(2, email);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, username);

            statement.executeUpdate();

        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }



    //use case 14: view flagged post(get flagged post servlet done)
    public List<Post> getFlaggedPosts(){
        Connection connection = null;
        PreparedStatement statement = null;
        List<Post> posts = new ArrayList<>();
        try{
            connection = getConnection();
            String SQL = "SELECT P.* FROM FLAGGEDPOST F JOIN POST P ON F.POST_POSTID = P.POSTID";
            //create a JDBC statement to be sent to the database
            statement = connection.prepareStatement(SQL);
            //send command to database and run it
            ResultSet rs = statement.executeQuery();
            //process the result set one row at a time
            while(rs.next()){
                int postId = rs.getInt(1);
                String text = rs.getString(3);
                int topicId = rs.getInt(5);

                Post post = new Post();
                post.setPostId(postId);
                post.setText(text);
                post.setTopicId(topicId);

                posts.add(post);
            }
        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return posts;
    }

    //use case 15:  delete flagged post(TODO: servlet)
    public void deletePost(int postId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            String SQL1 = "DELETE FROM FLAGGEDPOST WHERE post_postid = ?  " ;
            String SQL2 = "DELETE FROM DASHEDPOST WHERE post_postid = ?  " ;
            String SQL3 = "DELETE FROM POST WHERE postid = ?  " ;
            statement = connection.prepareStatement(SQL1);
            statement.setInt(1, postId);
            statement.executeUpdate();

            statement = connection.prepareStatement(SQL2);
            statement.setInt(1, postId);
            statement.executeUpdate();

            statement = connection.prepareStatement(SQL3);
            statement.setInt(1, postId);
            statement.executeUpdate();

        }
        catch(SQLException e){
            //print out error to catalina.out
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    //Use case: update post(updatepost servlet done)
    public void updatePost(String text, int postId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            String SQL = "update POST SET text = ? WHERE postid = ?";
            statement = connection.prepareStatement(SQL);
            statement.setString(1, text);
            statement.setInt(2, postId);

            statement.executeUpdate();

        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }


    //use case: 17: view all users(view all users servlet done)
    public List<MbUser> getAllUserProfiles(){
        Connection connection = null;
        PreparedStatement statement = null;
        List <MbUser> users = new ArrayList<>();
        try{
            connection = getConnection();
            String SQL = "SELECT * FROM MBUSER";
            //create a JDBC statement to be sent to the database
            statement = connection.prepareStatement(SQL);
            //send command to database and run it
            ResultSet rs = statement.executeQuery();
            //process the result set one row at a time
            while(rs.next()){
                String username = rs.getString(1);
                String password = rs.getString(2);
                String email = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                String admin = rs.getString(6);

                MbUser user = new MbUser();

                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAdmin(admin);
                users.add(user);
            }
        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return users;
    }




    //OPTIONAL:
    public void saveNewTopic(String topicName, String genre ) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            String SQL = "insert into TOPIC (topicid, topicname, genre) values" +
                    "(topic_seq.nextval, ?, ?)";
            statement = connection.prepareStatement(SQL);
            statement.setString(1, topicName);
            statement.setString(2, genre);

            statement.executeUpdate();

        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

     /*save a new user
    public void saveNewUser(String username, String password, String email, String firstName, String lastName ) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            String SQL = "insert into MBUSER values(?,?,?,?,?)";
            statement = connection.prepareStatement(SQL);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, firstName);
            statement.setString(5, lastName);
            statement.executeUpdate();

        }
        catch(SQLException e){
            //if anything goes wrong, print out error to the screen
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }*/















    //TODO: Create servlet for edit flagged post











}

