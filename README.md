# Spring Boot Starter Alexa

## Description 

- Alexa integrated with Spring Boot
- Using Strategy and Abstract Factory Design Pattern

## Usage

- Change servlet mapping in AlxeaConfiguration
- Implement AbstractSpeechlet and mark as @Service(and remove DefaultSpeechlet), handle common Alexa business
- Implement IntentResponseProvider, handle Alexa Intent