****
*Group number: g2D
*Project title: AquAssistant
*Project description: AquAssistant is a mobile Android application that is created to provide help to people
who are interested in aquariums and/or who have aquariums. Users can create their own profile, add and modify
tanks, keep track of their creatures, reach information through the encyclopedia and find a pet shop/veterinarian
close by if they need any further help. The main aim is to assist the users through their experience, as the name
of the application suggests.
*Project's current status: Working
****
*What has been accomplished: We were able to implement most of the parts we planned to. Users can create accounts, 
log out, sign in and delete accounts - also update their information. There is also a rank system. They can add 
their favorite places through map. They can add/modify tanks, remove them and add/remove creatures. The encyclopedia 
is available. Users are greeted with a splash screen. Our toast messages are customized and navigating through pages 
is smooth with animations. Overall, our application shows major functionality.
We tried uploading a text file to the firebase database but we could not figure out a way to upload and import a text 
file so we stuck with sql but some of the features get lost when we quit the application or delete it. Since sql is 
built in the program itself and not in an online database, the information gets lost when we delete the app - tanks and 
creatures also have the same issue. They are not saved for each user. Also in encyclopedia section we tried to use json 
and glide, we created gits website to store information but it unfortunately, did not worked.

*What remains to be done: We could have pursued a way to make the overall aesthetic of the app a little more 
professional and crisp but we currently do not have enough experience. But we could perhaps change the shape of some 
buttons etc. We could have added a progress bar for the experience. We also had to exclude the Comments section because
we could not figure out how to do it - similarly, the Notifications part was modified majorly. We could not convert the 
tank image to an animation that shows the creatures in the tank and  implement a way for the map to show the nearby vets 
and pet shops. In creatures page, we were planning to update the gridview according to the content of the text box which 
means when the user writes something to the search box, just this creature’s photo is shown. In the FAQ page, we were 
thinking we would take the info from the Jsoup but we could not.

****
*Group members:
Kaan Özkan – Log In & Sign Up pages, User-interaction/design(splash screen, toast messages, screen sliding)
Edip Kerem Tayhan – Home page, Profile page, Rank system
Zülal Nur Hıdıroğlu – Creatures page, Specific Creature pages, FAQ page, Designing logo
Zeynep Berber – Tanks page, Specific Tank pages, Favorites(maps activity)
Mustafa Tamyapar – Encyclopedia Page, Specific Encyclopedia Pages
Fatma Sena Genç – Settings(user-info updating, deleting account), To-Do List page
****
*Below are the software & tools we used in our project:
Android Studio version 29.0.3
Firebase library auth 19.3.1 - firestore 21.4.3 - storage 19.1.1
cardView library 1.0.0
recyclerView library 1.1.0
GooglePlay Map Services 17.0.0
Glide library 4.6.1
Java sdk version 23
****
*How to run the application:
You need to have Android Studio installed on your computer. Then, via Android Studio, open the project file
(namely AquaRepo). The project code will be displayed on Android Studio. Download an Android Virtual Device
(AVD/the emulator) in your Android Studio. After your emulator is ready, you can run the application on the 
emulator.


 

