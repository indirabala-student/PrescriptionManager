Task details :
--------------
--> Read a file having json each line of record  
fileData:
{"field1":"value1","field2":"value2","field3":"value3","rx":"rxValue1","description":"descriptionValue1","memberId":"12345"} 
{"field1":"value1","field2":"value2","field3":"value3","rx":"rxValue1","description":"descriptionValue","memberId":"874392"} 
{"field1":"value1","field2":"value2","field3":"value3","rx":"rxValue1","description":"descriptionValue","memberId":"3749"} 
{"field1":"value1","field2":"value2","field3":"value3","rx":"rxValue1","description":"descriptionValue","memberId":"43287"} 
{"field1":"value1","field2":"value2","field3":"value3","rx":"rxValue2","description":"descriptionValue2","memberId":"12345"} 
{"field1":"value1","field2":"value2","field3":"value3","rx":"rxValue3","description":"descriptionValue3","memberId":"12345"}
 
2)Bundling the records having same memberid records like below and store into new file.
{"memberId":"12345","field1":"value1","field2":"value2","field3":"value3","rxInfo":[{"rx":"rxValue1","description":"descriptionValue1"},{"rx":"rxValue2","description":"descriptionValue2"},{"rx":"rxValue3","description":"descriptionValue3"}]}
{"memberId":"874392","field1":"value1","field2":"value2","field3":"value3","rxInfo":[{"rx":"rxValue","description":"descriptionValue"}]}
 
3) store success records into in-memory database
4) store failed records into inmemory database (Just think add records which could be failed and store in failed table)
 
5) Get all success records  from db and print in console
5) Get all failed records  from db and print in console
