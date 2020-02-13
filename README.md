# PDF Invoice Printer Web Application
Invoice of flat renting 
### Running the application
1. Open root folder of project in `Command Line`  
2. Run commands in given sequence:  
`mvn clean install`  
`mvn spring-boot:run`  
3. Open given URL address in browser: `http://localhost:8081`  
### CSV File construction rules
* contractor structure: `firstName,lastName,address,postcode,city`
* used separators: `;` and `,`
* used file extensions: `.txt .csv` 
* used no extra spaces before and after evey record 
##### Example of Contractor record:  
`john,smith,street 12,PO3 1AX,12-222,London`
### Using Application with contractors from CSV file
1. Upload CSV file with contractors data
2. Set period of renting
3. Set amount for pay
4. Press `Generate PDF Invoice` button to generate invoice for all uploaded contractors
5. Generated invoices PDF files can be found in `genertedInvoice` package