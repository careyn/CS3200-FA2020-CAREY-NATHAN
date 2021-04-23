# MUSIC-CRITIQUE

MusicCritique September 2020 - December 2020
• Created a platform to allow artists and critics to interact, submit playlists, and leave critiques
• Maintained an SQL database to control user login details, manage CRUD privileges for each user, and used JPA/JDBC to interact with the Java application

Logging in: 
Run the program.  Enter th123 for the username and ttfy for the password.  The program will proceed to the main menu with artist functionality.

Creating a new user: 
Re-run the program.  Enter any value you want for the username and password.  A message will appear and then prompt you for information.  A new user is created and you will be sent to the main menu as your new user.

Updating or deleting user information: 
At the main menu, press 5.  You will be prompted as to whether you would like to make an update or delete your user.  To test the updates, press 1.  You are now prompted for the new user information.  After inputting your new data, the user is updated and you are sent back to the main menu.
In order to test the deletion of a user, press 5 at the main menu and, when prompted, enter “delete” to confirm.  This will remove your user from the database and end the program.  Re-run to make a new user or enter as an existing user.

Artist Functionality:
Creating a song: 
Login as an artist user (th123, ttfy) or create a new artist user.  Then, at the main menu hit 1. Then, hit 1 to select creating a new song. You will be prompted for the details of the song (use an existing playlist - “country songs”, and afterwards a new song is added to the database.

Creating a playlist: 
Login as an artist user (th123, ttfy) or create a new artist user.  Then, at the main menu hit 1. Then, hit 2 to select creating a new playlist. You will be prompted for the details of the playlist (use an existing genre - “country”), and afterwards a new playlist is added to the database.  You can add songs to the playlist by updating the playlist of existing songs, or by creating new songs with this playlist.

Viewing all songs, artists, playlists: 
From the main menu, enter 2 to view a list of all the songs, artists, and playlists.  You can then search for songs by inputting artist name, song name, or playlist name.  (try searching by artist name and input “tom”)

Updating or deleting a song: 
Login as an artist user (th123, ttfy) or create a new artist user.  Then, at the main menu hit 4.  You will then be prompted for the song that you would like to update (try entering “generic country song”).  To test updating a song, press 1 when prompted and enter the new fields as they appear in the prompt.  Changes made will make the song your own.  To delete the song, instead press 2 after inputting your song.  The song will then be deleted from the database.

Critic Functionality: 
Creating a new review: 
Login as a critic user (th345, ttp) or create a new critic user.  Then, at the main menu hit 1. You will be prompted for the details of the review: enter the song (try “generic country song”), rating out of 10, and comment.  The new review is then added to the database.

Viewing all critics, and reviews: 
From the main menu, enter 3 to view a list of all the critics and reviews.  You can then search for reviews by inputting artist name, song name, or critic id.  (try searching by artist name and input “tom”)

Updating or deleting a review: 
Login as a critic user (th345, ttp) or create a new critic user.  Then, at the main menu hit 4.  You will then be prompted for the song name and cid of review that you would like to update (try entering “generic country song” and 234).  To test updating a review, press 1 when prompted and enter the new fields as they appear in the prompt. Changes made will make the review your own. To delete the review, instead press 2 after inputting your review.  The review will then be deleted from the database.


Project log:
https://docs.google.com/document/d/1B9XQAQWcr3AxLoKofaO6WRO4eGt-kWcR1f1oEKjil_I/edit
