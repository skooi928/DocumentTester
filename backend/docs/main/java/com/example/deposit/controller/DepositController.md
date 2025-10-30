# DepositController Documentation

## Description
The `DepositController` class is a Spring REST controller that manages deposit-related operations. It provides endpoints to list deposits, calculate interest for a specific deposit, retrieve the current interest rate, place a new deposit, and update an existing deposit.

## Methods

### 1. `listDeposits()`
- **Description**: Retrieves a list of all deposits.
- **Return Value**: 
  - `List<Deposit>`: A list containing all deposit entities.
- **Usage Example**:
  ```java
  // Example of calling the listDeposits method
  List<Deposit> deposits = depositController.listDeposits();
  ```

### 2. `calculateDepositInterest(Long depositId)`
- **Parameters**:
  - `depositId` (Long): The unique identifier of the deposit for which interest is to be calculated.
- **Return Value**: 
  - `BigDecimal`: The calculated interest amount for the specified deposit.
- **Usage Example**:
  ```java
  // Example of calculating interest for a deposit with ID 1
  BigDecimal interest = depositController.calculateDepositInterest(1L);
  ```

### 3. `getCurrentInterestRate()`
- **Description**: Retrieves the current interest rate applicable to deposits.
- **Return Value**: 
  - `InterestRate`: An object containing the current interest rate details.
- **Usage Example**:
  ```java
  // Example of getting the current interest rate
  InterestRate currentRate = depositController.getCurrentInterestRate();
  ```

### 4. `placeDeposit(Deposit deposit)`
- **Parameters**:
  - `deposit` (Deposit): The deposit entity to be created.
- **Return Value**: 
  - `Deposit`: The created deposit entity with its generated ID.
- **Usage Example**:
  ```java
  // Example of placing a new deposit
  Deposit newDeposit = new Deposit(...); // Initialize deposit details
  Deposit createdDeposit = depositController.placeDeposit(newDeposit);
  ```

### 5. `updateDeposit(Long depositId, Deposit deposit)`
- **Parameters**:
  - `depositId` (Long): The unique identifier of the deposit to be updated.
  - `deposit` (Deposit): The deposit entity containing updated information.
- **Return Value**: 
  - `Deposit`: The updated deposit entity.
- **Usage Example**:
  ```java
  // Example of updating an existing deposit with ID 1
  Deposit updatedDeposit = new Deposit(...); // Initialize updated deposit details
  Deposit result = depositController.updateDeposit(1L, updatedDeposit);
  ```

## Important Notes
- Ensure that the `DepositService` is properly configured and injected for the controller to function correctly.
- The controller is annotated with `@CrossOrigin`, allowing requests from any origin. Adjust this as necessary for security purposes.
- The methods assume that the input data is valid and does not include error handling for simplicity. Consider adding exception handling for production use.

## Flowchart
```mermaid
flowchart TD
    A[Start] --> B[Request to /api/deposits]
    B --> C[List Deposits]
    C --> D[Return List of Deposits]
    A --> E[Request to /api/deposits/{depositId}/interest]
    E --> F[Calculate Interest]
    F --> G[Return Interest Amount]
    A --> H[Request to /api/deposits/rate]
    H --> I[Get Current Interest Rate]
    I --> J[Return Interest Rate]
    A --> K[Request to /api/deposits (POST)]
    K --> L[Place Deposit]
    L --> M[Return Created Deposit]
    A --> N[Request to /api/deposits/{depositId} (PUT)]
    N --> O[Update Deposit]
    O --> P[Return Updated Deposit]
    P --> A
```