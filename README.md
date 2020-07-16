# PDF Invoice Printer Web Application
Invoice of flat renting 
### Running the application
1. Open root folder of project in `Command Line`  
2. Run commands in given sequence:  
`mvn clean install`  
`mvn spring-boot:run`  
3. App web address: `http://localhost:8081`  
### CSV File validation rules
* 1 data record = 1 line of data file   
* validated data separator: `;`
* validated data file extension: `.csv` 
* no extra spaces before and after every line of record
* salesman record fields: 

    `companyName;address;postcode;city`
    
* example of `Salesman` record:

    `smithCompany;London Street 12;PO3 1AX;12-222;London`
        
* contractor record fields: 

    `firstName;lastName;address;postcode;city;apartmentType;amountOfPayment;apartmentNumber`

* example of `Contractor` record:  

    `john;smith;London Street 12;PO3 1AX;12-222;London;M;369,45;12`
    
### Using Application
1. Open `http://localhost:8081` in any browser
2. From `Salesman` drop-down menu, select `Upload Salesman` option
3. Upload `.csv` file with salesman data
4. From `Contractors` drop-down menu, select `Upload Contractors` option
5. Upload `.csv` file with contractors data
6. From `Invoices` drop-down menu, select `Add Invoices Batch` option
7. Approve or change invoice date and set period of renting
8. Select `Create Invoice Batch` option to generate the invoice for all uploaded contractors
9. Generated invoices display as a list

TBD...