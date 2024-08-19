# Speaking Clock Application

* The Speaking Clock application is a Spring Boot project designed to `convert a given time into words`.
* The application handles both the `current time` and `user-specified time inputs`, returning the time in words.
* Special cases like "12:00" and "00:00" are recognized as "It's Midday" and "It's Midnight," respectively.

***

## Features

* Current Time Conversion: Convert the current system time to words.
* User Input Time Conversion: Accept user input in `HH:mm` format and convert it to words.
* Special Case Handling: Recognize and return specific phrases for "Midday" and "Midnight".
* Exception Handling: Properly handle and return error messages for invalid inputs.
* Unit Tests: Includes test cases covering both positive and negative scenarios.
* Security: Basic authentication setup which can be customized or disabled as needed.

***

## Security
* The application uses basic authentication for securing endpoints. Use the following credentials to access the endpoints:

##### Username: `Tharun`
##### Password: `TharunKumar`

***

## Access the application
#### Prerequisites
* Java 8
* Maven installed

#### URL
* `http://localhost:8080`


### End Points

#### 1.Convert current time into words
* Method - `GET`
* URL - `http://localhost:8080/convert`

#### 2.Convert user input time into words
* Method - `GET`
* URL - `http://localhost:8080/time?time=<input-time>`
* If the input time matches `12:00`, it returns `"It's Midday`, else if the time is `00:00`, it returns `"It's Midnight"`, else it returns the input time in words.

***

## Unit Testing
* This application comes with comprehensive unit tests to cover both positive and negative scenarios, including:

- Validating the correct time conversion.
- Handling invalid time formats.
- Managing exceptions that might occur during processing.

***

## Conclusion

* The Speaking Clock application is a simple tool for converting time into spoken words, with features like special time handling, robust exception management, and extensive test coverage.
