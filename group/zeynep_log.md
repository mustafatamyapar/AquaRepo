# CS102 ~ Personal Log page ~
****
Zeynep Berber
****

On this page I will keep a weekly record of what I have done for the CS102 group project. This page will be submitted together with the rest of the repository, in partial fulfillment of the CS102 course requirements.

### ~ 30.04.2020 ~
This week I have learnt the main structure of android studio like how can I create layouts, how can I write my classes and how can I add photos in the layouts. I have designed tanks page and tank page activities and connect them each other. So the interaction between them works well. Also I have started to learn SQLite database to save users' tanks' information because when the user exit the application, the tanks have been added disappear when they enter again.

### ~ 08.05.2020 ~
This week I did the followings:
- I learned how to create SQLite database( Adding, updating, deleting data bby using SQliteStatement; using cursor to get the data from it...)
-I created tanks database (with columns: id, tankname, tanksize, numoffish(fishcount), numofplant, numofothers, watercheck(time until watercheck), timetofeed(time until feeding) and pictureint) and enable users to add tanks in this database with name and size of tank.According to tank size that user enters watercheck and feeding terms are changing. If the time will be 23.59, these day counts are decreasing one by one.
- I enabled users to delete tank completely and edit tank by changing tank name, adding creatures in it and removing creatures from the tank 
- I first created database of creatures separately (fish,plant,others) .However since some problems occur I combined them in only one creatures database(with columns id, type(fish,plant or other), tankname(which creature belongs to), creaturename, image). 
- When users click on the add creature button there is three choices: fish, plant and others. User will choose one of them and set the name. Then if the user wants to add a photo of the creature, application directs the user to the gallery and user chooses one of them. 
- I learned how to get permissions from the user for the gallery part to be able to access the gallery
- If the user wants to remove creature, there is a gridview layout to list the creatures in the tank with names and pictures and selected creature will be removed from tank, creatures database will be updated and number of creature in the tank will be decreased. I learned how to use gridview for this part
- I learned alert builder to show alert messages in these steps.
- I am trying to learn location services and how to use map for the following days to create map activity.

### ~ 14.05.2020 ~

-I learned get permissions to use location services and how to get the location of user and show it on the map.
-I create a page that shows the favourite vets and pet shops. If the user wants to add a favourite, he/she will direct to the map and by long clicking on a location, the place will be added to favourites list and appears on the list.
-If the user click on the places on the list, an alert message appears that ask whether user wants to remove the place from favourites or see the place on the map. If user chooses to see the map, the map page that shows the user location and favourite location is opened.If user chooses to remove, the place is removed from the places list. 
-I put the places in a SQL database to ensure that they dont disappear when user close the app.

### ~ 21.05.2020 ~

- I changed the logo of the application by using the logo that Zulal has created for the login page. First, logo did not appear on my friends' virtual devices. I download the image in the mipmap file, then it became visible.

****
