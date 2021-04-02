# real-estate-listing-website
## Prerequisites
* npm
* JDK 8
* maven
## Set up
* Create your own database with postgres user and import init.sql
* Edit application.properties in src/main/resources directory according to your database
## Backend
From the main folder:
* `mvn spring-boot:run`
## Frontend
From the frontend folder:
* `npm install`
* `npm run serve`
## Docker
* `cd src/main/docker`
* `docker-compose up`
