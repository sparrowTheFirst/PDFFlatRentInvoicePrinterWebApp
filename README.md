# Invoice Printer Web App
### Running the application
1. Open root folder of project in `Command Line`  
2. Run commands in given sequence:  
`$ mvn clean install`  
`$ mvn spring-boot:run`  
3. Open given URL address in browser: `http://localhost:8081`  
### CSV File construction rules
* structure: `firstName lastName address postcode city`
* used separators: `;` and `,`
* used file extensions: `.txt .csv` 
* used no extra spaces before and after evey record 
##### Example of Contractor record:  
`john,smith,street 12,PO3 1AX,12-222,London`