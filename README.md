This application processes the rate conversion between some currencies using Bellman-Ford algorithm.

We are using spring boot along a single endpoint '/currency-conversion/from/{from}/to/{to}/quantity/{quantity}'
which you can trigger by using postman or directly from your web browser

We are using java 8 along apache-maven-3.6.3 and spring 2.5.5 versions.

Once you deployed the service you can check the implementation by doing this request:
http://localhost:8081/currency-conversion/from/{from}/to/{to}/quantity/{quantity}.

The code has been added by following these instructions:

Tech task
Create FX Converter to allow conversion between any currency pair
Consider a data structure holding conversion rates between few currencies. Provider
implementation to get the converted value form one currency to other, note the
conversion rate for these may not be directly available.
e.g
AUD/USD 0.7754
USD/CAD 1.2330
CAD/CHF 0.9313
USD/DKK 6.6336
EUR/USD 1.1231
GBP/ CHF 1.5620
eg Get converted value from AUD to CHF or CHF to AUD
Conditions:
- task should be done approximately within 2 hours.
- should be ready for test
- ready to delivery to other dev team
- ready for fast/easy run
- simple for using
- rates should by dynamical
- you can choose all techs and features you want: no-frameworks at all, spring boot,
  gradle, maven, guava, java 8 etc. You are absolutely free
- ui command line is ok, rest is ok, web form is ok, what you want and fit
- please, don't push task and implementation in public source