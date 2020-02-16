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
* validated data file separator: `;`
* validated data file extension: `.csv` 
* used no extra spaces before and after every line of record
* salesman record fields: 

    `companyName;address;postcode;city`
    
* example of `Salesman` record:

    `smithCompany;London Street 12;PO3 1AX;12-222;London`
        
* contractor record fields: 

    `firstName;lastName;address;postcode;city;apartmentType;amountOfPayment;apartmentNumber`

* example of `Contractor` record:  

    `john;smith;London Street 12;PO3 1AX;12-222;London;M;369,45;12`
    
### Using Application with contractors from CSV file
1. Open `http://localhost:8081` in browser
2. From `Salesman` drop-down menu, press `Upload Salesman` link
3. Upload `.csv` file with salesman data
4. From `Contractors` drop-down menu, press `Upload Contractors` link
5. Upload `.csv` file with contractors data
6. From `Invoices` drop-down menu, press `Add Invoices Batch` link
7. Check or change invoice date and set period of renting
8. Press `Create Invoice Batch` button to generate invoice for all uploaded contractors
9. Generated invoices display as a list