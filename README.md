## Deposits-manager
TO-DO: 
        
       1. Add unit-tests 
       
       2. Expand/use exceptions
# About
REST API built with SPRING, Hibernate, HATEOAS, H2.
WebApp handles CRUD operations with preuploaded data.
There are 3 tables of banks, clients and deposits. Banks and clients relate to deposits as MANY-TO-ONE

# How to use

http://localhost:8080/deposits or http://localhost:8080/clients or http://localhost:8080/banks then use provided links (deletes, gets and sorts).

curl -X POST localhost:8080/banks -H "Content-type:application/hal+json" -d "{""bankName"": ""sampleBank"", ""bankBIC"": ""7832493""}" to add Bank

curl -X POST localhost:8080/clients -H "Content-type:application/hal+json" -d "{""name"": ""New Guy"", ""shortName"": ""Newbee"", ""address"": ""City, Sample st., 1"", ""incorpForm"": ""INDIVIDUAL""}" to add client (available enums for ""incorpForm"" are: ""INDIVIDUAL"", ""SOLE_PROPRIETORSHIP"", ""LLC"", ""INCORPORATED"") 

curl -X PUT localhost:8080/deposits/25/1/10 -H "Content-type:application/hal+json" -d "{""monthsSinceOpen"": 67, ""interestRate"": 6.7, ""openDate"": ""2018-06-14""}" to add deposit with id = 25 with client id = 1 and bank id = 10

PUT requests are the same (full body required, and you should provide "id" parameter for banks and clients, like that: .../banks/10 -H...).

Do note, that if your console use single quotation, console input should be something like this: curl -X POST localhost:8080/banks -H 'Content-type:application/hal+json' -d '{"bankName": "sampleBank", "bankBIC": "7832493"}'

