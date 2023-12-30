# booking - Hexagonal API
Booking API 

Port to test 8080

Import this API to Postman:

## To book
curl --location --request POST 'http://localhost:8080/book/book?guestName=Bruno&initialDate=01%2F01%2F2024&finalDate=01%2F31%2F2024'

## To update
curl --location --request PUT 'http://localhost:8080/book/1' \
--header 'Content-Type: application/json' \
--data '{
    "id": 1,
    "guest": {
        "id": 1,
        "name": "Bruno2"
    },
    "initialDate": "2024-02-02T00:00:00.000+00:00",
    "finalDate": "2024-03-02T00:00:00.000+00:00"
}'

## To cancel
curl --location --request PUT 'http://localhost:8080/book/cancel/1' \
--data ''

## To rebook
curl --location --request PUT 'http://localhost:8080/book/rebook/1' \
--data '

## To delete
curl --location --request DELETE 'http://localhost:8080/book/1' \
--data ''

## To get
curl --location 'http://localhost:8080/book/1' \
--data ''