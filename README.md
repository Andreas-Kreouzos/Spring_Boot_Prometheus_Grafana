# MSc Thesis Spring Boot Application


## Introduction
This project is the main application, developed in support of my MSc thesis entitled
"Implementation Of RESTful Web Service Application in Spring Framework with Metrics Visualization in Prometheus and Grafana"
The project is part of the Master of Science Program "Informatics" currently organised by University of Piraeus - Greece.

## Scope
The purpose of this project focuses on the captivation of possibilities provided by the
Spring Framework and how these could be taken one step further by exposing endpoints and
adding metrics to the famous platforms like Prometheus and Grafana.

The aforementioned will be performed with the consumption of two external APIs which provide
the data as JSON format, store these in a PostgreSQL database and finally expose them with the
necessary views in an HTML Thymeleaf template.

The whole project will be constructed under the hood of Spring Security with Login authentication
system as well as email verification procedure. The security aspect gets completed with the creation
of a Self Signed Certificate to accomodate the usage of HTTPS protocol on 8443 port.

At the start of the application, the ```management.server.port``` will be also configured to run with
the main application, on port 8080 with SSL disabled. This was done in order to expose the endpoints on
HTTP protocol and manage to fetch these with the Promotheus more easily.

Upon the Prometheus connection, Grafana will come also in the game by grabbing the metrics from Prometheus
and creating some beautiful dashboards for the users to see and extract useful information.

## Languages and Tools
<div>
  <img src="https://github.com/devicons/devicon/blob/master/icons/java/java-original-wordmark.svg" title="Java" alt="Java" width="100" height="100"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/spring/spring-original-wordmark.svg" title="Spring" alt="Spring" width="100" height="100"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/intellij/intellij-original.svg" title="IntelliJ" alt="IntelliJ" width="100" height="100"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/postgresql/postgresql-original-wordmark.svg" title="PostgreSQL" alt="PostgreSQL" width="100" height="100"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/docker/docker-original-wordmark.svg" title="Docker" alt="Docker" width="100" height="100"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/prometheus/prometheus-original-wordmark.svg" title="Prometheus" alt="Prometheus" width="100" height="100"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/grafana/grafana-original-wordmark.svg" title="Grafana" alt="Grafana" width="100" height="100"/>&nbsp;
</div>

## Application Map & Specifications
![35  MSc Thesis App Map](https://user-images.githubusercontent.com/77160233/197356806-ba9dbb4d-7bfa-40e8-a416-897d32adcde3.png)

1. Login system for registering users' data in database. The authentication includes, among other things, an Email Verification system in
which an automated email message is sent to the address that has been declared, while the user must open it and press the activation
link that will redirect him back to the application. This patch is hidden inside Spring Security.

2. Dual port system in which the project is launched both in HTTPS and in HTTP. The last port was deemed necessary in order to expose the
endpoints of the application. This implementation was done using Spring Actuator.

3. For the creation of HTTPS there was a small challenge in which a Self Signed Certificate had to be built and parameters were then made
in the application.properties file.

4. Use and consumption of two external API's whose data is entered into the application with POJO-type Java classes in order to store them
in databases through Repository classes and print them in Front End HTML pages. This process has been done with Thymeleaf while a minimal CSS
has been added.

5. Testing was done as much as possible in the code via JUnit Framework.

6. Implementation of DevOps processes in which the Prometheus platform was attached, which specializes in consuming the endpoints exposed by
the application and providing metrics information about it. Then it is further connected with the Grafana platform, which takes the data from
Prometheus and turns it into graphs.

7. The script of the previous step was done using Docker Container via WSL Ubuntu which was run via VM because of the presence of have Windows
11 OS. This was also, the biggest challenge since the communication of two containers (Prometheus - Grafana) within the Docker Host, required
specialized commands and reading the IP addresses of the two containers.

8. Creating separate classes for Exception Handling at the global level, which means that these classes control any exception that occurs.

## Docker Commands on WSL Ubuntu

**Start Docker**

```sudo service docker start```

**Display All Containers**

```docker ps --all```

**Install Prometheus**

```docker run -d --name=prometheus -p 9090:9090 -v /mnt/d/Projects/Msc-Thesis-Project/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml```

**Install Grafana**

```docker run -d --name=grafana -p 3000:3000 grafana/grafana```

**Stop Container**

```docker stop 'container name'```

**Remove Container**

```docker rm 'container name'```