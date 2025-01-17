@Codebase
I am working on a POC that tests multiple services talking to one REDIS instance.  The single redis instance will be used for a hash set (with a hierarchy of keys).  under one UUID root key, there will be 3 additional keys.  name, phone number, and email.  The POC will test the following:

1. The ability to get a hash set from redis with a hierarchy of keys
2. The ability to delete a hash set from redis with a hierarchy of keys
3. The ability to update a hash set in redis with a hierarchy of keys
4. The ability to set TTL on the entire Hash set
5. THe ability to use Keyspace notifications to notify a service when a key is updated
6. The ability to use Keyspace notifications to  notify servce when key expires based on TTL

In order to build POC, I need to create a service that talks to the redis instance and performs the following actions:

1. Create a hash set with the above hierarchy of keys, and set TTL at same time (short TTL).  Use proper restful conventions for the URL. `POST /hashset/{hashset_id}`  Payload will be a JSON object with the following keys: name, phone number, and email.  Each key will be added to the hash set as a separate key within the hset.  The TTL will be set to the value specified in the URL.  TTL will be set on the root key.  Default TTL will be 60 seconds.
2. Andother endpoint that will delete the hash set `DELETE /hashset/{hashset_id}`
3. Another endpoint that will update the TTL for an existing hash set to a set amoutn of seconds specified in the URL.  Use proper restful conventions for the URL. `POST /hashset/{hashset_id}/ttl/{seconds}`
4. A service that will listen for keyspace notifications and log the event to the console.  The service shoudl be able to run in a docker container.  
5. Multiple docker containers will be used to test the ability to talk to the redis instance.  The service should be able to handle multiple instances of itself running at the same time. 

Tech StacK:
* existing codebase
* spring boot 3
* java 17
* redis
* docker
* keyspace notifications
