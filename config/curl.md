Curl HTTP-requests
===============================
*works properly for win cmd*

### *Get all meals*

`curl -i http://localhost:8080/topjava/rest/profile/meals/`

### *Get meal with id 100004*

`curl -i http://localhost:8080/topjava/rest/profile/meals/100004`

### *Create new meal*

`curl -H "Content-Type: application/json" -X POST http://localhost:8080/topjava/rest/profile/meals -d "{\"dateTime\":\"2022-01-25T17:00:00\",\"description\":\"New food\",\"calories\": 500}`

### *Update meal with id 100004*

`curl -H "Content-Type: application/json" -X PUT http://localhost:8080/topjava/rest/profile/meals/100004 -d "{\"dateTime\":\"2022-11-25T17:30:00\",\"description\":\"Updated food\",\"calories\": 747}`

### *Delete meal with id 100004*
`curl -X DELETE http://localhost:8080/topjava/rest/profile/meals/100004`

### *Get all meals between filter borders*
`curl -i "http://localhost:8080/topjava/rest/profile/meals/between?startDate=2020-01-31&startTime=07:00&endDate=2020-01-31&endTime=20:00"`
