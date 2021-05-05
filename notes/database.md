## Database needs to be in 3NF:
### 1NF:
- Each cell contains a single value
- Each record(row) is unique
### 2NF:
- PK must be single column
### 3NF:
- No transitive Functional Dependencies
 
 
## Tables:
 
### Users
User_ID (PK)
Name
<other user info>
Username
Password
 
 
### Account_Access
Acct_Access_ID (PK)
User_ID (FK)
Acct_ID (FK)
 
 
### Accounts
Acct_ID (PK)
Balance
 
 
### Transaction_History
Transaction_ID (PK)
Acct_ID (FK)
User_ID (FK)
Type
Amount
