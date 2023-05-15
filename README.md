# gRPC Praxis - High-Performance gRPC Server with Spring Boot, Maven, and Hibernate

## Overview
gRPC Praxis is a high-performance gRPC server built using Spring Boot, Maven, and Hibernate. It provides seamless integration between gRPC and RESTful APIs, allowing efficient communication between clients and the server. The server leverages the power of gRPC to handle high-concurrency scenarios and utilizes Hibernate for database operations.

## Features

- Full support for gRPC and RESTful APIs
- Integration with Spring Security for secure communication using bearer token authentication
- User authentication and authorization using username and password stored in an H2 database
- High-performance data processing using Number and Factor tables to generate 1000 records of objects with fake names
- Seamless integration with Hibernate for efficient database operations
- Comprehensive error handling and exception management

## Getting Started

### Prerequisites
- JDK 8 or higher
- Maven
- Spring 2.6.x or higher

### Installation and Setup
1. Clone the repository:
```
git clone https://github.com/bhuvnsh/grpc-praxis.git
```
2. Navigate to the project directory:
```
cd grpc-praxis
```
3. Build the project:
```
mvn clean install
```
4. Run the server:
```
java -jar target/grpc-praxis.jar
```

## Usage

### Authentication and Authorization
The gRPC Praxis server uses bearer token authentication. To authenticate, include an Authorization header with the bearer token in your gRPC or RESTful API requests.

### RESTful APIs
The gRPC Praxis server also exposes RESTful APIs alongside gRPC. You can use tools like cURL or Postman to interact with the RESTful endpoints. Refer to the API documentation for detailed usage instructions.

### Data Generation
The server automatically generates 1000 records of objects with fake names in the Number and Factor tables to simulate processing intensity. You can access this data through the provided APIs.

## Contributing

Contributions to gRPC Praxis are welcome! If you find any issues or have suggestions for improvement, please open an issue or submit a pull request.

## Contact

For any inquiries or questions, feel free to reach out to the project maintainers:

- Bhuvaneshwar Venkatraman - bhuvaneshwar.venkatraman@gmail.com

Enjoy using gRPC Praxis for high-performance communication and data processing!
