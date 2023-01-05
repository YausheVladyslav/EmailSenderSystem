## Email system
### How to run the project
**1.** Download this project</br>
**1.** Open project in a development environment</br>
**2.** To connect database</br>
**3.** Start it
### How to use the project
#### About endpoints:
**POST** /add-user - adding new user to database by username(String) and email(String)</br>
**PUT** /edit-user - editing user from database by username(String) and email(String)</br>
**DELETE** /delete-user - deleting user from database by email(String)</br>
**GET** /get-user - returning user by the specified email<String></br>
**GET** /users-list - returning paginated all users</br>
**POST** /add-cron - adding new cron to database by correct String</br>
**PUT** /edit-cron - editing cron from database by Id</br>
**DELETE** /delete-cron - deleting cron from database by Id</br>
**GET** /cron-list - returning paginated all crons</br>
**POST** /send-mail - sending email for user by Id with subject(String), message(String)</br>
**GET** /statistic - returning email statistic</br>
**PUT** /start-schedule - start the scedule(thread) by cronId(int) from database</br>
**PUT** /edit-schedule - switching the scedule(thread) by cronId(int) from database</br>
