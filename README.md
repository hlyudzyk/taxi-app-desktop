# Taxi Operator Desktop Application

This project is a desktop application for taxi operators, developed using JavaFX. It allows operators to manage orders, drivers, and customers effectively. The application integrates with a backend server to provide real-time data and functionalities.

## Interface

![image](https://github.com/hlyudzyk/taxi-app-desktop/assets/141021338/557f71d7-6c87-44cd-b179-80cb488c9426)

![image](https://github.com/hlyudzyk/taxi-app-desktop/assets/141021338/9c374889-71de-4b1b-bc55-183ce748f7a6)


## Features

- **Order Management**: Create, view, and filter taxi orders.
- **Driver Management**: Track and manage online and offline drivers.
- **Customer Management**: Handle customer data and their requests.
- **Real-time Updates**: Display live data for orders and drivers.
- **Progress Indication**: Show progress bars for long-running operations.

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- JavaFX SDK
- Internet connection for server communication

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/hlyudzyk/taxi-app-desktop.git
   cd taxi-operator-app
   ```

2. **Open the project in your IDE**: Ensure that your IDE is configured to use JavaFX.

3. **Build the project**: Compile the project using your IDE’s build tools or using Maven/Gradle if configured.

## Configuration

1. **Backend Server URL**: Ensure that the backend server URL is correctly set in your application configuration files or constants.

2. **Database Configuration**: Make sure the application is pointed to the correct database (if applicable) in your backend server settings.

## Running the Application

1. **Run the Main Class**: Execute the main class to start the JavaFX application.
2. **Login**: Use the provided credentials to log in as an operator.
3. **Navigate**: Use the UI to navigate between different features like managing orders, drivers, and customers.

## Usage

- **Displaying Orders**:
  - Use the “Orders” tab to view and filter orders.
  - Long-running operations, such as fetching orders, will show a progress bar.

- **Managing Drivers**:
  - Use the “Drivers” tab to track online and offline drivers.
  - Assign and manage driver statuses and locations.

- **Customer Management**:
  - Access customer data and handle their requests via the “Customers” tab.

## Contributing

1. **Fork the repository**.
2. **Create a new branch**:
   ```bash
   git checkout -b feature-branch
   ```
3. **Make your changes**.
4. **Commit your changes**:
   ```bash
   git commit -m "Description of your changes"
   ```
5. **Push to the branch**:
   ```bash
   git push origin feature-branch
   ```
6. **Create a Pull Request**.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or feedback, please contact developers.
---
