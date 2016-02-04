1. Carteasy

A Shopping cart library for Android that allows you add to add items to cart and retrieve at ease.

2. Quick Start

Add the following dependency to your build.gradle:

//Dependecies will come here.

You should then checkout the library and investigate the sample code, which covers some of the features.

<image of sample app here>

3. Usage

 *Adding items to cart*

 ```
 Carteasy cs = new Carteasy();
 cs.add(String id, String key, Object value);
 cs.commit(getApplicationContext());
 ```

 ```
 cs.add("Your Product Id", "key", "value");
 ```


 Your product items is tied to your Id and can be retrieved anytime the Id is produced.

 ```
 String productIdOne = "e400";
 String productIdTwo= "e401";
 ```

 ```
 Carteasy cs = new Carteasy();

 cs.add(productIdOne, "product name", "Marlboro");
 cs.add(productIdOne, "product description", "cigarate box");
 cs.add(productIdOne, "product price", 200.00);
 cs.add(productIdOne, "quantity", 5);
 cs.add(productIdOne, "currency", "dollar");
 ...

 cs.add(productIdTwo, "product name", "Starbucks coffee");
 cs.add(productIdTwo, "product description", "coffee bags");
 cs.add(productIdTwo, "product price", 100.00);
 cs.add(productIdTwo, "quantity", 9);
 cs.add(productIdTwo, "currency", "dollar");
 ...
 cs.commit(getApplicationContext());
 ```


 *Retrieve item by Id and key*

 Returns an object. you can use it as an object or typecast it to what ever you want

 ```
 Carteasy cs = new Carteasy();
 Object value = cs.get( String id, String key, getApplicationContext());
 ```

 Returns a String

 ```
 Carteasy cs = new Carteasy();
 String value = cs.getString( String id, String key, getApplicationContext());
 ```

 You can return type of value using either of these below:

 *String*

 ```
 String value = cs.getString(String id, String key, Context context);
 ```

 *Integer*

 ```
 int value = cs.getInteger(String id, String key, Context context);
 ```

 *Float*

 ```
 Float value = cs.getFloat(String id, String key, Context context);
 ```

 *Double*

 ```
 Double value = cs.getDouble(String id, String key, Context context);
 ```

 *Long*

 ```
 Long value = cs.getLong(String id, String key, Context context);
 ```

 *Short*

 ```
 Short value = cs.getShort(String id, String key, Context context);
 ```


 *Update Items*

 To update a items value, you have to pass the id, key and your newValue as parameters

 ```
 Carteasy cs = new Carteasy();
 cs.update(String id, String key, Object newValue, Context context);
 ```

 *Removing specific item value from cart*

 ```
 Carteasy cs = new Carteasy();
 cs.Removekey(String id, String key, Context context);
 ```

 *Removing item and its property value from cart*

 ```
 Carteasy cs = new Carteasy();
 cs.RemoveId(String id, Context context);
 ```

 *Viewing all items stored in cart*

 ```
  Map<Integer, Map> data;
  Carteasy cs = new Carteasy();
  data = cs.ViewAll(Context context);

  for (Map.Entry<Integer, Map> entry : data.entrySet()) {
     //get the Id
     Log.d("Key: ",entry.getKey());
     Log.d("Value: ",entry.getValue());

     //Get the items tied to the Id
     Map<String, String> innerdata = entry.getValue();
     for (Map.Entry<String, String> innerentry : innerdata.entrySet()) {
         Log.d("Inner Key: ",innerentry.getKey());
         Log.d("Inner Value: ",innerentry.getValue());
     }
  }
  ```

 *Viewing all items stored which is tied to an ID*

  ```
  Map<Integer, Map> data;
  Carteasy cs = new Carteasy();
  data = cs.ViewData(String id, Context context);

  for (Map.Entry<Integer, Map> entry : data.entrySet()) {
     //get the Id
     Log.d("Key: ",entry.getKey());
     Log.d("Value: ",entry.getValue());

     //Get the items tied to the Id
     Map<String, String> innerdata = entry.getValue();
     for (Map.Entry<String, String> innerentry : innerdata.entrySet()) {
         Log.d("Inner Key: ",innerentry.getKey());
         Log.d("Inner Value: ",innerentry.getValue());
     }
  }
  ```




*Caveats*

Nothing here for now.



*Credits*

This library depends on json-simple-1.1.1