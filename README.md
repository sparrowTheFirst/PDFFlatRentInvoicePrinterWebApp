# PDF Invoice Printer Web Application
Invoice of flat renting 
### Running the application
1. Open root folder of project in `Command Line`  
2. Run commands in given sequence:  
`mvn clean install`  
`mvn spring-boot:run`  
3. Open given URL address in browser: `http://localhost:8081`  
### CSV File construction rules
* data record = line
* example of contractor record: `firstName,lastName,address,postcode,city`
* validated separators: `;` and `,`
* validated file extensions: `.txt .csv` 
* used no extra spaces before and after every record 
##### Example of Contractor record:  
`john,smith,street 12,PO3 1AX,12-222,London`
### Using Application with contractors from CSV file
1. Open `http://localhost:8081` in browser
2. Upload CSV file with contractors data
3. Set period of renting
4. Set amount for pay
5. Press `Create Invoice Batch` button to generate invoice for all uploaded contractors
6. Generated invoices PDF files can be found in `genertedInvoice` package