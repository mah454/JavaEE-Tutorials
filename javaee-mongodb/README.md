Create user on mongodb
```
use tutorial
db.createUser({user:'admin',pwd:'adminpass',roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]}); 
db.grantRolesToUser('admin',[{role :'readWrite',db :'tutorial'}]);
```