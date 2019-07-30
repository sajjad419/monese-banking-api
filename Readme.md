Application is Live on AWS , You can use API through this using below URL

http://ec2-3-94-82-51.compute-1.amazonaws.com/swagger-ui.html
There are TWO APIS:Required Parametes are there. you can use those.
1.sending money between accounts
POST /{version}/api/users/transfermoney
sample Request:
access_token=ya29.QQIBibTwvKkE39hY8mdkT_mXZoRh7Ub9cK9hNsqrxem4QJ6sQa36VHfyuBe
version=v1
transferMoneyReques= 
{
  "fromAccountNumber": "1234567890123456",
  "toAccountNumber": "1234567890123457",
  "transferAmount": 20
}


2.requesting account statement with account balance and list of transactions
GET /{version}/api/users/{accountNumber}/history/transaction
sample Request:
access_token=ya29.QQIBibTwvKkE39hY8mdkT_mXZoRh7Ub9cK9hNsqrxem4QJ6sQa36VHfyuBe
version=v1
accountNumber: 1234567890123456


Below is the sample Data:
version:v1
access_token:ya29.QQIBibTwvKkE39hY8mdkT_mXZoRh7Ub9cK9hNsqrxem4QJ6sQa36VHfyuBe

accountNumber:1234567890123456,1234567890123457

There are two user with account Number 1234567890123456 and 1234567890123457
