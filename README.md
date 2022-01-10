# coinbase

This program connect to coinbase api and pull bid/ask.

## Challenges:

First before going further let's put down the challenges:

### Intellij eat my memory

Having a low capacity(see section Prerequisites) for Intellj Idea, I run out of memory
as soon as Intelij was running and the computer was freezing. I finally end up writting 
the whole code using VIM(My day to day IDE configured for python my dotfiles on
my github venv.git) without autocompletion or any help. So they might be some
unused import or warnings around.

### Python Base

I am a python base developer. The last time I used Java was 4 years ago so my
code will closely look as python or not. I have spent some time reading JavaDoc
for the feature I used hopefully they are used correctly.

### Standard librairy verbose

The Java standard library is too verbose. I had to install maven to use multiple
packages. I usually use less packages as possible and like to stick with
standard libs which is a bit verbose in Java (Websocket boilerplat is too long
with standard Java lib).

## Prerequisites:
 
 You can use any Operating System you will need:
 - Maven
 - Java
 
 Tested with:
 
 ```bash
 Linux 5.10.0-9-amd64 #1 SMP Debian 5.10.70-1 (2021-09-30) x86_64 GNU/Linux
 Debian 11.1
 Intel Core i5
 ```

 ```bash
 mvn --version
 
    Apache Maven 3.6.3
    Maven home: /usr/share/maven
    Java version: 11.0.13, vendor: Debian, runtime: /usr/lib/jvm/java-11-openjdk-amd64
    Default locale: en_US, platform encoding: UTF-8
    OS name: "linux", version: "5.10.0-9-amd64", arch: "amd64", family: "unix"
 ```

## Installation

> Note that I am not a real user of Java/Maven but this is how you can run it.

1. clone the repository `git clone https://github.com/ChristfriedBalizou/coinbase.git coinbase.git`
2. move to the project directory `cd coinbase.git`
3. make sure you have maven installed (see Prerequisites) compile: `mvn clean compile assembly:single`


## App

Before running the application see Installation section.
Run the application by running:
```bash 
# Will read the product from the properties file
java -cp target/coinbase.git-1.0-SNAPSHOT-jar-with-dependencies.jar com.coinbase.app.App
```

You can run specifying your own product:
```bash 
java -cp target/coinbase.git-1.0-SNAPSHOT-jar-with-dependencies.jar com.coinbase.app.App ETH-GBP
```


## Tests

To run the tests use the command: `mvn test`
