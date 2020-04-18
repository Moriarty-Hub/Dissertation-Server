## Summary

This is the network asset management platform of my graduation project, powered by Spring Boot framework, thymeleaf, mybatis and some other components. It was developed for retrieving the scan result of the client, another sub-module of my project, from the database and display them on the browser, managing targets that will be scanned by the client script, and managing user.

Here are two roles in this system, they are normal user and administrator respectively, the former own authorities to view the scan result, the latter own authorities to manage normal users, such as, view the user list, add new user, or delete user, obviously, it also owns all authorities that normal user own.

## Instruction

#### Administrator

When the system has just be initialized, there is only one available account in the database, that is the administrator's account, with default username and password, they are "root" and "password" respectively.

Then you can use this account to log into our system, in this case, our system knows it is the first time for you to log in, so you will be asked to set a new password, instead of using the default one. After the password was set, you will be redirected to the index page, which used for showing all available buttons, the target list and the scan result. As an administrator, you can see more buttons than normal users, includes view the list of user, add new users, to name just a few. 

Besides, the admin can also views the target list table and scan result table at the index page. And they can add new targets or delete existing targets though the first table.

For adding a new user, you are required to provide a username, user's date of birth, and e-mail address, then our system will sends an e-mail with username and a random password to the user's e-mail box. The user can log into the system by the given account once they got the letter.

All users are listed at the page of manage user, you can do the operation of delete here.

#### Normal user

The only way to become a normal user in our system is by requesting the administrator to create an account for you. By doing so, you need to provide your e-mail address, when admin finished the operation, you will receive an e-mail that includes your account, you can use it to log in the system, once you log in, you are required to set a new password since it is the first time to log in, and the default password is only for temporary use.

As a normal user, you can view the target list and scanning result list at the index page, but cannot do any operation on them, likewise, you can modify your own password but cannot view the user list and operate on them as the administrator do.