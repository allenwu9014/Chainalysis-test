## Chainalysis | Software Engineer, University Grad | Applicant Take Home Test
Goggle Doc
https://docs.google.com/document/d/1u7y7sZas9JTNsfNZh8ZeLINMLy5obGuReXjhEKqKIGc/edit?usp=sharing
### Deliverables:
#### 1. Link to github with the code
https://github.com/allenwu9014/Chainalysis-test.git
#### 2. Detailed instructions on how to build and run
Java version OpenJDK: 11  
run the test.jar file in dockerDep  
$java -jar test.jar  
access web: http://localhost:8080/  
#### 3. Answers to below Questionnaire ( included as a markdown file in GH repo)
+ Are there any sub-optimal choices( or short cuts taken due to limited time ) in your implementation?  
  + Since using the heap, if one/more exchange(s) obj is null(fetch data failure), it will not be offered in PQ, and second choose or third will be polled.
  

+ Is any part of it over-designed? ( It is fine to over-design to showcase your skills as long as you are clear about it)  
  + Back-End:  
    + CORS permission  
    + interface of Exchange to match the list of all exchanges with different objs  
  + Front-End:  
    + UI implement by Material UI  
    + show all exchanges by card blocks  
    + auto fetch / manual fetch switching   
+ If you have to scale your solution to 100 users/second traffic what changes would you make, if any?  
  + Front-end request will be sent once by per 5 sec default, and for manual fetch, if the fetch process is still going on, the user will be allowed to click it again.  
  + Back-end: Tomcat server will easily handle 2k requests/second.  
Theoretically, there is nothing to change. However, it still needs to be tested and verified by stress tests.
+ What are some other enhancements you would have made, if you had more time to do this implementation?  
  + make a login/logout page  
  + set a mechanism for a time limit.  
    + If the time out for fetching or still in waiting, the PQ will poll the current priority to client.
    + not fetch all exchanges on one request, when the number of exchanges is too large. Instead, set a storage area(or DB) for storing all fetch data, fetching will do by every services(Microservices), which will maintain storage area together. The design will make sure online algorithm.

#### 4. For bonus points, provide a link to live version of the solution
+ access on a public link: http://18.117.85.215/
  + The web is deployed on EC2 AWS


## Project Document & Developer Log

### Requirements:
1. Prices of Bitcoin and Ethereum from two (any) different exchanges/sources.
    + Differentiate buy and sell price clearly

2. Recommendations on which exchange one should buy and/or sell.
    + Recommend where to buy and where to sell. Each of the recommendations can be a different exchange

### Deliverables:
+ Link to github with the code  
+ Detailed instructions on how to build and run  
+ Answers to below Questionnaire ( included as a markdown file in GH repo)  
+ For bonus points, provide a link to live version of the solution  

### Other Info:
+ If BTC is 10000$ to buy on Exchange A and 10050$ to buy on Exchange B, you would recommend to buy on Exchange A.   
+ Any front end technology is fine. We use React  
+ Any backend technology is fine. We use spring boot/java  
+ You can pick any exchange for pricing. If exchange price is unavailable, use price from any service provider such as blockchain.com  
+ Your webpage is not hard coding the prices. It should talk to its own backend to fetch the prices. And that backend fetches prices from other exchanges/service providers  

### Search:
Exchanges:

		Coinbase:
			buy:
				https://api.coinbase.com/v2/prices/BTC-USD/buy
			sell:
				https://api.coinbase.com/v2/prices/BTC-USD/sell
				ETH

		Kraken:
			https://api.kraken.com/0/public/Ticker?pair=BTCUSD
			Response 
a: ask
b: bid

		Gemini:
			https://api.gemini.com/v1/pubticker/BTCUSD
			Response
				ask
				bid

		CEX.IO
			https://cex.io/api/ticker/BTC/USD
			bid
			ask

		Blockchain
			need API Key
			Ignore


### System Design:
#### Back-end:
  + Hight-Level:
    1. use Spring Boot to implement the back-end service
    2. handle the request from front-end via REST API
    3. send price query to 2 + exchanges via GET request
    4. response a recommendation result to the front-end

  + Middle-Level:
    1. 4 sub-structures:
        + controllers: accept requests from front-end
        + models: for POJO design and JackJSON mapping
        + services: implement the recommendation by comparing ASK and BID between two exchanges data
    2. send the request query by RestTemplate API
    3. use PriorityQueue to poll the min ASK or max BID

System Structure:



###Front-End
  + one page:
    + show all BTC exchanges on left half page
    + show all ETH exchanges on right half page
    + Recommendation on the bottom of page
    + BTC buy and sell on left
    + ETH buy and sell on right
    + click the corresponding button will link to its landing page
    + switch will set auto-refresh data on when the first loading
    + click the switch will change to manual refresh status
    + A new refresh button will display on manual refresh status
    + click the refresh button will re-fetch the data



## Developer Log
Back-End

#### 10/20 
project initiated

#### 10/21
built models for 4 exchanges response data  
set the /recommend controller  
built recommend service  
test RestTemplate http client functions  
Error: 403 forbidden to access CEX.IO  
Error by using exchange() with  "No suitable HttpMessageConverter found for response type"  
fixed the error with override part functions of RestTemplate to implement conversion for JSON type  
integrated request of Coinbase which uses two urls to call the buy/sell data seperately  

#### 10/22
Implemented PQ to poll the best point on ASK and BID  
ArrayList to show all the exchanges  
Optimized: Spring Beans and Autowired  
Set to allow CORS  
Back-end Test Completed  


Front-end:  

#### 10/22
Front-end init  
use Material UI to handle the structure and displaying of page  
fetch data by axios library  

#### 10/23
added loading circular when data is loading  
added a switch to auto refresh or manual refresh  
Front-end test Completed  

#### 10/24
Dve:  
package front-end codes with back-end to jar file  
deploy the web app to EC2 AWS  
access link: http://18.117.85.215/  
 
