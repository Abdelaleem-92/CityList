# City List Application

this is Java spring that presents API for City List, 

## Technologies
Project is created with:
* JAvA - Spring Boot framework - JUnit - H2 - JWT.


## Installation 

* Application can be easily start and be running by docker 
below are the steps to run through docker   
prerequisite  docker engine to be installed and running

to be started as a docker image 
first you will need to clone the repository then locate into the project path 
cloning the git repository
```bash
 - git clone https://github.com/Abdelaleem-92/CityList.git   
``` 

* build docker image
```bash
 docker build -t citylist:citylist .
``` 

run docker container 
```bash
docker run -p 8080:8080 citylist:citylist
``` 
