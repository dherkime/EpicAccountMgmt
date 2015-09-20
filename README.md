# EpicAccountMgmt
A simple Grails 3 CRUD app using MongoDB.

## Installation

1. Download/Install [Grails](https://grails.org/download.html) (version 3.0.7 as of this writing)
2. Install [MongoDB](http://docs.mongodb.org/master/installation/) and fire up the Mongo daemon: `mongod`

## Fire up the app

1. `grails run-app`
2. Access the app at [http://localhost:8080](http://localhost:8080)

The Home page shows a list of created accounts
![Home page](/../screenshots/screenshots/Home.png?raw=true "Home page")

The Create page allows you to create accounts 
![Create page](/../screenshots/screenshots/Create.png?raw=true "Creating an account")

![Createe account page](/../screenshots/screenshots/Created.png?raw=true "Account created")

Account emails must be unique
![Emails must be unique](/../screenshots/screenshots/EmailTakenMessage.png?raw=true "Emails must be unique")
