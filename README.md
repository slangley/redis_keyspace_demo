1. Run a local Redis.  No configuration is needed for local redis, assuming running on localhost, port 6379 (defaults)
2. Java 17 required, mvn 3.9.9
3. To run, build with `mvn clean package -Dmaven.test.skip`  (Unit tests have not been written and are still enabled)
4. Import the postman.json into postman.
5. Use the postman to create a key in Redis, with TTL, reduce the TTL, or delete a key.
6. These API's set HSET (hash set) within redis.  You can introspect the redis data using redis-cli on your local machine.  HKEYS , and HGETALL <KEYNAME>


