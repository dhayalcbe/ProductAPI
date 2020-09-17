# ProductAPI
1. Each product is linked to a review group and we have only reviewGroupId in the table and not a explicit relationship 
   since it belongs to other micro-service.
2. Image will be processed as byte[](Base 64 encoded) and the conversion should be taken care in the client side.
3. Updates should pass all the json structure similar to create(to maintain consistency) with the values that the user needs to update.
4. Logs and Exception in the application are not provided comprehensively due to time constraints.
5. While importing the project in Eclipse, you may face any compilation errors due to lombok. If so, 
   try installing the lombok jar in Eclipse (Details are provided in their official site).